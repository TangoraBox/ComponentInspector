package com.tangorabox.componentinspector.fx;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.*;

public class FXComponentInspectorHandler {

	private static final int OFFSET = 25;
	private final Popup popup = new Popup();
	private final Popup highlightPopup = new Popup();
	private final Pane pane = new Pane();
	private final Pane highlightPane = new Pane();
	private final FXComponentInspector componentInspector = new FXComponentInspector();
	private final Set<Window> handledWindows = new HashSet<>();
	private Scene currentScene = null;

	private static FXComponentInspectorHandler instance = null;

	public static void handleAll() {
		getInstance().inspectAllWindows();
	}

	public static void handle(Node node) {
		getInstance().inspectNode(node);
	}

	private static FXComponentInspectorHandler getInstance() {
		if (instance == null) {
			instance = new FXComponentInspectorHandler();
		}
		return instance;
	}

	private FXComponentInspectorHandler() {
		popup.getContent().add(pane);
		pane.getStyleClass().add("content-pane");
		pane.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/component-inspector.css")).toExternalForm());

		highlightPopup.getContent().add(highlightPane);
		highlightPane.getStyleClass().add("highlight-pane");
		highlightPane.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/highlight.css")).toExternalForm());

	}


	private void inspectAllWindows() {
		Iterator<Window> windowIterator = Window.impl_getWindows();
		while (windowIterator.hasNext()) {
			Window window = windowIterator.next();
			if (window != popup && window != highlightPopup && !handledWindows.contains(window)) {
				handledWindows.add(window);
				window.addEventFilter(MouseEvent.MOUSE_MOVED, event -> handle(event, window.getScene()));
			}
		}
	}

	private void inspectNode(Node node) {
		node.addEventFilter(MouseEvent.MOUSE_MOVED, event -> handle(event, null));
	}


	private void handle(MouseEvent event, Scene scene) {
		if (!event.isControlDown()) {
			popup.hide();
			highlightPopup.hide();
			return;
		}

		if (!(event.getTarget() instanceof Node)) {
			return;
		}

		Node componentUnderMouse = (Node) event.getTarget();
		if (scene == null) {
			scene = componentUnderMouse.getScene();
		}

		List<Node> hierarchyNodes = componentInspector.inspect(componentUnderMouse);

		pane.getChildren().setAll(hierarchyNodes);
		popup.sizeToScene();
		if (currentScene != scene) {
			popup.hide();
			highlightPopup.hide();
			currentScene = scene;
		}
		highlightComponent(componentUnderMouse, scene);
		popup.show(scene.getRoot(), event.getScreenX() - pane.getWidth(), event.getScreenY() - pane.getHeight() - OFFSET);
		inspectAllWindows();
	}

	private void highlightComponent(Node component, Scene scene) {
		final Bounds bounds = component.localToScreen(component.getBoundsInLocal());
		highlightPane.setPrefSize(bounds.getWidth(), bounds.getHeight());
		highlightPopup.sizeToScene();
		highlightPopup.show(scene.getRoot(), bounds.getMinX(), bounds.getMinY());
	}

}
