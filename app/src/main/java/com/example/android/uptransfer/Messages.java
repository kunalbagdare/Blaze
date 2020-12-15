package com.example.android.uptransfer;
import java.util.Calendar;
import java.util.Date;


public class Messages {
    private String msg;
    private String date;
    private  String time;
    private  String type;

    public Messages(){}

   public Messages(String date,String msg,String time,String type){

       this.date=date;
       this.time=time;
       this.msg=msg;
       this.type=type;

    }

    public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return date;
    }
    public void setTime(String time){
        this.time=time;
    }
    public String getTime(){
        return time;
    }
    public void setMessage(String msg){
        this.msg=msg;
    }
    public String getMessage(){
        return msg;
    }
    public void setType(String type){
        this.type=type;
    }
    public String getType(){
        return type;
    }


}
