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

	//Excel2007�����ռ�
	private XSSFWorkbook wb = null;
	
	/**
	 * ����Excel�ļ�
	 * @param path �ļ�·��
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
        	throw new FileNotFoundException("����Excel�ļ�ʧ��"+ex.getMessage());
		}catch(IOException ex){
			ex.printStackTrace();
        	throw new IOException("����Excel�ļ�ʧ��"+ex.getMessage());
		}finally{
        	if(fis!=null){
        		fis.close();
        		fis = null;
        	}
		}
	}

	/**
	 * ��ȡsheetҳ����
	 * @param sheetNo
	 * @return 
	 */
	public String getSheetName(int sheetNo) {
		// TODO Auto-generated method stub
		return wb.getSheetName(sheetNo-1);
	}

	/**
	 * ��ȡsheetҳ��
	 * @return  
	 * @throws Exception
	 */
	public int getSheetCount() throws Exception {
		// TODO Auto-generated method stub
		int sheetCount = wb.getNumberOfSheets();
		if(sheetCount==0){
			throw new Exception("Excel��û��sheetҳ");
		}
		return sheetCount;
	}

	/**
	 * ��ȡsheetҳ�������
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
	 * ��ȡ��sheetNo��sheetҳ�е�rowNo�е�cellNo�е�����
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
			throw new Exception(sheetName+"sheetҳ�е�"+rowNo+"�е�"+cellNo+"��"+"���ݶ�ȡ������");
		}
		return rowCellData;
	}

	/**
	 * ��ȡĳһ�е���������
	 * @param sheetNo ָ����sheetҳ
	 * @param rowNo ָ��row��
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
	 * ��ȡĳһ�е�����
	 * @param SheetNo ָ��ҳ��
	 * @param cellNo ָ����
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
	 * �رչ����ռ�
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
