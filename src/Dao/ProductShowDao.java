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
public class ProductShowDao {
	public List<ProductBean> findProduct(String catelog) throws SQLException {
		String sql="select * from shop.product where catelog=\""+catelog+"\"";
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
	public List<ProductBean> searchProduct(String des) throws SQLException {
		String sql="select * from shop.product where catelog like \"%"+des+"%\""+"or des like \"%"+des+"%\"";
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
}
