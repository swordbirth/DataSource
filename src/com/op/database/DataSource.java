package com.op.database;

public class DataSource{
    private String idx;
    private String major;
    private String class_name;
    private String truancy_rate;
    private String late_rate;
    private String leave_rate;
    private String attendance_rate;
    private String attendance_ranking;
    
    public DataSource(){}
    public DataSource(String index, String major, String class_name, String truancy_rate, String late_rate, String leave_rate, String attendance_rate, String attendance_ranking){
    	this.idx = index;
    	this.major = major;
    	this.class_name = class_name;
    	this.truancy_rate = truancy_rate;
    	this.late_rate = late_rate;
    	this.leave_rate = leave_rate;
    	this.attendance_rate = attendance_rate;
    	this.attendance_ranking = attendance_ranking;
    }
  
    /*
     * 设置、获取index
     */
    public void setIndex(String index){
    	this.idx = index;
    }
    
    public String getIndex(){
    	return idx;
    }
  
    /*
     * 设置、获取major
     */
    public void setMajor(String major){
    	this.major = major;
    }
    
    public String getMajor(){
    	return major;
    }
    
    /*
     * 设置、获取class_name
     */
    public void setClass_name(String class_name){
    	this.class_name = class_name;
    } 
     
    public String getClass_name(){
    	return class_name;
    }
    
    /*
     * 设置、获取truancy_rate
     */
    public void setLate_rate(String truancy_rate){
    	this.truancy_rate = truancy_rate;
    }
    
    public String gettruancy_rate(){
    	return truancy_rate;
    }    
 
    /*
     * 设置、获取late_rate
     */
    public void settruancy_rate(String late_rate){
    	this.late_rate = late_rate;
    }
    
    public String getlate_rate(){
    	return late_rate;
    }

    /*
     * 设置、获取leave_rate
     */
    public void setleave_rate(String leave_rate){
    	this.leave_rate = leave_rate;
    }
    
    public String getleave_rate(){
    	return leave_rate;
    }
     
    /*
     * 设置、获取attendance_rate
     */
    public void setattendance_rate(String attendance_rate){
    	this.attendance_rate = attendance_rate;
    }
    
    public String getattendance_rate(){
    	return attendance_rate;  
    } 
    
    /*
     * 设置、获取attendance_ranking
     */
    public void setattendance_ranking(String attendance_ranking){
    	this.attendance_ranking = attendance_ranking;
    }
    
    public String getattendance_ranking(){
    	return attendance_ranking;
    } 
    public String showData(){
    	return "DataSource [index="+idx+", major="+major+", class_name="+class_name+", truancy_rate="+truancy_rate+", late_rate="+late_rate+", leave_rate="+leave_rate+", attendance_rate="+attendance_rate+", attendance_ranking="+attendance_ranking;
    }
}
