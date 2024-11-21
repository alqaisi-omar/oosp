package model;

import java.util.Stack;

public class ImageHistory {
    private final Stack<ImageSnapshot> undoStack = new Stack<>();
    private final Stack<ImageSnapshot> redoStack = new Stack<>();

    public void saveSnapshot(ImageSnapshot snapshot) {
        undoStack.push(snapshot);
        redoStack.clear(); 
    }

    public ImageSnapshot undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.peek());
            return undoStack.pop();
        }
        return null;
    }

    public ImageSnapshot redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.peek());
            return redoStack.pop();
        }
        return null;
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}
