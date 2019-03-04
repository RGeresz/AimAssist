package ProjektPZ.keyboard;

import lc.kra.system.keyboard.GlobalKeyboardHook;

public class KeyHook extends GlobalKeyboardHook {

    private KeyAdapter keyAdapter;

    public KeyHook() {
        keyAdapter = new KeyAdapter();
        this.addKeyListener(keyAdapter);
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    public void shutdownHook() {
        super.shutdownHook();
    }
}
