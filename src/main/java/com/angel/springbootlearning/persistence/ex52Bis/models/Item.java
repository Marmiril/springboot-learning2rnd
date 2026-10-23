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
    private ItemType type;
    private double value;
    private int inventary;

    protected Item() {}

    public Item(String name, ItemType type, double value, int inventary){
        this.name = name;
        this.type = type;
        this.value = value;
        this.inventary = inventary;
    }

    public String getName() { return name; }
    public ItemType getType() { return type; }
    public double getValue() { return value; }
    public int getInventary() { return inventary; }
    
}
