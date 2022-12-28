package gui.dominos;

import model.Controler;
import model.dominos.Domino_Plateau;
import model.dominos.Domino_Tuile;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Jeu_Dominos extends JFrame {

    private JPanel contPlateau;
    private JPanel contSelect;
    private JPanel tuileSelect;
    private JButton turnLeft,turnRight;
    private Domino_Tuile tuile;
    private Domino_Plateau plateau;
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
        this.contPlateau.setLayout(null);
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

        this.plateau = new Domino_Plateau(10);

        ImageIcon iconRight = new ImageIcon("src/main/resources/dominos/turnright.png");
        this.turnRight = new JButton(iconRight);
        this.tuileSelect.add(turnRight);

        this.contSelect.add(tuileSelect);

        JButton submit = new JButton("validé");
        submit.setPreferredSize(new Dimension(200,100));
        this.contSelect.add(submit);

        this.contPlateau.add(new Plateau());

        this.controler.domimosButtunPresed();
    }

    public class Plateau extends JPanel implements MouseInputListener{

        private int etat;
        private int xClick, yClick;
        private int xOnScreen, yOnScreen;

        public Plateau(){

            this.etat = 0;
            this.setBounds(100,100,1000,1000);
            this.setLayout(new GridLayout(plateau.getSize(),plateau.getSize()));
            this.setBackground(Color.white);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            plateau.addTuile(5,5,tuile);

            for(int i=1;i<plateau.getPlateau().length-1;i++){
                for(int j=1;j<plateau.getPlateau()[i].length-1;j++){
                    if(plateau.getPlateau()[i][j] != null){
                        this.add(new TuileDominos(plateau.getPlateau()[i][j].toString()));
                    }else{
                        this.add(new JLabel());
                    }
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            this.etat++;
            this.xClick = getX();
            this.yClick = getY();
            this.xOnScreen = e.getXOnScreen();
            this.yOnScreen = e.getYOnScreen();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(this.etat % 2 != 0) {
                this.setLocation(this.xClick + e.getXOnScreen() - this.xOnScreen,this.yClick + e.getYOnScreen() - this.yOnScreen);
            }
        }
    }

    public class TuileDominos extends JPanel{

        private JLabel[] num;

        public TuileDominos(String t){
            this.num = new JLabel[25];
            this.setPreferredSize(new Dimension(100,100));
            this.setBorder(BorderFactory.createLineBorder(Color.black,3));
            this.setBackground(Color.LIGHT_GRAY);
            this.setLayout(new GridLayout(5,5));

            for(int i=0;i<t.length();i++){
                num[i] = new JLabel(String.valueOf(t.charAt(i)),JLabel.CENTER);
                if(t.charAt(i) != ' '){
                    num[i].setOpaque(true);
                    num[i].setBackground(Color.white);
                }
                this.add(num[i]);
            }
        }

        public void miseAJourTuile(String t){
            this.removeAll();
            for(int i=0;i<t.length();i++){
                num[i].setText(String.valueOf(t.charAt(i)));
                this.add(num[i]);
            }
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
