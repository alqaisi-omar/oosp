package adapters;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileAdapter {
    List<Map<String, String>> read(File file) throws Exception;
    void write(File file, List<Map<String, String>> data) throws Exception;
}
