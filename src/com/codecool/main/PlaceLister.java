package com.codecool.main;

import com.codecool.place.types.Place;

import java.util.HashMap;
import java.util.List;

public class PlaceLister {
    private HashMap<Integer, Place> previousVisitedPlaces;
    private UI ui;

    public PlaceLister(UI ui) {
        this.ui = ui;
    }
    public void list(Place place) {
        previousVisitedPlaces = new HashMap<>();
        nextPlace(0, place, new Place() {}, "", false, false);
    }

    private void printPlaceData(Place place, String indent, boolean highlight) {
        String hPrefix;
        String hSuffix;
        if (highlight) {
            hPrefix = "";
            hSuffix = " [-->]";
        } else {
            hPrefix = "";
            hSuffix = "";
        }
        ui.print (indent + "|_" + hPrefix + place.getName()
            + " [" + place.getClass().getSimpleName() + "] "
            + "id:" + Integer.toString(place.getId()) + hSuffix);
    }

    private void nextPlace(int transitions, Place entryPlace, Place prevPlace, String indent, boolean hasSubsequentSiblings, boolean finisHAtHere) {
        transitions++;
        if (finisHAtHere) {
            printPlaceData(entryPlace, indent, true);
            return;
        } else {
            printPlaceData(entryPlace, indent, false);
        }
        List<Place> neighbours = entryPlace.getAllNeighbours();
        previousVisitedPlaces.put(entryPlace.getId(), entryPlace);

        int maxNeighbours = entryPlace.countNeighbours();
        if (neighbours.get(maxNeighbours - 1).equals(prevPlace)) {
            maxNeighbours--;
        }
        for (int i = 0; i < maxNeighbours; i++) {
            if (!neighbours.get(i).equals(prevPlace)) {
                String newIndent = indent + "   ";
                boolean newSubseq = false;
                if (maxNeighbours >= 2 && neighbours.get(i).countNeighbours() >= 2 && i < maxNeighbours - 1) {
                    newSubseq = true;
                }
                if (hasSubsequentSiblings) {
                    newIndent = indent + "|  ";
                }
                if (transitions < 10) {

                    if (previousVisitedPlaces.get(neighbours.get(i).getId()) == null) {
                        nextPlace(transitions, neighbours.get(i), entryPlace, newIndent, newSubseq, false);
                    } else {
                        nextPlace(transitions, neighbours.get(i), entryPlace, newIndent, newSubseq, true);
                    }
                }
            }
        }
    }

    private boolean checkIfOccured() {
        return true;
    }
}
