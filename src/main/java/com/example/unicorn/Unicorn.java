package com.example.unicorn;

import javax.persistence.*;

@Entity
public class Unicorn {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;
    public String color;
    public String name;

    @ManyToOne
    public Location location;

    public Unicorn() {}

    public Unicorn(String color,String name) {
        this.color = color;
        this.name = name;
    }

    public String toString() {
        return this.color + " unicorn named " + this.name + ", in " + (this.location != null ? this.location.name : "no location");
    }
}