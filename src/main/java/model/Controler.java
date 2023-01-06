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
            dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
            dominos.repaint();
        });
        dominos.getTurnRight().addActionListener(e -> {
            dominos.getTuile().tournerDroite();
            dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
            dominos.repaint();
        });
        dominos.getSubmit().addActionListener(e -> {
            if(dominos.getPlateau().sacEmpty() && dominos.getAffPlateau().addTuile(dominos.getTuile())){
                dominos.setTuile(dominos.getPlateau().nextTuile());
                dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
                dominos.repaint();
            }
        });
        dominos.getSkip().addActionListener(e -> {
            if(!dominos.getPlateau().sacEmpty()){
                dominos.setTuile(dominos.getPlateau().nextTuile());
                dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
                dominos.repaint();
            }
        });

    }

}
