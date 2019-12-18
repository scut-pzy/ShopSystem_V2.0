package Dao;
import Dao.DBDao;
import Bean.ProductBean;
import Bean.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Dao.DBDao;
import Bean.ProductBean;	
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
public class ProductDao {
	public List<ProductBean> finAllProduct() throws SQLException {
		String sql="select * from shop.product";
		Connection conn = null;
        PreparedStatement prestmt= null;
        List<ProductBean> list=new ArrayList<ProductBean>();
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

	@SuppressWarnings("null")
	public ProductBean finDetailProduct(String id)throws SQLException{
		String s=" where id=\""+id+"\"";
		//System.out.print(sql);
		String sql="select * from shop.product"+s; 
		System.out.println(sql);
		Connection conn = null;
        PreparedStatement prestmt= null;
        ProductBean product=new ProductBean();
        try {
        	conn=DBDao.GetConnection();
        	//创建执行sql语句 的对象prestmt
        	prestmt= (PreparedStatement)conn.prepareStatement(sql);
        	//prestmt.setString(1, username);
        	//prestmt.setString(2, password);
        	//执行sql,得到一个结果集
        	ResultSet rs=(ResultSet) prestmt.executeQuery(sql);
        	
        	if(rs.next()) {        
        		product.setCatelog(rs.getString("catelog"));
        		product.setName(rs.getString("name"));
        		product.setImgurl(rs.getString("img"));
        		product.setPrice(rs.getString("price"));
        		product.setNum(rs.getString("num"));
        		
        		product.seTDes(rs.getString("des"));
        		product.setId(rs.getString("id"));
        		
        	}
        	prestmt.close();
        	conn.close();
        }
        	catch( Exception e) {
        		e.printStackTrace();
        	}
        	finally {
        		DBDao.CloseConnection(conn);
        	}
        	return product;        
	}

public int insert(ProductBean u) throws SQLException {
	 String sql="insert into shop.product values(?,?,?,?,?,?,?)";
	Connection conn = null;
    PreparedStatement prestmt= null;
    int bool=0;
    try {
    	conn=DBDao.GetConnection();
   	    prestmt=conn.prepareStatement(sql);
   	 prestmt.setString(1, u.getId());
	 prestmt.setString(2, u.getName());
	 prestmt.setString(3, u.getPrice());
	 prestmt.setString(4, u.getCatelog());
	 prestmt.setString(5, u.getNum());
	 prestmt.setString(6, u.getImgurl());
	 prestmt.setString(7, u.getDes());
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
public int delete(String id) throws SQLException {
	 String sql="delete from shop.product where id="+id;
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
public int update(String id,String catelog,String name, String price,String num,String des) throws SQLException {
	 String sql="update shop.product set id=\""+id+"\"";
	Connection conn = null;
   PreparedStatement prestmt= null;
   String s1=null,s2=null,s3=null,s4=null,s5=null;
   String s6=" where id=\""+id+"\"";
   int bool=0;
   if(catelog!="") {
	   s1 =",catelog=\""+catelog+"\"" ;
	   sql+=s1;
   }
   if(name!="") {
	   s2 =",name=\""+name+"\"" ;
	   sql+=s2;
   }
   if(price!="") {
	   s3 =",price=\""+price+"\"" ;
	   sql+=s3;
   }
   if(num!="") {
	    s4 =",num=\""+num+"\"" ;
	    sql+=s4;
   }
   if(des!="" ){
	   s5 =",des=\""+des+"\"" ;
	   sql+=s5;
   }
   try {
	   sql+=s6;
	   System.out.println(sql);
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
