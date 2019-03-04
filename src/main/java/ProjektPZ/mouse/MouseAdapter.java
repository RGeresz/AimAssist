package ProjektPZ.mouse;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;


public class MouseAdapter extends Node implements GlobalMouseListener {


    @Override
    public void mousePressed(GlobalMouseEvent globalMouseEvent) {
        this.fireEvent(new MyMouseEvent(MyMouseEvent.MOUSE_PRESSED, globalMouseEvent.getX(), globalMouseEvent.getY(), globalMouseEvent.getButton()));
    }

    @Override
    public void mouseReleased(GlobalMouseEvent globalMouseEvent) {
        this.fireEvent(new MyMouseEvent(MyMouseEvent.MOUSE_RELEASED, globalMouseEvent.getX(), globalMouseEvent.getY(), globalMouseEvent.getButton()));
    }

    @Override
    public void mouseMoved(GlobalMouseEvent globalMouseEvent) {
        if (globalMouseEvent.getButtons() == 1 || globalMouseEvent.getButtons() == 2 || globalMouseEvent.getButtons() == 16) {
            this.fireEvent(new MyMouseEvent(MyMouseEvent.MOUSE_DRAGGED, globalMouseEvent.getX(), globalMouseEvent.getY(), globalMouseEvent.getButtons()));
        } else {
            this.fireEvent(new MyMouseEvent(MyMouseEvent.MOUSE_MOVED, globalMouseEvent.getX(), globalMouseEvent.getY(), globalMouseEvent.getButtons()));
        }
    }

    @Override
    public void mouseWheel(GlobalMouseEvent globalMouseEvent) {

    }


    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }
}
