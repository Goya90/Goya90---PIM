package Logictest;

import java.util.ArrayList;
import logic.Categories;
import logic.Products;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Frederik
 */
public class Logictest {

    @Test
    public void productGetters() {
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(56, "productname", "productnamedescription", "productdescription", "companyname", 22, 100, "picturename", true, "1", "1"));
        int id = 0;
        String productname = "";
        String productnamedescription = "";
        String productdescription = "";
        String companyname = "";
        double price = 0;
        int quantity = 0;
        String picturename = "";
        Boolean published = false;
        String minorcategory = "";
        String maincategory = "";
        for (Products product : products) {
            id = product.getId();
            productname = product.getName();
            productnamedescription = product.getNameDescription();
            productdescription = product.getDescription();
            companyname = product.getCompanyName();
            price = product.getPrice();
            quantity = product.getQty();
            picturename = product.getPictureName();
            published = product.getPublishedStatus();
            minorcategory = product.getMinorCategory();
            maincategory = product.getMainCategory();
        }

        assertEquals(56, id);
        assertEquals("productname", productname);
        assertEquals("productnamedescription", productnamedescription);
        assertEquals("productdescription", productdescription);
        assertEquals("companyname", companyname);
        assertEquals(22, price, 0.001);
        assertEquals(100, quantity);
        assertEquals("picturename", picturename);
        assertEquals(true, published);
        assertEquals("1", minorcategory);
        assertEquals("1", maincategory);
        assertNotNull(products.toString());
    }

    @Test
    public void CategoryGetters() {
        ArrayList<Categories> categories = new ArrayList();
        categories.add(new Categories(1, "Frugt og grønt"));
        int id = 0;
        String categoryname = "";
        for (Categories category : categories) {
            id = category.getID();
            categoryname = category.getName();
        }
        assertEquals(1, id);
        assertEquals("Frugt og grønt", categoryname);
        assertNotNull(categories.toString());
    }
    
    @Test
    public void TempArrayAndSetter() {
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(56, "productname", "productnamedescription", "productdescription", "companyname", 22, 100, "picturename", true, "1", "1"));
        for (Products product : products) {
            product.setPictureName("new_picturename");
        }
        Products.setProductTempholder(products);
        
        ArrayList<Products> getTempProducts = Products.getProductTempholder();
        
        assertEquals("new_picturename", getTempProducts.get(0).getPictureName());
        
    }
}
