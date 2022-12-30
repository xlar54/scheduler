package com.scheduler.pojo;

public class User {
private String User_Name;
private String Created_By;
private java.util.Date Last_Update;
private String Last_Updated_By;

public String getUser_name(){
        return User_Name;
        }

public void setUser_name(String User_Name){
        this.User_Name=User_Name;
        }

public String getCreated_by(){
        return Created_By;
        }

public void setCreated_by(String Created_By){
        this.Created_By=Created_By;
        }

public java.util.Date getLast_update(){
        return Last_Update;
        }

public void setLast_update(java.util.Date Last_Update){
        this.Last_Update=Last_Update;
        }

public String getLast_updated_by(){
        return Last_Updated_By;
        }

public void setLast_updated_by(String Last_Updated_By){
        this.Last_Updated_By=Last_Updated_By;
        }
}