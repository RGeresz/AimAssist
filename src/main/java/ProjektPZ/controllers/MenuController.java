package ProjektPZ.controllers;

import ProjektPZ.*;
import ProjektPZ.dialogs.ConfirmationDialog;
import ProjektPZ.dialogs.ErrorDialog;
import ProjektPZ.dialogs.InfoDialog;
import ProjektPZ.dialogs.TextInputDialog;
import ProjektPZ.keyboard.KeyEventHandler;
import ProjektPZ.keyboard.KeyHook;
import ProjektPZ.keyboard.MyKeyEvent;
import ProjektPZ.mouse.MouseEventHandler;
import ProjektPZ.mouse.MouseHook;
import ProjektPZ.mouse.MyMouseEvent;
import ProjektPZ.pattern.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.exception.ConstraintViolationException;

import java.io.File;
import java.util.Optional;


public class MenuController {

    private MouseHook mouseHook;
    private KeyHook keyHook;
    private MouseEventHandler mouseEventHandler;
    private Stage stage;
    private Database database;

    @FXML
    private Button exitButton;

    @FXML
    private Button loadPatternFromDatabaseButton;

    @FXML
    private Button loadPatternFromGIFButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button savePatternButton;

    @FXML
    private Label appState;

    @FXML
    private Label loadedPattern;

    @FXML
    private Button deletePatternButton;

    @FXML
    private void initialize() throws Exception {
        database = new Database();
        mouseEventHandler = new MouseEventHandler();
        keyHook = new KeyHook();
        keyHook.getKeyAdapter().addEventHandler(MyKeyEvent.ANY, new KeyEventHandler());
        mouseHook = new MouseHook();
        mouseHook.getMouseAdapter().addEventHandler(MyMouseEvent.ANY, mouseEventHandler);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        updateList();
    }

    @FXML
    void savePattern(ActionEvent event) {
        try {
            Pattern pattern = mouseEventHandler.getPattern();
            Optional<String> result = new TextInputDialog("Save pattern", "Write pattern name").showAndWait();
            if (result.isPresent()) {
                pattern.setWeaponName(result.get());
                database.savePattern(pattern);
                mouseEventHandler.setPattern(pattern);
                updateList();
                new InfoDialog("Success", "Pattern successfully saved to database!");
            }
        } catch (ConstraintViolationException e) {
            new ErrorDialog("Pattern names must be unique!");
        } catch (IllegalArgumentException e) {
            new ErrorDialog(e.getMessage());
        } catch (NullPointerException e) {
            new ErrorDialog(e.getMessage());
        }
    }

    @FXML
    void loadPatternFromDatabase(ActionEvent event) {

        try {
            mouseEventHandler.setPattern(database.loadPattern(getSelectedItemFromList()));
            new InfoDialog("Success", "Pattern successfully loaded from database!");
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    @FXML
    void loadPatternFromGIF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\Projekty\\ProjektPZ\\src\\main\\resources\\gifs"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                Pattern pattern = new Pattern(file.getName(), file.getAbsolutePath(), Integer.parseInt(new TextInputDialog("Load pattern", "Rate of fire (in rounds per minute)").showAndWait().get()));
                pattern.reverse();
                mouseEventHandler.setPattern(pattern);
                new InfoDialog("Success", "Pattern successfully loaded from GIF!");
            } catch (NumberFormatException e) {
                new ErrorDialog("Wrong input!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void deletePattern(ActionEvent event) {

        try {
            String patternToDelete = getSelectedItemFromList();
            if (new ConfirmationDialog("Do you really want to delete pattern called " + patternToDelete + "?").showAndWait().get() == ButtonType.OK) {
                database.deletePattern(patternToDelete);
                updateList();
                new InfoDialog("Success", "Pattern successfully deleted from database!");
            }
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
    }

    @FXML
    void exit(ActionEvent event) {
        close();
    }

    public void close() {

        mouseHook.shutdownHook();
        keyHook.shutdownHook();
        database.close();
        Platform.exit();
    }


    public void setAppStateLabel(AppState appState) {
        this.appState.setText(appState.name());
    }

    public void setLoadedPatternLabel(String pattern) {
        this.loadedPattern.setText(pattern);
    }

    private void updateList() {

        listView.setItems(FXCollections.observableList(database.updateList()));
    }

    private String getSelectedItemFromList() throws Exception {
        if (listView.getSelectionModel().getSelectedItems().get(0) == null)
            throw new Exception("Choose pattern from list!");
        else
            return listView.getSelectionModel().getSelectedItems().get(0);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

