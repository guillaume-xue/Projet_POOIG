package gui;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import model.carcassonne.*;
public class Main {

    private Menu menu;

    public Main(){
        this.menu = new Menu();
        this.menu.setVisible(true);
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        //new Main();
        SwingUtilities.invokeAndWait(
            new Runnable(){
                public void run(){
                    new AffichageCarc(1200, 700, 50);
                }
            }
        );
    }

}
