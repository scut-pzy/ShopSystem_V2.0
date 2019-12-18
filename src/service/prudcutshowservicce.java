package service;
import java.sql.SQLException;
import java.util.List;
import Bean.*;
import Dao.ProductDao;
import Dao.ProductShowDao;
public class prudcutshowservicce{
	public List<ProductBean> findProduct(String catelog) throws SQLException  {
	ProductShowDao dao=new ProductShowDao();
	List<ProductBean> list =dao.findProduct(catelog);
	return list;
	}
	
	public ProductBean findDetailProduct(String id) throws SQLException{
		ProductDao dao=new ProductDao();
		ProductBean product=new ProductBean();
		//System.out.println(product);
		product=dao.finDetailProduct(id);
		//System.out.println(product);
		return product;
	}
	public List<ProductBean> searchProduct(String des) throws SQLException  {
		ProductShowDao dao=new ProductShowDao();
		List<ProductBean> list =dao.searchProduct(des);
		return list;
		}
}
