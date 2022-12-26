package model;

import gui.Menu;

public class Controler {

    private Menu menu;

    public Controler(Menu menu){
        this.menu = menu;
    }

    public void ButtonPresed(){
        menu.getButtonJouer().addActionListener(e -> {
            menu.getButtonJouer().setVisible(false);
            menu.getDominos().setVisible(true);
            menu.getCarcassonne().setVisible(true);
            menu.validate();
        });
    }

}
