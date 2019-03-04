package ProjektPZ.dialogs;

import javafx.scene.control.Alert;

public class InfoDialog extends Alert {
    public InfoDialog(String title, String msg) {
        super(AlertType.INFORMATION);
        this.setTitle(title);
        this.setHeaderText(null);
        this.setContentText(msg);
        this.showAndWait();
    }
}
