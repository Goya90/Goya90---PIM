package presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.JsonWriter;
 
/**
 *
 * Takes an JsonWriter that takes all of the products in DB via
 * "jsonwriter.DataBaseToJson("/db.properties");"
 * once that is done we save the file as a temp in the property file path.
 * When that is done we stream the file to the user, we get the relativePath
 * where the file should be downloaded in depending on what browser the user
 * uses.
 * We then get the mine type of the file TODO WRITE MORE
 * Once that is done we create a response and force the download of the file.
 * And when the file has been downloaded we delete the temp file again.
 * "Files.deleteIfExists(Paths.get(path+"Products.json"));"
 * 
 * @author Bringordie - Frederik Braagaard
 */
@WebServlet(name = "DownloadJson", urlPatterns = {"/DownloadJson"})
public class DownloadJsonFile extends HttpServlet {
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            // load the properties file
            InputStream sa = UploadFiles.class.getResourceAsStream("/filepath.properties");
            Properties properties = new Properties();
            properties.load(sa);
            // assign properties parameters
            String path = properties.getProperty("filepath");
        try {
            JsonWriter jsonwriter = new JsonWriter();
            jsonwriter.DataBaseToJson("/db.properties");
            
        String filePath = path+"Products.json";
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();  
        Files.deleteIfExists(Paths.get(path+"Products.json")); 
    } catch (SQLException ex) {
            Logger.getLogger(DownloadJsonFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DownloadJsonFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DownloadJsonFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}