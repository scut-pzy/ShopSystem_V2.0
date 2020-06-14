package Dao;
import java.sql.*;  
public class DBDao {
	private static  String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static  String DB_URL = "jdbc:mysql://localhost:3306/shop?useSSL=false&serverTimezone=UTC";
	private static String DB_USERNAME = "root";
	private static String DB_PASSWORD = "123456";
	private static Connection conn = null;
	//连接数据库java_scourse
	public static Connection GetConnection() throws SQLException {
		try {
			//注册数据库驱动
			 Class.forName(DB_DRIVER);
			//连接数据库java_scourse
			conn=DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("连接数据库成功");
			
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
			 System.out.println("连接数据库失败");
		}
		return conn;
		}
	public static void CloseConnection(Connection conn)  throws SQLException{
		if(conn!=null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



