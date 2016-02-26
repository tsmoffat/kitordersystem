package kitordersystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * This is, as the name suggests, the main menu for my programme. WHere all the magic happens.
 */
public class MainMenu extends Application {

    public static String token;
    public static String ordering;


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
        ComboBox orderComboBox = new ComboBox();
        Label label = new Label();
        searchField.setPromptText("Search");
        orderComboBox.getItems().addAll("Name A-Z", "Name Z-A", "Item", "Order ID", "Squad", "Customer ID");
        remakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Caution");
                dialog.setContentText("Are you sure you want to do this? Enter 'remake' to remake the database");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    if (result.get() == "remake") {
                        DBCreate create = new DBCreate();

                        try {
                            create.SQLReader();
                        } catch (IOException e) {

                            e.printStackTrace();
                        } catch (SQLException e) {

                            e.printStackTrace();
                        } catch (SAXException e) {

                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {

                            e.printStackTrace();
                        }
                        try {
                            CSVReader reset = new CSVReader();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
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
                ordering = orderComboBox.getValue().toString();
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
                try {
                    DocWriter writer = new DocWriter();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                token = searchField.getText();
                ordering = orderComboBox.getValue().toString();
                try {
                    new DBSearch().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        grid.add(new Label("Order by: "), 0,3,1,1);
        grid.add(orderComboBox, 1,3,2,1);
        GridPane.setHalignment(remakeButton, HPos.CENTER);
        GridPane.setHalignment(emailButton, HPos.CENTER);
        GridPane.setHalignment(dbViewButton, HPos.CENTER);
        GridPane.setHalignment(searchButton, HPos.CENTER);
        GridPane.setHalignment(searchField, HPos.CENTER);
        GridPane.setHalignment(orderComboBox, HPos.CENTER);


        primaryStage.show();
    }

}
