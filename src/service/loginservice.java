package service;
import java.sql.SQLException;

import Bean.*;
import Dao.*;
public class loginservice {

	public UserBean login(String name,String password) throws SQLException {
		UserDao dao=new UserDao();
		UserBean user=null;
		user=(UserBean)dao.UserLogin(name, password);
		System.out.print(user.getPhone_number());
		return user;
	}
}
