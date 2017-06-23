import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {
    
	/*
	 * ��ѯ��������
	 */
	public static List<DataSource> getAllbyData(){
		List<DataSource> datalist = new ArrayList<DataSource>();
		
		try{
			JDhelper jd = new JDhelper();
			String sql = "select *from report";
			ResultSet ret = jd.Search(sql, null);
			
			while(ret.next()){
				String idx = ret.getString("idx");
				String major = ret.getString("major");
				String class_name = ret.getString("class_name");
				String late_rate = ret.getString("late_rate");
				String truancy_rate = ret.getString("truancy_rate");
				String leave_rate = ret.getString("leave_rate");
				String attendance_rate = ret.getString("attendance_rate");
				String attendance_ranking = ret.getString("attendance_ranking");

				datalist.add(new DataSource(idx,major,class_name,late_rate,truancy_rate,leave_rate,attendance_rate,attendance_ranking));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return datalist;
	}
	
	public static List<DataSource> getAllbyExcel(String file){
		List<DataSource> datelist = new ArrayList<DataSource>();
		try{
			Workbook wbk = Workbook.getWorkbook(new File(file));//��һ��������(Excel�ļ�)
			Sheet st = wbk.getSheet(0);//�õ�һ������һ���������п���������sheet��
			int  clos = st.getColumns();//�õ�������
			int rows = st.getRows();//�õ�������
			
			System.out.println("clos:"+clos+"rows:"+rows);
			for(int i=0; i<rows; i++){
				for(int j = 0; j<clos; j++){
					String index = st.getCell(j++,i).getContents();
					String major = st.getCell(j++, i).getContents();
					String class_name = st.getCell(j++, i).getContents();
					String late_rate = st.getCell(j++,i).getContents();
					String truancy_rate = st.getCell(j++,i).getContents();
					String leave_rate = st.getCell(j++,i).getContents();
					String attendance_rate = st.getCell(j++,i).getContents();
					String attendance_ranking = st.getCell(j++,i).getContents();
		            System.out.println("id:"+index+"major:"+major+"class_name:"+class_name+"truancy_rate:"+truancy_rate+"leave_rate:"+leave_rate+"attendance_rate:"+attendance_rate+"attendance_ranking"+attendance_ranking);
					datelist.add(new DataSource(index,major,class_name,late_rate,truancy_rate,leave_rate,attendance_rate,attendance_ranking));
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return datelist;
	}
	/**
	 * �ж�id�Ƿ����
	 * @param id
	 * @return
	 */
	public static boolean isExist(int index){
//		boolean flag = false;
		
		try{
		    JDhelper jd = new JDhelper();
		    
		    ResultSet rs = jd.Search("select *from report where idx=?", new String[]{index+""});
		    
		    if(rs.next()){
		    	return true; 
		    }
		}catch(SQLException ex){
			ex.printStackTrace();
		}		
		return false;
	}
	
	public static void main(String[] args){
//		List<DataSource> all = getAllbyData();
//		for(DataSource data:all){
//			System.out.println(data.showData());
//		}
//		
//		isExist(1);
		
		List<DataSource> all = getAllbyExcel("C:/Users/sword/Desktop/2015��2016��9�¿��ñ���.xls");
		for(DataSource data:all){
			System.out.println(data.showData());
		}
	
	}
	
	
}
