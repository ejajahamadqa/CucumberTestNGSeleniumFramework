package utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfToExcelConverter {

    public static void pdfToExcelConverter() {
    //public static void main(String args[]) {
        String pdfFilePath = "/Users/ejajahamad/Downloads/ReadPDFStream.pdf";
        //String jsonFilePath = "/Users/ejajahamad/Downloads/ConvertedJson.json";
        String excelFilePath = "/Users/ejajahamad/Downloads/ConvertedExcel.xlsx";

        try {
            // Load the PDF document
            PDDocument document = PDDocument.load(new File(pdfFilePath));

            // Create an Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("PDF Data");

            // Extract text from the PDF
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);
            String[] lines = text.split(System.getProperty("line.separator"));

            // Create rows and cells in the Excel sheet
            int rowNum = 0;
            for (String line : lines) {
                Row row = sheet.createRow(rowNum++);
                String[] cells = line.split("\t"); // Split by tabs, adjust as needed

                for (int i = 0; i < cells.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(cells[i]);
                }
            }

            // Write the Excel workbook to a file
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
               // System.out.println(" "+ workbook.write(outputStream););
            }

            document.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
