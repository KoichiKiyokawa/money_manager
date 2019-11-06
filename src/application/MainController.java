package application;

import java.io.IOException;
import java.net.URL;
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
	}

	private void initComboBox() {
		// TODO:
		category.getItems().setAll(CategoryEnum.values());
	}

	// テーブルを初期化
	private void initTableView() {
		ObservableList<MoneyHistory> moneyHistory = FXCollections.observableArrayList();
		history.setItems(moneyHistory);
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	// 選択, 入力を初期化
	private void initIntput() {
		category.setValue(null);
		item.setText("");
		price.setText("");
	}
	
	private void updateChart() {
		// TODO:
	}

	public void handleAddBtnClick() {
		if (category.getValue() == null) {
			return;
		}
		try {
			MoneyHistory newHistory = new MoneyHistory(category.getValue(), item.getText(),
					Integer.valueOf(price.getText()));
			history.getItems().add(newHistory);
			initIntput();
		} catch (NumberFormatException e) {
			return;
		}
	}

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
