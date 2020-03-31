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
public class DeleteMainCategoryCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        db.deleteMainCategory(id, "/db.properties");

        ArrayList<Categories> mainCategories = new ArrayList();
        mainCategories = db.getMainCategories("/db.properties");
        request.getSession().setAttribute("mainCategories", mainCategories);

        ArrayList<Categories> minorCategories = new ArrayList();
        minorCategories = db.getMinorCategories("/db.properties");
        request.getSession().setAttribute("minorCategories", minorCategories);

        return "DeleteCategory";
    }

}
