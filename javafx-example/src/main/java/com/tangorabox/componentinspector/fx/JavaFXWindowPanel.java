package com.tangorabox.componentinspector.fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.UncheckedIOException;

public class JavaFXWindowPanel extends BorderPane {

    @FXML
    private HBox panelUp;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private HBox panelDown;

    @FXML
    private TitledPane titledPane;

    @FXML
    private VBox panelLeft;

    @FXML
    private VBox panelRight;

    public JavaFXWindowPanel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JavaFxWindow.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            comboBox.getItems().setAll("One", "Two", "Tree");
            comboBox.getSelectionModel().selectFirst();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
