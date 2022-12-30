package com.scheduler.pojo;

public class User {
        private int User_ID;
        private String User_Name;
        private String Password;
        private java.util.Date Create_date;
        private String Created_By;
        private java.util.Date Last_Update;
        private String Last_Updated_By;


        public int getUser_ID() { return User_ID; }

        public void setUser_ID(int ID) { this.User_ID = ID; }

        public String getUser_name(){
                return User_Name;
                }

        public void setUser_name(String User_Name){
                this.User_Name=User_Name;
                }
        public String getPassword() { return this.Password; }

        public void setPassword(String password) { this.Password = password; }

        public java.util.Date getCreate_date(){
                return Create_date;
        }

        public void setCreate_date(java.util.Date Create_Date){
                this.Create_date=Create_Date;
        }

        public String getCreated_by() { return Created_By; }

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