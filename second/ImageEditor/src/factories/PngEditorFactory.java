package factories;

import models.ImageEditor;
import models.PngEditor;


public class PngEditorFactory implements ImageEditorFactory {
    @Override
    public ImageEditor createEditor() {
        return new PngEditor();
    }
}
