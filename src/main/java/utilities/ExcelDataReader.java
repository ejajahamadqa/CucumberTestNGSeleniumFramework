package utilities;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataReader {

    public static void main(String[] args) {
        System.out.println("++++++++++++++++++++");
        for(int i=0; i<readExcelData().size(); i++) {
            System.out.println(readExcelData().get(i).toString());
        }
    }

    public static List<String> readExcelData() {

        String stringValue = "";
        String excelFilePath = "/Users/ejajahamad/Downloads/ConvertedExcel.xlsx";
        List<String> list = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            // Create a workbook instance for the Excel file
            Workbook workbook = new XSSFWorkbook(inputStream);

            // Get the first sheet from the workbook (index 0)
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through the rows and cells to read data
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Check the cell type and process accordingly
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            stringValue = cell.getStringCellValue();
                            list.add(stringValue);
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            double numValue = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            boolean boolValue = cell.getBooleanCellValue();
                            break;
                        case BLANK:
                            //System.out.print("Blank Cell\t");
                            break;
                        default:
                            // System.out.print("Unknown Cell Type\t");
                    }
                }
                System.out.println(); // Start a new line for each row
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
