package com.codecool.main;

import com.codecool.entity.Entity;
import com.codecool.entity.Furniture;
import com.codecool.entity.Person;
import com.codecool.place.*;
import com.codecool.place.types.EntityContainerPlace;
import com.codecool.place.types.Place;

import java.util.Scanner;
import java.util.Set;


public class Main {

    public static void main(String[] args) {
        // menuTest();
        try {
            placeStructureTest();
        } catch (NotEnoughSpaceException e) {
            e.printStackTrace();
        }
    }

    public static void menuTest() {
        // Menu of greatest cats
        Menu catMenu = new Menu("Cats of the world");
        catMenu.addOption(new MenuOption("Meow") {
            @Override
            public void run() {
                System.out.println("121212.");
            }
        });
        catMenu.addOption(new MenuOption("Bird (not a cat)") {
            @Override
            public void run() {
                System.out.println("Program 12321.");
            }
        });
        catMenu.addOption(new MenuOption("Octopus (not a cat either)") {
            @Override
            public void run() {
                System.out.println("56834 started.");
            }
        });

        // Menu of humble mainitude
        Menu mainMenu = new Menu("Test Menu", "Exit program");
        mainMenu.addOption(new MenuOption("Start program") {
            @Override
            public void run() {
                System.out.println("Program started.");
            }
        });
        mainMenu.addOption(new MenuOption("Do interesting stuff") {
            @Override
            public void run() {
                System.out.println("Interesting stuff happening now, just look out the window.");
            }
        });
        mainMenu.addOption(new MenuOption("Do boring stuff") {
            @Override
            public void run() {
                System.out.println("Boring stuff happening now, just look out the window.");
            }
        });
        mainMenu.addOption(catMenu.getMenuLink());

        // Start main menu
        mainMenu.handle();
    }

    public static void placeStructureTest() throws NotEnoughSpaceException{


        UI ui = new UI();
        PlaceManager placeMgr = new PlaceManager(ui, new PlaceCreator(), new PlaceDeserializer("src/places.xml"));

        placeMgr.load();

        PlaceNavigator mov = new PlaceNavigator();
        mov.goTo(placeMgr.getPlaceById(0));
        PlaceLister placeLister = new PlaceLister(ui);
        Entity ent = new Furniture(45, "cupboard", 10);
        Entity ent2 = new Furniture(46, "cupboard", 5);
        Entity ent3 = new Person(6, "cucu", 10);

        ((EntityContainerPlace) placeMgr.getPlaceById(0)).addEntity(ent);
        //((EntityContainerPlace) placeMgr.getPlaceById(0)).addEntity(ent3);



        EntityContainerPlace ePlace = (EntityContainerPlace) placeMgr.getPlaceById(0);
        System.out.println(ePlace.getFurniture().size());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = ui.getIntInput("Enter a place id: ");
            Place place = placeMgr.getPlaceById(choice);
            placeLister.list(placeMgr.getPlaceById(choice));
            if (place instanceof EntityContainerPlace) {
                Set<Entity> entitySet = ((EntityContainerPlace) place).getEntities();
                int size = entitySet.size();
                if (size > 0) {
                    Entity[] entities = entitySet.toArray(new Entity[0]);
                    ui.print(entities[0].getId());
                }
            }
        }
        //System.out.println(mov.prevPlace.getName());
    }

    public static void testLoader(PlaceManager placeMgr) {
        Place livingRoom = placeMgr.createPlace("Room", "Living Room");
        Place toilet = placeMgr.createPlace("Room", "Toilet");
        Place ballroom = placeMgr.createPlace("Room", "Ballroom");
        Place cell = placeMgr.createPlace("Room", "Cell");
        Place kitchen = placeMgr.createPlace("Room", "Kitchen");
        Place bedroom = placeMgr.createPlace("Room", "Bedroom");
        Place goatRoom = placeMgr.createPlace("Room", "Goatroom");
        Place teleportChamber = placeMgr.createPlace("Room", "Teleport Chamber");
        Place storage = placeMgr.createPlace("Room", "Storage room");
        Place westCorridor = placeMgr.createPlace("Room", "West Corridor");
        Place mainHall = placeMgr.createPlace("Room", "Main Hall");
        Place courtyard = placeMgr.createPlace("Garden", "Courtyard");
        livingRoom.join(toilet);
        livingRoom.join(ballroom);
        livingRoom.join(goatRoom);
        ballroom.join(cell);
        ballroom.join(kitchen);
        teleportChamber.join(kitchen);
        kitchen.join(bedroom);
        goatRoom.join(storage);
        bedroom.join(livingRoom);
        mainHall.join(livingRoom);
        mainHall.join(ballroom);
        mainHall.join(westCorridor);
        westCorridor.join(courtyard);
        courtyard.join(mainHall);
    }
}
