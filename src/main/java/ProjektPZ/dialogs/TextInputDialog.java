package ProjektPZ.dialogs;

public class TextInputDialog extends javafx.scene.control.TextInputDialog {

    public TextInputDialog(String title, String msg) {
        this.setTitle(title);
        this.setHeaderText(null);
        this.setContentText(msg);
    }
}
