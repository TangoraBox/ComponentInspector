package com.tangorabox.componentinspector.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMetadataExtractor {

	private ObjectMetadataExtractor() {}

	public static <T> String getDeclaredFieldNameInParent(T component, AbstractComponentInspector<T> inspector) {
		return findFieldNameInHierarchy(component, inspector.getParent(component), inspector);
	}

	private static <T> String findFieldNameInHierarchy(T component, T parent, AbstractComponentInspector<T> inspector) {
		String result = null;
		if (parent != null) {
			String fieldNameInParent = null;
			List<Field> parentFieldsOfSameClassOfComponent = Arrays.stream(parent.getClass().getDeclaredFields())
					.filter(field -> field.getType() == component.getClass())
					.collect(Collectors.toList());

			for(int i = 0; i < parentFieldsOfSameClassOfComponent.size() && fieldNameInParent == null; i++) {
				fieldNameInParent = getFieldNameInParent(component, parent, parentFieldsOfSameClassOfComponent.get(i));
			}

			if (fieldNameInParent != null) {
				result = fieldNameInParent;
			} else {
				result = findFieldNameInHierarchy(component, inspector.getParent(parent), inspector);
			}
		}
		return result;
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