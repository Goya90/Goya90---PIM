package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * Used for reading an excel sheet.
 * This specific reader was choosen because it can understand if an row is empty.
 * It has pre-determined row of lines on line 39. It then reads through them in 
 * extractInfoFromCell where it extracts the data where it returns a product
 * object that is then put into a Products arraylist. Once all is read it 
 * closes the file again.
 *
 * @author Bringordie - Frederik Braagaard
 */
public class ExcelHandler {


    public ArrayList<Products> extractInfo(String fileName) throws IOException {
        ArrayList<Products> productList = new ArrayList<>();
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(new FileInputStream(new File(fileName)));
            Sheet sheet = wb.getSheetAt(0);
            boolean skipHeader = true;
            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                ArrayList<Cell> cells = new ArrayList<Cell>();
                int lastColumn = Math.max(row.getLastCellNum(), 10);
                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                    cells.add(c);
                }
                Products product = extractInfoFromCell(cells);
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return productList;
    }

    private Products extractInfoFromCell(ArrayList<Cell> cells) {
        
       int id = 0;
       String productname ="";
       String productnamedescription ="";
       String productdescription =""; 
       String companyname = "";
       double price = 0;
       int quantity = 0;
       String picturename = "";
       String minorcategory = "empty";
       String maincategory = "empty";
               
        Cell idCell = cells.get(0);
        if (idCell != null) {
            idCell.setCellType(Cell.CELL_TYPE_STRING);
            id = Integer.parseInt(idCell.getStringCellValue()); 
        }
        Cell productnameCell = cells.get(1);
        if (productnameCell != null) {
            productnameCell.setCellType(Cell.CELL_TYPE_STRING);
            productname = productnameCell.getStringCellValue();
        }
        Cell productnamedescCell = cells.get(2);
        if (productnamedescCell != null) {
            productnamedescCell.setCellType(Cell.CELL_TYPE_STRING);
            productnamedescription = productnamedescCell.getStringCellValue();
        }
        Cell productdescCell = cells.get(3);
        if (productdescCell != null) {
            productdescCell.setCellType(Cell.CELL_TYPE_STRING);
            productdescription = productdescCell.getStringCellValue();
        }
        Cell companynameCell = cells.get(4);
        if (companynameCell != null) {
            companynameCell.setCellType(Cell.CELL_TYPE_STRING);
            companyname = companynameCell.getStringCellValue();
        }
        Cell priceCell = cells.get(5);
        if (priceCell != null) {
            priceCell.setCellType(Cell.CELL_TYPE_STRING);
            price = Double.parseDouble(priceCell.getStringCellValue()); 
        }
        Cell quantityCell = cells.get(6);
        if (quantityCell != null) {
            quantityCell.setCellType(Cell.CELL_TYPE_STRING);
            quantity = Integer.parseInt(quantityCell.getStringCellValue()); 
        }
        Cell picturenameCell = cells.get(7);
        if (picturenameCell != null) {
            picturenameCell.setCellType(Cell.CELL_TYPE_STRING);
            picturename = picturenameCell.getStringCellValue();
        }
        Cell minorcategoryCell = cells.get(8);
        if (minorcategoryCell != null) {
            minorcategoryCell.setCellType(Cell.CELL_TYPE_STRING);
            minorcategory = minorcategoryCell.getStringCellValue();
        }
        Cell maincategoryCell = cells.get(9);
        if (maincategoryCell != null) {
            maincategoryCell.setCellType(Cell.CELL_TYPE_STRING);
            maincategory = maincategoryCell.getStringCellValue();
        }
        Products product = new Products(id, productname, productnamedescription, productdescription, companyname, price, quantity, picturename, true ,minorcategory, maincategory);
        return product;
    }
}