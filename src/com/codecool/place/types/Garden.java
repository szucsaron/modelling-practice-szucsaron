package com.codecool.place.types;

public class Garden extends EntityContainerPlace {
    private static String type = "garden";
    public Garden(String name) {
        super(name);
    }
    public Garden(int id, String name, int capacity) {
        super(id, name, capacity);

    }

}
