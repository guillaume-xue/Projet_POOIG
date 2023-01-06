package gui;

import model.Controler;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JButton buttonJouer, dominos, carcassonne, joueurAndIa, joueur;
    private JButton j2, j3, j4;
    private Controler controler;
    private JPanel body, select, fond1, fond2;

    public Menu(){

        this.controler = new Controler(this);

        this.setTitle("Projet_POOIG");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //body
        body = new JPanel();
        body.setBackground(new Color(229, 229, 229, 255));
        this.getContentPane().add(body);

        //bouton Jouer
        buttonJouer = new JButton("JOUER");
        body.add(buttonJouer);



        bouttonGame();
        bouttonJAI();
        bouttonJoueur();

        //controleur
        controler.menuButtonPresed();
    }

    public void bouttonGame(){
        select = new JPanel();
        select.setLayout(new GridLayout(2,1));
        select.setBackground(new Color(229, 229, 229, 255));

        //bouton dominos
        dominos = new JButton("Jeu_Dominos_IG");
        dominos.setVisible(true);
        select.add(dominos);

        //bouton carcassonne
        carcassonne = new JButton("Carcassonne");
        carcassonne.setVisible(true);
        select.add(carcassonne);
    }

    public void bouttonJAI(){
        fond1 = new JPanel();
        fond1.setLayout(new GridLayout(2,1));
        fond1.setBackground(new Color(229, 229, 229, 255));

        joueurAndIa = new JButton("Joueur et IA");
        joueurAndIa.setVisible(true);
        fond1.add(joueurAndIa);

        joueur = new JButton("Joueur");
        joueur.setVisible(true);
        fond1.add(joueur);

    }

    public void bouttonJoueur(){
        fond2 = new JPanel();
        fond2.setLayout(new GridLayout(3,1));
        fond2.setBackground(new Color(229, 229, 229, 255));

        j2 = new JButton("2 joueurs");
        j2.setVisible(true);
        fond2.add(j2);

        j3 = new JButton("3 joueurs");
        j3.setVisible(true);
        fond2.add(j3);

        j4 = new JButton("4 joueurs");
        j4.setVisible(true);
        fond2.add(j4);

    }

    public void switchMenu(JPanel jp){
        body.removeAll();
        body.add(jp);
        body.invalidate();
        body.validate();
        
        body.repaint();
        
    }

    public JButton getButtonJouer(){return buttonJouer;}
    public JButton getDominos(){return dominos;}
    public JButton getCarcassonne(){return carcassonne;}
    public JButton getJAI(){return joueurAndIa;}
    public JButton getJoueur(){return joueur;}
    public JButton getJ2(){return j2;}
    public JButton getJ3(){return j3;}
    public JButton getJ4(){return j4;}
    public JPanel getFond1(){return fond1;}
    public JPanel getFond2(){return fond2;}
    public JPanel getSelect(){return select;}
}
