package com.tangorabox.componentinspector.swing;

import javax.swing.*;
import java.awt.*;

public class SwingWindowPanel extends JPanel {

    private final Component panelUp = createUpPanel();
    private final Component panelDown = createPanelDown();
    private final Component panelLeft = createPanelLeft();
    private final Component panelRight = createPanelRight();
    private final Component panelCenter = createPanelCenter();

    public SwingWindowPanel() {
        setLayout(new BorderLayout());
        add(panelUp, BorderLayout.NORTH);
        add(panelDown, BorderLayout.SOUTH);
        add(panelLeft, BorderLayout.WEST);
        add(panelRight, BorderLayout.EAST);
        add(panelCenter, BorderLayout.CENTER);
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
        helloWorldPanel.add(new JLabel("Hello World!!"));
        targetPanel.add(helloWorldPanel, BorderLayout.CENTER);

        return result;
    }
}
