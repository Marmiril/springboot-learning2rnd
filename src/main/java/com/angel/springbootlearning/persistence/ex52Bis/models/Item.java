package com.angel.springbootlearning.persistence.ex52Bis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
@Entity 
public class Item {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    @Column(name = "item_value")
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

    public void setName(String name) { this.name = name; }
    public void setType(ItemType type) { this.type = type; }
    public void setValue(double value) { this.value = value; }
    public void setInventary (int inventary) { this.inventary = inventary; }
    
}
