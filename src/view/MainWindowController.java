package view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Platform;
import mainpack.MainClass;
import model.DataModel;
import utilities.MessageBox;
import entity.Auto—ompany;

public class MainWindowController<AutoCompany> {
	private static FileChooser fileChooser;
	@FXML
	private TableView<Auto—ompany> companyTable;
	@FXML
	private TableColumn<Auto—ompany, String> AutoCompanyNameColumn;
	@FXML
	private TableColumn<Auto—ompany, Double> TariffColumn;
	@FXML
	private TableColumn<Auto—ompany, Double> ElectricityColumn;
	@FXML
	private TableColumn<Auto—ompany, Double> averageColumn;
	@FXML
	private TextField groupValue1Field;
	@FXML
	private TextField groupValue2Field;

	private static DataModel model;
	private static TableViewSelectionModel<Auto—ompany> sM;
	private static final String f = "%5.2f";

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		model = DataModel.GetInstance();
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("“ÂÍÒÚÓ‚˚Â Ù‡ÈÎ˚ (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		AutoCompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("AutoCompanyName"));
		TariffColumn.setCellValueFactory(new PropertyValueFactory<>("tariff"));
		ElectricityColumn.setCellValueFactory(new PropertyValueFactory<>("electricity"));
		averageColumn.setCellValueFactory(cellData -> cellData.getValue().calcAutoCompanyElecricity().asObject());
		sM = companyTable.getSelectionModel();
		companyTable.setItems(model.getCache());
	}

	public MainWindowController() {
	}
	public boolean showStudentEditDialog(Auto—ompany company) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("/view/StudentEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Company");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(MainClass.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AutoCompanyNameEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCompany(company);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
	}

	private void setSummaryInfo() {
		groupValue1Field.setText(String.format(f, model.calcElectricityValue()));
		groupValue2Field.setText(String.format(f, model.calcPaymentValue()));
	}

	@FXML
	private void handleAdd() {
		Auto—ompany tempCompany = new Auto—ompany();
		boolean okClicked = showStudentEditDialog(tempCompany);
		if (okClicked) {
			model.add(tempCompany);
			setSummaryInfo();
		}
	}

	@FXML
	private void handleDelete() {
		int selectedIndex = sM.getSelectedIndex();
		if (selectedIndex >= 0) {
			model.delete(selectedIndex);
			List<Auto—ompany> lst = model.getCache();
			if (!lst.isEmpty()) {
				setSummaryInfo();
			} else {
				groupValue1Field.setText("");
				groupValue2Field.setText("");
			}
		} else {

			// Nothing selected.
			MessageBox.Show(AlertType.WARNING, "No Selection", "No Company Selected",
					"Please select a company to delete!");
		}
	}

	@FXML
	private void handleEdit() {
		int selectedIndex = sM.getSelectedIndex();
		Auto—ompany selectedCompany = sM.getSelectedItem();
		if (selectedCompany != null) {
			boolean okClicked = showStudentEditDialog(selectedCompany);
			if (okClicked) {
				model.edit(selectedIndex);
				setSummaryInfo();
			}

		} else {
			// Nothing selected.
			MessageBox.Show(AlertType.WARNING, "No Selection", "No Company Selected",
					"Please select a company to edit!");
		}
	}

	@FXML
	private void handleAbout() {
		MessageBox.Show(AlertType.INFORMATION, "AutoCompanyElecricityApp", "About developer",
				"Litovchemko Vladislav \n3 Avenue Pobedy 55, Kharkiv 61173, go109@mail.ru");
	}

	@FXML
	private void handleExit() {
		Platform.exit();
	}

	@FXML
	private void handleOpen() {
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			try {
				model.clear();
				model.load(file);
				setSummaryInfo();
			} catch (ClassNotFoundException | IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Opens a FileChooser to let the user select a file to save to.
	 */
	@FXML
	private void handleSaveAs() {
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".txt")) {
				file = new File(file.getPath() + ".txt");
			}
			try {
				model.persist(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}