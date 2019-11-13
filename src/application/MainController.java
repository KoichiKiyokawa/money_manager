package application;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	private final Path outputCSVPath = Paths.get("history.csv");
	@FXML
	public ComboBox<CategoryEnum> category;

	@FXML
	public TextField item;

	@FXML
	public TextField price;

	@FXML
	public Button addBtn;

	// Table
	@FXML
	public TableView<MoneyHistory> history;
	@FXML
	public TableColumn<MoneyHistory, String> categoryCol;
	@FXML
	public TableColumn<MoneyHistory, String> itemCol;
	@FXML
	public TableColumn<MoneyHistory, Integer> priceCol;

	@FXML
	public PieChart categoryChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initComboBox();
		initTableView();
		updateChart();
	}

	/**
	 * カテゴリーの選択ボックスを初期化
	 */
	private void initComboBox() {
		// TODO:
		category.getItems().setAll(CategoryEnum.values());
	}

	/**
	 *  テーブルを初期化
	 */
	private void initTableView() {
		ObservableList<MoneyHistory> moneyHistory = FXCollections.observableArrayList(loadHistory());
		history.setItems(moneyHistory);
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	/**
	 * 選択, 入力を初期化
	 */
	private void initIntput() {
		category.setValue(null);
		item.setText("");
		price.setText("");
	}
	
	private List<MoneyHistory> loadHistory() {
		List<MoneyHistory> moneyHistories = new ArrayList<>();
		try {
			for(String line: Files.readAllLines(outputCSVPath,Charset.forName("UTF-8"))) {
				String[] cols = line.split(",");
				CategoryEnum category = CategoryEnum.getCategoryByText(cols[0]);
				String item = cols[1];
				int price = Integer.valueOf(cols[2]);
				moneyHistories.add(new MoneyHistory(category, item, price));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return moneyHistories;
	}

	/**
	 * 項目ごとの円グラフを更新
	 */
	private void updateChart() {
		ObservableList<PieChart.Data> categoryChartData = FXCollections.observableArrayList();
		for (Map.Entry<CategoryEnum, Integer> entry : Aggregation.aggregate(history.getItems()).entrySet()) {
			categoryChartData.add(new PieChart.Data(entry.getKey().toString(), entry.getValue()));
		}
		categoryChart.setData(categoryChartData);
	}

	/**
	 * ファイルに書き出す
	 */
	private void saveHistory() {
		List<String> lines = new ArrayList<>();
		for (MoneyHistory his : history.getItems()) {
			lines.add(his.toString());
		}
		try {
			if (!Files.exists(outputCSVPath)) {
				Files.createFile(outputCSVPath);
			}
			Files.write(outputCSVPath, lines,
					Charset.forName("UTF-8"), StandardOpenOption.WRITE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加ボタンが押されたときの処理
	 */
	public void handleAddBtnClick() {
		if (category.getValue() == null) {
			// カテゴリーが初期状態から変更されていなかったら何もしない
			return;
		}
		try {
			MoneyHistory newHistory = new MoneyHistory(category.getValue(), item.getText(),
					Integer.valueOf(price.getText()));
			history.getItems().add(newHistory);
			initIntput();
			updateChart();
			saveHistory();
		} catch (NumberFormatException e) {
			return;
		}
	}

	/**
	 * 「Money Managerについて」を表示
	 */
	public void showAbout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));
		try {
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Money Managerについて");
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
