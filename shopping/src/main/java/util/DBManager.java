package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			Context env = (Context)init.lookup("java:/comp/env");
			DataSource ds = (DataSource)env.lookup("jdbc/shopping");
			conn = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return conn;
		
	}
	
	// select를 실행한 후 리소스 해제 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(conn != null)
				conn.close();
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// insert, delete, update 실행 후 리소스 해제
	public static void close(Connection conn, Statement stmt) {
		try {
			if(conn != null)
				conn.close();
			if(stmt != null)
				stmt.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}









