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
public class AddMinorCategoryCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        String categoryResponse;
        String minorCatName = request.getParameter("MinorName");
        
        categoryResponse = db.addMinorCategory(minorCatName, "/db.properties");
        request.getSession().setAttribute("minorResponse", categoryResponse);
        
        return "AddCategory";
    }
    
}
