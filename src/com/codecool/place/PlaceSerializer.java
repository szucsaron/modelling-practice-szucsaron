package com.codecool.place;

import com.codecool.place.types.Place;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceSerializer {
    private String filename;

    public PlaceSerializer(String filename) {
        this.filename = filename;
    }

    public PlaceSerializer() {

    }

    public void save(PlaceCreator placeFactory, Place entryPlace) throws ParserConfigurationException,
        TransformerException, FileNotFoundException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element docPlaces = doc.createElement("places");
        Element docLinks = doc.createElement("links");
        Element docPlaceData = doc.createElement("placedata");
        doc.appendChild(docPlaceData);
        docPlaceData.appendChild(docPlaces);
        docPlaceData.appendChild(docLinks);
        Set<String> pairedLists = new HashSet<>();

        Place[] places = placeFactory.getAllPlaces();

        for (Place place : places) {
            System.out.println(place.getName());
            saveNext(doc, docPlaces, docLinks, place, pairedLists);
        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(domSource, result);
        System.out.println(doc.getChildNodes().getLength());

    }

    private void saveNext(Document doc, Element docPlaces, Element docLinks, Place rootPlace, Set<String> pairedLists) {
        List<Place> neighbours = rootPlace.getAllNeighbours();
        Element docNewPlace = doc.createElement("place");
        docNewPlace.setAttribute("id", Integer.toString(rootPlace.getId()));
        docNewPlace.setAttribute("type", rootPlace.getClass().getSimpleName());
        docNewPlace.setAttribute("name", rootPlace.getName());
        docPlaces.appendChild(docNewPlace);
        for (Place neighbourPlace : neighbours) {
            String id1 = Integer.toString(rootPlace.getId());
            String id2 = Integer.toString(neighbourPlace.getId());
            if (!pairedLists.contains(id2 + "|" + id1)) {
                Element docNewLink = doc.createElement("link");
                docNewLink.setAttribute("id1", id1);
                docNewLink.setAttribute("id2", id2);
                docLinks.appendChild(docNewLink);
                pairedLists.add(id1 + "|" + id2);
            }

        }
    }
}
