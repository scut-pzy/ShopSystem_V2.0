package service;
import java.sql.SQLException;
import java.util.List;
import Bean.*;
import Dao.ProductDao;
public class produceService {
	public List<ProductBean> finALLProduct() throws SQLException  {
	ProductDao dao=new ProductDao();
	List<ProductBean> list =dao.finAllProduct();
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
}
