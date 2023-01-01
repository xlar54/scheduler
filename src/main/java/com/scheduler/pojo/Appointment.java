package com.scheduler.pojo;


/**
 * all plain old java objects are containers for some type of database record
 * they do not contain logic and only have private members
 */
import java.util.Date;


public class Appointment {
        private int Appointment_ID;
        private String Title;
        private String Description;
        private String Location;
        private String Type;
        private Date Start;
        private Date End;
        private String Created_By;
        private java.util.Date Last_Update;
        private String Last_Updated_By;
        private int Customer_ID;
        private int User_ID;
        private int Contact_ID;


        /**
         *getter for Appointment_ID
         * @return return Appointment_ID
         */
        public int getAppointment_ID() {
                return Appointment_ID;
        }

        public void setAppointment_ID(int ID) {this.Appointment_ID = ID; }


        public String getTitle() {
                return Title;
        }

        /**
         *setter for title
         * @param Title title
         */
        public void setTitle(String Title) {
                this.Title = Title;
        }

        /**
         *getter for description
         * @return return description
         */
        public String getDescription() {
                return Description;
        }

        /**
         * setter for description
         * @param Description description
         */
        public void setDescription(String Description) {
                this.Description = Description;
        }

        /**
         * getter for location
         * @return return location
         */
        public String getLocation() {
                return Location;
        }

        /**
         * setter for location
         * @param Location location
         */
        public void setLocation(String Location) {
                this.Location = Location;
        }

        /**
         *getter for type
         * @return return type
         */
        public String getType() {
                return Type;
        }


        /**
         * setter for type
         * @param Type type
         */

        public void setType(String Type) {
                this.Type = Type;
        }




        public Date getStart() {
                return Start;
        }
        public void setStart(Date Start) {
                this.Start = Start;
        }

        public Date getEnd() {
                return End;
        }
        public void setEnd(Date End) {
                this.End = End;
        }

        /**
         * getter for createdby
         * @return createdby
         */
        public String getCreated_by() {
                return Created_By;
        }

        /**
         * setter for created by
         * @param Created_By created by
         */
        public void setCreated_by(String Created_By) {
                this.Created_By = Created_By;
        }

        /**
         * getter for last update
         * @return last update
         */
        public java.util.Date getLast_update() {
                return Last_Update;
        }

        /**
         * setter for last update
         * @param Last_Update last update
         */
        public void setLast_update(java.util.Date Last_Update) {
                this.Last_Update = Last_Update;
        }

        /**
         * getter for lastupdatedby
         * @return lastupdatedby
         */
        public String getLast_updated_by() {
                return Last_Updated_By;
        }

        /**
         * setter for lastupdatedby
         * @param Last_Updated_By lastupdatedby
         */
        public void setLast_updated_by(String Last_Updated_By) {
                this.Last_Updated_By = Last_Updated_By;
        }


        /**
         * getter for customerId
         * @return customerID
         */
        public int getCustomer_id() {
                return Customer_ID;
        }

        /**
         * setter for CustomerID
         * @param Customer_ID customerID
         */
        public void setCustomer_id(int Customer_ID) {
                this.Customer_ID = Customer_ID;
        }

        /**
         *getter for userID
         * @return userID
         */
        public int getUser_id() {
                return User_ID;
        }

        /**
         * setter for userID
         * @param User_ID userID
         */
        public void setUser_id(int User_ID) {
                this.User_ID = User_ID;
        }

        /**
         * getter for contactID
         * @return contactID
         */
        public int getContact_id() {
                return Contact_ID;
        }

        /**
         * setter for contactID
         * @param Contact_ID contactID
         */
        public void setContact_id(int Contact_ID) {
                this.Contact_ID = Contact_ID;
        }

        public int getCustomer_ID() {
                return Customer_ID;
        }

        public void setCustomer_ID(int Customer_ID) {
                this.Customer_ID = Customer_ID;
        }

        public int getUser_ID() {
                return User_ID;
        }

        public void setUser_ID(int User_ID) {
                this.User_ID = User_ID;
        }

        public int getContact_ID() {
                return Contact_ID;
        }

        public void setContact_ID(int Contact_ID) {
                this.Contact_ID = Contact_ID;
        }


}