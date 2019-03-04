package ProjektPZ.pattern;

import ProjektPZ.App;
import ProjektPZ.AppState;
import ProjektPZ.mouse.MyMouseEvent;
import javafx.scene.input.MouseButton;

import java.util.LinkedList;

public class PatternRecorder {


    private long prevTime;
    private LinkedList<dMousePosition> mousePositions;
    private MyMouseEvent prev;


    public PatternRecorder() {
        mousePositions = new LinkedList<>();
        prevTime = System.currentTimeMillis();
    }


    public void captureMouse(MyMouseEvent event) {
        if (App.getState() == AppState.RECORD && event.getMouseButton() == MouseButton.PRIMARY) {
            if (mousePositions.size() == 0) {
                mousePositions.add(new dMousePosition(0, 0, 0));
            } else {
                mousePositions.add(new dMousePosition(prev.getX() - event.getX(),
                        prev.getY() - event.getY(), (int) System.currentTimeMillis() - prevTime));
            }
            prev = event;
            prevTime = (int) System.currentTimeMillis();
        }
    }

    public Pattern getPattern() {
        return new Pattern("temp", new LinkedList<>(Pattern.optimize(mousePositions)));
    }


    public void clearPattern() {
        mousePositions.clear();
    }
}
