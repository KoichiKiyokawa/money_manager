package application;

public enum CategoryEnum {
	food("食費"), transportation("交通費"), hoby("娯楽"), other("その他");

	private final String text;

	private CategoryEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
