package Bean;

public class MiddleBean {
	private String username;//用户名
	private String product;//产品名
	private String lable;//标签（为产品类别
	private String action;//行为
	private String date;//精确到天
	private String lasttime;
	//用户名和密码的javabean
	public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return this.username;
	}
	public void setProduct(String product) {
		this.product=product;
	}	
	public String getProduct() {
		return this.product;
	}
	public void setLable(String lable) {
		this.lable=lable;
	}	
	public String getLable() {
		return this.lable;
	}
	public void setAction(String action) {
		this.action=action;
	}	
	public String getAction() {
		return this.action;
	}
	
	public void setDate(String date) {
		this.date=date;
	}	
	public String getDate() {
		return this.date;
	}
	public void setLasttime(String lasttime) {
		this.lasttime=lasttime;
	}	
	public String getLasttime() {
		return this.lasttime;
	}
}
