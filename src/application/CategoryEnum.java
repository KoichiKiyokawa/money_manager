package application;

public enum CategoryEnum {
	food("食費"), transportation("交通費"), hoby("娯楽"), other("その他");

	private final String text;

	private CategoryEnum(final String text) {
		this.text = text;
	}

	/**
	 * 日本語名からenumを取得する
	 * ex) getCategoryByText("食費") => food
	 * @param text {String} 日本語名
	 */
	public static CategoryEnum getCategoryByText(String text) {
		for (CategoryEnum category : CategoryEnum.values()) {
			if (category.toString().equals(text)) {
				return category;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return text;
	}
}
