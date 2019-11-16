package application;

import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;

/**
 * 日付ごとの使用金額を集計
 * @author koichi
 *
 */
public class DateAggregation extends Aggregation<DateOfUse> {
	@Override
	public void aggregate(ObservableList<MoneyHistory> moneyHistories) {
		Map<DateOfUse, Integer> date2priceSum = new TreeMap<>();
		for (MoneyHistory history : moneyHistories) {
			DateOfUse key = history.getDate();
			if (date2priceSum.containsKey(key)) {
				int currentPrice = date2priceSum.get(key);
				date2priceSum.replace(key, currentPrice + history.getPrice());
			} else {
				date2priceSum.put(key, history.getPrice());
			}
		}

		setResult(date2priceSum);
	}
}
