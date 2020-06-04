package com.tangorabox.componentinspector.core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractComponentInspector<T> {

	protected static final int HORIZONTAL_SPACING = 25;
	protected static final int VERTICAL_SPACING = 25;


	public List<T> inspect(T component) {
		return inspectDetails(component).stream().map(this::createComponentDetailsPanel).collect(Collectors.toList());
	}

	private List<ComponentDetails<T>> inspectDetails(T component) {
		List<ComponentDetails<T>> hierarchy = buildHierarchyRecursive(component);
		buildCascade(hierarchy);
		return hierarchy;
	}

	private List<ComponentDetails<T>> buildHierarchyRecursive(T component) {
		if (component == null) {
			return Collections.emptyList();
		}
		List<ComponentDetails<T>> hierarchy = new ArrayList<>();
		hierarchy.add(createComponentDetails(component));
		hierarchy.addAll(buildHierarchyRecursive(getParent(component)));
		return hierarchy;
	}

	private ComponentDetails<T> createComponentDetails(T component) {
		ComponentDetails<T> details = new ComponentDetails<>();
		details.setFieldNameComponent(createFieldNameComponent(component));
		details.setClassComponent(createClassComponent(component));
		details.setStylesComponent(createStylesComponent(component));
		return  details;
	}

	protected abstract T getParent(T component);

	protected abstract T createFieldNameComponent(T component);

	protected abstract T createClassComponent(T component);

	protected abstract T createStylesComponent(T component);

	protected abstract T createComponentDetailsPanel(ComponentDetails<T> details);

	protected abstract void buildCascade(List<ComponentDetails<T>> hierarchy);
}
