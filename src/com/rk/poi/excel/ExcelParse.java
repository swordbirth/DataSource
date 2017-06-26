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
			throw new Exception("�ݲ�֧�������ļ��Ľ���");
		}
		return true;
	}
	
	/**
	 * ��ȡexcel�����ռ�
	 * @param filePath �ļ�·��
	 * @throws Exception
	 */
	public void loadExcel(String filePath)throws Exception{
		getInstance(filePath);
		excelParse.loadExcel(filePath);
	}
	
	/**
	 * ��ȡsheetҳ������
	 * @param sheetNo ҳ�����
	 * @return
	 */
	public String getSheetName(int sheetNo){
		return excelParse.getSheetName(sheetNo);
	}
	
	/**
	 * ��ȡsheetҳ��
	 * @return
	 * @throws Exception
	 */
	public int getSheetCount() throws Exception{
		return excelParse.getSheetCount();
	}
	
	/**
	 * ��ȡsheetҳ������
	 * @param sheetNo
	 * @return
	 */
	public int getRowCount(int sheetNo){
		return excelParse.getRowCount(sheetNo);
	}
	
	/**
	 * ��ȡsheet��ĳһ��Ԫ�������
	 * @param sheetNo ָ��ҳ��
	 * @param rowNo ָ������
	 * @param cellNo ָ������
	 * @return
	 * @throws Exception
	 */
	public String readExcelByRowAndCell(int sheetNo,int rowNo,int cellNo)throws Exception{
		return excelParse.readExcelByRowAndCell(sheetNo,rowNo,cellNo);
	}
	
	/**
	 * ��ȡĳһ�е�����
	 * @param sheetNo ָ��ҳ��
	 * @param rowNo ָ������
	 * @return
	 * @throws Exception
	 */
	public String[] readExcelByRow(int sheetNo,int rowNo)throws Exception{
		return excelParse.readExcelByRow(sheetNo,rowNo);
	}
	
	/**
	 * ��ȡĳһ�е�����
	 * @param SheetNo ָ��ҳ��
	 * @param cellNo ָ������
	 * @return
	 */
	public String[] readExcelByCell(int SheetNo,int cellNo)throws Exception{
		return excelParse.readExcelByCell(SheetNo,cellNo);
	}
	
	/**
	 * �رտռ�
	 */
	public void close(){
		excelParse.close();
	}
	
	public static void main(String[] args){
		ExcelParse ts = new ExcelParse();
		try{
			ts.loadExcel("C:\\Users\\sword\\Desktop\\2015��2016��9�¿��ñ���.xlsx");	
			System.out.println(ts.getSheetCount());
			System.out.println(ts.getRowCount(1));
			System.out.println(ts.readExcelByRowAndCell(1, 5, 5));
		}catch(Exception ex){
			ex.printStackTrace();
		}


	}
}
