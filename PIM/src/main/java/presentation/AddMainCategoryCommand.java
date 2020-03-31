package presentation;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Malthe
 */
public class AddMainCategoryCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        String categoryResponse;
        String mainCatName = request.getParameter("MainName");
        
        categoryResponse = db.addMainCategory(mainCatName, "/db.properties");
        request.getSession().setAttribute("mainResponse", categoryResponse);
        
        return "AddCategory";
    }
    
}
