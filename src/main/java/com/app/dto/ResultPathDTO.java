package com.app.dto;

import java.util.List;

public class ResultPathDTO {

	private Integer maxSum;
	private List<Integer> selectedNodes;
	
	public ResultPathDTO(Integer maxSum, List<Integer> selectedNodes) {
		this.maxSum = maxSum;
		this.selectedNodes = selectedNodes;
	}

	public Integer getMaxSum() {
		return maxSum;
	}
	public List<Integer> getSelectedNodes() {
		return selectedNodes;
	}
	
}
