package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.ProductBean;
import utils.IpadrUtils;



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
	public int getNum(String pid,String username) throws SQLException {
		int count=0;
		String sql="select num from shop.shopcart where product_id=\""+pid+"\" and user_name=\""+username+"\";";
		Connection conn = null;
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null;
            try {    	 
	    	 conn=DBDao.GetConnection();
	    	 prestmt=conn.prepareStatement(sql);
	    	 rs=prestmt.executeQuery();
	    	 if(rs.next()) {
	    		 if(rs.getString("num").equals("null")) {
	    	        count=Integer.parseInt(rs.getString("num"));
	    	       System.out.println("count:"+count);
	    		 }
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
	public List<String> findProductnum(List<String>l) throws SQLException {
		List<String> list=new ArrayList<String>();
		if(l.size()==0) {
			return null;
		}
		int count=0;
		
		Connection conn = null;
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null;
        
        try {
        	 conn=DBDao.GetConnection();
        		for(int i=0;i<l.size();i++) {
        			String sql="select num  from shop.shopcart where product_id=\"";
        			sql+=l.get(i)+"\"";
       	    	 prestmt=conn.prepareStatement(sql);
    	    	 rs=prestmt.executeQuery();
    	    	 if(rs.next()) {
    	    	 count=rs.getInt(1);
    	    	 list.add(count+"");
        		}
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
        //System.out.println(list.size());
		return list;
	    }
	public List<String> findCartId(String username) throws SQLException {
		List<String> list=new ArrayList<String>();		
		Connection conn = null;
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null;
        
        try {
        	 conn=DBDao.GetConnection();
        	String sql="select id  from shop.shopcart where user_name=\""+username+"\";";
       	    prestmt=conn.prepareStatement(sql);
    	    rs=prestmt.executeQuery();
    	   while(rs.next()) {
    		   String cid=rs.getString("id");
    	    	 list.add(cid);
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
        //System.out.println(list.size());
		return list;
	    }
	public int insert(String id,String product_id,String user_name,String product_num,String pname,String pcatelog)  throws SQLException{
		 String sql="insert into shop.shopcart values(?,?,?,?,?,?,?,?)";
		 Connection conn = null;
	     PreparedStatement prestmt= null;	
	     String date=IpadrUtils.getTime();
	     int bool=0;
	    	 try {

	    	 conn=DBDao.GetConnection();
	    	 prestmt=conn.prepareStatement(sql );
	    	 prestmt.setString(1,id);
	    	 prestmt.setString(2, product_id);
	    	 prestmt.setString(3, user_name);
	    	 prestmt.setString(4, "wait");
	    	 prestmt.setString(5, product_num);
	    	 prestmt.setString(6, pname);
	    	 prestmt.setString(7, pcatelog);
	    	 prestmt.setString(8, date);
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
	public List<ProductBean> finAllCart(List<String> l,String username) throws SQLException {
		List<String> listnum=new ArrayList<String>();
		List<String> listcartID=new ArrayList<String>();
		listnum=(List<String>)findProductnum(l);
		listcartID=(List<String>)findCartId(username);
		List<ProductBean> list=new ArrayList<ProductBean>();
		if(l.size()==0) {
			return null;
		}
		String sql=null;
				
		String sql2="select statue from  shop.shopcart where user_name=\""+username+"\";";
		
		//System.out.print(sql);
		Connection conn = null;
        PreparedStatement prestmt= null;
        ResultSet rs=null;
        try {
        	conn=DBDao.GetConnection();
        	//创建执行sql语句 的对象prestmt
        	
        	//prestmt.setString(1, username);
        	//prestmt.setString(2, password);
        	//执行sql,得到一个结果集
        	for(int i=0;i<l.size();i++) {
    			sql="select * from shop.product where ";
    			sql+=" id=\""+l.get(i)+"\";";
    			prestmt= (PreparedStatement)conn.prepareStatement(sql);
        	rs=(ResultSet) prestmt.executeQuery();
        	while(rs.next()) {        		
        		ProductBean product =new ProductBean();
        		product.setId(rs.getString("id"));
        		product.setName(rs.getString("name"));
        		product.setImgurl(rs.getString("img"));
        		product.setPrice(rs.getString("price"));
        		product.setCatelog(rs.getString("catelog"));
        		product.seTDes(rs.getString("des"));
        		product.setSid(rs.getString("sid"));
        		list.add(product);
        		}
        	}
        	System.out.print(list.size());
        	prestmt= (PreparedStatement)conn.prepareStatement(sql2);
        	rs=(ResultSet) prestmt.executeQuery();
        	int index=0;
        	int size=list.size();
        	while(rs.next()) {      
        		list.get(index).setStatue(rs.getString("statue"));
        		//System.out.print(rs.getString("statue"));
        		index++;
        	}
        	prestmt.close();
        	conn.close();
        	//System.out.print(listnum.size());
        	//System.out.print(listcartID.size());
        	for(int i=0;i<listnum.size();i++) {
        		list.get(i).setNum(listnum.get(i));
        		list.get(i).setCartid(listcartID.get(i));
        	}
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
        	DBDao.CloseConnection(conn);
        }
        System.out.print(list.size());
		return list;
	}
	public void delete(String id,String username,String cart_id) throws SQLException {
		 String sql="update shop.shopcart set statue ='buyed' where product_id="+id+" and  user_name=\""
		 		+ username+"\" and id=\""+cart_id+"\";";
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
	
	public void deleteShopCart(String id, String username) {
		// TODO Auto-generated method stub
		 String sql="update shop.shopcart set statue ='off' where product_id="+id+" and  user_name=\""
			 		+ username+"\";";
			Connection conn = null;
		   PreparedStatement prestmt= null;
		   int bool=0;
		   try {
		   	conn=DBDao.GetConnection();
		   	prestmt=conn.prepareStatement(sql);  
		    bool=prestmt.executeUpdate();
			 prestmt.close();
			 conn.close();
			 DBDao.CloseConnection(conn);
		   }
		   catch (Exception e) {
		   	e.printStackTrace();
		   }
	}
}
