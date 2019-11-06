package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * カテゴリー(食費、交通費、、、)ごとの金額を集計するクラス
 * @author 1J17F044-4 清川航一
 *
 */
public class Aggregation {

	public static Map<CategoryEnum, Integer> aggregate(List<MoneyHistory> moneyHistories) {
		Map<CategoryEnum, Integer> category2priceSum = new HashMap<>();
		for (CategoryEnum category : CategoryEnum.values()) {
			category2priceSum.put(category, 0);
		}

		for (MoneyHistory history : moneyHistories) {
			int currentCateroryPrice = category2priceSum.get(history.getCategory());
			category2priceSum.replace(history.getCategory(), currentCateroryPrice + history.getPrice());
		}

		return category2priceSum;
	}
}
