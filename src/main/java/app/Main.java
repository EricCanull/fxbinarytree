package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Demonstrates a Binary Search Tree using javafx components.
 * @author Eric Canull
 * @version 1.0
 */
public class Main extends Application {

	/**
	 * Builds the Graphic UI.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLGraphicsPanel.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String... args) {
		launch(args);
	}
}
