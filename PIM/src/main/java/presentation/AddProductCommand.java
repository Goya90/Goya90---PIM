package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Products;

/**
 * Once the user has entered the necesarry informations for adding a product
 * we grab the attributes and add the product to the database. At the same time we create
 * a link between minor and main categories.
 * @author ClausFindinge - Claus Mikkelsen Findinge
 * @author Bringordie - Frederik Braagaard
 */
public class AddProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        ArrayList<Products> products = new ArrayList();
        int id = Integer.parseInt(request.getParameter("ProductId"));
        String productname = (request.getParameter("ProductName"));
        String productnamedescription = (request.getParameter("ProductNameDescription"));
        String productdescription = (request.getParameter("ProductDescription"));
        String companyname = (request.getParameter("CompanyName"));
        double price = Double.parseDouble(request.getParameter("Price"));
        int quantity = Integer.parseInt(request.getParameter("Quantity"));
        String picturename = (request.getParameter("PictureName"));
        String minorcategory = request.getParameter("minorcategory");
        String maincategory = request.getParameter("maincategory");
        
        String returnvalue = "";
        
        minorcategory = String.valueOf(db.getMinorCategoriesID(minorcategory, "/db.properties"));
        maincategory = String.valueOf(db.getMainCategoriesID(maincategory, "/db.properties"));
        
        products.add(new Products(id, productname, productnamedescription, productdescription, companyname, price, quantity, picturename, true, minorcategory, maincategory));
        returnvalue = db.addProduct(products, "/db.properties");
        
        db.checkOrCreateLinkminormain(Integer.parseInt(maincategory), Integer.parseInt(minorcategory), "/db.properties");
        
        request.getSession().setAttribute("returnproductvalue", returnvalue);

        return "AddProduct";
    }

}
