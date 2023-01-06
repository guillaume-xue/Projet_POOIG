package model.carcassonne;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;

import javax.swing.JPanel;

import model.carcassonne.Piece.CarteComplet;
import model.carcassonne.Piece.DonneeCarte;
import model.carcassonne.Pion.Joueur;
import model.carcassonne.Plateau.Game;

import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

public class AffGestionTuile extends JFrame {
    private JButton rotGauche, valider, rotDroite, annuler, repiocher, poser, skip;
    private ImagePane imagePane;
    private AffichageCarc aff;
    private CarteComplet carte;
    private Game game;
    private JLabel jlabel;
    JMenuBar menu, menuBis;

    /* Créaction du panneau en charge
     * de la tuile avant de la poser
     * sur le plateau.
     */
    AffGestionTuile(AffichageCarc aff){
        this.aff = aff;
        setTitle("Editeur");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        initButPion();
        menu = new JMenuBar();
        rotGauche = new JButton("Rotation Gauche");
        valider = new JButton("Valider");
        rotDroite = new JButton("Rotation Droite");
        //annuler = new JButton("Annuler");
        repiocher = new JButton("Repiocher");
        boutonTuile(true);

        poser = new JButton("Poser un pion");
        skip = new JButton("Skip");
        boutonPion(false);



        menu.add(rotGauche);
        menu.add(valider);
        menu.add(rotDroite);
        //menu.add(annuler);
        menu.add(repiocher);
        menu.add(poser);
        menu.add(skip);
        setJMenuBar(menu);

        imagePane = new ImagePane();
        setContentPane(imagePane);

        rotGauche.addActionListener(
            (ActionEvent e) -> {
                carte.rotationGauche();
                nextTuile(carte, carte.getRot());
            }
        );
        valider.addActionListener(
            (ActionEvent e) -> {
                if(game.verifPosition() && aff.getModeMouv()){
                    aff.ajoutTuile(carte);
                    boutonTuile(false);
                    boutonPion(true);
                    jlabel.setText("Poser un pion ?");
                }else{
                    //aff.setModeMouv(false);
                    aff.afficherMessage("Emplacement ou sens de la tuile invalide à cette position");
                }
            }
        );
        rotDroite.addActionListener(
            (ActionEvent e) -> {
                carte.rotationDroite();
                nextTuile(carte, carte.getRot());
            }
        );
        /*
        annuler.addActionListener(
            (ActionEvent e) ->{
                aff.setModeMouv(false);
            } 
        );
        */
        repiocher.addActionListener(
            (ActionEvent e) -> {
                game.repiocher();
            }
        );
        poser.addActionListener(
            (ActionEvent e) -> {
                changeMenu(menuBis);
            }
        );
        skip.addActionListener(
            (ActionEvent e) -> {
                nextMove();
            }
        );


        pack();
        setVisible(true);
    }

    public void nextMove(){
        changeMenu(menu);
        game.prochainTour();
        aff.setModeMouv(false);
        boutonTuile(true);
        boutonPion(false);
    }

    public void boutonTuile(boolean b){
        rotGauche.setEnabled(b);
        valider.setEnabled(b);
        rotDroite.setEnabled(b);
        //annuler.setEnabled(b);
        repiocher.setEnabled(b);
    }

    public void boutonPion(boolean b){
        poser.setEnabled(b);
        skip.setEnabled(b);
    }

    public void initButPion(){
        menuBis = new JMenuBar();
        JButton no = new JButton("N-O"),
        n = new JButton("N"),
        ne = new JButton("N-E"),
        e = new JButton("E"),
        se = new JButton("S-E"),
        s = new JButton("S"),
        so = new JButton("S-O"),
        o = new JButton("O"),
        c = new JButton("C"),
        annuler = new JButton("Annuler");
        menuBis.add(no);
        menuBis.add(n);
        menuBis.add(ne);
        menuBis.add(e);
        menuBis.add(so);
        menuBis.add(se);
        menuBis.add(s);
        menuBis.add(so);
        menuBis.add(o);
        menuBis.add(c);
        menuBis.add(annuler);

        no.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.NORD_OUEST);
                nextMove();
            }
        );
        n.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.NORD);
                nextMove();
            }
        );
        ne.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.NORD_EST);
                nextMove();
            }
        );
        e.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.EST);
                nextMove();
            }
        );
        se.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.SUD_EST);
                nextMove();
            }
        );
        s.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.SUD);
                nextMove();
            }
        );
        so.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.SUD_OUEST);
                nextMove();
            }
        );
        o.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.OUEST);
                nextMove();
            }
        );
        c.addActionListener(
            (ActionEvent a) -> {
                game.addPion(DonneeCarte.CENTRE);
                nextMove();
            }
        );
        annuler.addActionListener(
            (ActionEvent a) -> {
                nextMove();
            }
        );
    }

    public void changeMenu(JMenuBar j){
        setJMenuBar(j);
        invalidate();
        validate();
        repaint();
    }

    /*public void changeCarte(CarteComplet carte){
        String s = "src/main/resources/modeleCarte/" + carte.getCarte().toString() + "0.png";           
            try {
                //ImageIcon fichier = new ImageIcon(s);
                BufferedImage fichier = ImageIO.read(new File(s));
                imagePane.add(new JLabel(new ImageIcon(fichier)));
                invalidate();
                validate();
                repaint();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(s);
            }
    }*/

    /* Affichage de la prochaine tuile dans cette fenêtre. */
    public void nextTuile(CarteComplet s, int rot){
        carte = s;
        String m = "Projet_POOIG\\src\\main\\resources\\modeleCarte\\" + s.getCarte().toString() + rot + ".png";   
        try {
            //ImageIcon fichier = new ImageIcon(s);
            BufferedImage fichier = ImageIO.read(new File(m));
            fichier = aff.resizeImage(fichier, 250, 250);
            imagePane.removeAll();
            imagePane.add(new JLabel(new ImageIcon(fichier)));
            affJoueur(game.joueurAct());
            invalidate();
            validate();
            repaint();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(s);
        }
    }

    public ImagePane getImagePane(){
        return imagePane;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void affJoueur(Joueur j){
        jlabel = new JLabel("Joueur actuel : " + j.getColorName());
        jlabel.setBounds(0, 0, 500, 500);
        add(jlabel);
        repaint();
    }



    private class ImagePane extends JPanel{

        ImagePane(){
            setPreferredSize(new Dimension(250, 250));
        }
        
    }



}
