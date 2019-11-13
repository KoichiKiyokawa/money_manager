package application;

import java.util.Map;

import javafx.collections.ObservableList;

/**
 * 
 * @author koichi
 *
 * @param <T> T型についての集計を表す抽象クラス
 */
public abstract class Aggregation<T> {
	public abstract Map<T, Integer> aggregate(ObservableList<MoneyHistory> moneyHistories);
}
