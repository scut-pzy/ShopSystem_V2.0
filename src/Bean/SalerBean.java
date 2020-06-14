package Bean;

public class SalerBean {
	private String uid;//销售员id
	private String username;//销售名
	private String password;//密码
	private String email;
	private String phone_number;
	private String statue;
	private String pass;//通信证
	
	//用户名和密码的javabean
	public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return this.username;
	}
	public void setStatue(String statue) {
		this.statue=statue;
	}
	public String getStatue() {
		return this.statue;
	}
	public void setPass(String pass) {
		this.pass=pass;
	}	
	public String getPass() {
		return this.pass;
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
	public void setUid(String uid) {
		this.uid=uid;
	}	
	public String getUid() {
		return this.uid;
	}
}
