package application.aggregation;

import java.util.HashMap;
import java.util.Map;

import application.model.CategoryEnum;
import application.model.MoneyHistory;
import javafx.collections.ObservableList;

/**
 * カテゴリー(食費、交通費、、、)ごとの金額を集計するクラス
 * @author 1J17F044-4 清川航一
 *
 */
public class CategoryAggregation extends Aggregation<CategoryEnum> {

	@Override
	public void aggregate(ObservableList<MoneyHistory> moneyHistories) {
		// key: カテゴリー, value: そのカテゴリーの合計値
		Map<CategoryEnum, Integer> category2priceSum = new HashMap<>();
		for (CategoryEnum category : CategoryEnum.values()) {
			// それぞれのカテゴリーの合計値を0で初期化
			category2priceSum.put(category, 0);
		}

		// 足し上げる
		for (MoneyHistory history : moneyHistories) {
			int currentCateroryPrice = category2priceSum.get(history.getCategory());
			category2priceSum.replace(history.getCategory(), currentCateroryPrice + history.getPrice());
		}

		setResult(category2priceSum);
	}
}
