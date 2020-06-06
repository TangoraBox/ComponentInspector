package com.tangorabox.componentinspector.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;


public class ObjectMetadataExtractor {

	private ObjectMetadataExtractor() {}

	public static <T> String getDeclaredFieldNameInParent(T component, AbstractComponentInspector<T> inspector) {
		return findFieldNameInHierarchy(component, inspector.getParent(component), inspector);
	}

	private static <T> String findFieldNameInHierarchy(T component, T parent, AbstractComponentInspector<T> inspector) {
         return Optional.ofNullable(parent)
			.map(parentComponent ->
			  Arrays.stream(parentComponent.getClass().getDeclaredFields())
					.filter(field -> field.getType() == component.getClass())
					.map(parentField->  getFieldNameInParent(component, parentComponent, parentField))
					.filter(Objects::nonNull)
					.findFirst()
					.orElseGet(()->findFieldNameInHierarchy(component, inspector.getParent(parent), inspector))
			).orElse(null);

	}


	private static String getFieldNameInParent(Object component, Object parent, Field field) {
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
