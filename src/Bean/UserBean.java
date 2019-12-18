
package Bean;

public class UserBean {
	private String username;//用户名
	private String password;//密码
	private String email;
	private String phone_number;
	private String wealth;
	
	//用户名和密码的javabean
	public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return this.username;
	}
	public void setPassword(String password) {
		this.password=password;
	}	
	public String getPassord() {
		return this.password;
	}
	public void setEmail(String email) {
		this.email=email;
	}	
	public String getEmail() {
		return this.email;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number=phone_number;
	}	
	public String getPhone_number() {
		return this.phone_number;
	}
	public void setWealth(String wealth) {
		this.wealth=wealth;
	}	
	public String getWealth() {
		return this.wealth;
	}
}
