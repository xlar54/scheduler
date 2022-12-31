package com.scheduler.pojo;

public class FirstLevelDivision {

	private int Division_ID;
	private String Division;
	private String Created_By;
	private java.util.Date Last_Update;

	private java.util.Date Create_Date;
	private String Last_Updated_By;
	private int Country_ID;

	public void setDivision_ID(int ID) {this.Division_ID = ID;}

	public Integer getDivision_ID() { return this.Division_ID; }

	public String getDivision(){
		return Division;
	}

	public void setDivision(String Division){
		this.Division=Division;
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

	public int getCountry_id(){
		return Country_ID;
	}

	public void setCountry_id(int Country_ID){
		this.Country_ID=Country_ID;
	}

	public void setCreate_Date(java.util.Date dateTime){this.Create_Date = dateTime;}

	public java.util.Date getCreate_Date(){return Create_Date;}

	@Override
	public String toString() {
		return this.getDivision();
	}
}