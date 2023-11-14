package utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PdfToJsonConverter {

    public static void main(String[] args) {
        String pdfFilePath = "/Users/ejajahamad/Downloads/ReadPDFStream.ashx.pdf";
        String jsonFilePath = "/Users/ejajahamad/Downloads/ConvertedJson.json";

        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);

            // Perform any necessary text processing here to format it as JSON
            String jsonContent = convertTextToJson(text);

            // Write the JSON content to a file
            FileWriter jsonWriter = new FileWriter(jsonFilePath);
            jsonWriter.write(jsonContent);
            jsonWriter.close();

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertTextToJson(String text) {
        // You can implement your own logic to convert the extracted text to JSON
        // For example, you can create a JSON object or array and populate it based on the extracted text.
        // Here, we'll just wrap the text in a simple JSON object.

        return "{\"content\": \"" + text + "\"}";
    }
}
