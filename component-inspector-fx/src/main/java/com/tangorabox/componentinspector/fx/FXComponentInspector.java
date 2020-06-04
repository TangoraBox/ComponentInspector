package com.tangorabox.componentinspector.fx;

import com.tangorabox.componentinspector.core.AbstractComponentInspector;
import com.tangorabox.componentinspector.core.CSSStyleClass;
import com.tangorabox.componentinspector.core.ComponentDetails;
import com.tangorabox.componentinspector.core.ObjectMetadataExtractor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;

public class FXComponentInspector extends AbstractComponentInspector<Node> {

    @Override
    protected Node getParent(Node component) {
        if (component == null) {
            return null;
        }
        return component.getParent();
    }

    @Override
    protected Node createFieldNameComponent(Node component) {
        String fieldName = ObjectMetadataExtractor.getDeclaredFieldNameInParent(component, this);
        if (fieldName == null) {
            return null;
        }

        Label result = new Label(fieldName);
        result.getStyleClass().add(CSSStyleClass.FIELD_COMPONENT.getCssClassName());
        return result;
    }

    @Override
    protected Node createClassComponent(Node component) {
        Label result = new Label(component.getClass().getName());
        result.getStyleClass().add(CSSStyleClass.CLASS_COMPONENT.getCssClassName());
        return result;
    }

    @Override
    protected Node createStylesComponent(Node component) {
        String labelText = String.join(", ",component.getStyleClass());
        if (labelText.isBlank()) {
            labelText = "(empty)";
        }
        Label result = new Label(labelText);
        result.getStyleClass().add(CSSStyleClass.STYLES_COMPONENT.getCssClassName());
        return result;
    }

    @Override
    protected Node createComponentDetailsPanel(ComponentDetails<Node> details) {
        HBox result = new HBox(details.getClassComponent(), details.getStylesComponent());
        result.getStyleClass().add(CSSStyleClass.COMPONENT_DETAILS.getCssClassName());
        result.relocate(details.getLocationX(), details.getLocationY());

        if (details.getFieldNameComponent() != null) {
            result.getChildren().add(details.getFieldNameComponent());
        }

        return result;
    }

    @Override
    protected void buildCascade(List<ComponentDetails<Node>> hierarchy) {
        for (int componentLevel = 0, componentIndex = hierarchy.size() - 1; componentIndex >= 0; componentIndex--, componentLevel++) {
            ComponentDetails<Node> componentChild = hierarchy.get(componentIndex);
            componentChild.setLocation(componentLevel * HORIZONTAL_SPACING, componentLevel * VERTICAL_SPACING);
        }
    }

}
