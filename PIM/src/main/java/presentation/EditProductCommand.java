package presentation;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Products;

/**
 *
 * Once the .jsp page has found a product and the necessary changes has been 
 * made we grab the attributes and do an update on the product. And create
 * a link between minor and main categories if it hasn't already been made.
 * 
 * @author Bringordie - Frederik Braagaard
 */
public class EditProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("ProductId"));
        String productname = (request.getParameter("ProductName"));
        String productnamedescription = (request.getParameter("ProductNameDescription"));
        String productdescription = (request.getParameter("ProductDescription"));
        String companyname = (request.getParameter("CompanyName"));
        double price = Double.parseDouble(request.getParameter("Price"));
        int quantity = Integer.parseInt(request.getParameter("Quantity"));
        String picturename = (request.getParameter("PictureName"));
        String minorcategory = (request.getParameter("minorcategory"));
        String maincategory = (request.getParameter("maincategory"));

        minorcategory = String.valueOf(db.getMinorCategoriesID(minorcategory, "/db.properties"));
        maincategory = String.valueOf(db.getMainCategoriesID(maincategory, "/db.properties"));

        db.editProduct(id, new Products(id, productname, productnamedescription, productdescription, companyname, price, quantity, picturename, true, minorcategory, maincategory), "/db.properties");
        
        db.checkOrCreateLinkminormain(Integer.parseInt(maincategory), Integer.parseInt(minorcategory), "/db.properties");
        
        String productupdated = "Productupdated";
        request.getSession().setAttribute("productupdated", productupdated);

        return "EditProduct";
    }

}
