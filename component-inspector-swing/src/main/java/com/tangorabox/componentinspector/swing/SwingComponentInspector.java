package com.tangorabox.componentinspector.swing;

import com.tangorabox.componentinspector.core.AbstractComponentInspector;
import com.tangorabox.componentinspector.core.CSSStyleClass;
import com.tangorabox.componentinspector.core.ComponentDetails;
import com.tangorabox.componentinspector.core.ObjectMetadataExtractor;
import com.tangorabox.componentinspector.swing.styling.SwingCSSStyleDecorator;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class SwingComponentInspector extends AbstractComponentInspector<Component> {

    private final SwingCSSStyleDecorator cssStyleDecorator = new SwingCSSStyleDecorator();

    @Override
    protected Component getParent(Component component) {
        if (component == null) {
            return null;
        }
        return component.getParent();
    }

    @Override
    protected Optional<Component> createFieldNameComponent(Component component) {
         return new ObjectMetadataExtractor<>(component,this)
                 .getDeclaredFieldNameInParent()
                  .map(fieldName -> cssStyleDecorator.decorate(new JLabel(fieldName), CSSStyleClass.FIELD_COMPONENT));
    }

    @Override
    protected Component createClassComponent(Component component) {
        JLabel result = new JLabel(component.getClass().getName());
        return cssStyleDecorator.decorate(result, CSSStyleClass.CLASS_COMPONENT);
    }

    @Override
    protected Component createStylesComponent(Component component) {
        return null;
    }

    @Override
    protected Component createComponentDetailsPanel(ComponentDetails<Component> details) {
        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.add(details.getClassComponent());
        details.getFieldNameComponent().ifPresent(jPanel::add);
        Component result = cssStyleDecorator.decorate(jPanel, CSSStyleClass.COMPONENT_DETAILS);
        result.setBounds(details.getLocationX(), details.getLocationY(),
                result.getPreferredSize().width, result.getPreferredSize().height);
        return result;
    }

    @Override
    protected void buildCascade(List<ComponentDetails<Component>> hierarchy) {
        for (int componentLevel = 0, componentIndex = hierarchy.size() - 1; componentIndex >= 0; componentIndex--, componentLevel++) {
            ComponentDetails<Component> componentChild = hierarchy.get(componentIndex);
            componentChild.setLocation(componentIndex * HORIZONTAL_SPACING, componentLevel * VERTICAL_SPACING);
        }
    }
}
