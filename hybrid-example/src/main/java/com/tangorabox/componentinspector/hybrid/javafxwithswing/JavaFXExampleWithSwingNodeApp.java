package com.tangorabox.componentinspector.hybrid.javafxwithswing;

import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXExampleWithSwingNodeApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        JavaFXWindowWithSwingPanel windowPanel = new JavaFXWindowWithSwingPanel();
        primaryStage.setScene(new Scene(windowPanel));
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
        FXComponentInspectorHandler.handleAll();
    }
}
