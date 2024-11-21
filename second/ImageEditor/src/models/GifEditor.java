package models;

public class GifEditor implements ImageEditor {
    @Override
    public void open(String filePath) {
        System.out.println("Opening GIF file: " + filePath);
    }

    @Override
    public void crop() {
        System.out.println("Cropping GIF file");
    }

    @Override
    public void resize(double scale) {
        System.out.println("Resizing GIF file by scale: " + scale);
    }

    @Override
    public void applyFilter(String filterName) {
        System.out.println("Applying filter to GIF: " + filterName);
    }

    @Override
    public void save(String outputPath) {
        System.out.println("Saving GIF to: " + outputPath);
    }
}
