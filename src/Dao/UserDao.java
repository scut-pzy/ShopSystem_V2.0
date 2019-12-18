package Dao;
import java.sql.*;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import java.sql.PreparedStatement;
import Bean.UserBean;
import Dao.DBDao;
public class UserDao {
	private UserBean User=new UserBean();;
	private String sql="";
	public UserBean UserLogin(String username, String password) throws SQLException {
		//username="root";
		//password="123456";
		sql = "select * from shop.user where name=\""+username+"\"and password=\""+password+"\"";//where username = ? and password= ?;";
		System.out.print(sql);
		Connection conn = null;
        PreparedStatement prestmt= null;
        try {
        	conn=DBDao.GetConnection();
        	//创建执行sql语句 的对象prestmt
        	prestmt= (PreparedStatement)conn.prepareStatement(sql);
        	//prestmt.setString(1, username);
        	//prestmt.setString(2, password);
        	//执行sql,得到一个结果集
        	ResultSet rs=(ResultSet) prestmt.executeQuery(sql);
        	if(rs.next()) {
        		
        		User.setUsername(rs.getString("name"));
        		User.setPassword(rs.getString("password"));
        		User.setPhone_number(rs.getString("phone"));
        		User.setEmail(rs.getString("email"));
        		User.setWealth(rs.getString("wealth"));
        	}
        	prestmt.close();
        	conn.close();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	DBDao.CloseConnection(conn);
        }
        System.out.print(User.getUsername());
		return User;
	}
    public int UserRegister(UserBean u)  throws SQLException{
	 sql="insert into shop.user values(?,?,?,?,?)";
	 Connection conn = null;
     PreparedStatement prestmt= null;	
     int bool=0;
     try {
    	 
    	 conn=DBDao.GetConnection();
    	 prestmt=conn.prepareStatement(sql);
    	 
    	 prestmt.setString(1, u.getUsername());
    	 prestmt.setString(2, u.getPassord());
    	 prestmt.setString(3, u.getPhone_number());
    	 prestmt.setString(4, u.getEmail());
    	 prestmt.setString(5, "0");
    	 
    	 /*
    	 prestmt.setString(1, "root");   	
    	 prestmt.setString(2, "123456");  	
    	 prestmt.setString(3, "17665184565");  	 
    	 prestmt.setString(4, "978389921@qq.com");
    	 */
    	 System.out.println(sql);
    	 bool=prestmt.executeUpdate();
    	 prestmt.close();
    	 conn.close();
     }
     catch (Exception e) {
    	 e.printStackTrace();
     }
     finally {
    	 DBDao.CloseConnection(conn);
     }
     return bool;
    }
    public UserBean ManagerLogin(String username, String password) throws SQLException {
    	//username="root";
    	//password="123456";
    	sql = "select * from shop.manager where name=\""+username+"\"and password=\""+password+"\"";//where username = ? and password= ?;";
    	System.out.print(sql);
    	Connection conn = null;
        PreparedStatement prestmt= null;
        try {
        	conn=DBDao.GetConnection();
        	//创建执行sql语句 的对象prestmt
        	prestmt= (PreparedStatement)conn.prepareStatement(sql);
        	//prestmt.setString(1, username);
        	//prestmt.setString(2, password);
        	//执行sql,得到一个结果集
        	ResultSet rs=(ResultSet) prestmt.executeQuery(sql);
        	if(rs.next()) {
        		User=new UserBean();
        		User.setUsername(rs.getString("name"));
        		User.setPassword(rs.getString("password"));
        		User.setPhone_number(rs.getString("phone"));
        		User.setEmail(rs.getString("email"));
        		//User.setWealth(rs.getString("wealth"));
        	}
        	prestmt.close();
        	conn.close();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	DBDao.CloseConnection(conn);
        }
    	return User;
    }
}
