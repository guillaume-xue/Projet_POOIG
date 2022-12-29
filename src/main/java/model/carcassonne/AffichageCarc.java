package model.carcassonne;

import java.awt.GridLayout;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.Image;

import model.carcassonne.Piece.*;
import model.carcassonne.Plateau.Game;

public class AffichageCarc extends JFrame{
    private Dimension dimension;
    private int scale;

    private boolean modePlacement;
    private AffGestionTuile gestion;
    private Game game;

    private JPanel[][] tab;

    /* Création du plateau */
    public AffichageCarc(int x, int y, int scale){
        this.scale = scale;
        dimension = new Dimension(((x/scale)*scale), ((y/scale)*scale));
        setPreferredSize(dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout((int) dimension.getHeight()/scale, (int) dimension.getWidth()/scale));
        //setLayout(new GridLayout(3, 2));
        
        //tab = new JPanel[3][2];
        tab = new JPanel[(int) dimension.getHeight()/scale][(int) dimension.getWidth()/scale];
        for(int i=0; i<tab.length; i++){
            for(int j=0; j<tab[i].length; j++){
                tab[i][j] = new JPanel();
                add(tab[i][j]);
            }
        }

        gestion = new AffGestionTuile(this);
        game = new Game(this, gestion);
        gestion.setGame(game);

        
        tuileCentrale();


        

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public int getMilieuY(){
        return (int) dimension.getHeight()/scale/2;
    }

    public int getMilieuX(){
        return (int) dimension.getWidth()/scale/2;
    }

    /* Pose automatique la première tuile du jeu
     * au centre du plateau.
     */
    public void tuileCentrale(){
        tab[getMilieuY()][getMilieuX()].add(new SacCarteAff(getMilieuY(), getMilieuX()));
        caseDispo(getMilieuY(), getMilieuX());
        System.out.println("Centre");
        game.getPP().affichage();
        game.prochainTour();
    }
    
    /*public void remplirCaseVide(){
        for(int i=0; i<dimension.getWidth(); i+=scale){
            for(int j=0; j<dimension.getHeight(); j+=scale){
                jpanel.add(new SacCarteAff(i, j, false));
            }
        }
    }*/

    /* Verifie s'il y'a de l'espace libre
     * sur les 4 faces de la tuile posée puis
     * pose des tuiles dit "neutre" sur lesquelles
     * on peut intéragir avec (ce sont les emplacement
     * valides pour les prochaines tuiles).
     */
    public void caseDispo(int y, int x){
        if(x-1>=0 && tab[y][x-1].getComponentCount() == 0){
            tab[y][x-1].add(new SacCarteAff(y, x-1, true));
        }
        if(x+1<dimension.getWidth()/scale && tab[y][x+1].getComponentCount() == 0){
            tab[y][x+1].add(new SacCarteAff(y, x+1, true));
        }
        if(y-1>=0 && tab[y-1][x].getComponentCount() == 0){
            tab[y-1][x].add(new SacCarteAff(y-1, x, true));
        }
        if(y+1<dimension.getHeight()/scale && tab[y+1][x].getComponentCount() == 0){
            tab[y+1][x].add(new SacCarteAff(y+1, x, true));
        }

    }

    /* Verifie si la position qu'on cherche n'est pas hors du plateau. */
    public boolean horsLimite(int x, int y){
        if(x>=0 && x<dimension.getWidth()/scale && y>=0 && y<dimension.getHeight()/scale){
            return true;
        }
        return false;
    }

    /* Renvoie le composant(tuile s'il existe) à l'emplacement x y. */
    public SacCarteAff getCompo(int x, int y){
        if(horsLimite(x, y) && tab[y][x].getComponentCount() != 0){
            return ((SacCarteAff) tab[y][x].getComponents()[0]);
        }else{
            return null;
        }
    }

    /* Pose la tuile qui était stocké dans la fenêtre de l'éditeur. */
    public void ajoutTuile(CarteComplet carteBis){
        tab[game.getY()][game.getX()].removeAll();
        tab[game.getY()][game.getX()].add(new SacCarteAff(game.getX(), game.getY()));
        caseDispo(game.getY(), game.getX());
        invalidate();
        validate();
        repaint();
        game.prochainTour();
    }

    /* Empêche tout autre action après qu'on ait séléctionné
     * l'emplacement où l'on veut poser la tuile (sauf si on l'annule).
     */
    public void setModeMouv(boolean b){
        modePlacement = b;
    }

    public class SacCarteAff extends JPanel implements MouseInputListener{
        int x, y, width, height;
        CarteComplet tmp;

        /* Création graphique de la tuile. */
        SacCarteAff(int y, int x){
            tmp = game.getPP();
            String s = "src/main/resources/modeleCarte/" + tmp.getCarte().toString() + tmp.getRot() + ".png";
            this.x = x;
            this.y = y;
            width = scale;
            height = scale;           
            try {
                BufferedImage fichier = ImageIO.read(new File(s));
                
                fichier = resizeImage(fichier, width, height);
                add(new JLabel(new ImageIcon(fichier)));
                
                this.setBounds(0, 0, width, height);

                addMouseListener(this);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(s);
            }
        }

        /* Création graphique de la tuile dit neutre. */
        SacCarteAff(int y, int x, boolean b){
            tmp = new CarteComplet(Carte.Null);
            this.x = x;
            this.y = y;
            width = scale;
            height = scale;
            String s = "src/main/resources/modeleCarte/" + tmp.getCarte().toString();
            if(b){
                s+="0.png";
            }else{
                s+="1.png";
            }
            try{    
                BufferedImage fichier = ImageIO.read(new File(s));
                fichier = resizeImage(fichier, width, height);
                add(new JLabel(new ImageIcon(fichier)));
                this.setBounds(0, 0, width, height);
                addMouseListener(this);
            } catch (Exception e){
                System.out.println(e);
            }

        }

        public CarteComplet getCarteComplet(){
            return tmp;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(modePlacement){
                afficherMessage("Veuillez d'abord poser la tuile actuel");
            }else{
                if(tmp.getCarte() == Carte.Null){
                    modePlacement = true;
                    game.placement(x, y, scale);
                }else{
                    afficherMessage("Case déjà remplis");
                }
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
        
    }

    /* Fonction en charge de réduire/agrandir la taille de l'image. */
    public BufferedImage resizeImage(BufferedImage imageDepart, int x, int y) throws IOException {
        Image imageFinal = imageDepart.getScaledInstance(x, y, Image.SCALE_DEFAULT);
        BufferedImage imageSortie = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        imageSortie.getGraphics().drawImage(imageFinal, 0, 0, null);
        return imageSortie;
    }

    /* Renvoie sous forme d'une nouvelle fenêtre le message voulu. */
    public void afficherMessage(String s){
        JOptionPane.showMessageDialog(null, s);
    }
    /*@Override
    public void mouseClicked(MouseEvent e) {
        int xPos = (e.getXOnScreen() - getX());
        int yPos = (e.getYOnScreen() - getY() - getInsets().top);
        xPos = xPos - (xPos%50);
        yPos = yPos - (yPos%50);
        jpanel.add(new SacCarteAff(xPos, yPos));
        //jpanel.repaint();
        invalidate();
        validate();
        repaint();
        
    }*/

}
