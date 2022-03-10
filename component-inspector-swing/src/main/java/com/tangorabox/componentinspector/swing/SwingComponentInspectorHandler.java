package com.tangorabox.componentinspector.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SwingComponentInspectorHandler {

	private static final int OFFSET = 25;
	private final JDialog popup = new JDialog();
	private final JDialog highlightPopup = new JDialog();
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
		initPopup(popup);
		initPopup(highlightPopup);
		highlightPopup.getRootPane().setBorder(BorderFactory.createDashedBorder(Color.BLUE));
		popup.getContentPane().add(pane, BorderLayout.CENTER);
		pane.setOpaque(false);
	}

	private void initPopup(JDialog popup) {
		popup.setAlwaysOnTop(true);
		popup.setUndecorated(true);
		popup.setBackground(new Color(0, 0, 0, 0));
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
			highlightPopup.setVisible(false);
			return;
		}

		final Component componentUnderMouse = findComponentUnderMouse(mouseEvent);
		List<Component> hierarchyNodes = componentInspector.inspect(componentUnderMouse);
		if (hierarchyNodes.isEmpty()) {
			popup.setVisible(false);
			highlightPopup.setVisible(false);
		} else {
			highlightComponent(componentUnderMouse);
			showHierarchyPopup(hierarchyNodes);
		}
	}


	private void highlightComponent(Component componentUnderMouse) {
		final Rectangle bounds = componentUnderMouse.getBounds();
		highlightPopup.getContentPane().setPreferredSize(new Dimension(bounds.width, bounds.height));
		highlightPopup.setSize(bounds.width, bounds.height);
		highlightPopup.setVisible(true);
		highlightPopup.setLocation(componentUnderMouse.getLocationOnScreen());
	}

	private Component findComponentUnderMouse(MouseEvent mouseEvent) {
		final Point location = MouseInfo.getPointerInfo().getLocation();
		final Component topLevelAncestor = findWindowUnderMouse().orElse(mouseEvent.getComponent());
		SwingUtilities.convertPointFromScreen(location, topLevelAncestor);
		return SwingUtilities.getDeepestComponentAt(topLevelAncestor, location.x, location.y);
	}

	private static Optional<Component> findWindowUnderMouse() {
		for (Window window : Window.getWindows()) {
			if (window.getMousePosition(true) != null) {
				return Optional.of(window);
			}
		}
		return Optional.empty();
	}

	private void showHierarchyPopup(List<Component> hierarchyNodes) {
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
		final int maxWidth = hierarchyNodes.stream()
				.mapToInt(hierarchyNode -> hierarchyNode.getX() + hierarchyNode.getPreferredSize().width)
				.max().orElse(0);

		final int maxHeight = hierarchyNodes.stream().findFirst()
				.map(firstComponent -> firstComponent.getPreferredSize().height + firstComponent.getY())
				.orElse(0);

		return new Dimension(maxWidth, maxHeight);
	}
}
