package view;

import entity.Auto—ompany;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.MessageBox;

public class AutoCompanyNameEditDialogController {
	@FXML
	private TextField AutoCompanyNameField;
	@FXML
	private TextField tariffField;
	@FXML
	private TextField electricityField;

	private Stage dialogStage;
	private Auto—ompany company;
	private boolean okClicked = false;


	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setCompany(Auto—ompany company) {
		this.company = company;
		AutoCompanyNameField.setText(company.getAutoCompanyName());
		tariffField.setText(Double.toString(company.getTariff()));
		electricityField.setText(Double.toString(company.getElectricity()));
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			company.setAutoCompanyName(AutoCompanyNameField.getText());
			company.setTariff(Double.parseDouble(tariffField.getText()));
			company.setElectricity(Double.parseDouble(electricityField.getText()));
			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		if (AutoCompanyNameField.getText() == null || AutoCompanyNameField.getText().length() == 0) {
			errorMessage += "No valid name company!\n";
		}
		if (tariffField.getText() == null || tariffField.getText().length() == 0) {
			errorMessage += "No valid tariff value!\n";
		}
		if (electricityField.getText() == null || electricityField.getText().length() == 0) {
			errorMessage += "No valid electricity value!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			MessageBox.Show(AlertType.ERROR, "Invalid fields", "Please correct invalid fields!", errorMessage);
			return false;
		}
	}

	}