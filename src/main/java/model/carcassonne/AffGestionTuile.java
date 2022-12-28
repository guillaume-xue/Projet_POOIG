package model.carcassonne;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.carcassonne.Piece.Carte;
import model.carcassonne.Piece.CarteComplet;
import model.carcassonne.Plateau.Game;

import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;

public class AffGestionTuile extends JFrame {
    JButton rotGauche, valider, rotDroite;
    ImagePane imagePane;
    AffichageCarc aff;
    CarteComplet carte;
    Game game;

    AffGestionTuile(AffichageCarc aff){
        this.aff = aff;
        setTitle("Editeur");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JMenuBar menu = new JMenuBar();
        rotGauche = new JButton("Rotation Gauche");
        rotGauche.setEnabled(true);
        valider = new JButton("Valider");
        valider.setEnabled(true);
        rotDroite = new JButton("Rotation Droite");
        rotDroite.setEnabled(true);
        menu.add(rotGauche);
        menu.add(valider);
        menu.add(rotDroite);
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
                if(game.verifPosition()){
                    aff.ajoutTuile(carte);
                }else{
                    aff.setModeMouv(false);
                    JOptionPane.showMessageDialog(null, "Emplacement ou sens de la tuile invalide à cette position");
                }
            }
        );
        rotDroite.addActionListener(
            (ActionEvent e) -> {
                carte.rotationDroite();
                nextTuile(carte, carte.getRot());
            }
        );

        pack();
        setVisible(true);
    }

    public void changeCarte(CarteComplet carte){
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
    }

    public void nextTuile(CarteComplet s, int rot){
        carte = s;
        String m = "src/main/resources/modeleCarte/" + s.getCarte().toString() + rot + ".png";   
        try {
            //ImageIcon fichier = new ImageIcon(s);
            BufferedImage fichier = ImageIO.read(new File(m));
            fichier = aff.resizeImage(fichier, 250, 250);
            imagePane.removeAll();
            imagePane.add(new JLabel(new ImageIcon(fichier)));
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



    private class ImagePane extends JPanel{

        ImagePane(){
            setPreferredSize(new Dimension(250, 250));

        }
        
    }



}
