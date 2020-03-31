package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * Used for uploading an .JPG pictures.
 * We take 0-multiple pictures and read and save them to the filepath
 * property file. Once that is done we tell the user if the upload went 
 * successfully or if an error has occured.
 * 
 * @author Frederik Braagaard - Bringordie
 */
@WebServlet(name = "UploadImage", urlPatterns = {"/UploadImage"})
public class UploadImage extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        boolean isMultipart = ServletFileUpload.isMultipartContent(
                request);
        System.out.println("request: " + request);
        if (!isMultipart) {
            System.out.println("File Not Uploaded");
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;

            try {
                items = upload.parseRequest(request);
                System.out.println("items: " + items);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    System.out.println("name: " + name);
                    String value = item.getString();
                    System.out.println("value: " + value);
                } else {
                    try {

                        InputStream sa = UploadFiles.class.getResourceAsStream("/filepath.properties");

                        // load the properties file
                        Properties properties = new Properties();
                        properties.load(sa);
                        // assign properties parameters
                        String path = properties.getProperty("picturepath");

                        String fileNameAndPath = path + item.getName();

                        File savedFile = new File(fileNameAndPath);
                        item.write(savedFile);
                        

                    } catch (Exception ex) {
                        Logger.getLogger(UploadImage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            out.println("<html>");
                        out.println("<body>");
                        out.println("<h1>Pictures has been uploaded<h1>");
                        out.println("</body>");
        }
    }
}
