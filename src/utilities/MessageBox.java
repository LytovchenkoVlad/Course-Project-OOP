package utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageBox {
	private static Alert alert = new Alert(AlertType.NONE);
	
	public static void Show(AlertType messageType, String title, String header, String content) {		
		alert.setAlertType(messageType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();		
	}
}