package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.JsonHandler;
import logic.ExcelHandler;
import logic.Products;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistence.DBFacade;
import java.nio.file.Paths;

/**
 *
 * Used for uploading an .xlsx or .json document.
 * First we read the file to see that it isn't too big, then we read the file
 * with a factory and upload it to a ServletFileUpload.
 * Once that is done we read the attributes of the file. And save the file
 * in the resource package filepath. We then call uploadToDB to upload the file
 * before we delete the temp file again, and redirect the user to a upload
 * page that says if the upload went good or bad.
 * 
 * @author Frederik Braagaard - Bringordie
 */
@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
public class UploadFiles extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    @Override
    public void init() {
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException, FileNotFoundException {

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();

        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);

                    // load the properties file
                    InputStream sa = UploadFiles.class.getResourceAsStream("/filepath.properties");
                    Properties properties = new Properties();
                    properties.load(sa);
                    // assign properties parameters
                    String path = properties.getProperty("filepath");

                    String fileNametest = path + fi.getName();
                    uploadToDB(fileNametest);
                    Files.deleteIfExists(Paths.get(path+fi.getName())); 
                    
                    
                    out.println("Uploaded Filename: " + fileName + " has been uploaded to the server" + "<br>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Something went wrong. Your file did not match the criterias."+"<br>");
            out.println("</body>");
            out.println("</html>");
            System.out.println(ex);
        }
    }

    public void uploadToDB(String fileNametest) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
        DBFacade db = new DBFacade();
        if (fileNametest.contains(".json")) {
            JsonHandler handler = new JsonHandler();
            ArrayList<Products> s = handler.makeJSonFileIntoArray(fileNametest);
            db.jsonInsertOrUpdateToDB(s, "/db.properties");
        } else if (fileNametest.contains(".xlsx") || fileNametest.contains(".xml")) {
            ExcelHandler excelhandler = new ExcelHandler();
            ArrayList<Products> products = new ArrayList();
            products = excelhandler.extractInfo(fileNametest);
            db.excelInsertOrUpdateToDB(products, "/db.properties");
        }
    }

}
