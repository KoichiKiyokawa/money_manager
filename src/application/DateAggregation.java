package application;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;

/**
 * 日付ごとの使用金額を集計
 * @author koichi
 *
 */
public class DateAggregation extends Aggregation<DateOfUse> {
	@Override
	public Map<DateOfUse, Integer> aggregate(ObservableList<MoneyHistory> moneyHistories) {
		Map<DateOfUse, Integer> date2priceSum = new HashMap<>(); 
		for (MoneyHistory history:moneyHistories) {
			DateOfUse key = history.getDate();
			if (date2priceSum.containsKey(key)) {
				int currentPrice = date2priceSum.get(key);
				date2priceSum.replace(key, currentPrice + history.getPrice());
			}else {
				date2priceSum.put(key, 0);
			}
		}
		
		return date2priceSum;
	}
}
