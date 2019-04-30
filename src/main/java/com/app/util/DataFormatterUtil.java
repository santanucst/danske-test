package com.app.util;

import java.util.List;

import com.app.dto.ElementDTO;

public class DataFormatterUtil {
	
	public static List<List<ElementDTO>> format(List<List<ElementDTO>> recordList) {
		Integer maxElements = recordList.get(recordList.size()-1).size();
		recordList.forEach(elmList -> {
			for (int i = elmList.size(); i < maxElements; i++) {
				elmList.add(new ElementDTO(0));
			}
		});
		
		return recordList;
	}

}
