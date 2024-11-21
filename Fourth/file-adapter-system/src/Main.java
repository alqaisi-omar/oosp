import adapters.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());  

    public static void main(String[] args) {
        try {
            String[] formats = {"csv", "json", "xml"};
            for (String format : formats) {
                File inputFile = new File("src/data/data." + format);
                if (!inputFile.exists()) {
                    System.out.println("The " + format + " file is not found: " + inputFile.getPath());
                    continue;
                }

                // Get the appropriate adapter for the file format
                FileAdapter adapter = FileAdapterFactory.getAdapter(format);

                // Read data from the file
                List<Map<String, String>> data = adapter.read(inputFile);
                System.out.println("Data read from " + format + ": " + data);

                // Write the data to a new output file
                File outputFile = new File("src/data/output." + format);
                adapter.write(outputFile, data);
                System.out.println("Data written to " + outputFile.getPath());
            }

            System.out.println("Files processed successfully!");
        } catch (Exception e) {
            // Using Logger to print errors
            logger.log(Level.SEVERE, "An error occurred while processing the files: " + e.getMessage(), e);  
            // Print error message
            System.err.println("An error occurred while processing the files: " + e.getMessage());
        }
    }
}
