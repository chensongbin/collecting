package tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	
	static {
		try {
			Properties prop = new Properties();
			InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(in);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//连接数据库
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, userName, password);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//查询
	public static ResultSet query(String sql) {
		Connection conn = getConn();
		Statement stmt = null;
		ResultSet res = null;
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConn(conn);
		return res;
	}
	
	//更新
	public static int update(String sql) {
		Connection conn = getConn();
		Statement stmt = null;
		int ans = -1;
		try {
			stmt = conn.createStatement();
			ans =stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 try {      
	                stmt.close();    
	                conn.close();    
	            } catch (SQLException e) {        
	                e.printStackTrace();    
	            }    
		}
		return ans;
	}
	
	//关闭连接
	public static void closeConn(Connection conn) {
		try {
			if(conn != null) { conn.close(); }
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
