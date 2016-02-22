package kitordersystem;

import org.apache.poi.xwpf.usermodel.*;

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
    public DocWriter() throws Exception{
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
            query = "SELECT Item from Items where idItems =" + i;
            rs = conn.createStatement().executeQuery(query);
            String item = rs.getString("Item");
            XWPFRun paraRun = paragraph.createRun();
            paraRun.setBold(true);
            paraRun.setUnderline(UnderlinePatterns.SINGLE);
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            paraRun.setText(item);
            System.out.println(i);
            XWPFTableRow trOne = table.getRow(0);
            trOne.getCell(0).setText("Name");
            trOne.addNewTableCell().setText("Size");
            trOne.addNewTableCell().setText("Name on Back");
            query = "SELECT c.Name, o.Size, o.NameOnBack FROM Orders o INNER JOIN Customers c on o.CustomerID = c.ID where o.Order =" + i;
            rs = conn.createStatement().executeQuery(query);
            document.write(out);
            while(rs.next()){
                String size = rs.getString("Size");
                String name = rs.getString("Name");
                String nameOnBack = rs.getString("NameOnBack");
                XWPFTableRow tRow = table.createRow();
                tRow.getCell(0).setText(name);
                tRow.getCell(1).setText(size);
                tRow.getCell(2).setText(nameOnBack);
                document.write(out);
            }

        }
    }

}
