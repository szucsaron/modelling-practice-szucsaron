package com.codecool.place;


import com.codecool.place.types.Garden;
import com.codecool.place.types.Place;
import com.codecool.place.types.Room;

import java.util.Collection;
import java.util.HashMap;


public class PlaceCreator {
    private int maxPlaceId = 0;
    private HashMap<Integer, Place> idMap = new HashMap<>();


    public Place createPlace(int id, String placeType, String name, int capacity) {
        Place place = null;

        switch (placeType) {
            case "Room":
                place = new Room(id, name, capacity);
                break;
            case "Garden":
                place = new Garden(id, name, capacity);
                break;
            case "Corridor":
                place = new Room(id, name, capacity);
                break;
        }
        idMap.put(id, place);

        return place;
    }

    public Place createPlace(String placeType, String name) {
        Place place = this.createPlace(maxPlaceId, placeType, name, 10);
        maxPlaceId++;
        return place;
    }

    public Place getPlaceById(int id) {
        return idMap.get(id);
    }

    public Place[] getAllPlaces() {
        Collection<Place> places = idMap.values();
        return places.toArray(new Place[places.size()]);
    }
}
