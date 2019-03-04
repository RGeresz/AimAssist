package ProjektPZ.keyboard;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

public class KeyAdapter extends Node implements GlobalKeyListener {

    @Override
    public void keyPressed(GlobalKeyEvent event) {
        this.fireEvent(new MyKeyEvent(MyKeyEvent.KEY_PRESSED, event.getVirtualKeyCode()));
    }

    @Override
    public void keyReleased(GlobalKeyEvent event) {
        this.fireEvent(new MyKeyEvent(MyKeyEvent.KEY_RELEASED, event.getVirtualKeyCode()));
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
