package mainpack;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainClass extends Application {
	private static Stage primaryStage;

	public MainClass() {
	}

	@Override
	public void start(Stage pr) {
		primaryStage = pr;
		primaryStage.setTitle("Electricity consumption");  //main title project
		initMainWindow();
	}

	/** 
	 * Initializes the root layout. 
	 */
	public void initMainWindow() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainClass.class.getResource("/view/MainWindow.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}