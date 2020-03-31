package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection singleton;

    public static Connection getConnection(String propertiesfilelink) throws ClassNotFoundException, SQLException, IOException {

        if (singleton == null) {
            try (InputStream sa = DBConnection.class.getResourceAsStream(propertiesfilelink)) {

                // load the properties file
                Properties properties = new Properties();
                properties.load(sa);
                Class.forName(DRIVER);
                // assign db parameters
                String password = properties.getProperty("password");
                String user = properties.getProperty("user");
                String url = properties.getProperty("url");

                // create a connection to the database
                singleton = DriverManager.getConnection(url + "?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8", user, password);
            } catch (SQLException| IOException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
        return singleton;
    }
    
}
