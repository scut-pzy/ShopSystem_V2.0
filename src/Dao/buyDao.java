package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import Bean.BuyBean;
import Bean.HistoryBean;
import Bean.ProductBean;
import utils.IpadrUtils;

public class buyDao {
	public int getcount() throws SQLException {
		int count=0;
		String sql="select count(*) from shop.buy ";
		Connection conn = null;
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null;
            try {    	 
	    	 conn=DBDao.GetConnection();
	    	 prestmt=conn.prepareStatement(sql);
	    	 rs=prestmt.executeQuery();
	    	 if(rs.next()) {
	    	 count=rs.getInt(1);
	    	 System.out.println("count:"+count);
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
	     return count;
	    }
	public static String getCatellogid(String catelog) {
		if(catelog.equals("手机数码")) {catelog="1";}
		else if(catelog.equals("电子产品")) {catelog="2";}
		else if(catelog.equals("衣服服饰")) {catelog="3";}
		else if(catelog.equals("穿戴设备")) {catelog="4";}
		else if(catelog.equals("生活用品")) {catelog="5";}
		else if(catelog.equals("医疗药物")) {catelog="6";}
		else if(catelog.equals("交通出行")) {catelog="7";}
		return catelog;
    }
	public int insert(int id,String user_name,String product_name,String saler_id,String price,String product_num,String SumPrice ,String pcatelog)  throws SQLException{
		 String sql="insert into shop.buy values(?,?,?,?,?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null; 
         String date=IpadrUtils.getTime();// new Date()为获取当前北京时间
	     int bool=0;
	     try {
	    	 
	    	 conn=DBDao.GetConnection();
	    	 prestmt=conn.prepareStatement(sql);
	    	 prestmt.setInt(1,id);
	    	 prestmt.setString(2, user_name);
	    	 prestmt.setString(3, product_name);    	 
	    	 prestmt.setString(4, price);
	    	 prestmt.setString(5, product_num);
	    	 prestmt.setString(6, SumPrice);
	    	 prestmt.setString(7, date);
	    	 prestmt.setString(8, saler_id);
	    	 prestmt.setString(9, getCatellogid(pcatelog));
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
	public List<String> findProductid(String username) throws SQLException {
		String sql="select * from shop.shopcart where user_name=\""+username+"\"";
		Connection conn = null;
        PreparedStatement prestmt= null;
        List<String> list=new ArrayList<String>();
        try {
        	conn=DBDao.GetConnection();
        	//创建执行sql语句 的对象prestmt
        	prestmt= (PreparedStatement)conn.prepareStatement(sql);
        	//prestmt.setString(1, username);
        	//prestmt.setString(2, password);
        	//执行sql,得到一个结果集
        	ResultSet rs=(ResultSet) prestmt.executeQuery(sql);
        	while(rs.next()) {        		
        		list.add(rs.getString("product_id"));
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
	public List<ProductBean> finAllCart(List<String> l) throws SQLException {
		List<ProductBean> list=new ArrayList<ProductBean>();
		if(l.size()==0) {
			return null;
		}
		String sql="select * from shop.product where ";
		for(int i=0;i<l.size();i++) {
			sql+=" id=\""+l.get(i)+"\"";
			if(i<l.size()-1) {
				sql+="or";
			}
		}
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
        		ProductBean product =new ProductBean();
        		product.setId(rs.getString("id"));
        		product.setName(rs.getString("name"));
        		product.setImgurl(rs.getString("img"));
        		product.setPrice(rs.getString("price"));
        		product.setNum(rs.getString("num"));
        		product.setCatelog(rs.getString("catelog"));
        		product.seTDes(rs.getString("des"));
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
	public List<BuyBean> findBuy() throws SQLException {
		List<BuyBean> list=new ArrayList<BuyBean>();
		
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
        		BuyBean product =new BuyBean();
        		product.setPname(rs.getString("pname"));
        		product.setUsername(rs.getString("uname"));		
        		product.setPrice(rs.getString("price"));
        		product.setPnum(rs.getString("pnum"));
        		product.setSumprice(rs.getString("sumprice"));
        		product.setDate(rs.getString("date"));
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
	public List<BuyBean> findBuy(String sid) throws SQLException {
		// TODO Auto-generated method stub
List<BuyBean> list=new ArrayList<BuyBean>();
		
		String sql="select * from shop.buy where uname=\""+sid+"\";";
		
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
        		BuyBean product =new BuyBean();
        		product.setPname(rs.getString("pname"));
        		product.setUsername(rs.getString("uname"));		
        		product.setPrice(rs.getString("price"));
        		product.setPnum(rs.getString("pnum"));
        		product.setSumprice(rs.getString("sumprice"));
        		product.setDate(rs.getString("date"));
        		product.setSid(rs.getString("sid"));
        		//product.setIp(rs.getString("ip"));
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
