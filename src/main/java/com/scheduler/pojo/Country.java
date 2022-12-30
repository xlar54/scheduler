package com.scheduler.pojo;

public class Country{
        private int Country_ID;
        private String Country;
        private java.util.Date Create_Date;
        private String Created_By;
        private java.util.Date Last_Update;
        private String Last_Updated_By;

        public int getCountry_ID() {
                return Country_ID;
        }

        public String getCountry(){
                return Country;
        }

        public void setCountry(String Country){
                this.Country=Country;
        }

        public String getCreated_by(){
                return Created_By;
        }

        public void setCreated_by(String Created_By){
                this.Created_By=Created_By;
        }

        public java.util.Date getCreate_date(){
                return Create_Date;
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