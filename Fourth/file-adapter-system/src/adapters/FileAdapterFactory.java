package adapters;

public class FileAdapterFactory {
    public static FileAdapter getAdapter(String fileType) {
        return switch (fileType.toLowerCase()) {
            case "csv" -> new CsvAdapter();
            case "json" -> new SimpleJsonAdapter();
            case "xml" -> new XmlAdapter();
            default -> throw new IllegalArgumentException("Unsupported file type: " + fileType);
        };
    }
}
