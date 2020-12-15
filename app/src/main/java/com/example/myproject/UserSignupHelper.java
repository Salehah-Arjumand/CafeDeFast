package com.example.myproject;

public class UserSignupHelper {

    String UserName, UserRollNumber, UserEmail, UserPhone, UserPassword;

    public UserSignupHelper() {

    }

    public UserSignupHelper(String userName, String userRollNumber, String userEmail, String userPhone, String userPassword) {
        UserName = userName;
        UserRollNumber = userRollNumber;
        UserEmail = userEmail;
        UserPhone = userPhone;
        UserPassword = userPassword;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserRollNumber() {
        return UserRollNumber;
    }

    public void setUserRollNumber(String userRollNumber) {
        UserRollNumber = userRollNumber;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
