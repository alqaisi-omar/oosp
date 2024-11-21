package models;


public class JpgEditor implements ImageEditor {
    @Override
    public void open(String filePath) {
        System.out.println("Opening JPG file: " + filePath);
    }

    @Override
    public void crop() {
        System.out.println("Cropping JPG file");
    }

    @Override
    public void resize(double scale) {
        System.out.println("Resizing JPG file by scale: " + scale);
    }

    @Override
    public void applyFilter(String filterName) {
        System.out.println("Applying filter to JPG: " + filterName);
    }

    @Override
    public void save(String outputPath) {
        System.out.println("Saving JPG to: " + outputPath);
    }
}
