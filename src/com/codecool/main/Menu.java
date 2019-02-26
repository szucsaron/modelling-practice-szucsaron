package com.codecool.main;

public class Menu {
    private static int optionSize = 10;
    private MenuOption[] options = new MenuOption[optionSize];
    private int maxOption = 1;
    private String header;
    private UI ui;

    public Menu(String header) {
        this(header, "Back to previous");
    }
    public Menu(String header, String exitMessage) {
        this.header = header;
        options[0] = new MenuOption(exitMessage, "0");
        options[0].setExitMenu(true);
    }
    public void handle() {
        boolean exit = false;
        while (!exit) {
            display();
            exit = handleSelection();
        }
    }
    private void display() {
        ui.print("\n");
        ui.print(header);
        for (int i = 1; i < maxOption; i++) {
            options[i].display();
        }
        options[0].display();
    }
    private boolean handleSelection() {
        boolean selectionValid = false;
        int input = 0;
        while (!selectionValid) {
            try {
                input = ui.getIntInput("Enter a number: ");
                options[input].run();
                selectionValid = true;
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                ui.print("Selected option doesn't exist! Try again.");
            }
        }
        return options[input].exitMenu();
    }
    public void addOption(MenuOption option) {
        option.setSelector(maxOption);
        options[maxOption] = option;
        maxOption++;
    }
    public MenuOption getMenuLink() {
        return new MenuLink(this);
    }
    public String getHeader() {
        return header;
    }

}
