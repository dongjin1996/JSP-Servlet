package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.NewsDAO;
import DTO.News;


@WebServlet("/")
@MultipartConfig(maxFileSize=1024*1024*2, location="c:/Temp/img")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO dao; //model
	private ServletContext ctx; //페이지이동, forward를 위해 씀
       

    public NewsController() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new NewsDAO();
		ctx = getServletContext(); 
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글깨짐 방지
		String command = request.getServletPath(); //경고를 가지고 온다.
		String site = null;
		
		System.out.println("command: " + command);
		
		//1.경로를 정해준다(라우팅)
		switch (command) {
		case "/list" : site = getList(request); break;
		case "/view" : site = getView(request); break;
		case "/write": site = "write.jsp"; break;
		case "/insert": site = insertNews(request); break;
		case "/edit" : site = getViewforEdit(request); break;
		case "/update" : site = updateNews(request); break;
		}
		
		if(site.startsWith("redirect:/")) { //redirect
			String rview = site.substring("redirect:/".length());
			System.out.println("rview: " + rview);
			
			response.sendRedirect(rview); //페이지 이동
		}else { //forward
			ctx.getRequestDispatcher("/" + site).forward(request, response);
		}
	}
	
	public String getList(HttpServletRequest request) {
		ArrayList<News> list;
		
		try {
			list = dao.getList();
			request.setAttribute("newsList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생");
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다.");
		}
		
		return "index.jsp";
	}
	
	public String getView(HttpServletRequest request) {
		int news_no = Integer.parseInt(request.getParameter("news_no"));
		
		try {
			dao.updateViews(news_no);
			News n = dao.getView(news_no);
			request.setAttribute("news", n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다.");
		}
		
		return "view.jsp";
	}
	
	public String insertNews(HttpServletRequest request) {
		News n = new News();
		
		try {
			BeanUtils.populate(n, request.getParameterMap());
			
			//1.이미지 파일 자체를 서버 컴퓨터에 저장
			Part part = request.getPart("file"); //이미지 파일 받기
			String fileName = getFilename(part); //파일 이름 구하기
			
			if(fileName != null && !fileName.isEmpty()) {
				part.write(fileName); //파일을 컴퓨터에 저장
			}
			
			//2. 이미지 파일 이름에 "/img/ 경로를 붙여서 news 객체에 저장
			n.setImg("/img/" + fileName);
			
			dao.insertNews(n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 작성과정에서 문제 발생");
			
			try {
				//get 방식으로 넘길떄 한글 깨짐 방지
				String encodeName = URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다.", "UTF-8");
				return "redirect:/list?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		
		return "redirect:/list";
	}
	
	//게시글 수정 보여주는 메소드
	public String getViewforEdit(HttpServletRequest request) {
		int news_no = Integer.parseInt(request.getParameter("news_no"));
		
		try {
			News n = dao.getViewForEdit(news_no);
			request.setAttribute("news", n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을 정상적으로 가져오지 못했습니다.");
		}
		return "edit.jsp";
	}
	
	//게시글 수정 업데이트 메소드
	public String updateNews(HttpServletRequest request) {
		News n = new News();
		try {
			BeanUtils.populate(n, request.getParameterMap());
			
			//1. 이미지 파일 자체를 서버 컴퓨터에 저장
			Part part = request.getPart("file"); //이미지파일 받기
			String fileName = getFilename(part); //파일 이름 구하기
			
			if(fileName != null && !fileName.isEmpty()) {
				part.write(fileName); //파일을 컴퓨터에 저장
			}
			
			//2. 이미지 파일 이름에 "/img/경로를 붙여서 board 객체에 저장"
			n.setImg("/img/" + fileName);
			
			dao.updateNews(n);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("수정 과정에서 문제 발생");
			try {
				String encodeName = URLEncoder.encode("게시물이 정상적으로 수정되지 않았습니다", "UTF-8");
				return "redirect:/list?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} 
		
		return "redirect:/view?board_no=" + n.getNews_no();
	}
	
	//파일 이름 추출
	private String getFilename(Part part) {
		String fileName = null;
		String header = part.getHeader("content-disposition");
		System.out.println("header => " + header);
		
		int start = header.indexOf("filename=");
		fileName = header.substring(start + 10, header.length() -1);
		System.out.println("파일명: " + fileName);
		
		return fileName;
	}
	
}
