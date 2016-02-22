package kitordersystem;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Not sure why this is still here, it was an experiment that I'm not sure if I got working. The CSV reader is useful though
 */
public class DBReset {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static Connection c;

    static {
        try {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded");
        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
        }

    }

    /**
     * @throws SQLException
     */
    public static void resetDatabase() throws SQLException {
        String s;
        StringBuffer sb = new StringBuffer();

        try {
            connection();
            FileReader fr = new FileReader(
                    new File("/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/KitOrderSystem.sql"));
            // be sure to not have line starting with "--" or "/*" or any other
            // non aplhabetical character

            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");


            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                // This removes whitespace to ensure no empty statements
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                }
            }
            st.executeUpdate("use mydb");
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader("/Users/tsmoffat/kitordersystem/kitordersystem/kitordersystem/Hope.csv"));
            while ((line = reader.readLine()) != null) {
                String[] item = line.trim().split(",");
                // if you want to check either it contains some name
                // index[0] is auto-incremented ID created when inserted into the table so we only use index[1]
                st.executeUpdate("insert into Items (Item) values (\"" + item[1] + "\")");
            }

        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }

    }

    public static void connection() throws IOException, SQLException, org.xml.sax.SAXException, javax.xml.parsers.ParserConfigurationException {
        c = new getConnection().getConnection();
    }


}
