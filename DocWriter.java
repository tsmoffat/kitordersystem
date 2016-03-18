package kitordersystem;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tsmoffat on 21/02/2016.
 * A class to essentially dump the contents of the database to a spreadsheet
 * in order to facilitate easy order sheet creation. This can be done as Word
 * has an import from Excel option, although there is a possibility that the
 * spreadsheet itself will be used as the order sheet
 */
public class DocWriter {
    public DocWriter() throws Exception{
        XWPFDocument document = new XWPFDocument();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd_HH.mm.ss");
        Date dateobj = new Date();


        Connection conn = new getConnection().getConnection();
        ResultSet rs;
        String query;

        Workbook wb = new XSSFWorkbook();

        FileOutputStream fileOut = new FileOutputStream
                ("Tilehurst_Order_Raw_" + df.format(dateobj) + ".xlsx");
        for (int i=1; i<9; i++){
            String stmt = "USE mydb";
            conn.createStatement().executeQuery(stmt);
            stmt="SELECT Item from Items where idItems = " + i;
            rs = conn.createStatement().executeQuery(stmt);
            rs.next();
            String item = rs.getString("Item");
            Sheet sheet1 = wb.createSheet(item);
            XSSFRow row;
            Map< String, Object[] > orderInfo =
                    new TreeMap< String, Object[] >();
            orderInfo.put("1", new Object[] {item});
            orderInfo.put("2", new Object[] {"Name", "Size", "Printed On"});
            stmt = "SELECT c.Name, o.OrderSize, o.NameOnGarment FROM Orders o" +
                    " INNER JOIN Customers c on o.CustomerID " +
                    "= c.ID where o.Orders = " + i;
            wb.write(fileOut);
            while(rs.next()){
                int j = 3;
                String size = rs.getString("OrderSize");
                String name = rs.getString("Name");
                String nameOnBack = rs.getString("NameOnGarment");
                String rowNum = Integer.toString(j);
                orderInfo.put(rowNum, new Object[] {name, size, nameOnBack});
                wb.write(fileOut);
            }
            wb.write(fileOut); 
        }
        wb.write(fileOut);
        fileOut.close();
        wb.close();




            XWPFTable table1 = document.createTable();
            XWPFParagraph paragraph1 = document.createParagraph();
            String stmt = "USE mydb;";
            conn.createStatement().executeQuery(stmt);
            query = "SELECT Item from Items where idItems = 1";
            rs = conn.createStatement().executeQuery(query);
            rs.next();
            String item1 = rs.getString("Item");
            XWPFRun paraRun1 = paragraph1.createRun();
            paraRun1.setBold(true);
            paraRun1.setUnderline(UnderlinePatterns.SINGLE);
            paragraph1.setAlignment(ParagraphAlignment.CENTER);
            paraRun1.setText(item1);
            System.out.println(1);
            XWPFTableRow trOne1 = table1.getRow(0);
            trOne1.getCell(0).setText("Name");
            trOne1.addNewTableCell().setText("Size");
            trOne1.addNewTableCell().setText("Name on Back");
            query = "SELECT c.Name, o.OrderSize, o.NameOnGarment FROM Orders " +
                    "o INNER JOIN Customers c on o.CustomerID" +
                    " = c.ID where o.Orders = 1";
            rs = conn.createStatement().executeQuery(query);

            while(rs.next()) {
                String size = rs.getString("OrderSize");
                String name = rs.getString("Name");
                String nameOnBack = rs.getString("NameOnGarment");
                XWPFTableRow tRow = table1.createRow();
                tRow.getCell(0).setText(name);
                tRow.getCell(1).setText(size);
                tRow.getCell(2).setText(nameOnBack);

            }
            return;


    }

}
