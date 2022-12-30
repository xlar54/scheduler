package com.scheduler.pojo;

public class Appointment {
    private String Title;
private String Description;
private String Location;
private String Type;
private String Created_By;
private java.util.Date Last_Update;
private String Last_Updated_By;
private int Customer_ID;
private int User_ID;
private int Contact_ID;

public String getTitle(){
        return Title;
        }

public void setTitle(String Title){
        this.Title=Title;
        }

public String getDescription(){
        return Description;
        }

public void setDescription(String Description){
        this.Description=Description;
        }

public String getLocation(){
        return Location;
        }

public void setLocation(String Location){
        this.Location=Location;
        }

public String getType(){
        return Type;
        }

public void setType(String Type){
        this.Type=Type;
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

public int getCustomer_id(){
        return Customer_ID;
        }

public void setCustomer_id(int Customer_ID){
        this.Customer_ID=Customer_ID;
        }

public int getUser_id(){
        return User_ID;
        }

public void setUser_id(int User_ID){
        this.User_ID=User_ID;
        }

public int getContact_id(){
        return Contact_ID;
        }

public void setContact_id(int Contact_ID){
        this.Contact_ID=Contact_ID;
        }
        }