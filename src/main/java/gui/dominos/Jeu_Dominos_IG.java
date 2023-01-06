package gui.dominos;

import model.Controler;
import model.dominos.Domino_Plateau;
import model.dominos.Domino_Tuile;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Jeu_Dominos_IG extends JFrame {

    private JPanel contPlateau;
    private JPanel contSelect;
    private JPanel tuileSelect;
    private JButton turnLeft,turnRight,submit,skip;
    private Domino_Tuile tuile;
    private Domino_Plateau plateau;
    private Controler controler;
    private Tuile_IG selectTuile;
    private Plateau_IG affPlateau;

    public Jeu_Dominos_IG(){

        this.controler = new Controler(this);   // control les boutons du jeu

        this.setSize(1200, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        /*  contSelect est la partie à droite ou il regroupe tous les affichages de la tuile, bouton tourner à droite ou
            à gauche et le bouton valide ou passer.*/
        this.contSelect = new JPanel();
        this.contSelect.setLayout(new GridLayout(3,1));
        this.contSelect.setBackground(new Color(213, 213, 213));
        this.contSelect.setPreferredSize(new Dimension(400,0));
        this.contSelect.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.black));

        // contPlateau est la partie à gauche ou il y a le plateau
        this.contPlateau = new JPanel();
        this.contPlateau.setLayout(null);
        this.contPlateau.setBackground(new Color(117, 117, 117));
        this.contPlateau.setBackground(Color.gray);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(contPlateau);
        this.getContentPane().add(contSelect,BorderLayout.EAST);

        this.tuileSelect = new JPanel();

        // bouton tourner à gauche
        ImageIcon iconLeft = new ImageIcon("src/main/resources/dominos/turnleft.png");
        this.turnLeft = new JButton(iconLeft);
        this.tuileSelect.add(turnLeft);

        // Affiche la tuile actuel
        this.tuile = plateau.nextTuile();
        this.selectTuile = new Tuile_IG(this.tuile.toString());
        this.selectTuile.setBorder(BorderFactory.createLineBorder(Color.black,3));
        this.selectTuile.setPreferredSize(new Dimension(250,250));
        this.tuileSelect.add(this.selectTuile);

        // bouton tourne à droite
        ImageIcon iconRight = new ImageIcon("src/main/resources/dominos/turnright.png");
        this.turnRight = new JButton(iconRight);
        this.tuileSelect.add(turnRight);

        this.contSelect.add(tuileSelect);

        // bouton valide
        this.submit = new JButton("validé");
        this.submit.setPreferredSize(new Dimension(200,100));
        this.contSelect.add(submit);

        // bouton passer
        this.skip = new JButton("Skip");
        this.skip.setPreferredSize(new Dimension(200,100));
        this.contSelect.add(skip);

        this.plateau = new Domino_Plateau(5);
        this.affPlateau = new Plateau_IG();
        this.contPlateau.add(affPlateau);

        this.controler.domimosButtunPresed();
    }

    public class Plateau_IG extends JPanel implements MouseInputListener{

        private int xClick,yClick;
        private int lastClick;
        private JPanel[] affTuile;

        public Plateau_IG(){
            this.lastClick = 0;
            this.affTuile = new JPanel[plateau.getSize()*plateau.getSize()];
            this.setBounds(100,100,600,600);
            this.setLayout(new GridLayout(plateau.getSize(),plateau.getSize()));
            this.setBackground(Color.white);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            miseAJour();
        }

        /*
            Il sélectionne l'emplacement où on veut placer la tuile et met un contour noir
         */
        public void selectTuile(){
            this.removeAll();
            int n = this.xClick + this.yClick * plateau.getSize();
            this.affTuile[lastClick].setBorder(BorderFactory.createLineBorder(Color.black,0));
            this.affTuile[n].setBorder(BorderFactory.createLineBorder(Color.black,3));
            int k = 0;
            for(int i=1;i<plateau.getPlateau().length-1;i++){
                for(int j=1;j<plateau.getPlateau()[i].length-1;j++){
                    this.add(this.affTuile[k++]);
                }
            }
            repaint();
        }

        public boolean addTuile(Domino_Tuile t){    // Ajout de la tuile
            if(plateau.addVerif(yClick+1,xClick+1,t)){
                this.removeAll();
                plateau.addTuile(yClick+1,xClick+1,t);
                miseAJour();
                repaint();
                return true;
            }
            return false;
        }

        public void miseAJour(){                    // Actualise le plateau
            int k = 0;
            for(int i=1;i<plateau.getPlateau().length-1;i++){
                for(int j=1;j<plateau.getPlateau()[i].length-1;j++){
                    if(plateau.getPlateau()[i][j] != null){
                        this.affTuile[k] = new Tuile_IG(plateau.getPlateau()[i][j].toString());
                    }else{
                        JPanel p = new JPanel();
                        p.add(new JLabel());
                        this.affTuile[k] = p;
                    }
                    this.add(this.affTuile[k++]);
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            this.xClick = e.getX()/(600/plateau.getSize());
            System.out.println(xClick);
            this.yClick = e.getY()/(600/plateau.getSize());
            System.out.println(yClick);
            selectTuile();
            this.lastClick = this.xClick + this.yClick * plateau.getSize();
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

        }
    }

    public class Tuile_IG extends JPanel{

        private JLabel[] num;

        public Tuile_IG(String t){
            this.num = new JLabel[25];
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

        public void miseAJourTuile(String t){       // Actualise la tuile
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

    public JButton getSubmit() {
        return submit;
    }

    public JButton getSkip() {
        return skip;
    }

    public Domino_Tuile getTuile() {
        return tuile;
    }

    public Tuile_IG getSelectTuile() {
        return selectTuile;
    }

    public Plateau_IG getAffPlateau() {
        return affPlateau;
    }

    public Domino_Plateau getPlateau() {
        return plateau;
    }

    public void setTuile(Domino_Tuile tuile) {
        this.tuile = tuile;
    }

}
