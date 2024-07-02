package com.example.apartmentpal;

public class Visitor {
    int id;
    String name;
    String role;

    Visitor(String id, String name, String role){
        this.id = Integer.parseInt(id);
        this.name = name;
        this.role = role;
    }
}
