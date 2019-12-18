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
public class internetHistory {
	public int insert(String username,ProductBean product)  throws SQLException{
		 String sql="insert into shop.history values(?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     int bool=0;
	     try {
	    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	         String date=df.format(new Date());// new Date()为获取当前系统时间
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
}
