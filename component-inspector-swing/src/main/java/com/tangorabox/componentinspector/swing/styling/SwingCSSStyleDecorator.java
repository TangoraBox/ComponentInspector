package com.tangorabox.componentinspector.swing.styling;

import com.tangorabox.componentinspector.core.CSSStyleClass;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwingCSSStyleDecorator {

    private static final float FONT_SIZE = 10f;
    private static final int PADDING = 1;

    public Component decorate(JComponent component, CSSStyleClass styleClass) {
       if (styleClass == null) {
           return component;
       }
        Font font = component.getFont().deriveFont(FONT_SIZE);
       component.setFont(font);

        switch (styleClass) {
           case COMPONENT_DETAILS: return decorateComponentDetails(component);
           case CLASS_COMPONENT: return decorateClassComponent(component);
           case FIELD_COMPONENT: return decorateFieldComponent(component);
           default: return component;
       }
    }


    private Component decorateComponentDetails(JComponent component) {
        Border paddingBorder = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING);
        component.setBorder(paddingBorder);
        DropShadowPanel dropShadowPanel = new DropShadowPanel(5);

        dropShadowPanel.add(component, BorderLayout.CENTER);
        return dropShadowPanel;
    }

    private Component decorateClassComponent(Component component) {
        Font font = component.getFont().deriveFont(Font.BOLD + Font.ITALIC);
        component.setFont(font);
        return component;
    }

    private Component decorateFieldComponent(JComponent component) {
        component.setOpaque(true);
        component.setBackground(Color.LIGHT_GRAY);
        Font font = component.getFont().deriveFont(Font.BOLD);
        component.setFont(font);

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1, true);
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 10, 0, 10);

        component.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
        return component;
    }

}
