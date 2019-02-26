package com.codecool.place;

import com.codecool.main.UI;
import com.codecool.place.types.Place;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class PlaceManager {

    private PlaceCreator placeFactory;
    private PlaceDeserializer fileHandler;
    private UI ui;

    public PlaceManager(UI ui, PlaceCreator placeFactory, PlaceDeserializer placeDeserializer) {
        this.placeFactory = placeFactory;
        this.fileHandler = placeDeserializer;
        this.ui = ui;
    }


    public Place createPlace(String placeType, String name) {
        Place place = placeFactory.createPlace(placeType, name);
        return place;
    }

    public Place getPlaceById(int id) {
        return placeFactory.getPlaceById(id);
    }

    public void save() {
        try {
            fileHandler.save(placeFactory, placeFactory.getPlaceById(0));
        } catch (ParserConfigurationException |
            TransformerException | IOException | SAXException e) {
            ui.print(e.toString());
        }
    }

    public void load() {
        Place[] places = null;
        try {
            fileHandler.load(placeFactory);
        } catch (ParserConfigurationException |
            TransformerException | IOException | SAXException e) {
            ui.print(e.toString());
        }
    }
}
