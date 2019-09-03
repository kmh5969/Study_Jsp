package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	public static Connection getConnection() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01 driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01 error");
		}

		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			System.out.println("02 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02 error");
		}

		return con;
	}

	public static boolean isConnection(Connection con) {

		boolean boo = true;

		try {
			if (con == null || con.isClosed()) {

				boo = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return boo;
	}

	public static void close(Connection con) {

		if (isConnection(con)) {
			try {
				con.close();
				System.out.println("05 con종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05 error");
			}
		}
	}

	public static void close(Statement stmt) {

		if (stmt != null) {
			try {
				stmt.close();
				System.out.println("05 stmt종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05 error");
			}
		}
	}

	public static void close(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
				System.out.println("05 rs종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05 error");
			}
		}
	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {

		if (rs != null && stmt != null && isConnection(con)) {

			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("05 db종료");

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05 error");
			}
		}
	}

	public static void commit(Connection con) {

		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {

		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
