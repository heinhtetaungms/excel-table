package com.ai.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ai.model.TeamStructure;


public class ExcelUtil {
	
	public static List<TeamStructure> parseExcelFile(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
		  //  System.out.println(workbook.getSheetName(0)); 
		    
    		Sheet sheet = workbook.getSheet("Sheet1");
    	//	System.out.println(sheet);
        	
    		Iterator<Row> rows = sheet.iterator();
    		List<TeamStructure> list = new ArrayList<>();
	    		
    		int rowNum = 0;
    		while(rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			//Skip Header
    			if(rowNum == 0) {
    				rowNum++;
    				continue;
    			}
    			Iterator<Cell> cellsInRow = currentRow.iterator();
    			
    			TeamStructure ts = new TeamStructure();
    			
    			int cellIndex = 0;
    			while(cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex == 0) {	//No
    					ts.setNo((int)currentCell.getNumericCellValue());
    				}else if(cellIndex == 1) { //Name
    					ts.setName(currentCell.getStringCellValue());
    				}else if(cellIndex == 2) { //Staff ID
    					ts.setStaffId(currentCell.getStringCellValue());
    				}else if(cellIndex == 3) { //Team
    					ts.setTeam(currentCell.getStringCellValue());
    				}else if(cellIndex == 4) { //Project Name
    					ts.setProjectName(currentCell.getStringCellValue());
    				}else if(cellIndex == 5){ //Project ID
						ts.setProject(currentCell.getStringCellValue());
					}else if(cellIndex == 6){ //Position
						ts.setName(currentCell.getStringCellValue());
					}
    				cellIndex++;
    			}
    			list.add(ts);
    		}
    		//Close Workbook
    		workbook.close();
	    	return list;
		} catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
		
	}
	
}
