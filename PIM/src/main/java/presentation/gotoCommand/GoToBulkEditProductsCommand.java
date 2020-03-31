package presentation.gotoCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Categories;
import logic.FileReaderLogic;
import logic.Products;
import presentation.Command;

/**
 *
 * @author Malthe
 * @author Bringordie - Frederik Braagaard
 */
public class GoToBulkEditProductsCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        String[] products = request.getParameterValues("selected");
        ArrayList<Products> allProducts = db.showAllProducts("/db.properties");
        ArrayList<Categories> maincategories = db.getMainCategories("/db.properties");
        ArrayList<Categories> minorcategories = db.getMinorCategories("/db.properties");
        Products obj = null;
        ArrayList<Products> selectedProducts = new ArrayList();
        String website = "";
        String errormsg = "";
        if (!(products == null)) {
            for (int i = 0; i < products.length; ++i) {
                int id = Integer.parseInt(products[i]);
                for (int j = 0; j < allProducts.size(); ++j) {
                    obj = allProducts.get(j);

                    if (obj.getId() == id) {
                        selectedProducts.add(obj);
                    }
                }
            }
            website = "BulkEditProducts";
        } else {
            ArrayList<Products> viewallproducts = db.showAllProducts("/db.properties");
            errormsg = "noinput";
            website = "ShowProducts";
            FileReaderLogic filechecker = new FileReaderLogic();
        for (Products viewallproduct : viewallproducts) {
            viewallproduct.getPictureName();
            try {
                String picturestatus = filechecker.FileCheck(viewallproduct.getPictureName());
                viewallproduct.setPictureName(picturestatus);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
            request.getSession().setAttribute("viewallproducts", viewallproducts);
        }
        
        request.getSession().setAttribute("selected", selectedProducts);
        request.getSession().setAttribute("maincategories", maincategories);
        request.getSession().setAttribute("minorcategories", minorcategories);
        request.getSession().setAttribute("errormsg", "noinput");
        request.getSession().setAttribute("callback", "empty");
        Products.setProductTempholder(selectedProducts);
        return website;
    }

}
