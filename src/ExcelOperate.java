import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelOperate {
	
	ArrayList<DataSource> datalist = new ArrayList<DataSource>();
	DataSource data = new DataSource();
	
	public ArrayList<DataSource> readExcel(int title_row,String file){
		try{
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));//打开文件
			HSSFWorkbook hwb = new HSSFWorkbook(fs); //获取工作簿
			HSSFSheet sheet = hwb.getSheetAt(0);  //获取表空间
			int rowNum = sheet.getLastRowNum();  //获取表的总行数
			int columNum = sheet.getRow(0).getPhysicalNumberOfCells();//获取表的总列数
			
			System.out.println("row:"+rowNum+" columNum:"+columNum);
			
			for(int i=0; i<rowNum; i++){
				for(int j=0; j<columNum; j++){
					data.setIndex(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.setMajor(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.setClass_name(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.settruancy_rate(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.setLate_rate(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.setleave_rate(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.setattendance_rate(sheet.getRow(i).getCell(j++).getStringCellValue());
					data.setattendance_ranking(sheet.getRow(i).getCell(j++).getStringCellValue());
				}
				datalist.add(data);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return datalist;
	}
	
	public static void main(String[] args){
		ExcelOperate test = new ExcelOperate();
		String file = "C:\\Users\\sword\\Desktop\\2015级2016年9月课堂报告.xlsx"; 
		test.readExcel(2, file);
		for(DataSource data:test.readExcel(2, file)){
			data.showData();
		}
	}
	
}
