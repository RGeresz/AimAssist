package ProjektPZ.dialogs;

import javafx.scene.control.Alert;

public class ErrorDialog extends Alert {

    public ErrorDialog(String msg) {
        super(Alert.AlertType.ERROR);
        this.setTitle("Error");
        this.setHeaderText(null);
        this.setContentText(msg);
        this.showAndWait();
    }

}
