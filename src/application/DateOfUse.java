package application;

import java.util.Calendar;

public class DateOfUse {

	private int year;
	private int month;
	private int day;

	public DateOfUse(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * 
	 * @param dateStr yyyy/MM/dd のフォーマット
	 */
	public DateOfUse(String dateStr) {
		String[] items = dateStr.split("/");
		this.year = Integer.valueOf(items[0]);
		this.month = Integer.valueOf(items[1]);
		this.day = Integer.valueOf(items[2]);
	}

	/**
	 * 何も引数を指定しなかった場合は今日の年月日で初期化
	 */
	public DateOfUse() {
		this(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONDAY),
				Calendar.getInstance().get(Calendar.DATE));
	}

	@Override
	public String toString() {
		return String.format("%d/%d/%d", year, month, day);
	}
}
