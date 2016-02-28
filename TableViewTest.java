package kitordersystem;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class that shows everything in the database all at once. Excuse the name, I put it in as a test to see if it would work and then it worked so well that I just never changed it.
 */
public class TableViewTest extends Application {

    // TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    private TableView tableview;

    // MAIN EXECUTOR
    public static void main(String[] args) {
        launch(args);
    }

    // CONNECTION DATABASE

    /**
     * @throws SQLException
     * @throws IOException
     */
    public void buildData() throws SQLException, IOException {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = new getConnection().getConnection();
            String SQL = "USE mydb";
            c.createStatement().executeQuery(SQL);
            // SQL FOR SELECTING TABLES
            String ordering = MainMenu.ordering;
            String order;
            switch (ordering){
                case "Name A-Z":    order = "Name ASC";
                    break;
                case "Name Z-A":    order = "Name DESC";
                    break;
                case "Item":        order = "Orders";
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

            SQL = "select o.ID, o.CustomerID, c.Name, c.Email_Address, c.Squad,  o.Orders, o.OrderSize, o.OrderNumber, o.NameOnGarment, o.PaidFor, o.PaymentMethod, i.Item from Orders o INNER JOIN Customers c ON o.CustomerID = c.ID INNER JOIN Items i ON i.idItems=o.Orders ORDER BY " + order +";";


            // ResultSet

            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**
             * Gets column headings and adds them to the TableView dynamically
             */

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

                // We are using non property style for making dynamic table

                final int j = i;

                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
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
        buildData();
        // Main Scene
        Scene scene = new Scene(tableview);
        stage.setScene(scene);
        stage.show();
    }
}
