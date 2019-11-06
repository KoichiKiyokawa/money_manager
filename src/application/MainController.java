package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {
	@FXML
	public ComboBox<String> category;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initComboBox();
		initTableView();
	}

	private void initComboBox() {
		// TODO:
		category.getItems().addAll("食費", "交通費", "娯楽");
	}

	// テーブルを初期化
	private void initTableView() {
		ObservableList<MoneyHistory> moneyHistory = FXCollections.observableArrayList();
		history.setItems(moneyHistory);
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	public void handleAddBtnClick() {
		if (category.getValue() == null) {
			return;
		}
		try {
			MoneyHistory newHistory = new MoneyHistory(category.getValue(), item.getText(),
					Integer.valueOf(price.getText()));
			history.getItems().add(newHistory);
			
			category.setValue(null);
			item.setText("");
			price.setText("");
		} catch (NumberFormatException e) {
			return;
		}
	}
}
