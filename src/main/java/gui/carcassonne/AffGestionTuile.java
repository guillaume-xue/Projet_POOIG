package gui.carcassonne;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;

import javax.swing.JPanel;

import model.carcassonne.Piece.CarteComplet;
import model.carcassonne.Pion.Joueur;
import model.carcassonne.Plateau.Game;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

public class AffGestionTuile extends JFrame {
    private JButton rotGauche, valider, rotDroite, repiocher, poser, skip;
    private JButton jNo, jN, jNe, jE, jSe, jS, jSo, jO, jC, annuler;
    private ImagePane imagePane;
    private AffichageCarc aff;
    private CarteComplet carte;
    private Game game;
    private JLabel jlabel;
    private JMenuBar menu, menuBis;


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
        repiocher = new JButton("Repiocher");
        boutonTuile(true);

        poser = new JButton("Poser un pion");
        skip = new JButton("Skip");
        boutonPion(false);

        menu.add(rotGauche);
        menu.add(valider);
        menu.add(rotDroite);
        menu.add(repiocher);
        menu.add(poser);
        menu.add(skip);
        setJMenuBar(menu);

        imagePane = new ImagePane();
        setContentPane(imagePane);

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
        repiocher.setEnabled(b);
    }

    public void boutonPion(boolean b){
        poser.setEnabled(b);
        skip.setEnabled(b);
    }

    public void initButPion(){
        menuBis = new JMenuBar();
        jNo = new JButton("N-O");
        jN = new JButton("N");
        jNe = new JButton("N-E");
        jE = new JButton("E");
        jSe = new JButton("S-E");
        jS = new JButton("S");
        jSo = new JButton("S-O");
        jO = new JButton("O");
        jC = new JButton("C");
        annuler = new JButton("Annuler");
        menuBis.add(jNo);
        menuBis.add(jN);
        menuBis.add(jNe);
        menuBis.add(jE);
        menuBis.add(jSe);
        menuBis.add(jS);
        menuBis.add(jSo);
        menuBis.add(jO);
        menuBis.add(jC);
        menuBis.add(annuler);
    }

    public void changeMenu(JMenuBar j){
        setJMenuBar(j);
        invalidate();
        validate();
        repaint();
    }

    /* Affichage de la prochaine tuile dans cette fenêtre. */
    public void nextTuile(CarteComplet s, int rot){
        carte = s;
        String m = "Projet_POOIG\\src\\main\\resources\\modeleCarte\\" + s.getCarte().toString() + rot + ".png";   
        try {
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

    public JButton getRotG(){return rotGauche;}
    public JButton getRotD(){return rotDroite;}
    public JButton getValider(){return valider;}
    public JButton getRepioche(){return repiocher;}
    public JButton getPoser(){return poser;}
    public JButton getSkip(){return skip;}
    public JButton getJNO(){return jNo;}
    public JButton getJN(){return jN;}
    public JButton getJNE(){return jNe;}
    public JButton getJE(){return jE;}
    public JButton getJSE(){return jSe;}
    public JButton getJS(){return jS;}
    public JButton getJSO(){return jSo;}
    public JButton getJO(){return jO;}
    public JButton getJC(){return jC;}
    public JButton getAnnuler(){return annuler;}
    public JLabel getJLabel(){return jlabel;}
    public JMenuBar getMenuBis(){return menuBis;}
    public CarteComplet getCarteComplet(){return carte;}
    
    private class ImagePane extends JPanel{

        ImagePane(){
            setPreferredSize(new Dimension(250, 250));
        }
        
    }



}
