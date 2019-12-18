package Bean;

public class HistoryBean {
	private String username;//用户名
	private String name;
    private String price;
    private String date;
    private String catelog;
    public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return this.username;
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
	public void setCatelog(String catelog) {
		this.catelog=catelog;
	}
	public String getCatelog() {
		return this.catelog;
	}
}
