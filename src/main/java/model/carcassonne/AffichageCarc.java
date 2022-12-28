package model.carcassonne;

import java.awt.Component;
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
    Dimension dimension;
    int scale;
    JPanel jpanel;
    boolean modePlacement;
    AffGestionTuile gestion;
    Game game;

    public AffichageCarc(int x, int y, int scale){
        this.scale = scale;
        dimension = new Dimension((x/scale)*scale, (y/scale)*scale);
        setPreferredSize(dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jpanel = new JPanel();
        jpanel.setLayout(null);

        gestion = new AffGestionTuile(this);
        game = new Game(this, gestion);
        gestion.setGame(game);

        //remplirCaseVide();
        tuileCentrale();

        getContentPane().add(jpanel);
        

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void tuileCentrale(){
        jpanel.add(new SacCarteAff((int) dimension.getWidth()/2,(int) dimension.getHeight()/2));
        caseDispo((int) dimension.getWidth()/2,(int) dimension.getHeight()/2);
        game.prochainTour();
    }
    
    /*public void remplirCaseVide(){
        for(int i=0; i<dimension.getWidth(); i+=scale){
            for(int j=0; j<dimension.getHeight(); j+=scale){
                jpanel.add(new SacCarteAff(i, j, false));
            }
        }
    }*/
    public void caseDispo(int x, int y){
        if(x-scale>0 && jpanel.getComponentAt(x-scale, y) == null){
            jpanel.add(new SacCarteAff(x-scale, y, true));
        }
        if(x+scale<dimension.getWidth() && jpanel.getComponentAt(x+scale, y) == null){
            jpanel.add(new SacCarteAff(x+scale, y, true));
        }
        if(y-scale>0 && jpanel.getComponentAt(x, y-scale) == null){
            jpanel.add(new SacCarteAff(x, y-scale, true));
        }
        if(y+scale<dimension.getHeight() && jpanel.getComponentAt(x, y+scale) == null){
            jpanel.add(new SacCarteAff(x, y+scale, true));
        }

    }

    public boolean horsLimite(int x, int y){
        if(x>=0 && x<dimension.getWidth() && y>=0 && y<dimension.getHeight()){
            return true;
        }
        return false;
    }

    public SacCarteAff getCompo(int x, int y){
        Component comp = jpanel.getComponentAt(x, y);
        if(horsLimite(x, y) && comp != null){
            return ((SacCarteAff) comp);
        }else{
            return null;
        }
    }

    public void ajoutTuile(CarteComplet carteBis){
        jpanel.add(new SacCarteAff(game.getX(), game.getY()));
        caseDispo(game.getX(), game.getY());
        game.prochainTour();
    }

    public void setModeMouv(boolean b){
        modePlacement = b;
    }

    public class SacCarteAff extends JPanel implements MouseInputListener{
        int x, y, width, height;
        CarteComplet tmp;

        SacCarteAff(int x, int y){
            tmp = game.getPP();
            String s = "src/main/resources/modeleCarte/" + tmp.getCarte().toString() + tmp.getRot() + ".png";
            this.x = x;
            this.y = y;
            width = scale;
            height = scale;           
            try {
                //ImageIcon fichier = new ImageIcon(s);
                BufferedImage fichier = ImageIO.read(new File(s));
                fichier = resizeImage(fichier, width, height);
                add(new JLabel(new ImageIcon(fichier)));
                
                this.setBounds(x, y, width, height);

                addMouseListener(this);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(s);
            }
        }

        SacCarteAff(int x, int y, boolean b){
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
                this.setBounds(x, y, width, height);
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
                JOptionPane.showMessageDialog(null, "Veuillez d'abord poser la tuile actuel");
            }else{
                if(tmp.getCarte() == Carte.Null){
                    modePlacement = true;
                    game.placement(x, y, scale);
                }else{
                    JOptionPane.showMessageDialog(null, "Case déjà remplis");
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
    public BufferedImage resizeImage(BufferedImage imageDepart, int x, int y) throws IOException {
        Image imageFinal = imageDepart.getScaledInstance(x, y, Image.SCALE_DEFAULT);
        BufferedImage imageSortie = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        imageSortie.getGraphics().drawImage(imageFinal, 0, 0, null);
        return imageSortie;
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
