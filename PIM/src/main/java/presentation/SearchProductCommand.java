package presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.FileReaderLogic;
import logic.Products;

/**
 *
 * When we search for a product we have a drowndown list.
 * This list is on the jsp page showed for a viewers eyes and are therefor
 * not the attributes that we have in the database. Therefor we use a switch
 * that checks what dropdown was choosen and correct the dropdown value.
 * And then we call the dbSearch method to check if a product with those
 * criteria actually exists. If it exists an arraylist will be populated
 * and depending on if the arraylist is 0 or less than0 we either give show all 
 * hits or give an error message.
 * For publicstatus we do it a little different as we are using a boolean so 
 * that the user doesn't "have" to write true or false. But have several options.
 * When that is done we use a FileReaderLogic in order to show the picture 
 * correctly if it exists and a standard picture if it doesn't.
 * 
 * @author Bringordie - Frederik Braagaard
 */
public class SearchProductCommand extends Command {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        String results = "empty";
        String dropdown = request.getParameter("searchCriteria");
        String search = request.getParameter("searchInput");

        ArrayList<Products> viewallproducts = new ArrayList();
        switch (dropdown) {
            case "ProductID":
                dropdown = "productid";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Product Name":
                dropdown = "name";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Product Name Description":
                dropdown = "nameDescription";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Product Description":
                dropdown = "description";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Company Name":
                dropdown = "companyName";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Price":
                dropdown = "price";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Quantity":
                dropdown = "quantity";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Picture Name (associated with product)":
                dropdown = "pictureName";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Published Status":
                dropdown = "publishedStatus";
                if (search.toLowerCase().contains("yes") || search.contains("1")|| search.toLowerCase().contains("true")) {
                    viewallproducts = dbSearch("1", dropdown);
                } else if (search.toLowerCase().contains("no") || search.contains("0") || search.toLowerCase().contains("false"))
                    viewallproducts = dbSearch("0", dropdown);
                break;
            case "Main Category":
                dropdown = "mainCategoryName";
                viewallproducts = dbSearch(search, dropdown);
                break;
            case "Minor Category":
                dropdown = "minorCategoryName";
                viewallproducts = dbSearch(search, dropdown);
                break;
            default:
                System.err.print("Something went wrong");
        }

        int resulthits = viewallproducts.size();

        if (viewallproducts.isEmpty()) {
            results = "empty";
        } else if (viewallproducts.size() > 0) {
            results = "hit";
        }
        
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
        request.getSession().setAttribute("errormsg", "empty");
        request.getSession().setAttribute("resulthits", results);
        request.getSession().setAttribute("viewallproducts", viewallproducts);
        request.getSession().setAttribute("resultDBhits", resulthits);

        return "ShowProducts";
    }

    private ArrayList<Products> dbSearch(String search, String dropdown) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<Products> viewallproducts;
        viewallproducts = db.showSearchedProduct(search, dropdown, "/db.properties");
        return viewallproducts;
    }

}
