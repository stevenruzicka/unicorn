package com.example.unicorn;
import javax.persistence.*;
import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id;
    public String name;

    @OneToMany(mappedBy = "location")
    public List<Unicorn> unicorns;
    public Location() {}

    public Location(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name + " with " + (this.unicorns != null ? this.unicorns.size() + " unicorns" : "by themself");
    }
}