package com.codecool.entity;

public class Person extends Entity {
    private String name;
    public Person(int id, String name, int size) {
        super(id, "person", size);
        this.name = name;
    }
}
