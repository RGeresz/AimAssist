package ProjektPZ.mouse;

import ProjektPZ.mouse.MouseAdapter;
import lc.kra.system.mouse.GlobalMouseHook;

public class MouseHook extends GlobalMouseHook {
    private MouseAdapter mouseAdapter;


    public MouseHook() {
        super();
        this.mouseAdapter = new MouseAdapter();
        this.addMouseListener(mouseAdapter);
    }


    public void shutdownHook() {
        super.shutdownHook();
    }

    public MouseAdapter getMouseAdapter() {
        return mouseAdapter;
    }
}
