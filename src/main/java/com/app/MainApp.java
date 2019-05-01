package com.app;

import java.util.List;

import com.app.dto.ElementDTO;
import com.app.dto.ResultPathDTO;
import com.app.service.AppLogicService;
import com.app.util.DataFormatterUtil;
import com.app.util.ReadDataUtil;

public class MainApp {
	public static void main(String[] args) {
		List<List<ElementDTO>> recordList = ReadDataUtil.read();
		if (recordList == null) {
			System.out.println("Record File is not found!");
		} else {
			recordList = DataFormatterUtil.format(recordList);
			
			ResultPathDTO result = AppLogicService.traverse(recordList);
			System.out.println("Max Sum: " +result.getMaxSum());
			System.out.println("Path: " +result.getSelectedNodes());
		}
	}
}
