package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import Bean.SalerBean;
import Bean.UserBean;
import Dao.DBDao;
public class UserDao {
	private UserBean User=new UserBean();
	private SalerBean Saler=new SalerBean();;
	private String sql="";
	public SalerBean SalerLogin(String username, String password) throws SQLException {
		//username="root";
		//password="123456";
		sql = "select * from shop.saler where salename=\""+username+"\"and password=\""+password+"\"";//where username = ? and password= ?;";
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
        		
        		Saler.setUsername(rs.getString("salename"));
        		Saler.setPassword(rs.getString("password"));
        		Saler.setPhone_number(rs.getString("phone_number"));
        		Saler.setEmail(rs.getString("email"));
        		Saler.setUid(rs.getString("uid"));
        		Saler.setPass(rs.getString("pass"));
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
        if(Saler.getStatue().equals("freeze"))
        	return null;
        else
        	return Saler;
	}
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
        //System.out.print(User.getUsername());
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
    public int UserRegister(SalerBean u)  throws SQLException{
    	String sql="insert into shop.saler values(?,?,?,?,?,?,?)";
    	String sql_num="select count(*)  from shop.saler;";
     	PreparedStatement num=null;
     	int id=0;
   	   	Connection conn = null;
        PreparedStatement prestmt= null;	
        ResultSet rs = null;
        int bool=0;
        try {
       	 
       	 conn=DBDao.GetConnection();
       	 prestmt=conn.prepareStatement(sql);
       	 num=conn.prepareStatement(sql_num);
       	rs=num.executeQuery();
       	if(rs.next()) {
	    	 id=rs.getInt(1);
	    	 }
       	id++;
       	String sid=id+"";
       	 prestmt.setString(1, sid);
       	 prestmt.setString(2, u.getUsername());
       	 prestmt.setString(3, u.getPassord());
       	 prestmt.setString(4, u.getEmail());
       	 prestmt.setString(5, u.getPhone_number());
       	prestmt.setString(6, "alive");
       	prestmt.setString(7, u.getPassord());
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
    public List<SalerBean> findAllSaler() throws SQLException {
		//username="root";
		//password="123456";
		sql = "select * from shop.saler ;";
		Connection conn = null;
        PreparedStatement prestmt= null;
        List<SalerBean> list=new ArrayList<SalerBean>();
        try {
        	conn=DBDao.GetConnection();
        	//创建执行sql语句 的对象prestmt
        	prestmt= (PreparedStatement)conn.prepareStatement(sql);
        	//prestmt.setString(1, username);
        	//prestmt.setString(2, password);
        	//执行sql,得到一个结果集
        	ResultSet rs=(ResultSet) prestmt.executeQuery(sql);
        	while(rs.next()) {
        		SalerBean saler=new SalerBean();
        		saler.setUsername(rs.getString("salename"));
        		saler.setPassword(rs.getString("password"));
        		saler.setPass(rs.getString("pass"));
        		saler.setPhone_number(rs.getString("phone_number"));
        		saler.setEmail(rs.getString("email"));
        		saler.setUid(rs.getString("uid"));
        		saler.setStatue(rs.getString("statue"));
        		list.add(saler);
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
		return list;
	}
	public int FreezeSaler(String sid) throws SQLException {
		String sql="update shop.saler set statue ='freeze' where uid=\""+sid+"\";";
		Connection conn = null;
	   PreparedStatement prestmt= null;
	   int bool=0;
	   try {
	   	conn=DBDao.GetConnection();
	   	prestmt=conn.prepareStatement(sql);  
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
	public int UpdateSalerPassword(String sid, String password) throws SQLException {
		// TODO Auto-generated method stub
		 String sql="update shop.saler set password =\""+password+"\" , pass=\""+password+"\" where uid=\""+sid+"\";";
		Connection conn = null;
	   PreparedStatement prestmt= null;
	   int bool=0;
	   try {
	   	conn=DBDao.GetConnection();
	   	prestmt=conn.prepareStatement(sql);  
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
	public int ThaweSaler(String sid) throws SQLException {
		String sql="update shop.saler set statue ='alive' where uid=\""+sid+"\";";
		Connection conn = null;
	   PreparedStatement prestmt= null;
	   int bool=0;
	   try {
	   	conn=DBDao.GetConnection();
	   	prestmt=conn.prepareStatement(sql);  
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
}
