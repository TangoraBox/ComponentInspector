package com.tangorabox.componentinspector.fx;

import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FXComponentInspectorHandler {

	public static final int OFFSET = 25;
	private final Popup popup = new Popup();
	private final Pane pane = new Pane();
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
		pane.getStylesheets().add(this.getClass().getResource("/component-inspector.css").toExternalForm());
	}


	private void inspectAllWindows() {
		Window.getWindows().stream().filter(window -> window != popup && !handledWindows.contains(window)).forEach(window -> {
			handledWindows.add(window);
			window.addEventFilter(MouseEvent.MOUSE_MOVED, event -> handle(event, window.getScene()));
		});
	}

	private void inspectNode(Node node) {
		node.addEventFilter(MouseEvent.MOUSE_MOVED, event -> handle(event, null));
	}


	private void handle(MouseEvent event, Scene scene) {
		if (!event.isControlDown()) {
			popup.hide();
			return;
		}
		EventTarget target = event.getTarget();
		List<Node> hierarchyNodes = componentInspector.inspect((Node) target);
		if (scene == null) {
			scene = ((Node) target).getScene();
		}
		pane.getChildren().setAll(hierarchyNodes);
		popup.sizeToScene();
		if (currentScene != scene) {
			popup.hide();
			currentScene = scene;
		}
		popup.show(scene.getRoot(), event.getScreenX() - pane.getWidth(), event.getScreenY() - pane.getHeight() - OFFSET);
		inspectAllWindows();
	}
}
