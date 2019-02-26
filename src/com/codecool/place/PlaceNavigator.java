package com.codecool.place;

import com.codecool.place.types.Place;

public class PlaceNavigator {
    private Place place = new Place() {};
    public Place prevPlace;

    public void goTo(Place place) {
        this.prevPlace = this.place;
        this.place = place;
    }
    public void goTo(int nodeIndex) {
        this.goTo(place.getNeighbour(nodeIndex));
    }

    public Place getPlace() {
        return place;
    }

}
