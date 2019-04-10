package com.example.demo;

import javax.persistence.*;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private VendorAddress address;

    private Vendor() { }

    public Vendor(String name, VendorAddress address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VendorAddress getAddress() {
        return address;
    }
}
