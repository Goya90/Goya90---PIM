package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Categories;

/**
 *
 * @author Malthe
 */
public class EditMainCategoryCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String MainName = request.getParameter("MainName");
        db.editMainCategory(id, MainName, "/db.properties");
        ArrayList<Categories> mainCategories = new ArrayList();
        mainCategories = db.getMainCategories("/db.properties");
        request.getSession().setAttribute("mainCategories", mainCategories);
        return "EditCategory";
    }
    
}
