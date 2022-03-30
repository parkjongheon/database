package t3_DBTest_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DAO(
public class DBTest {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String sql = "";
	
	public DBTest() {
		try {
			// 1. JDBC드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2. 데이터 베이스 연결
			String url = "jdbc:mysql://localhost:3306/javagreen";
			String user = "root";
			String password ="1234";
			conn = DriverManager.getConnection(url, user, password); // (url,user,password)
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~");
			System.exit(0);

		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~~");
			System.exit(0);
		}
	}
	
	// 데이터베이스안의 테이블 검색
	public void searchTest(String name) {
		try {
			stmt = conn.createStatement();
			sql = "select * from aaa where name = '"+name+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
//				System.out.println("홍길동을 찾았습니다.");
				name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String joinday = rs.getString("joinday");
				System.out.println("=====================================");
				System.out.println("성명 : " + name);
				System.out.println("나이 : " + age);
				System.out.println("성별 : " + gender);
				System.out.println("가입일 : " + joinday);
				System.out.println("=====================================");
			}
			else {
				System.out.println("=====================================");
				System.out.println(name + "을 찾지못했습니다.");
				System.out.println("=====================================");
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류" + e.getMessage());
		}		
	}
	// stmt 객체 Close
	private void stmtClose() {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {}
		}

	}
	
	// rs 객체 Close
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
				stmtClose();
			} catch (Exception e) {}
		}
		
	}
	
	// 데이터베이스 객체 Close
	public void dbClose() {
		try {
			conn.close();
//			System.out.println("DB를 닫았습니다.");
		} catch (Exception e) {
		
		}
		
	}
}
