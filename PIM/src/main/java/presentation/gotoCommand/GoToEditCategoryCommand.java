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
 * @author Malthe
 */
public class GoToEditCategoryCommand extends Command {
    
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        
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
        return "EditCategory";
    }
    
}
