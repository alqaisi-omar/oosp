package adapters;

import java.io.*;
import java.util.*;

public class SimpleJsonAdapter implements FileAdapter {

    @Override
    public List<Map<String, String>> read(File file) throws Exception {
        List<Map<String, String>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line.trim());
            }
            
            String data = json.toString().replaceAll("[\\[\\]{}\"]", "");
            String[] records = data.split("},\\{");
            for (String record : records) {
                Map<String, String> row = new HashMap<>();
                String[] fields = record.split(",");
                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    row.put(keyValue[0].trim(), keyValue[1].trim());
                }
                result.add(row);
            }
        }
        return result;
    }

    @Override
    public void write(File file, List<Map<String, String>> data) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("[\n");
            for (int i = 0; i < data.size(); i++) {
                Map<String, String> row = data.get(i);
                writer.write("  {");
                int j = 0;
                for (Map.Entry<String, String> entry : row.entrySet()) {
                    writer.write("\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                    if (++j < row.size()) writer.write(", ");
                }
                writer.write("}");
                if (i < data.size() - 1) writer.write(",");
                writer.newLine();
            }
            writer.write("]");
        }
    }
}
