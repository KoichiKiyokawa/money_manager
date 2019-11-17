package application.model;

public class MoneyHistory {

	private DateOfUse date;
	private CategoryEnum category;
	private String item;
	private int price;

	public DateOfUse getDate() {
		return date;
	}

	public void setDate(DateOfUse date) {
		this.date = date;
	}

	public MoneyHistory(DateOfUse date, CategoryEnum category, String item, int price) {
		this.date = date;
		this.category = category;
		this.item = item;
		this.price = price;
	}

	public MoneyHistory(CategoryEnum category, String item, int price) {
		this(new DateOfUse(), category, item, price);
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

	public String toCSV() {
		return String.format("%s,%s,%s,%d", date, category.name(), item, price);
	}
}
