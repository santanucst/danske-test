package com.app.dto;

import java.util.ArrayList;
import java.util.List;

public class ElementDTO implements Comparable<ElementDTO> {
	
	private Integer element;
	private Boolean selected;
	private List<Integer> selectedElements;
	private Integer updatedElement;
	
	public ElementDTO(Integer element) {
		this.element = element;
		this.selected = false;
		this.updatedElement = 0;
		this.selectedElements = new ArrayList<>();
	}

	public Integer getElement() {
		return element;
	}
	public void setElement(Integer element) {
		this.element = element;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public List<Integer> getSelectedElements() {
		return selectedElements;
	}
	public void addSelectedElements(List<Integer> existingElements) {
		this.selectedElements.clear();
		this.selectedElements.addAll(existingElements);
		this.selectedElements.add(this.element);
	}
	public Integer getUpdatedElement() {
		return updatedElement;
	}
	public void setUpdatedElement(Integer updatedElement) {
		this.updatedElement = updatedElement;
	}

	@Override
	public int compareTo(ElementDTO elmObj) {
		return elmObj.updatedElement - this.updatedElement;
	}
	
}
