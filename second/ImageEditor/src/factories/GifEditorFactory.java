package factories;

import models.GifEditor;
import models.ImageEditor;


public class GifEditorFactory implements ImageEditorFactory {
    @Override
    public ImageEditor createEditor() {
        return new GifEditor();
    }
}
