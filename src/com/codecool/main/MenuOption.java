package com.codecool.main;

public class MenuOption {
    private String label;
    private String selector;
    private boolean exitMenu = false;
    private UI ui;
    public MenuOption() {
    }
    public MenuOption(String label) {
        this.label = label;
    }
    public MenuOption(String label, String selector) {
        this.label = label;
        this.selector = selector;
    }
    public void setSelector(int selector) {
        this.selector = Integer.toString(selector);
    }
    public void setExitMenu(boolean exitMenu) {
        this.exitMenu = exitMenu;
    }
    public boolean exitMenu() {
        return exitMenu;
    }
    public void run() {
        // Override this method on instantination to place code to be executed
    }
    public void display() {
        ui.print("(" + selector + ") " + label);
    }
}
