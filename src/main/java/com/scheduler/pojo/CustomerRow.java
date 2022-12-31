package com.scheduler.pojo;

public class CustomerRow {

    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private int Division_ID;
    private String Division;
    private int Country_ID;
    private String Country;


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

    public int getDivisionID(){ return Division_ID;}

    public void setDivisionID(Integer DivisionID){
        this.Division_ID=DivisionID;
    }

    public String getDivision(){ return Division;}

    public void setDivision(String Division){
        this.Division=Division;
    }
    public int getCountryID(){ return Country_ID;}

    public void setCountryID(int CountryID){

        Country_ID=CountryID;
    }
    public String getCountry(){ return Country;}

    public void setCountry(String Country){
        this.Country=Country;
    }

}
