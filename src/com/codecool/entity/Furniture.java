package com.codecool.entity;

public class Furniture extends Entity {
    private String type;
    public Furniture(int id, String type, int size) {
        super(id, "furniture", size);
    }
}
