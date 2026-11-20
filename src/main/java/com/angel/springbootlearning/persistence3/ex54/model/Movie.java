
/*
 * Exercise 54 - Create First JPA Entity
 * Purpose: Define a Movie entity and map it to an H2 database table.
 * URLs: None. REST endpoints are not implemented yet.
 */
package com.angel.springbootlearning.persistence3.ex54.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table 
@Entity(name="movies")
public class Movie {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, updatable = false)
    private String name;

    @Column (nullable  = false, updatable = false)
    private String director;

    @Column (name="release_year", nullable  = false, updatable = false)
    private Integer year;

    private Integer stock;

    @Column(name = "movie-value")
    private BigDecimal value;

    // Required by JPA  to instantiate entities.
    protected Movie() {}

    public Movie(String name, String director, Integer year, Integer stock, BigDecimal value) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.stock = stock;
        this.value = value;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDirector() { return director; }
    public Integer getYear() { return year; }
    public Integer getStock() { return stock; }
    public BigDecimal getValue() { return value; }

    public void setStock(Integer stock) { this.stock = stock; }
    public void setValue(BigDecimal value) { this.value = value; }
    
}
