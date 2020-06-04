package com.tangorabox.componentinspector.core;

public enum CSSStyleClass {

    COMPONENT_DETAILS("component-details"),
    FIELD_COMPONENT("field-component"),
    CLASS_COMPONENT("class-component"),
    STYLES_COMPONENT("styles-component");

    private final String cssClassName;

    CSSStyleClass(String cssClassName) {
        this.cssClassName = cssClassName;
    }

    public String getCssClassName() {
        return cssClassName;
    }
}
