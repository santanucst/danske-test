package com.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.app.constant.AppConstant;
import com.app.dto.ElementDTO;

public class ReadDataUtil {
	
	public static List<List<ElementDTO>> read(String fileName) {
		String filePath = AppConstant.FILE_PATH + fileName;
		File file = new File(filePath); 
		
		List<List<ElementDTO>> recordList = new ArrayList<>();
		  
		try {
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String recordLine = ""; 
		  while ((recordLine = br.readLine()) != null) {
//		    System.out.println(recordLine);
		    String[] records = recordLine.split(" ");
		    List<ElementDTO> recordElements = new ArrayList<>();
		    for (String record : records) {
		    	Integer rec = 0;
		    	try {
		    		rec = Integer.parseInt(record);
		    	} catch (NumberFormatException ne) {
		    		ne.printStackTrace();
				}
		    	recordElements.add(new ElementDTO(rec));
		    }
		    
		    recordList.add(recordElements);		    
		  }
		  
		  return recordList;
		} catch(IOException ioe) {
//			ioe.printStackTrace();
			return null;
		}
	}

}
