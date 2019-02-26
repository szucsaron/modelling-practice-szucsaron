package com.codecool.entity;

import com.codecool.place.types.EntityContainerPlace;

public abstract class Entity {
    private int id;
    private final String type;

    public int getSize() {
        return size;
    }

    private int size;

    public Entity(int id, String type, int size) {
        this.id = id;
        this.type = type;
        this.size = size;
    }

    private EntityContainerPlace container;
    public int getId() {
        return id;
    }

    public void attachContainer (EntityContainerPlace container) {
        this.container = container;
    }

    public EntityContainerPlace getContainer () {
        return container;
    }

    public String getType() {
        return type;
    }

    public boolean isAttached() {
        return container != null;
    }
}
