package ProjektPZ.keyboard;

import ProjektPZ.App;
import ProjektPZ.AppState;
import ProjektPZ.utils.PlatformUtil;
import javafx.event.EventHandler;

public class KeyEventHandler implements EventHandler<MyKeyEvent> {
    @Override
    public void handle(MyKeyEvent event) {
        if (event.getEventType() == MyKeyEvent.KEY_PRESSED) {
            if (event.getKeyCode() == 112) { // F1
                App.setState(AppState.RECORD);
                PlatformUtil.run(() -> App.menuController.setLoadedPatternLabel("NONE"));
            }
            if (event.getKeyCode() == 113) { // F2
                App.setState(AppState.PLAY);
            }
            if (event.getKeyCode() == 114) { // F3
                App.setState(AppState.STOPPED);
            }
        }
    }
}
