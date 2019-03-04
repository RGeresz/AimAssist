package ProjektPZ.mouse;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;

public class MyMouseEvent extends Event {

    public static final EventType<MyMouseEvent> ANY =
            new EventType<>("MOUSE");

    public static final EventType<MyMouseEvent> MOUSE_PRESSED =
            new EventType<>(MyMouseEvent.ANY, "MOUSE_PRESSED");

    public static final EventType<MyMouseEvent> MOUSE_RELEASED =
            new EventType<>(MyMouseEvent.ANY, "MOUSE_RELEASED");

    public static final EventType<MyMouseEvent> MOUSE_MOVED =
            new EventType<>(MyMouseEvent.ANY, "MOUSE_MOVED");

    public static final EventType<MyMouseEvent> MOUSE_DRAGGED =
            new EventType<>(MyMouseEvent.ANY, "MOUSE_DRAGGED");

    private int x;
    private int y;
    private MouseButton mouseButton;

    public MyMouseEvent(EventType<? extends Event> eventType, int x, int y, int mouseButton) {
        super(eventType);
        this.x = x;
        this.y = y;
        switch (mouseButton) {
            case 0:
                this.mouseButton = MouseButton.NONE;
                break;
            case 1:
                this.mouseButton = MouseButton.PRIMARY;
                break;
            case 2:
                this.mouseButton = MouseButton.SECONDARY;
                break;
            case 16:
                this.mouseButton = MouseButton.MIDDLE;
                break;
            default:
                this.mouseButton = MouseButton.NONE;
                break;
        }

    }

    public MyMouseEvent(Object source, EventTarget target, EventType<? extends Event> eventType, int x, int y, int mouseButton) {
        super(source, target, eventType);
        this.x = x;
        this.y = y;
        switch (mouseButton) {
            case 0:
                this.mouseButton = MouseButton.NONE;
                break;
            case 1:
                this.mouseButton = MouseButton.PRIMARY;
                break;
            case 2:
                this.mouseButton = MouseButton.SECONDARY;
                break;
            case 16:
                this.mouseButton = MouseButton.MIDDLE;
                break;
            default:
                this.mouseButton = MouseButton.NONE;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }


    @Override
    public String toString() {
        return "MyMouseEvent{" +
                "x=" + x +
                ", y=" + y +
                ", mouseButton=" + mouseButton +
                ", eventType=" + eventType +
                ", target=" + target +
                ", source=" + source +
                '}';
    }

}
