package presentation.gotoCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Categories;
import presentation.Command;

/**
 *
 * Used for the JSP page AddProduct.jsp
 * When we enter the page we check if there are any main or minor categories.
 * As a newly created product via the webpage must have all information if it
 * will give an "empty" response back if there aren't any and the submit button
 * will be hidden untill categories are created.
 * 
 * @author Bringordie - Frederik Braagaard
 * @author ClausFindinge - Claus Mikkelsen Findinge
 */
public class GoToAddProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
     
        ArrayList<Categories> mainCategories = new ArrayList();
        mainCategories = db.getMainCategories("/db.properties");
        if (!mainCategories.isEmpty()) {
            request.getSession().setAttribute("mainCategories", mainCategories);
        } else {
            request.getSession().setAttribute("mainCategories", null);
        }
        
        ArrayList<Categories> minorCategories = new ArrayList();
        minorCategories = db.getMinorCategories("/db.properties");
        if (!minorCategories.isEmpty()) {
            request.getSession().setAttribute("minorCategories", minorCategories);
        } else {
            request.getSession().setAttribute("minorCategories", null);
        }
        request.getSession().setAttribute("returnproductvalue", "empty"); 
        return "AddProduct";
    }
    
}
