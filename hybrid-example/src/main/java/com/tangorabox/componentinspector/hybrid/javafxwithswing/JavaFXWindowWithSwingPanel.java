package com.tangorabox.componentinspector.hybrid.javafxwithswing;

import com.tangorabox.componentinspector.swing.SwingComponentInspectorHandler;
import com.tangorabox.componentinspector.swing.SwingWindowPanel;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.io.UncheckedIOException;

public class JavaFXWindowWithSwingPanel extends BorderPane {

    @FXML
    private SwingNode swingNode;

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

    public JavaFXWindowWithSwingPanel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JavaFxWindow.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            comboBox.getItems().setAll("One", "Two", "Tree");
            comboBox.getSelectionModel().selectFirst();
            createSwingContent();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private void createSwingContent() {
        SwingUtilities.invokeLater(() -> {
            SwingWindowPanel swingWindowPanel = new SwingWindowPanel();
            swingNode.setContent(swingWindowPanel);
            SwingComponentInspectorHandler.handle(swingWindowPanel);
        });
    }
}
