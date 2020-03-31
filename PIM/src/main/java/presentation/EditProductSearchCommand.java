package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Categories;
import logic.Products;

/**
 *
 * When we are at the EditProduct.jsp we have to enter a product id.
 * When we have the ID we do a search to the DB to check that it exists
 * "search = db.searchProduct(idsearch, "/db.properties");"
 * If it exists we will then see a product on the .jsp page. If it doesn't exist
 * we will return empty back to show a message
 * "request.getSession().setAttribute("productupdated", "empty");".
 * We do a for loop with all the categories from the DB in order to remove the
 * one that matches with what the product already has (if it has one).
 * 
 * 
 * @author Bringordie - Frederik Braagaard
 */
public class EditProductSearchCommand extends Command {
    

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException, SQLException, ClassNotFoundException{

        String results = "empty";
        int idsearch = 0;
        idsearch = Integer.parseInt(request.getParameter("ProductID"));
        ArrayList<Products> search = new ArrayList();
        search = db.searchProduct(idsearch, "/db.properties");
        
        if (search.isEmpty()){
            results = "empty";
        } else if (search.size() == 1){
            results = "hit";
        }
        
        String maincategory = "";
        String minorcategory = "";
        
        for (Products products : search) {
            maincategory = products.getMainCategory();
            minorcategory = products.getMinorCategory();
        }
        
        ArrayList<Categories> maincategories = db.getMainCategories("/db.properties");
        ArrayList<Categories> minorcategories = db.getMinorCategories("/db.properties");
        
        ArrayList<Categories> maincategoriesarray = new ArrayList();
        ArrayList<Categories> minorcategoriesarray = new ArrayList();
        
        
        for (Categories categories : maincategories) {
            if (categories.getName().contains(maincategory)) {
                //do nothing to avoid ConcurrentModificationException
            } else {
                maincategoriesarray.add(categories);
            }
        }
        
        for (Categories categories : minorcategories) {
            if (categories.getName().contains(minorcategory)) {
                //do nothing to avoid ConcurrentModificationException
            } else {
                minorcategoriesarray.add(categories);
            }
        }
        
        request.getSession().setAttribute("resulthits", results);
        request.getSession().setAttribute("productarray", search);
        request.getSession().setAttribute("productupdated", "empty");
        request.getSession().setAttribute("currentmain", maincategory);
        request.getSession().setAttribute("currentminor", minorcategory);
        request.getSession().setAttribute("maincategoriesarray", maincategoriesarray);
        request.getSession().setAttribute("minorcategoriesarray", minorcategoriesarray);
        

        return"EditProduct"; 
    }
    
}