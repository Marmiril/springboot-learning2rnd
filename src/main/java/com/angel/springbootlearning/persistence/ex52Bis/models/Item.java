package com.angel.springbootlearning.persistence.ex52Bis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Item {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private double price;
    private int stock;

    protected Item() {}

    public Item(String name, String type, double price, int stock){
        this.name = name;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public String type() { return type; }
    
}
