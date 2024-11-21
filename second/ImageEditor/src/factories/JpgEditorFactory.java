package factories;

import models.ImageEditor;
import models.JpgEditor;


public class JpgEditorFactory implements ImageEditorFactory {
    @Override
    public ImageEditor createEditor() {
        return new JpgEditor();
    }
}
