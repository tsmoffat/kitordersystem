package kitordersystem;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by tsmoffat on 10/02/2016.
 * This is a class to search the database for a specific string. Works in
 * much the same way as the connection part in TableViewTest but uses a user
 * inputted search string as opposed to returning everything. Uses
 * PreparedStatement to sanitise inputs. Yay.ø
 */
public class DBSearch extends Application {

    private ObservableList<ObservableList> data;
    private javafx.scene.control.TableView tableview;

    // MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }

    public void Search() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = new getConnection().getConnection();
            String SQL = "USE mydb";
            c.createStatement().executeQuery(SQL);
            // SQL FOR SELECTING TABLES
            String token = MainMenu.token;
            String ordering = MainMenu.ordering;
            String order;
            switch (ordering){
                case "Name A-Z":    order = "Name ASC";
                                    break;
                case "Name Z-A":    order = "Name DESC";
                                    break;
                case "Item":        order = "Order";
                                    break;
                case "Order ID":    order = "ID";
                                    break;
                case "Squad":       order = "Squad";
                                    break;
                case "Customer ID:":order = "CustomerID";
                                    break;
                case "Payment Method":  order = "PaymentMethod";
                    break;
                default:            order = "ID";
            }
            PreparedStatement statement = c.prepareStatement("select o.ID,  o" +
                    ".CustomerID, c.Name, c.Email_Address, c.Squad, o.Orders," +
                    " o.OrderSize, o.OrderNumber, o.NameOnGarment, o.PaidFor, " +
                    "o.PaymentMethod, i.Item from Orders o INNER JOIN " +
                    "Customers c ON o.CustomerID = c.ID INNER JOIN Items i ON" +
                    " i.idItems=o.Order where o.ID like " +
                    "? or o.CustomerID like ? or c.Name like ? or c" +
                    ".Email_Address like ? or c.Squad like ? or o.Orders like" +
                    " ? or o.OrderSize like ? or o.OrderNumber like ? or  o" +
                    ".NameOnGarment like ? or o.PaymentMethod like ? or i" +
                    ".Item  like ? ORDER BY "+ order +";");

            statement.setString(1, "%" + token + "%");
            statement.setString(2, "%" + token + "%");
            statement.setString(3, "%" + token + "%");
            statement.setString(4, "%" + token + "%");
            statement.setString(5, "%" + token + "%");
            statement.setString(6, "%" + token + "%");
            statement.setString(7, "%" + token + "%");
            statement.setString(8, "%" + token + "%");
            statement.setString(9, "%" + token + "%");
            statement.setString(10, "%" + token + "%");
            statement.setString(11, "%" + token + "%");
            System.out.println("Executing: " + statement);
            ResultSet rs = statement.executeQuery();

            /**********************************
             *
             * TABLE COLUMN ADDED DYNAMICALLY *
             *
             **********************************/

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

                // We are using non property style for making dynamic table

                final int j = i;

                TableColumn col = new TableColumn(rs.getMetaData()
                        .getColumnName(i + 1));
                col.setCellValueFactory(
                        new Callback<TableColumn.CellDataFeatures
                                <ObservableList, String>, ObservableValue
                                <String>>() {
                            public ObservableValue<String> call(TableColumn.
                                                                        CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param
                                        .getValue().get(j).toString());
                            }
                        });
                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }
            /********************************
             *
             * Data added to ObservableList *
             *
             ********************************/
            while (rs.next()) {
                // Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    // Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            // FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    /**
     *
     */
    @Override
    public void start(Stage stage) throws Exception {
        // TableView
        tableview = new TableView();
        Search();
        // Main Scene
        Scene scene = new Scene(tableview);
        stage.setScene(scene);
        stage.show();
    }
}

