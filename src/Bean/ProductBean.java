package Bean;

public class ProductBean {
    private String id;
    private String name;
    private String price;
    private String num;
    private String catelog;
    private String des;
    private String sid;
    private String imgurl;
    private String statue;
    private String cartid;
    public void setId(String id) {
		this.id=id;
	}
	public String getId() {
		return this.id;
	}
	public void setStatue(String statue) {
		this.statue=statue;
	}
	public String getStatue() {
		return this.statue;
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
	public void setImgurl(String imgurl) {
		this.imgurl=imgurl;
	}
	public String getImgurl() {
		return this.imgurl;
	}
	public void setPrice(String price) {
		this.price=price;
	}
	public String getPrice() {
		return this.price;
	}
	public void setNum(String num) {
		this.num=num;
	}
	public String getNum() {
		return this.num;
	}
	public void setCatelog(String catelog) {
		this.catelog=catelog;
	}
	public String getCatelog() {
		return this.catelog;
	}

	public void seTDes(String des) {
		this.des=des;
	}
	public String getDes() {
		return this.des;
	}
	public void setCartid(String cartid) {
		this.cartid=cartid;
	}
	public String getCartid() {
		return this.cartid;
	}
}
