package gui.dominos;

import model.Controler;
import model.dominos.Domino_Tuile;
import javax.swing.*;
import java.awt.*;

public class Jeu_Dominos extends JFrame {

    private JPanel contPlateau;
    private JPanel contSelect;
    private JPanel tuileSelect;
    private JButton turnLeft,turnRight;
    private Domino_Tuile tuile;
    private Controler controler;
    private TuileDominos afficheTuile;

    public Jeu_Dominos(){

        this.controler = new Controler(this);

        this.setSize(1200, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        this.contSelect = new JPanel();
        this.contSelect.setLayout(new GridLayout(2,1));
        this.contSelect.setBackground(new Color(213, 213, 213));
        this.contSelect.setPreferredSize(new Dimension(400,0));
        this.contSelect.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.black));

        this.contPlateau = new JPanel();
        this.contPlateau.setBackground(new Color(117, 117, 117));
        this.contPlateau.setBackground(Color.gray);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(contPlateau);
        this.getContentPane().add(contSelect,BorderLayout.EAST);

        this.tuileSelect = new JPanel();

        ImageIcon iconLeft = new ImageIcon("src/main/resources/dominos/turnleft.png");
        this.turnLeft = new JButton(iconLeft);
        this.tuileSelect.add(turnLeft);

        this.tuile = new Domino_Tuile();
        this.afficheTuile = new TuileDominos(this.tuile.toString());
        this.tuileSelect.add(this.afficheTuile);

        ImageIcon iconRight = new ImageIcon("src/main/resources/dominos/turnright.png");
        this.turnRight = new JButton(iconRight);
        this.tuileSelect.add(turnRight);

        this.contSelect.add(tuileSelect);

        JButton submit = new JButton("validé");
        submit.setPreferredSize(new Dimension(200,100));
        this.contSelect.add(submit);

        this.controler.domimosButtunPresed();
    }

    public class TuileDominos extends JPanel{

        private JPanel affTuile;
        private JLabel[] num;

        public TuileDominos(String t){
            this.affTuile = new JPanel();
            this.num = new JLabel[25];
            this.affTuile.setPreferredSize(new Dimension(250,250));
            this.affTuile.setBorder(BorderFactory.createLineBorder(Color.black,3));
            this.affTuile.setBackground(Color.LIGHT_GRAY);
            this.affTuile.setLayout(new GridLayout(5,5));

            for(int i=0;i<t.length();i++){
                num[i] = new JLabel(String.valueOf(t.charAt(i)),JLabel.CENTER);
                if(t.charAt(i) != ' '){
                    num[i].setOpaque(true);
                    num[i].setBackground(Color.white);
                }
                this.affTuile.add(num[i]);
            }
            this.add(affTuile);
        }

        public void miseAJourTuile(String t){
            this.removeAll();
            this.affTuile.removeAll();
            for(int i=0;i<t.length();i++){
                num[i].setText(String.valueOf(t.charAt(i)));
                this.affTuile.add(num[i]);
            }
            this.add(affTuile);
        }

    }



    public JButton getTurnLeft() {
        return turnLeft;
    }

    public JButton getTurnRight() {
        return turnRight;
    }

    public Domino_Tuile getTuile() {
        return tuile;
    }

    public TuileDominos getAfficheTuile() {
        return afficheTuile;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {new Jeu_Dominos();}
        });
    }

}
