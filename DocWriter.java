package kitordersystem;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tsmoffat on 21/02/2016.
 * TODO Get number of orders for each item from the database.
 * TODO Get number of rows under each item
 * TODO Write tables to MSDocument
 * TODO Letterhead and all that fun stuff
 */
public class DocWriter {
    public void DocumentWriter() throws Exception{
        XWPFDocument document = new XWPFDocument();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dateobj = new Date();
        FileOutputStream out = new FileOutputStream(new File("TilehurstOrderForm " + df.format(dateobj) + ".docx"));

        Connection conn = new getConnection().getConnection();
        ResultSet rs;
        String query;


        for (int i=1; i<9; i++){
            XWPFTable table = document.createTable();
            XWPFParagraph paragraph = document.createParagraph();
            query = "SELECT Item from Items where idItems "
            System.out.println(i);
            query = "SELECT c.Name, o.Size, o.NameOnBack FROM Orders o INNER JOIN Customers c on o.CustomerID = c.ID where o.Order " + i;
            rs = conn.createStatement().executeQuery(query);
            while(rs.next()){

            }
        }
    }

}
