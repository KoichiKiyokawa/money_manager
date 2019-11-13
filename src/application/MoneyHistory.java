package application;

public class MoneyHistory {

	private CategoryEnum category; // TODO: class or interface
	private String item;
	private int price;

	public MoneyHistory(CategoryEnum category, String item, int price) {
		this.category = category;
		this.item = item;
		this.price = price;
	}

	public CategoryEnum getCategory() {
		return category;
	}

	public void setCategory(CategoryEnum category) {
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
	
	@Override
	public String toString() {
		return String.format("%s,%s,%d", category.toString(), item, price);
	}
}
