/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesreadexcel;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 *
 * @author PC 07
 */
public class Tesreadexcel {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
//        
//        Tesreadexcel obj = new Tesreadexcel();
//        String no = obj.ReadExcel("Sheet1", 1, 0);
//        System.out.println("No = " + no);
//        System.out.println(no);

String filePath = "C:\\Users\\WIN11\\OneDrive\\Dokumen\\NetBeansProjects\\tesreadexcel\\src\\tesreadexcel\\tes-excel.xlsx";
        String sheetName = "Sheet1";

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                // Iterate through each row
                for (Row row : sheet) {
                    // Iterate through each cell in the row
                    for (Cell cell : row) {
                        // Check the cell type and read the value accordingly
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t\t");
                                break;
                            case BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t\t");
                                break;
                            case BLANK:
                                System.out.print("[BLANK]\t\t");
                                break;
                            default:
                                System.out.print("[UNKNOWN]\t\t");
                        }
                    }
                    System.out.println(); // Move to the next row
                }
            } else {
                System.out.println("Sheet not found: " + sheetName);
            }

            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        // reading workbook
//        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\Users\\PC 07\\Downloads"));
//        
//        // Reading Sheet
//        // Sheet1
//        XSSFSheet sh = wb.getSheet("Sheet1");
//        
//        String data = sh.getRow(1).getCell(0).toString();
//        System.out.println(data);    
    }
    
    public String ReadExcel(String sheetName, int rNum, int cNum){
        String data = "";
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\PC 07\\Downloads\\tes-excel.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(sheetName);
            Row r = s.getRow(rNum);
            Cell c = r.getCell(cNum);
            data = c.getStringCellValue();
            
        } catch (Exception ex) {
            System.out.println("Read Excel Catch Blok");
            ex.printStackTrace();
        }
        return data;
    }
}
