package logic;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import presentation.UploadFiles;

/**
 *
 * This is used for checking/reading a .jpg picture.
 * When we have to display a picture we need to convert it so that we can
 * see a picture on the website. We take a picture name and then check the 
 * filepath folder if it exists. If it exists we wrap it around in a 
 * returnpath and if it doesn't exist we give a returnpath back with a picture
 * of a missing picture.
 * 
 * @author Bringordie - Frederik Braagaard
 */
public class FileReaderLogic {

    public String FileCheck(String path) throws Exception {
        //Loading property file
        InputStream sa = UploadFiles.class.getResourceAsStream("/filepath.properties");
        Properties properties = new Properties();
        properties.load(sa);
        // assign properties parameters
        String filepath = properties.getProperty("picturepath");
        String fullpath = filepath + path + ".jpg";
        String returnpath = "";

        File file = new File(fullpath);
        if (file.exists()) {
            returnpath = "http://localhost:8080/pictures/" + path + ".jpg";
        } else {
            returnpath = "http://localhost:8080/pictures/noimage.jpg";
        }
        return returnpath;

    }
}
