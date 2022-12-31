package com.scheduler.pojo;

public class Customer {
        private int Customer_ID;
private String Customer_Name;
private String Address;
private String Postal_Code;
private String Phone;
private String Created_By;
private java.util.Date Last_Update;
private String Last_Updated_By;
private int Division_ID;

public int getCustomer_ID() { return this.Customer_ID; }

        public void setCustomer_ID(int ID) {this.Customer_ID = ID; }

public String getCustomer_name(){
        return Customer_Name;
        }

public void setCustomer_name(String Customer_Name){
        this.Customer_Name=Customer_Name;
        }

public String getAddress(){
        return Address;
        }

public void setAddress(String Address){
        this.Address=Address;
        }

public String getPostal_code(){
        return Postal_Code;
        }

public void setPostal_code(String Postal_Code){
        this.Postal_Code=Postal_Code;
        }

public String getPhone(){
        return Phone;
        }

public void setPhone(String Phone){
        this.Phone=Phone;
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

public int getDivision_id(){
        return Division_ID;
        }

public void setDivision_id(int Division_ID){
        this.Division_ID=Division_ID;
        }
}