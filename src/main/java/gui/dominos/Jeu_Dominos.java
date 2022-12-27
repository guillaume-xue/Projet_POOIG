package gui.dominos;

import model.dominos.Domino_Tuile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Jeu_Dominos extends JFrame {

    private JPanel contPlateau;
    private JPanel contSelect;

    public Jeu_Dominos(){

        this.setSize(1200, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.contSelect = new JPanel();
        this.contSelect.setBackground(new Color(213, 213, 213));
        this.contSelect.setPreferredSize(new Dimension(400,0));
        this.contSelect.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.black));

        this.contPlateau = new JPanel();
        this.contPlateau.setBackground(new Color(117, 117, 117));
        this.contPlateau.setBackground(Color.gray);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(contPlateau);
        this.getContentPane().add(contSelect,BorderLayout.EAST);

        ImageIcon left = new ImageIcon("src/main/resources/dominos/turnleft.png");
        JButton b1 = new JButton(left);
        this.contSelect.add(b1);

        this.contSelect.add(new TuileDominos(new Domino_Tuile()));

        ImageIcon right = new ImageIcon("src/main/resources/dominos/turnright.png");
        JButton b2 = new JButton(right);
        this.contSelect.add(b2);
    }

    public class TuileDominos extends JPanel{

        public TuileDominos(Domino_Tuile tuile){
            this.setPreferredSize(new Dimension(250,250));
            this.setBorder(BorderFactory.createLineBorder(Color.black,3));
            this.setBackground(Color.LIGHT_GRAY);
            this.setLayout(new GridLayout(5,5));

            String t = String.valueOf(tuile);

            for(int i=0;i<t.length();i++){
                JLabel l = new JLabel(String.valueOf(t.charAt(i)),JLabel.CENTER);
                if(t.charAt(i) != ' '){
                    l.setOpaque(true);
                    l.setBackground(Color.white);
                }
                this.add(l);
            }

        }

    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {new Jeu_Dominos();}
        });
    }

}
