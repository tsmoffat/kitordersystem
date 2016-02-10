package kitordersystem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import javafx.geometry.HPos;
import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenu extends Application {
	public static void main(String[] args) throws SQLException, IOException {
		launch(args);
	}

	/**
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Ordr");
		Button remakeButton = new Button("Remake");
		Button dbViewButton = new Button("Contents");
		Button emailButton = new Button("Generate");
		Button searchButton = new Button("Search");
		TextField searchField = new TextField();
		searchField.setPromptText("Search");
		remakeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Caution");
				dialog.setContentText("Are you sure you want to do this? Enter remake to remake the database");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()) {
					if (result.get() == "remake") {
						DBCreate create = new DBCreate();
						try {
							create.SQLReader();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {

					}
				} else {

				}

			}
		});
		dbViewButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {
					public void run() {
						try {
							new TableViewTest().start(new Stage());
						} catch (Exception e) {

							e.printStackTrace();
						}

					}
				});
			}

		});
		emailButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("You clicked this! Sadly, it doesn't work yet, but it will soon!");
			}
		});
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String token = searchField.getText();

			}
		});

		GridPane grid = new GridPane();

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);
		grid.add(dbViewButton, 0, 0, 1, 1);
		grid.add(remakeButton, 1, 0, 1, 1);
		remakeButton.setTextFill(Color.RED);
		grid.add(searchButton, 0, 1, 1, 1);
		grid.add(emailButton, 1, 1, 1, 1);
		grid.add(searchField, 0, 2, 3, 1);
        GridPane.setHalignment(remakeButton, HPos.CENTER);
        GridPane.setHalignment(emailButton, HPos.CENTER);
        GridPane.setHalignment(dbViewButton, HPos.CENTER);
        GridPane.setHalignment(searchButton, HPos.CENTER);
        GridPane.setHalignment(searchField, HPos.CENTER);


		primaryStage.show();
	}

}
