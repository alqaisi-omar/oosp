package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageEditor {
    private BufferedImage currentImage;
    private final ImageHistory history = new ImageHistory();
    private final List<ImageSnapshot> snapshots = new ArrayList<>();

    public ImageEditor(BufferedImage initialImage) {
        this.currentImage = initialImage;
        snapshots.add(new ImageSnapshot(initialImage)); 
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public void applyChange(BufferedImage newImage) {
        history.saveSnapshot(new ImageSnapshot(currentImage));
        snapshots.add(new ImageSnapshot(newImage));
        currentImage = newImage;
    }

    public void undo() {
        ImageSnapshot snapshot = history.undo();
        if (snapshot != null) {
            currentImage = snapshot.getImageState();
        }
    }

    public void redo() {
        ImageSnapshot snapshot = history.redo();
        if (snapshot != null) {
            currentImage = snapshot.getImageState();
        }
    }

    public void restoreTo(int index) {
        if (index >= 0 && index < snapshots.size()) {
            currentImage = snapshots.get(index).getImageState();
        }
    }

    public boolean canUndo() {
        return history.canUndo();
    }

    public boolean canRedo() {
        return history.canRedo();
    }

    public List<ImageSnapshot> getHistory() {
        return snapshots;
    }
}
