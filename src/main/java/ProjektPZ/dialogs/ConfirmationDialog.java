package ProjektPZ.dialogs;

import javafx.scene.control.Alert;

public class ConfirmationDialog extends Alert {

    public ConfirmationDialog(String msg) {
        super(AlertType.CONFIRMATION);
        this.setTitle("Confirmation");
        this.setHeaderText(null);
        this.setContentText(msg);
    }
}
