package test.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1. jdbc driver 로딩하기 (driver를 구현한 interface를 메모리에 올려야함!)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기-> driverManager 클래스
			String url = "jdbc:mysql://192.168.43.232:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "apfhd123"); // webdb, password
			
			System.out.println("연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+ e);
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				// 3. 자원정리
				if(conn != null) {					
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}