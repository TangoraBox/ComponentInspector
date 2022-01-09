package com.tangorabox.componentinspector.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;


public class ObjectMetadataExtractor<T> {

    private final T component;
    private final AbstractComponentInspector<T> inspector;

    public ObjectMetadataExtractor(T component, AbstractComponentInspector<T> inspector) {
        this.component = component;
        this.inspector = inspector;
    }

    public Optional<String> getDeclaredFieldNameInParent() {
        return Optional.ofNullable(findFieldNameInHierarchy(inspector.getParent(component)));
    }

    private String findFieldNameInHierarchy(T parentOfComponent) {
        if (parentOfComponent == null) {
            return null;
        }
        return Arrays.stream(parentOfComponent.getClass().getDeclaredFields())
                .filter(field -> field.getType().isAssignableFrom(component.getClass()))
                .map(parentField -> getFieldNameInParent(component, parentOfComponent, parentField))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(() -> findFieldNameInHierarchy(inspector.getParent(parentOfComponent)));
    }


    private String getFieldNameInParent(Object component, Object parent, Field field) {
        try {
            // Allow access to private fields
            field.setAccessible(true);
            Object fieldObj = field.get(parent);
            if (fieldObj == component) {
                return field.getName();
            }
        } catch (Exception ex) {
            // sssssshhhh!
        }
        return null;
    }
}
