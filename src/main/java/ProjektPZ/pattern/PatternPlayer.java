package ProjektPZ.pattern;

import ProjektPZ.App;
import ProjektPZ.AppState;
import ProjektPZ.mouse.MyMouseEvent;
import javafx.scene.input.MouseButton;

import java.awt.*;


public class PatternPlayer {

    private Robot robot;
    private Pattern pattern;
    private int i = 0;

    public PatternPlayer() throws AWTException {
        this.robot = new Robot();
    }


    private void moveMouse(dMousePosition point) {
        robot.delay((int) point.getDt());
        robot.mouseMove((int) (MouseInfo.getPointerInfo().getLocation().getX() - point.getDx()), (int) (MouseInfo.getPointerInfo().getLocation().getY() - point.getDy()));
    }

    public void playPattern(MyMouseEvent event) {
        if (App.getState() == AppState.PLAY && event.getMouseButton() == MouseButton.PRIMARY && i < pattern.getDMousePositions().size()) {
            dMousePosition point = pattern.getDMousePositions().get(i++);
            moveMouse(point);
        }
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        if (pattern == null)
            throw new NullPointerException("There is no pattern to save!");
        else
            return pattern;
    }

    public void setI(int i) {
        this.i = i;
    }
}
