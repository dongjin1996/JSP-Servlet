package DAO;

import java.sql.*;
import java.sql.DriverManager;

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
}
