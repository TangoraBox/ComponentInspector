package com.tangorabox.componentinspector.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collections;
import java.util.List;

public class SwingComponentInspectorHandler {

	public static final int OFFSET = 25;
	private final JDialog popup = new JDialog();
	private final JPanel pane = new JPanel(null);
	private final SwingComponentInspector componentInspector = new SwingComponentInspector();

	private static SwingComponentInspectorHandler instance = null;

	public static void handleAll() {
		getInstance().inspectAllWindows();
	}

	public static void handle(Component component) {
		getInstance().inspectComponent(component);
	}


	private static SwingComponentInspectorHandler getInstance() {
		if (instance == null) {
			instance = new SwingComponentInspectorHandler();
		}
		return instance;
	}


	private SwingComponentInspectorHandler() {
		popup.setAlwaysOnTop(true);
		popup.setUndecorated(true);
		popup.setBackground(new Color(0, 0, 0, 0));
		popup.getContentPane().add(pane, BorderLayout.CENTER);
		pane.setOpaque(false);
		popup.setFocusableWindowState(false);
	}


	private void inspectAllWindows() {
		long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK;
		Toolkit.getDefaultToolkit().addAWTEventListener(event -> handleMouseEvent((MouseEvent) event), eventMask);
	}

	private void inspectComponent(Component component) {
		component.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				handleMouseEvent(e);
			}
		});
	}

	private void handleMouseEvent(MouseEvent mouseEvent) {

		if (!mouseEvent.isControlDown()) {
			popup.setVisible(false);
			return;
		}

		Point location = MouseInfo.getPointerInfo().getLocation();

		Component targetComponent;
		Window window = findWindow();
		if (window != null) {
			SwingUtilities.convertPointFromScreen(location, window);
			targetComponent = SwingUtilities.getDeepestComponentAt(window, location.x, location.y);
		} else {
			SwingUtilities.convertPointFromScreen(location, mouseEvent.getComponent());
			targetComponent = SwingUtilities.getDeepestComponentAt(mouseEvent.getComponent(), location.x, location.y);
		}

		List<Component> hierarchyNodes = componentInspector.inspect(targetComponent);
		if (hierarchyNodes.isEmpty()) {
			popup.setVisible(false);
			return;
		}
		Dimension panelSize = calculatePanelSize(hierarchyNodes);
		pane.invalidate();
		pane.removeAll();
		Collections.reverse(hierarchyNodes);
		hierarchyNodes.forEach(pane::add);
		pane.revalidate();
		pane.repaint();
		popup.setSize(panelSize.width, panelSize.height);
		popup.setVisible(true);
		Point absoluteLocation = MouseInfo.getPointerInfo().getLocation();
		popup.setLocation(absoluteLocation.x, absoluteLocation.y - pane.getHeight() - OFFSET);
	}

	private Dimension calculatePanelSize(List<Component> hierarchyNodes) {
		int maxWidth = 0;
		int maxHeight = 0;
		for (Component hierarchyNode : hierarchyNodes) {
			int componentWidth = hierarchyNode.getX() + hierarchyNode.getPreferredSize().width;
			if (componentWidth > maxWidth) {
				maxWidth = componentWidth;
			}
		}

		Component firstComponent = hierarchyNodes.stream().findFirst().orElseThrow();
		if (firstComponent != null) {
			maxHeight = firstComponent.getPreferredSize().height + firstComponent.getY();
		}

		return new Dimension(maxWidth, maxHeight);
	}

	private static Window findWindow() {
		for (Window window : Window.getWindows()) {
			if (window.getMousePosition(true) != null) {
				return window;
			}
		}

		return null;
	}
}
