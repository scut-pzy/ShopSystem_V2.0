package Dao;
import Dao.DBDao;
import Bean.ProductBean;
import Bean.UserBean;
import Bean.UserdrawBean;

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
	public int getid() throws SQLException {
		int count=0;
		String sql="select count(*) from shop.product ";
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
	
	public List<String> getUserLable(String username,Connection conn) throws SQLException {
		 List<String> list=new ArrayList<String>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
    try {
    	String sql="select *  from shop.usercommend where username=\""+username+"\";";
   	    prestmt=conn.prepareStatement(sql);
	    rs=prestmt.executeQuery();
	   if(rs.next()) {
		 UserdrawBean m=new UserdrawBean();
		    list.add(rs.getString("lable1"));
		   list.add(rs.getString("lable2"));
    		}
	    	 prestmt.close();
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
		return list;
	    }
	public List<String> getProducttrend(List<String> catelog,Connection conn) {
		// TODO Auto-generated method stub
		 List<String> list=new ArrayList<String>();		
	     PreparedStatement prestmt= null;	
	     ResultSet rs = null; 
   try {
   	for(int i=0;i<catelog.size();i++){
   	String sql="select *  from shop.productcommend where catelog= \""+catelog.get(i)+"\"";
  	    prestmt=conn.prepareStatement(sql);
	    rs=prestmt.executeQuery();
	   while(rs.next()) {
		   if(!rs.getString("p1").equals("null")) {
			   list.add(rs.getString("p1"));
		   }
		   if(!rs.getString("p2").equals("null")) {
			   list.add(rs.getString("p2"));
		   }
		   if(!rs.getString("p3").equals("null")) {
			   list.add(rs.getString("p3"));
		   }}
	    	 prestmt.close();
   }}
   catch (Exception e) {
   	e.printStackTrace();
   }
		return list;
	}
	public List<String> getUserProductRecommend(String username,Connection conn) throws SQLException {//获取用户推荐的商品名称
		 List<String> listproduct=new ArrayList<String>();	
		 List<String> listlable=new ArrayList<String>();		
   try {
   	listlable=getUserLable(username,conn);
   	listproduct=getProducttrend(listlable,conn);
   }
   catch (Exception e) {
   	e.printStackTrace();
   }
		return listproduct;
	    }
	
	
	public List<ProductBean> finAllProductInUserProductRecommend(String username) throws SQLException {

		Connection conn = null;
        PreparedStatement prestmt= null;
        ResultSet rs = null;
        List<String> listpname=new ArrayList<String>();
        List<ProductBean> list=new ArrayList<ProductBean>();
        try {
        	conn=DBDao.GetConnection();
        	listpname=getUserProductRecommend(username,conn);
        	for(int i=0;i<listpname.size();i++) {
        		String sql="select * from shop.product where name=\""+listpname.get(i)+"\";";
        		prestmt= (PreparedStatement)conn.prepareStatement(sql);
        		 rs=(ResultSet) prestmt.executeQuery(sql);
            	while(rs.next()) {        		
            		ProductBean product =new ProductBean();
            		product.setId(rs.getString("id"));
            		product.setName(rs.getString("name"));
            		product.setImgurl(rs.getString("img"));
            		product.setPrice(rs.getString("price"));
            		product.setNum(rs.getString("num"));
            		product.setCatelog(rs.getString("catelog"));
            		product.setSid(rs.getString("sid"));
            		product.setStatue(rs.getString("statue"));
            		product.seTDes(rs.getString("des"));
            		list.add(product);
            	}
            	prestmt.close();
        	}
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
        		product.setStatue(rs.getString("statue"));
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
	public List<ProductBean> finAllProduct(String sid) throws SQLException {
		String sql="select * from shop.product where sid=\""+sid+"\";";
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
        		product.setStatue(rs.getString("statue"));
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
        		product.setStatue(rs.getString("statue"));
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
	 String sql="insert into shop.product values(?,?,?,?,?,?,?,?,?)";
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
	 prestmt.setString(8, u.getSid());
	 System.out.print(u.getStatue());
	 prestmt.setString(9, u.getStatue());
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
public int delete(String id,String sid) throws SQLException {
	 String sql="update shop.product  set statue=\"off\" where id=\""+id+"\" and sid=\""+sid+"\";";
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
public int update(String id,String catelog,String name, String price,String num,String des,String statue) throws SQLException {
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
   if(statue!="" ){
	   s5 =",statue=\""+statue+"\"" ;
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
public int update(String product_id, String stockNum, String product_num,String ShopStatue) {
	// TODO Auto-generated method stub
	
	     int bool=0;
	     if(ShopStatue.equals("off")) return bool;//商品已经下架无法购买了
	     int StockNum=Integer.parseInt(stockNum);
	     int ProductNum=Integer.parseInt(product_num);
	     if(StockNum<ProductNum) {
	    	 return bool;
	     }
	     if(StockNum<1) {
	    	 return bool;
	     }
	     int NowStockNum=StockNum-ProductNum;
	     String nowStockNum=NowStockNum+"";
		String sql="update shop.product  set num=\""+nowStockNum+"\" where id=\""+product_id+
				"\" ;";
		Connection conn = null;
		PreparedStatement prestmt= null;
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
		return bool;
}
}
