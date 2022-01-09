package com.tangorabox.componentinspector.core;

import java.util.Optional;

public class ComponentDetails<T> {

    private T fieldNameComponent = null;
    private T classComponent;
    private T stylesComponent;

    private int locationX;
    private int locationY;


    public void setLocation(int x, int y) {
        this.locationX = x;
        this.locationY = y;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setFieldNameComponent(T fieldNameComponent) {
        this.fieldNameComponent = fieldNameComponent;
    }

    public Optional<T> getFieldNameComponent() {
        return Optional.ofNullable(fieldNameComponent);
    }

    public void setClassComponent(T classComponent) {
        this.classComponent = classComponent;
    }

    public T getClassComponent() {
        return classComponent;
    }

    public T getStylesComponent() {
        return stylesComponent;
    }

    public void setStylesComponent(T stylesComponent) {
        this.stylesComponent = stylesComponent;
    }
}
