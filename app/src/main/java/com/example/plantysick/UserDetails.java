package com.example.plantysick;


import android.content.Context;

public class UserDetails {
    private String userName, userEmail, userPassword, userContact, userConfirmPassword, DOB, FullName;
    Context context;

    public String getDOB() {
        return DOB;
    }

    public String getFullName() {
        return FullName;
    }

    public UserDetails(Context context, String userName, String userEmail, String userContact, String userConfirmPassword, String DOB, String FullName) {
        this.context = context;
        this.userName = userName;
        this.userEmail = userEmail;
         this.userContact = userContact;
        this.userConfirmPassword = userConfirmPassword;
        this.DOB = DOB;
        this.FullName = FullName;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }
}
