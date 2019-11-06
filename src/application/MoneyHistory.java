package application;

public class MoneyHistory {

	private String category; // TODO: class or interface
	private String item;
	private int price;

	public MoneyHistory(String category, String item, int price) {
		this.category = category;
		this.item = item;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
