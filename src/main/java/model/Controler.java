package model;

import gui.Menu;
import gui.dominos.Jeu_Dominos;

public class Controler {

    private Menu menu;
    private Jeu_Dominos dominos;

    public Controler(Menu menu){
        this.menu = menu;
    }
    public Controler(Jeu_Dominos dominos){
        this.dominos = dominos;
    }

    public void menuButtonPresed(){
        menu.getButtonJouer().addActionListener(e -> {
            menu.getButtonJouer().setVisible(false);
            menu.getDominos().setVisible(true);
            menu.getCarcassonne().setVisible(true);
            menu.validate();
        });
    }

    public void domimosButtunPresed(){
        dominos.getTurnLeft().addActionListener(e -> {
            dominos.getTuile().tournerGauche();
            System.out.println(dominos.getTuile());
            dominos.getAfficheTuile().miseAJourTuile(dominos.getTuile().toString());
            dominos.repaint();
        });
        dominos.getTurnRight().addActionListener(e -> {
            dominos.getTuile().tournerDroite();
            System.out.println(dominos.getTuile());
            dominos.getAfficheTuile().miseAJourTuile(dominos.getTuile().toString());
            dominos.repaint();
        });
    }

}
