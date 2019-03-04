package ProjektPZ.mouse;

import ProjektPZ.*;
import ProjektPZ.pattern.Pattern;
import ProjektPZ.pattern.PatternPlayer;
import ProjektPZ.pattern.PatternRecorder;
import ProjektPZ.utils.PlatformUtil;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;

import java.awt.*;

public class MouseEventHandler implements EventHandler<MyMouseEvent> {

    private PatternRecorder patternRecorder;
    private PatternPlayer patternPlayer;

    public MouseEventHandler() throws AWTException {
        patternRecorder = new PatternRecorder();
        patternPlayer = new PatternPlayer();

    }

    @Override
    public void handle(MyMouseEvent event) {
        if (event.getEventType() == MyMouseEvent.MOUSE_PRESSED) {
            patternPlayer.playPattern(event);
            patternRecorder.captureMouse(event);
        }

        if (event.getEventType() == MyMouseEvent.MOUSE_RELEASED) {
            if (event.getMouseButton() == MouseButton.PRIMARY) {
                if (App.state == AppState.RECORD) {
                    setPattern(patternRecorder.getPattern());
                    patternRecorder.clearPattern();
                    App.setState(AppState.PLAY);
                }
                if (App.state == AppState.PLAY)
                    patternPlayer.setI(0);
            }
        }

        if (event.getEventType() == MyMouseEvent.MOUSE_DRAGGED) {
            patternRecorder.captureMouse(event);
            patternPlayer.playPattern(event);
        }

    }

    public Pattern getPattern(){
        return patternPlayer.getPattern();
    }

    public void setPattern(Pattern pattern) {
        patternPlayer.setPattern(pattern);
        PlatformUtil.run(() -> App.menuController.setLoadedPatternLabel(pattern.getWeaponName()));
    }

    public void clearPattern() {
        patternRecorder.clearPattern();
    }
}
