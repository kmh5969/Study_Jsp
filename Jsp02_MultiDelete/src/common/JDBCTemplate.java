package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// 1. driver 연결
	// 2. 계정 연결
	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01. error");
		}

		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("02. 계정 연결");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02. error");
		}

		return con;
	}

	public static boolean isConnection(Connection con) {

		boolean valid = true;

		try {
			
			if (con == null || con.isClosed()) {
				valid = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valid;
	}

	// 5. db 종료
	public static void close(Connection con) {

		if (isConnection(con)) {

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement stmt) {

		if (stmt != null) {

			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {

		if (rs != null) {

			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		
		if(rs != null && stmt != null && isConnection(con)) {
			
			try {
				rs.close();
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// commit & rollback
	public static void rollback(Connection con) {

		if(isConnection(con)) {
			
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void commit(Connection con) {

		if(isConnection(con)) {
			
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
