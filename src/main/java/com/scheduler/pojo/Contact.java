package com.scheduler.pojo;

public class Contact {
private String Contact_Name;
private String Email;

private int Contact_ID;

        /**
         *
         * @return
         */
        public String getContact_name(){
        return Contact_Name;
        }

        /**
         *
         * @param Contact_Name
         */
        public void setContact_name(String Contact_Name){
        this.Contact_Name=Contact_Name;
        }

        /**
         *
         * @return
         */
        public String getEmail(){
        return Email;
        }

        /**
         *
         * @param Email
         */
        public void setEmail(String Email){
        this.Email=Email;
        }

        /**
         *
         * @param ID
         */
        public void setContact_ID(int ID){this.Contact_ID = ID;}
        public int getContact_ID(){return this.Contact_ID;}

        @Override
        public String toString() {
                return this.getContact_name();
        }
}

