package model.carcassonne;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.Image;

import model.carcassonne.Piece.*;

public class AffichageCarc extends JFrame implements MouseInputListener {
    SacCarte sac;
    JPanel jpanel;

    public AffichageCarc(){
        setPreferredSize(new Dimension(1200, 700));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        sac = new SacCarte();
        jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.add(new SacCarteAff(250, 250));
        getContentPane().add(jpanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public class SacCarteAff extends JPanel implements MouseInputListener{
        int x, y, width, height;

        SacCarteAff(int x, int y){
            Carte tmp = sac.getSac().pop();
            String s = "src/main/resources/modeleCarte/" + tmp.toString() + "0.png";
            this.x = x;
            this.y = y;
            width=50;
            height=50;            
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

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            
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
        private BufferedImage resizeImage(BufferedImage imageDepart, int x, int y) throws IOException {
            Image imageFinal = imageDepart.getScaledInstance(x, y, Image.SCALE_DEFAULT);
            BufferedImage imageSortie = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
            imageSortie.getGraphics().drawImage(imageFinal, 0, 0, null);
            return imageSortie;
        }
    }

    @Override
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
