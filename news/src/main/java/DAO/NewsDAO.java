package DAO;

import java.sql.*;
import java.util.ArrayList;

import DTO.News;

public class NewsDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//db연결 메소드
	public Connection open() {
		Connection conn = null;  //데이터베이스 연결 담당
		try {
			Class.forName(JDBC_DRIVER); //드라이버 로드
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234"); //DB연결
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//게시판 리스트 가져오는 메소드
	public ArrayList<News> getList() throws Exception {
		Connection conn = open();
		
		//New 객체를 저장할 ArrayList
		ArrayList<News> newsList = new ArrayList<News>();
		
		String sql = "select news_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') reg_date, views, content from news";
		PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록
		ResultSet rs = pstmt.executeQuery(); //쿼리문 실행
		
		try(conn; pstmt; rs) {
			while(rs.next()) { //1라인씩 데이터를 읽어온다.
				News n = new News();
				
				n.setNews_no(rs.getInt("news_no"));
				n.setTitle(rs.getString("title"));
				n.setUser_id(rs.getString("user_id"));
				n.setReg_date(rs.getString("reg_date"));
				n.setViews(rs.getInt("views"));
				
				newsList.add(n);
			}
			
			return newsList;
		}
	}
	
	//게시물 내용 가져오기
	public News getView(int news_no) throws Exception {
		Connection conn = open();
		News n = new News();
		
		String sql = "select news_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') reg_date, views, content, img from news where news_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, news_no);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				n.setNews_no(rs.getInt("news_no"));
				n.setTitle(rs.getString("title"));
				n.setUser_id(rs.getString("user_id"));
				n.setReg_date(rs.getString("reg_date"));
				n.setViews(rs.getInt("views"));
				n.setContent(rs.getString("content"));
				n.setImg(rs.getString("img"));
			}
			
			return n;
		}
	}
	
	//조회수 증가 메소드
	public void updateViews(int news_no) throws Exception {
		Connection conn = open();
		String sql = "update news set views = (views + 1) where news_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, news_no);
			pstmt.executeUpdate();
		}
	}
	
	//게시글 등록
	public void insertNews(News n) throws Exception {
		Connection conn = open();
		String sql = "insert into news(news_no, user_id, title, content, reg_date, views, img) values(news_seq.nextval, ?, ?, ?, sysdate, 0, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, n.getUser_id());
			pstmt.setString(2, n.getTitle());
			pstmt.setString(3, n.getContent());
			pstmt.setString(4, n.getImg());
			pstmt.executeUpdate();
		}
	}
	
	//게시글 수정화면 보여주기
	public News getViewForEdit(int news_no) throws Exception {
		Connection conn = open();
		News n = new News();
		
		String sql = "select news_no, title, user_id, content, to_char(reg_date, 'yyyy.mm.dd') reg_date, views, img from news where news_no = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, news_no);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				n.setNews_no(rs.getInt("news_no"));
				n.setTitle(rs.getString("title"));
				n.setUser_id(rs.getString("user_id"));
				n.setReg_date(rs.getString("reg_date"));
				n.setViews(rs.getInt("views"));
				n.setContent(rs.getString("content"));
				n.setImg(rs.getString("img"));
			}
			
			return n;
		}
		
	}
	
	//게시글 수정하기
	public void updateNews(News n) throws Exception {
		Connection conn = open();
		String sql = "update news set user_id = ?, title = ?, content = ?, img = ? " 
				+ "where news_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getUser_id());
			pstmt.setString(3, n.getContent());
			pstmt.setString(4, n.getImg());
			pstmt.setInt(5, n.getNews_no());
			
			//수정된 글이 없으면
			if(pstmt.executeUpdate() != 1) {
				throw new Exception("수정된 글이 없습니다.");
			}
		}
	}
	

	
	
}
