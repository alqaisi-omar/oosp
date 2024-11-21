package models;


public class PngEditor implements ImageEditor {
    @Override
    public void open(String filePath) {
        System.out.println("Opening PNG file: " + filePath);
    }

    @Override
    public void crop() {
        System.out.println("Cropping PNG file");
    }

    @Override
    public void resize(double scale) {
        System.out.println("Resizing PNG file by scale: " + scale);
    }

    @Override
    public void applyFilter(String filterName) {
        System.out.println("Applying filter to PNG: " + filterName);
    }

    @Override
    public void save(String outputPath) {
        System.out.println("Saving PNG to: " + outputPath);
    }
}
