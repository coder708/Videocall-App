package com.example.videocall;

public class Users {

    String mail1,user1,password1;


    public Users() {
    }

    public Users(String mail1, String user1, String password1) {
        this.mail1 = mail1;
        this.user1 = user1;
        this.password1 = password1;
    }

    public String getMail1() {
        return mail1;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }
}
