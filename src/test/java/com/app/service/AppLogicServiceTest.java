package com.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.app.constant.AppConstant;
import com.app.dto.ElementDTO;
import com.app.dto.ResultPathDTO;
import com.app.util.DataFormatterUtil;
import com.app.util.ReadDataUtil;

class AppLogicServiceTest {
	
	@Test
	@DisplayName("Validate with sample data")
	void testBySampleData() {
		List<List<ElementDTO>> recordList = ReadDataUtil.read(AppConstant.SAMPLE_FILE);
		assertNotEquals(null, recordList, "Invalid input data");
		
		recordList = DataFormatterUtil.format(recordList);
		ResultPathDTO result = AppLogicService.traverse(recordList);
		
		Integer expectedMaxSum = 16;
		List<Integer> expectedPath = Arrays.asList(1, 8, 5, 2);
		
		assertAll(
			() -> assertEquals(expectedMaxSum, result.getMaxSum(), () -> "Not received Expected Max sum: "+expectedMaxSum),
			() -> assertArrayEquals(expectedPath.toArray(), result.getSelectedNodes().toArray(), "Invalid Final Path"));
	}

	@Test
	@DisplayName("Validate with test data")
	void testByTestData() {
		List<List<ElementDTO>> recordList = ReadDataUtil.read(AppConstant.TEST_FILE);
		assertNotEquals(null, recordList, "Invalid input data");
		
		recordList = DataFormatterUtil.format(recordList);
		ResultPathDTO result = AppLogicService.traverse(recordList);
		
		Integer expectedMaxSum = 8186;
		List<Integer> expectedPath = Arrays.asList(215, 192, 269, 836, 805, 728, 433, 528, 863, 632, 931, 778, 413, 310, 253);
		
		assertAll(
			() -> assertEquals(expectedMaxSum, result.getMaxSum(), () -> "Not received Expected Max sum: "+expectedMaxSum),
			() -> assertArrayEquals(expectedPath.toArray(), result.getSelectedNodes().toArray(), "Invalid Final Path"));
	}

}
