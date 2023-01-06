package gui;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import gui.carcassonne.AffichageCarc;
import model.carcassonne.*;
public class Main {

    private Menu menu;

    public Main(){
        this.menu = new Menu();
        this.menu.setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }

}
