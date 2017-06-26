package com.rk.poi.excel;

public interface IExcelParse {
	public void loadExcel(String path) throws Exception;
	
	public String getSheetName(int sheetNo);
	
	public int getSheetCount() throws Exception;
	
	public int getRowCount(int sheetNo);
	
	public String readExcelByRowAndCell(int sheetNo,int rowNo,int cellNo)throws Exception;
	
	public String[] readExcelByRow(int sheetNo,int rowNo)throws Exception;
	
	public String[] readExcelByCell(int SheetNo,int cellNo)throws Exception;
	
	public void close();
}
