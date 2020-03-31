package logic;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * Used for uploading a .json document. This will only work if the .json document
 * has the structure of the class Products. This method would have been changed 
 * in the future if there were more time as it would fail to any other .json
 * documment that doesn't have it in the same grid. That means that the 
 * .json file you can download you can't upload straight away.
 * But the mathod takes the location of the file where it has been uploaded
 * and then uses a filereader to read the file. We then use gson to read
 * the file into an array list of products.
 * Added close for file.
 * 
 * 
 * @author Frederik Braagaard - Bringordie
 */
public class JsonHandler {

    Gson gson = new Gson();

        
        public ArrayList<Products> makeJSonFileIntoArray(String location) throws FileNotFoundException, IOException  {
            ArrayList<Products> products = new ArrayList();
            
            FileReader reader = new FileReader(location);
            //Products[] userArray = gson.fromJson(new FileReader(location), Products[].class);
            Products[] userArray = gson.fromJson(reader, Products[].class);
            for (Products products1 : userArray) {
                products.add(products1);
            }
            reader.close();
            return products;
            }

        
}
