package com.example.dhwanigrocerystore.models;

public class UserModel {
    String name;
    String email;
    String password;
    public UserModel(){

    }
    public UserModel(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getEmail() {
        return email;
    }
}