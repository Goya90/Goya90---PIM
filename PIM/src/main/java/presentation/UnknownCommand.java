package presentation;


import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used to show a #404 page when someone tries to modify the "cmd=" on the http.
 * 
 * @author Frederik Braagaard - Bringordie
 */
public class UnknownCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String msg = "Unknown command. Contact IT";
        throw new IOException(msg);
    }

}
