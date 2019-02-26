package com.codecool.place.types;

import com.codecool.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Room extends EntityContainerPlace {
    private List<Entity> entities = new ArrayList<>();


    public Room(String name) {
        super(name);
    }

    public Room(int id, String name, int capacity) {
        super(id, name, capacity);
    }
}
