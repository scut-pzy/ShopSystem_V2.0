package Bean;

public class BuyLoginBean {
	private String username;//用户名
	private String name;
    private String price;
    private String sid;
    private String product_num;
    private String SumPrice;
    private String date;
    public void setUsername(String username) {
		this.username=username;
	}
	public  String getUsername() {
		return this.username;
	}
	public void  setSumPrice(String SumPrice) {
		this.SumPrice=SumPrice;
	}
	public void setSumPrice( ) {
		int price=Integer.parseInt(this.price);
		int num=Integer.parseInt(this.product_num);
		int  SumPrice=price*num;
		String sp=SumPrice+"";
		this.SumPrice=sp;
	}
	public String getSumPrice() {
		return this.SumPrice;
	}
	  public void setProductNum(String product_num) {
			this.product_num=product_num;
		}
		public String getProductNum() {
			return this.product_num;
		}
		public void setSid(String sid) {
			this.sid=sid;
		}
		public String getSid() {
			return this.sid;
		}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setPrice(String price) {
		this.price=price;
	}
	public String getPrice() {
		return this.price;
	}
	public void setDate(String date) {
		this.date=date;
	}
	public String getDate() {
		return this.date;
	}
}
