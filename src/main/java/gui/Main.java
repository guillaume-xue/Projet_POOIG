package gui;
import model.carcassonne.*;
public class Main {

    private Menu menu;

    public Main(){
        this.menu = new Menu();
        this.menu.setVisible(true);
    }

    public static void main(String[] args) {
        //new Main();
        new AffichageCarc();
    }

}
