package test.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {

	public static void main(String[] args) {

		for (int i = 11; i <= 20; i++) {

			BookVo vo = new BookVo();
			vo.setTitle("책" + i);
			vo.setAuthor("저자" + i);
			vo.setPrice(2000 * i);

			boolean result = insert(vo);
			if (result) {
				System.out.println("성공");
			}
		}
	}

	public static boolean insert(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. jdbc driver 로딩하기 (driver를 구현한 interface를 메모리에 올려야함!)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기-> driverManager 클래스
			String url = "jdbc:mysql://192.168.43.232:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "apfhd123"); // webdb, password

//			// 3. statement 객체 생성
//			stmt = conn.createStatement();
//
//			// 4. SQL문 실행 (** ; 없어야함!!)
//			String sql = " insert " + " into book " + " values(null, '" + vo.getTitle() + "', '" + vo.getAuthor()
//					+ "', " + vo.getPrice() + ")";
//			int count = stmt.executeUpdate(sql);
//			result = count == 1;
			
			// ====preparedStatement
			// 3-1. SQL 준비
			String sql = 
					" insert " + 
					" into book " + 
					" values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setInt(3, vo.getPrice());
			
			// 5. sql문 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 3. 자원정리
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
