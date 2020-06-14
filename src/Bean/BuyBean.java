package Bean;

public class BuyBean {
	private String username;//用户名
	private String pname;
	private String pcatelog;
    private String price;
    private String sid;
    private String pnum;
    private String sumprice;
    private String date;
    private String ip;

    public void setUsername(String username) {
		this.username=username;
	}
	public  String getUsername() {
		return this.username;
	}
	public void  setSumprice(String sumprice) {
		this.sumprice=sumprice;
	}
	public String getSumprice() {
		return this.sumprice;
	}
	  public void setPnum(String pnum) {
			this.pnum=pnum;
		}
		public String getPnum() {
			return this.pnum;
		}
	public void setSid(String sid) {
		this.sid=sid;
	}
	public String getSid() {
		return this.sid;
	}
	public void setIp(String ip) {
		this.ip=ip;
	}
	public String getIp() {
		return this.ip;
	}
	public void setPname(String name) {
		this.pname=name;
	}
	public String getPname() {
		return this.pname;
	}
	public void setPcatelog(String pcatelog) {
		this.pcatelog=pcatelog;
	}
	public String getPcatelog() {
		return this.pcatelog;
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
