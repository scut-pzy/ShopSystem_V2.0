package Dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Bean.HistoryBean;
import Bean.ProductBean;
import utils.IpadrUtils;
public class internetHistory {
	public int insert(String username,ProductBean product)  throws SQLException{
		 String sql="insert into shop.history values(?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     int bool=0;
	     try {
	    	 
	         String date=IpadrUtils.getTime();// new Date()为获取当前系统时间
	    	 conn=DBDao.GetConnection();
	    	 prestmt=conn.prepareStatement(sql);
	    	 prestmt.setString(1,username);
	    	 prestmt.setString(2, product.getName());
	    	 prestmt.setString(3, date);
	    	 prestmt.setString(4, product.getPrice());
	    	 prestmt.setString(5, product.getCatelog());
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
	public List<HistoryBean> findHistory() throws SQLException {
		List<HistoryBean> list=new ArrayList<HistoryBean>();
		
		String sql="select * from shop.history  ";
		
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
        	while(rs.next()) {        		
        		HistoryBean product =new HistoryBean();
        		product.setName(rs.getString("product_name"));
        		product.setUsername(rs.getString("username"));
        		product.setCatelog(rs.getString("catelog"));
        		product.setDate(rs.getString("date"));
        		product.setPrice(rs.getString("price"));
        		list.add(product);
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
        System.out.println(list.size());
		return list;
	}
	public List<HistoryBean> findHistory(String username) throws SQLException {
		List<HistoryBean> list=new ArrayList<HistoryBean>();
		
		String sql="select * from shop.history where username=\""+username+"\"";
		
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
        	while(rs.next()) {        		
        		HistoryBean product =new HistoryBean();
        		product.setName(rs.getString("product_name"));
        		product.setUsername(rs.getString("username"));
        		product.setCatelog(rs.getString("catelog"));
        		product.setDate(rs.getString("date"));
        		product.setPrice(rs.getString("price"));
        		list.add(product);
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
        System.out.println(list.size());
		return list;
	}
	public int getcount(Connection conn) throws SQLException {
		int count=0;
		String sql="select count(*) from shop.history2 ";
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null;
            try {    	 
	    	 prestmt=conn.prepareStatement(sql);
	    	 rs=prestmt.executeQuery();
	    	 if(rs.next()) {
	    	 count=rs.getInt(1);
	    	 }
	     }
	     catch (Exception e) {
	    	 e.printStackTrace();
	     }
	     return count;
	    }
	public int insert(String username,String pname,String pcatelog,String lasttime,String price)  throws SQLException{
		 String sql="insert into shop.history2 values(?,?,?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     int bool=0;
	     int id=0;
	     String date=IpadrUtils.getTime();
	     try {
	    	 conn=DBDao.GetConnection();
	    	 id=getcount(conn);
	    	 id++;
	    	 prestmt=conn.prepareStatement(sql);
	    	 prestmt.setInt(1,id);
	    	 prestmt.setString(2,username);
	    	 prestmt.setString(3, pname);
	    	 prestmt.setString(4, pcatelog);
	    	 prestmt.setString(5, lasttime);
	    	 prestmt.setString(6, price);
	    	 prestmt.setString(7, date);
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
