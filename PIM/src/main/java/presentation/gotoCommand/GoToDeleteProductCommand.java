package presentation.gotoCommand;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 * Used for the JSP page DeleteProduct.jsp
 * @author ClausFindinge - Claus Mikkelsen Findinge
 */
public class GoToDeleteProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
     
        request.getSession().setAttribute("returndeleteproductvalue", "empty");
        return "DeleteProduct";
    }
    
}
