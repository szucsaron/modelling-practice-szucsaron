package com.codecool.place.types;


import java.util.ArrayList;
import java.util.List;

public abstract class Place {
    private List<Place> relatives = new ArrayList<>();
    private String name;
    private int id;

    public Place() {}

    public Place(String name) {
        this.name = name;
    }

    public Place(int id, String name) {
        this.name = name;
        this.id = id;
    }

    private void addPlace(Place place) {
        relatives.add(place);
    }

    public void join(Place place) {
        this.addPlace(place);
        place.addPlace(this);
    }

    public List<Place> getAllNeighbours() {
        return relatives;
    }

    public int countNeighbours() {
        return relatives.size();
    }

    public Place getNeighbour(int index) {
        return relatives.get(index);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


}
