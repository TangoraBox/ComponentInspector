package com.tangorabox.componentinspector.hybrid.swingwithjavafx;

import com.tangorabox.componentinspector.swing.SwingComponentInspectorHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingExampleWithJavaFXLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.getContentPane().add(new SwingWindowWithJavaFXPanel(), BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            SwingComponentInspectorHandler.handleAll();
        });
    }
}
