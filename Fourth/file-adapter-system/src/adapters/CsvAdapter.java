package adapters;

import java.io.*;
import java.util.*;

public class CsvAdapter implements FileAdapter {

   
    @Override
    public List<Map<String, String>> read(File file) throws Exception {
        List<Map<String, String>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String headerLine = reader.readLine();
            if (headerLine == null) return result;

            
            String[] headers = headerLine.split(",");

            String line;
           
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], values[i]);
                }
                result.add(row);
            }
        }
        return result;
    }

    
    @Override
    public void write(File file, List<Map<String, String>> data) throws Exception {
        if (data.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            
            Set<String> headersSet = data.get(0).keySet();
            String headers = String.join(",", headersSet);  
            writer.write(headers);  
            writer.newLine();

            
            for (Map<String, String> row : data) {
                List<String> values = new ArrayList<>();
                for (String header : headersSet) {
                    values.add(row.getOrDefault(header, ""));
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }
        }
    }
}
