package com.codecool.main;


public class MenuLink extends MenuOption {
    private Menu menu;
    
    public MenuLink(Menu menu) {
        super(menu.getHeader());
        this.menu = menu;
    }
    @Override
    public void run() {
        menu.handle();
    }
}
