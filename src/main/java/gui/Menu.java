package gui;

import model.Controler;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JButton buttonJouer, dominos, carcassonne;
    private Controler controler;

    public Menu(){

        this.controler = new Controler(this);

        this.setTitle("Projet_POOIG");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //body
        JPanel body = new JPanel();
        body.setBackground(new Color(229, 229, 229, 255));
        body.setLayout(new GridLayout(2,1));
        this.getContentPane().add(body);

        //bouton Jouer
        buttonJouer = new JButton("JOUER");
        body.add(buttonJouer);

        JPanel select = new JPanel();
        select.setLayout(new GridLayout(2,1));
        select.setBackground(new Color(229, 229, 229, 255));

        //bouton dominos
        dominos = new JButton("Jeu_Dominos");
        dominos.setVisible(false);
        select.add(dominos);

        //bouton carcassonne
        carcassonne = new JButton("Carcassonne");
        carcassonne.setVisible(false);
        select.add(carcassonne);

        body.add(select);

        //controleur
        controler.menuButtonPresed();
    }

    public JButton getButtonJouer() {
        return buttonJouer;
    }

    public JButton getDominos() {
        return dominos;
    }

    public JButton getCarcassonne() {
        return carcassonne;
    }
}
