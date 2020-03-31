package presentation.gotoCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.FileReaderLogic;
import logic.Products;
import presentation.Command;

/**
 *
 * Used to show all products that we have in the database and it's attributes.
 * First we get all the products
 * "viewallproducts = db.showAllProducts("/db.properties");"
 * once that is done we do a for each loop of all of the products to check
 * their pictureName we then check it with FileReaderLogic to see if the 
 * picture exists or if we need to use a default "not found" picture
 * "String picturestatus = filechecker.FileCheck(viewallproduct.getPictureName());".
 * If it exists we modify it so that it can show the picture, and if it doesn't
 * we modify the picturename completely with a setter
 * "viewallproduct.setPictureName(picturestatus);"
 * 
 * @author Bringordie - Frederik Braagaard
 */
public class GoToViewAllProductsCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        FileReaderLogic filechecker = new FileReaderLogic();

        ArrayList<Products> viewallproducts = new ArrayList();
        viewallproducts = db.showAllProducts("/db.properties");
        for (Products viewallproduct : viewallproducts) {
            viewallproduct.getPictureName();
            try {
                String picturestatus = filechecker.FileCheck(viewallproduct.getPictureName());
                viewallproduct.setPictureName(picturestatus);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        request.getSession().setAttribute("errormsg", "empty");
        request.getSession().setAttribute("resulthits", "empty");
        request.getSession().setAttribute("viewallproducts", viewallproducts);
        request.getSession().setAttribute("resultDBhits", 0);

        return "ShowProducts";
    }

}
