package com.devsenior.atellez_jbenavides.model;

import java.time.LocalDate;

public class User {


    private String name;
    private String email;
    private String id;
    private LocalDate registerDate;


    public User(String name, String email, String id) {
        this(name, email, id, LocalDate.now());
    }


    public User(String name, String email, String id, LocalDate registerDate) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.registerDate = registerDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    

    

}
