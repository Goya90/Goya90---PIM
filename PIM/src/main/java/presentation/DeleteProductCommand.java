package presentation;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * * Once the user has entered the ID of the product he/she wants to delete,
 * we grab the attribute and delete the product from the database.
 * @author ClausFindinge - Claus Mikkelsen Findinge
 */



public class DeleteProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        String returnvalue = "";
        returnvalue = db.deleteProduct(Integer.parseInt(request.getParameter("ProductId")), "/db.properties");
        request.getSession().setAttribute("returndeleteproductvalue", returnvalue);
        return "DeleteProduct";
    }

}
