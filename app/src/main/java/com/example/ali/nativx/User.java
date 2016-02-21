package com.example.ali.nativx;

/**
 * Created by ali on 2/20/2016.
 */
public class User {
    String first_name, last_name, email, password;

    public User (String email){
        this.email = email;
    }

    /*public User (String first_name, String last_name, String email, String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }
    */
    /*public boolean addUser(){
        DatabaseManager DbMan = DatabaseManager.getInstance();
        DbMan.addUser(name,username,password,email);
        return true;
    }
    */
}
