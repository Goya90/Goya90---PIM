package DBtest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.ExcelHandler;
import logic.JsonHandler;
import logic.Products;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Ignore;
import org.junit.Test;
import persistence.DBConnection;

public class ConnectionTest {
    
    @Ignore
    @Test(expected = SQLException.class)
    public void connectionFailTest() throws ClassNotFoundException, SQLException, IOException {
        DBConnection.getConnection("/dberror.properties");
    }
    
    @Test
    public void connectionTest() throws SQLException, ClassNotFoundException, IOException {
        assertNotNull(DBConnection.getConnection("/dbtest.properties"));

    }
    }
