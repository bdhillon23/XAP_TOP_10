package com.leagacyTeam.readExcel;

import java.util.Hashtable;

public class DataUtil {
	
public static String TestCaseName;	

public static Object[][] getData(Xls_Reader reader,String TestCaseName){
	
	

	int CurrentRowNumber=0;
	while(!reader.getCellData("Data", 0, CurrentRowNumber).equals(TestCaseName)){
		CurrentRowNumber=CurrentRowNumber+1;
	}
	
	
	int Start_TestDataRow=CurrentRowNumber+2;
	
	
	
	
	int TestdataRows=0;
	while(!reader.getCellData("Data", 0, TestdataRows+Start_TestDataRow).equals("")){
		TestdataRows=TestdataRows+1;
		
	}
	
	
	int TestdataColumn=0;
	while(!reader.getCellData("Data", TestdataColumn,Start_TestDataRow).equals("")){
		TestdataColumn=TestdataColumn+1;
		
	}
	Object [][] data=new Object[TestdataRows][1];
	
	
	
	int row=0;
	
	Hashtable<String,String> table;
	for(int RowNum=Start_TestDataRow;RowNum<Start_TestDataRow+TestdataRows;RowNum++){
		table=new Hashtable<String,String>();
		for(int ColNum=0;ColNum<TestdataColumn;ColNum++){
			
			String key=reader.getCellData("Data", ColNum, CurrentRowNumber+1);
			String value=reader.getCellData("Data", ColNum, RowNum);
			table.put(key, value);
			
		}
		
		
		data[row][0]=table;
		
		row=row+1;
		
	}
	return data;
}
public static boolean isRunnable(String TestCaseName,Xls_Reader reader){
	String SheetName="TestCases";
	int RowsCount=reader.getRowCount(SheetName);
	for(int RowNum=2;RowNum<RowsCount+1;RowNum++){
		String tName=reader.getCellData(SheetName, 0, RowNum);
		if(tName.equals(TestCaseName)){
			String Runmode=reader.getCellData(SheetName, "RunMode", RowNum);
			if(Runmode.equals("Y")){
				return true;
			}
		}
	}
	return false;
}
}


