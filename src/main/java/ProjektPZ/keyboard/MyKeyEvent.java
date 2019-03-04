package ProjektPZ.keyboard;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class MyKeyEvent extends Event {

    public static final EventType<MyKeyEvent> ANY =
            new EventType<>("KEY");
    public static final EventType<MyKeyEvent> KEY_PRESSED =
            new EventType<>(MyKeyEvent.ANY, "KEY_PRESSED");
    public static final EventType<MyKeyEvent> KEY_RELEASED =
            new EventType<>(MyKeyEvent.ANY, "KEY_RELEASED");

    private int keyCode;
    public MyKeyEvent(EventType<? extends Event> eventType, int keyCode) {
        super(eventType);
        this.keyCode = keyCode;
    }

    public MyKeyEvent(Object source, EventTarget target, EventType<? extends Event> eventType, int keyCode) {
        super(source, target, eventType);
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
