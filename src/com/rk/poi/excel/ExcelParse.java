package com.rk.poi.excel;

public class ExcelParse {
	private IExcelParse excelParse = null;
	
	private boolean getInstance(String path)throws Exception{
		path = path.toLowerCase();
		if(path.endsWith(".xls")){
			excelParse = new ExcelParse2003();
		}else if(path.endsWith(".xlsx")){
			excelParse = new ExcelParse2007();
		}else{
			throw new Exception("暂不支持这类文件的解析");
		}
		return true;
	}
	
	/**
	 * 获取excel工作空间
	 * @param filePath 文件路径
	 * @throws Exception
	 */
	public void loadExcel(String filePath)throws Exception{
		getInstance(filePath);
		excelParse.loadExcel(filePath);
	}
	
	/**
	 * 获取sheet页面名称
	 * @param sheetNo 页面序号
	 * @return
	 */
	public String getSheetName(int sheetNo){
		return excelParse.getSheetName(sheetNo);
	}
	
	/**
	 * 获取sheet页数
	 * @return
	 * @throws Exception
	 */
	public int getSheetCount() throws Exception{
		return excelParse.getSheetCount();
	}
	
	/**
	 * 获取sheet页面行数
	 * @param sheetNo
	 * @return
	 */
	public int getRowCount(int sheetNo){
		return excelParse.getRowCount(sheetNo);
	}
	
	/**
	 * 读取sheet中某一单元格的数据
	 * @param sheetNo 指定页面
	 * @param rowNo 指定行数
	 * @param cellNo 指定列数
	 * @return
	 * @throws Exception
	 */
	public String readExcelByRowAndCell(int sheetNo,int rowNo,int cellNo)throws Exception{
		return excelParse.readExcelByRowAndCell(sheetNo,rowNo,cellNo);
	}
	
	/**
	 * 读取某一行的数据
	 * @param sheetNo 指定页面
	 * @param rowNo 指定行数
	 * @return
	 * @throws Exception
	 */
	public String[] readExcelByRow(int sheetNo,int rowNo)throws Exception{
		return excelParse.readExcelByRow(sheetNo,rowNo);
	}
	
	/**
	 * 读取某一列的数据
	 * @param SheetNo 指定页面
	 * @param cellNo 指定列数
	 * @return
	 */
	public String[] readExcelByCell(int SheetNo,int cellNo)throws Exception{
		return excelParse.readExcelByCell(SheetNo,cellNo);
	}
	
	/**
	 * 关闭空间
	 */
	public void close(){
		excelParse.close();
	}
	
	public static void main(String[] args){
		ExcelParse ts = new ExcelParse();
		try{
			ts.loadExcel("C:\\Users\\sword\\Desktop\\2015级2016年9月课堂报告.xlsx");	
			System.out.println(ts.getSheetCount());
			System.out.println(ts.getRowCount(1));
			System.out.println(ts.readExcelByRowAndCell(1, 5, 5));
		}catch(Exception ex){
			ex.printStackTrace();
		}


	}
}
