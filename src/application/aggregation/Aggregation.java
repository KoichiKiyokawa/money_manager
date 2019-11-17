package application.aggregation;

import java.util.Map;

import application.model.MoneyHistory;
import javafx.collections.ObservableList;

/**
 * 
 * @author koichi
 *p
 * @param <T> T型についての集計を表す抽象クラス
 */
public abstract class Aggregation<T> {

	private Map<T, Integer> result;

	public abstract void aggregate(ObservableList<MoneyHistory> moneyHistories);

	public Map<T, Integer> getResult() {
		return result;
	}

	public void setResult(Map<T, Integer> result) {
		this.result = result;
	}

	public double getPercentage(T key) {
		int total = 0;
		for (int price : result.values()) {
			total += price;
		}
		return (double) 100.0 * result.get(key) / total;
	}
}
