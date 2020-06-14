package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Bean.BuyLoginBean;
import utils.IpadrUtils;

import java.util.Date;
import java.text.SimpleDateFormat;
public class LogDao {
	public List<BuyLoginBean> findBuy() throws SQLException {
		List<BuyLoginBean> list=new ArrayList<BuyLoginBean>();
		
		String sql="select * from shop.buy;";
		
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
        		BuyLoginBean product =new BuyLoginBean();
        		product.setSid(rs.getString("sid"));		
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
	public int getid(Connection conn) throws SQLException {
		int count=0;
		String sql="select count(*) from shop.logrecord ";
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
	public void insertLogRecord(String name,String type,String date,String ipadr,String operate )  throws SQLException{
		 String sql="insert into shop.logrecord values(?,?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null; 
	     int lrid=0;
	     int bool=0;
	     try {  	 
	    	 conn=DBDao.GetConnection();
	    	 lrid=getid(conn);
	    	 prestmt=conn.prepareStatement(sql);
	    	 prestmt.setInt(1,lrid);
	    	 prestmt.setString(2, name);
	    	 prestmt.setString(3, type);    	 
	    	 prestmt.setString(4, date);
	    	 prestmt.setString(5, ipadr);
	    	 prestmt.setString(6, operate);
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
	    }
}
