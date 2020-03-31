package presentation.gotoCommand;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Bringordie - Frederik Braagaard
 */
public class GoToEditProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        request.getSession().setAttribute("resulthits", "gotoempty");
        request.getSession().setAttribute("productupdated", "empty");
        return "EditProduct";
    }
    
}