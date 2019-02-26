package com.codecool.place.types;

import com.codecool.entity.Entity;
import com.codecool.entity.Furniture;
import com.codecool.entity.Person;
import com.codecool.place.NotEnoughSpaceException;


import java.util.*;

public class EntityContainerPlace extends Place {

    HashSet<Entity> entities = new HashSet<>();
    private int capacity;
    private int filledSpace = 0;


    public EntityContainerPlace(String name) {
        super(name);
    }

    public EntityContainerPlace(int id, String name, int capacity) {
        super(id, name);
        this.capacity = capacity;
    }

    private boolean canAccommodate(Entity entity) {
        if (capacity - filledSpace - entity.getSize() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addEntity(Entity entity) throws NotEnoughSpaceException{
        if (canAccommodate(entity)) {
            if (entity.isAttached()) {
                entity.getContainer().removeEntity(entity);
            }
            entities.add(entity);
            entity.attachContainer(this);
            filledSpace += entity.getSize();
        } else {
            throw new NotEnoughSpaceException();
        }
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void removeEntity(Entity entity) {
        if (entities.contains(entity)) {
            entities.remove(entity);
            filledSpace -= entity.getSize();
        }
    }

    public List<Furniture> getFurniture() {
        Entity[] entArray = getEntityArray();
        List<Furniture> furniture = new ArrayList<>();
        for (Entity entity : entArray) {
            if (entity instanceof Furniture) {
                furniture.add((Furniture)entity);
            }
        }
        return furniture;
    }

    public List<Person> getPersons() {
        Entity[] entArray = getEntityArray();
        List<Person> persons = new ArrayList<>();
        for (Entity entity : entArray) {
            if (entity instanceof Person) {
                persons.add((Person)entity);
            }
        }
        return persons;
    }

    private Entity[] getEntityArray() {
        Entity[] entArray = entities.toArray(new Entity[entities.size()]);
        return entArray;
    }

}
