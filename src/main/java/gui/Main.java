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
        new AffichageCarc(1280, 720, 75, 4);
        /*SwingUtilities.invokeAndWait(
            new Runnable(){
                public void run(){
                    
                }
            }
        );*/
    }

}
