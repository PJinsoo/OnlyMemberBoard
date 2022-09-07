package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public static Connection getConnection() {
		try {
			//드라이버 연결
			//Class.forName()을 통해 JSP와 DB가 연결되도록 하는 드라이버를 JVM에 등록
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("com.mysql.jdbc.Driver"); //구 드라이버
		} catch (ClassNotFoundException e) {
			//드라이버 연결 실패
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/boarddb";
		String id = "root"; //MySQL ID
		String pw = "root"; //MYSQL PW
		
		/* Connection 인터페이스
		 * DriverManager 클래스의 getConnection()메소드로 정의되며
		 * DB와 연결된 세션 역할
		 * 이 세션을 통해 쿼리문을 전송하고 그 결과를 ResultSet객체로 받음
		 */
		Connection conn = null;
		
		try {
			//DB와 연결될 세션을 생성
			conn = DriverManager.getConnection(url, id, pw);
			conn.setAutoCommit(false);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//DB와의 연결을 확인하는 메서드
	public static boolean isConnection(Connection conn) {
		//valid = true  -> 커넥션 연결 O
		//valid = false -> 커넥션 연결 X
		boolean valid = true;
		try {
			if(conn == null || conn.isClosed()) {
				//커넥션이 연결 안돼있다면
				valid = false;
			}
		} catch(SQLException e) {
			valid = true;
			e.printStackTrace();
		}
		return valid;
	}
	
	//DB와의 연결을 종료하는 메서드
	//호출 시점에서 DB와 연결 상태라면 close
	public static void close(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Statement 객체를 종료시키는 메서드
	//stmt가 Null값이 아닐 때 close
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//ResultSet 객체를 종료시키는 메서드
	//rs가 Null값이 아닐 때 close
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//DB 커밋 메서드
	public static void commit(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//DB 롤백 메서드
	public static void rollback(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
