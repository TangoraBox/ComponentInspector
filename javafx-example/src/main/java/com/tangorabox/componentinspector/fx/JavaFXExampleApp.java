package com.tangorabox.componentinspector.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXExampleApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFXWindowPanel windowPanel = new JavaFXWindowPanel();
        primaryStage.setScene(new Scene(windowPanel));
        primaryStage.sizeToScene();
        primaryStage.show();
        FXComponentInspectorHandler.handleAll();
    }
}
