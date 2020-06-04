package com.tangorabox.componentinspector.hybrid.swingwithjavafx;

import com.tangorabox.componentinspector.fx.FXComponentInspectorHandler;
import com.tangorabox.componentinspector.fx.JavaFXWindowPanel;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;

public class SwingWindowWithJavaFXPanel extends JPanel {

    private final JFXPanel javaFXPanel = new JFXPanel();

    private final Component panelUp = createUpPanel();
    private final Component panelDown = createPanelDown();
    private final Component panelLeft = createPanelLeft();
    private final Component panelRight = createPanelRight();
    private final Component panelCenter = createPanelCenter();


    public SwingWindowWithJavaFXPanel() {
        setLayout(new BorderLayout());
        add(panelUp, BorderLayout.NORTH);
        add(panelDown, BorderLayout.SOUTH);
        add(panelLeft, BorderLayout.WEST);
        add(panelRight, BorderLayout.EAST);
        add(panelCenter, BorderLayout.CENTER);
        createJavaFXContent();
    }

    private void createJavaFXContent() {
        Platform.runLater(() -> {
            // This method is invoked on the JavaFX thread
            JavaFXWindowPanel javaFXWindowPanel = new JavaFXWindowPanel();
            Scene scene = new Scene(javaFXWindowPanel);
            javaFXPanel.setScene(scene);
            FXComponentInspectorHandler.handle(javaFXWindowPanel);
        });
    }

    private Component createUpPanel() {
        return new JComboBox<String>(new String[]{"One", "Two", "Tree"});
    }

    private Component createPanelDown() {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.CENTER));
        result.setBorder(BorderFactory.createTitledBorder("South Panel"));
        int buttonCount = 4;
        for (int i = 1; i <= buttonCount; i++) {
            JButton button = new JButton("Button" + i);
            result.add(button);
        }
        return result;
    }

    private Component createPanelLeft() {
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JButton("Left Panel"), BorderLayout.CENTER);
        return result;
    }

    private Component createPanelRight() {
        JPanel result = new JPanel(new BorderLayout());
        result.add(new JButton("Right Panel"), BorderLayout.CENTER);
        return result;
    }

    private Component createPanelCenter() {
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(BorderFactory.createTitledBorder("ParentPanel"));

        int panelCount = 4;

        JPanel targetPanel = result;

        for (int i = 1; i <= panelCount; i++) {
            JPanel child = new JPanel(new BorderLayout());
            child.setBorder(BorderFactory.createTitledBorder("ChildPanel" + i));
            targetPanel.add(child);
            targetPanel = child;
        }

        JPanel helloWorldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        helloWorldPanel.add(javaFXPanel);
        targetPanel.add(helloWorldPanel, BorderLayout.CENTER);

        return result;
    }


}
