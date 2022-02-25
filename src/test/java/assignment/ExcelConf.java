package assignment;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelConf {
    //Making Global Variable for our workbook//
    XSSFWorkbook wb;
    XSSFSheet sheet1;

    //Accepting the Excel path through parameter and loading the Excel file//
    public ExcelConf(String excelpath) {
        try {
            File src = new File(excelpath);
            FileInputStream demofile = new FileInputStream(src);

            wb = new XSSFWorkbook(demofile);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Fetching data from Excel file and reading number of rows,column in Excel file//
    public String getData(int sheetNumber, int row, int column) {
        sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(row).getCell(column).getStringCellValue();
        return data;
    }
}
