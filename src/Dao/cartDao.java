package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.ProductBean;



public class cartDao {
	
	public int getcount(String username) throws SQLException {
		int count=0;
		String sql="select count(*) from shop.shopcart where user_name=\""+username+"\"";
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
	public int getid() throws SQLException {
		int count=0;
		String sql="select count(*) from shop.shopcart ";
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
	public int insert(int id,String product_name,String user_name)  throws SQLException{
		 String sql="insert into shop.shopcart values(?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     int bool=0;
	     try {
	    	 
	    	 conn=DBDao.GetConnection();
	    	 prestmt=conn.prepareStatement(sql );
	    	 prestmt.setInt(1,id);
	    	 prestmt.setString(2, product_name);
	    	 prestmt.setString(3, user_name);
	    	 prestmt.setString(4, "待支付");

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
	public void delete(String id) throws SQLException {
		 String sql="delete from shop.shopcart where product_id="+id;
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
	}
}
