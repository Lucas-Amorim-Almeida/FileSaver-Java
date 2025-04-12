package com.filesaver;

import com.filesaver.domain.core.DTO.Username;
import com.filesaver.domain.core.User;
import com.filesaver.domain.core.DTO.Password;

public class Main {
    public static void main(String[] args) {
        try{
            Username username = new Username("john_doe");
            Password password = new Password("abc$ABC$123");

            User user = new User(username, password);

            System.out.println("\n\nusername ->" + user.getUsername().toString());
            System.out.println("password -> " + user.getPassword().toString()+"\n\n");

        } catch(Exception error){
            System.err.println(error.getMessage());
        }


    }
}
