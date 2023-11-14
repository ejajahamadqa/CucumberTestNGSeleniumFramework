package utilities;

import java.io.File;

public class DeleteFiles {

    public static void deletePDFFile() {
        String pdfFilePath = "/Users/ejajahamad/Downloads/ReadPDFStream.pdf"; // Replace with the path to your PDF file
        File pdfFile = new File(pdfFilePath);
        try {
        if (pdfFile.exists()) {
            boolean deleted = pdfFile.delete();
            if (deleted) {
                System.out.println("PDF file deleted successfully.");
            } else {
                System.err.println("Failed to delete the PDF file.");
            }
        } else {
            System.err.println("PDF file not found.");
        }

    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteExcelFile() {
        String excelFilePath = "/Users/ejajahamad/Downloads/ConvertedExcel.xlsx"; // Replace with the path to your Excel file
        File excelFile = new File(excelFilePath);
        try {
            if (excelFile.exists()) {
                boolean deleted = excelFile.delete();
                if (deleted) {
                    System.out.println("Excel file deleted successfully.");
                } else {
                    System.err.println("Failed to delete the Excel file.");
                }
            } else {
                System.err.println("Excel file not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
