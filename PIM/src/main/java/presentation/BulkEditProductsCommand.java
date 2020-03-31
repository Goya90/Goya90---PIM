package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Products;


/**
 *
 * @author Malthe
 * @author Bringordie - Frederik Braagaard
 */
public class BulkEditProductsCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        ArrayList<Products> selectedProducts = Products.getProductTempholder();
        String dropdown = request.getParameter("chosenAttribute");
        String edit = request.getParameter("bulkEditProducts");
        String callback = "";
        
        switch (dropdown) {
            case "ProductID":
                dropdown = "productid";
                break;
            case "Product Name":
                dropdown = "name";
                break;
            case "Product Name Description":
                dropdown = "nameDescription";
                break;
            case "Product Description":
                dropdown = "description";
                break;
            case "Company Name":
                dropdown = "companyName";
                break;
            case "Price":
                dropdown = "price";
                break;
            case "Quantity":
                dropdown = "quantity";
                break;
            case "Picture Name (associated with product)":
                dropdown = "pictureName";
                break;
            case "Published Status":
                dropdown = "publishedStatus";
                if (edit.toLowerCase().contains("yes") || edit.contains("1")|| edit.toLowerCase().contains("true")) {
                    edit = "true";
                } else if (edit.toLowerCase().contains("no") || edit.contains("0") || edit.toLowerCase().contains("false"))
                    edit = "false";
                break;
            case "Main Category":
                dropdown = "mainCategory";
                edit = String.valueOf(db.getMainCategoriesID(edit, "/db.properties"));
                for (Products selectedProduct : selectedProducts) {
                    String minorcategory = selectedProduct.getMinorCategory();
                    String maincategory = edit;
                    int minorID = db.getMinorCategoriesID(minorcategory, "/db.properties");
                    int mainID = db.getMainCategoriesID(maincategory, "/db.properties");
                    db.checkOrCreateLinkminormain(minorID, mainID, "/db.properties");
                }
                break;
            case "Minor Category":
                dropdown = "minorCategory";
                edit = String.valueOf(db.getMinorCategoriesID(edit, "/db.properties"));
                for (Products selectedProduct : selectedProducts) {
                    String minorcategory = edit;
                    String maincategory = selectedProduct.getMainCategory();
                    int minorID = db.getMinorCategoriesID(minorcategory, "/db.properties");
                    int mainID = db.getMainCategoriesID(maincategory, "/db.properties");
                    db.checkOrCreateLinkminormain(minorID, mainID, "/db.properties");
                }
                break;
            default:
                System.err.print("Something went wrong");
        }
        if (edit.equals("true") || edit.equals("false")) {
            callback = db.bulkEditPublished(dropdown, Boolean.parseBoolean(edit), selectedProducts, "/db.properties");
        } else {
            callback = db.bulkEditProducts(dropdown, edit, selectedProducts, "/db.properties");
        }
        
        request.getSession().setAttribute("callback", callback);
        
        return "BulkEditProducts";
    }
    
}
