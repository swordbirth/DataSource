package com.rk.poi.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParse2007 implements IExcelParse {

	//Excel2007工作空间
	private XSSFWorkbook wb = null;
	
	/**
	 * 加载Excel文件
	 * @param path 文件路径
	 * @throws FileNotFoundException IOException
	 */
	public void loadExcel(String path) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		
		try{
			fis = new FileInputStream(path);
        	wb = new XSSFWorkbook(fis);			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
        	throw new FileNotFoundException("加载Excel文件失败"+ex.getMessage());
		}catch(IOException ex){
			ex.printStackTrace();
        	throw new IOException("加载Excel文件失败"+ex.getMessage());
		}finally{
        	if(fis!=null){
        		fis.close();
        		fis = null;
        	}
		}
	}

	/**
	 * 获取sheet页名字
	 * @param sheetNo
	 * @return 
	 */
	public String getSheetName(int sheetNo) {
		// TODO Auto-generated method stub
		return wb.getSheetName(sheetNo-1);
	}

	/**
	 * 获取sheet页数
	 * @return  
	 * @throws Exception
	 */
	public int getSheetCount() throws Exception {
		// TODO Auto-generated method stub
		int sheetCount = wb.getNumberOfSheets();
		if(sheetCount==0){
			throw new Exception("Excel中没有sheet页");
		}
		return sheetCount;
	}

	/**
	 * 获取sheet页面的行数
	 * @param sheetNo
	 * @return
	 */
	public int getRowCount(int sheetNo) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		rowCount = wb.getSheetAt(sheetNo-1).getLastRowNum();
		return  rowCount;
	}

	/**
	 * 读取第sheetNo个sheet页中第rowNo行第cellNo列的数据
	 * @param sheetNo
	 * @param rowNo
	 * @param cellNo
	 * @return 
	 * @throws Exception
	 */
	public String readExcelByRowAndCell(int sheetNo, int rowNo, int cellNo) throws Exception {
		// TODO Auto-generated method stub
		String rowCellData = "";
		sheetNo = sheetNo -1;
		XSSFSheet sheet = wb.getSheetAt(sheetNo);
		String sheetName = wb.getSheetName(sheetNo);
		
		try{
			XSSFRow row = sheet.getRow(rowNo-1);
			if(row==null){
				return "No Data";
			}
			XSSFCell cell = row.getCell(cellNo-1);
			if(cell == null){
				return "NO Data";
			}
			int cellType = cell.getCellType();
			switch(cell.getCellType()){
			case HSSFCell.CELL_TYPE_STRING:
				rowCellData = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				rowCellData = "";
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				rowCellData = "";
				break;
			default:
				break;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(sheetName+"sheet页中第"+rowNo+"行第"+cellNo+"列"+"数据读取有问题");
		}
		return rowCellData;
	}

	/**
	 * 获取某一行的所有数据
	 * @param sheetNo 指定的sheet页
	 * @param rowNo 指定row行
	 * @return 
	 * @throws Exception
	 */
	public String[] readExcelByRow(int sheetNo, int rowNo) throws Exception {
		// TODO Auto-generated method stub
		String[] rowData = null;
		XSSFSheet sheet = wb.getSheetAt(sheetNo-1);
		XSSFRow row = sheet.getRow(rowNo);
		int cellCount = row.getLastCellNum();
		rowData = new String[cellCount];
		for(int k=1; k<=cellCount; k++){
			rowData[k-1] = readExcelByRowAndCell(sheetNo,rowNo,k);
		}
		return rowData;
	}

	/**
	 * 获取某一列的数据
	 * @param SheetNo 指定页面
	 * @param cellNo 指定列
	 * return 
	 * @throws Exception
	 */
	public String[] readExcelByCell(int sheetNo, int cellNo) throws Exception {
		// TODO Auto-generated method stub
		String[] cellData = null;
		XSSFSheet sheet = wb.getSheetAt(sheetNo-1);
		int rowCount = sheet.getLastRowNum();
		cellData = new String[rowCount+1];
		for(int i=0;i<rowCount;i++){
			cellData[i] = readExcelByRowAndCell(sheetNo-1,i,cellNo-1);
		}
		return cellData;
	}

	/**
	 * 关闭工作空间
	 * @throws IOException
	 */
	public void close() {
		// TODO Auto-generated method stub
		if(wb!=null){
			try{
				wb.close();
				wb = null;
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
}
