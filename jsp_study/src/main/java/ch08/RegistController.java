package ch08;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/rcontrol")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RegistService service;
	

    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //서블릿 초기화
		
		//프로그램 실행시 최초로 객체를 딱 한번만 생성한다.
		//init()은 초기화 역할을 하는 메소드이므로 딱 한번 실행
		service = new RegistService();
	}


	public RegistController() {
        super();
    }
	
	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request: 화면단에서 전달해 준 데이터가 들어있다.
		String action = request.getParameter("action");
		String view = "";
		
		//action이 null이면
		//http://localhost:8081/jsp_study/rcontrol?action=list 주소로 forward
		if(action == null) {
			getServletContext()
			.getRequestDispatcher("/rcontrol?action=list")
			.forward(request, response);
		} else {
			switch (action) {
			case "list": view = list(request, response); break;
			case "info": view = info(request, response); break;
			
			}
			
			getServletContext()
			.getRequestDispatcher("/ch08/" + view)
			.forward(request, response);
		}
	}

	//registServic에게 find메소드를 실행
	private String info(HttpServletRequest request, HttpServletResponse response) {
		Regist r = service.find(request.getParameter("id"));
		
		//특정 ID로 찾은 regist객체를 request 객체에 넣어준다.
		request.setAttribute("r", r);
		return "registInfo.jsp";
	}
	
	private String list(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Regist> regists = service.findAll();
		request.setAttribute("regists", regists);
		return "registList.jsp";
	}
	


}
