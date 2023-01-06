package gui.carcassonne;

import java.awt.GridLayout;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MouseInputListener;

import java.awt.Image;

import model.carcassonne.Piece.*;
import model.carcassonne.Plateau.Game;

public class AffichageCarc extends JFrame {
    private Dimension dimension;
    private int scale;

    private boolean modePlacement;
    private AffGestionTuile gestion;
    private Game game;

    private JPanel[][] tab;

    /* Création du plateau */
    public AffichageCarc(int x, int y, int scale, int nbJoueur) {
        this.scale = scale;
        dimension = new Dimension(((x / scale) * scale), ((y / scale) * scale));
        setSize(dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout((int) dimension.getHeight() / scale, (int) dimension.getWidth() / scale));

        tab = new JPanel[(int) dimension.getHeight() / scale][(int) dimension.getWidth() / scale];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = new JPanel();
                add(tab[i][j]);
            }
        }

        gestion = new AffGestionTuile(this);
        game = new Game(this, gestion, nbJoueur);
        gestion.setGame(game);

        tuileCentrale();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* Renvoie la position du milieu de terrain en Y */
    public int getMilieuY() {
        return (int) dimension.getHeight() / scale / 2;
    }

    /* En X */
    public int getMilieuX() {
        return (int) dimension.getWidth() / scale / 2;
    }

    /*
     * Pose automatique la première tuile du jeu
     * au centre du plateau.
     */
    public void tuileCentrale() {
        tab[getMilieuY()][getMilieuX()].add(new SacCarteAff(getMilieuY(), getMilieuX()));
        caseDispo(getMilieuY(), getMilieuX());
        game.prochainTour();
    }

    /*
     * Verifie s'il y'a de l'espace libre
     * sur les 4 faces de la tuile posée puis
     * pose des tuiles dit "neutre" sur lesquelles
     * on peut intéragir avec (ce sont les emplacement
     * valides pour les prochaines tuiles).
     */
    public void caseDispo(int y, int x) {
        if (x - 1 >= 0 && tab[y][x - 1].getComponentCount() == 0) {
            tab[y][x - 1].add(new SacCarteAff(y, x - 1, true));
        }
        if (x + 1 < dimension.getWidth() / scale && tab[y][x + 1].getComponentCount() == 0) {
            tab[y][x + 1].add(new SacCarteAff(y, x + 1, true));
        }
        if (y - 1 >= 0 && tab[y - 1][x].getComponentCount() == 0) {
            tab[y - 1][x].add(new SacCarteAff(y - 1, x, true));
        }
        if (y + 1 < dimension.getHeight() / scale && tab[y + 1][x].getComponentCount() == 0) {
            tab[y + 1][x].add(new SacCarteAff(y + 1, x, true));
        }

    }

    /* Verifie si la position qu'on cherche n'est pas hors du plateau. */
    public boolean horsLimite(int x, int y) {
        if (x >= 0 && x < dimension.getWidth() / scale && y >= 0 && y < dimension.getHeight() / scale) {
            return true;
        }
        return false;
    }

    /* Renvoie le composant(tuile s'il existe) à l'emplacement x y. */
    public SacCarteAff getCompo(int x, int y) {
        if (horsLimite(x, y) && tab[y][x].getComponentCount() != 0) {
            return ((SacCarteAff) tab[y][x].getComponents()[0]);
        } else {
            return null;
        }
    }

    /* Ajoute l'image du pion sur la tuile séléctionnée */
    public void addPionOnBoard(int x, int y, String s, DonneeCarte d) {
        tab[y][x].removeAll();
        ;
        tab[y][x].add(new SacCarteAff(y, x, s, d));
        invalidate();
        validate();
        repaint();
    }

    /* Pose la tuile qui était stocké dans la fenêtre de l'éditeur. */
    public void ajoutTuile(CarteComplet carteBis) {
        tab[game.getY()][game.getX()].removeAll();
        tab[game.getY()][game.getX()].add(new SacCarteAff(game.getX(), game.getY()));
        caseDispo(game.getY(), game.getX());
        invalidate();
        validate();
        repaint();

    }

    /*
     * Empêche tout autre action après qu'on ait séléctionné
     * l'emplacement où l'on veut poser la tuile (sauf si on l'annule).
     */
    public void setModeMouv(boolean b) {
        modePlacement = b;
    }

    public boolean getModeMouv() {
        return modePlacement;
    }

    public class SacCarteAff extends JPanel implements MouseInputListener {
        private int x, y, width, height;
        private CarteComplet tmp;

        /* Ajout de la tuile. */
        SacCarteAff(int y, int x) {
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

                addMouseListener(this);
            } catch (Exception e) {
            }
        }

        /* Ajout de la tuile. fusionnée avec le pion */
        SacCarteAff(int y, int x, String couleur, DonneeCarte d) {
            tmp = game.getPP();
            String s = "src/main/resources/modeleCarte/" + tmp.getCarte().toString() + tmp.getRot() + ".png";
            String sbis = "src/main/resources/modeleCarte/" + couleur + ".png";
            this.x = x;
            this.y = y;
            width = scale;
            height = scale;
            try {

                BufferedImage fichier = ImageIO.read(new File(s));
                BufferedImage fichierbis = ImageIO.read(new File(sbis));

                fichier = resizeImage(fichier, width, height);
                fichierbis = resizeImage(fichierbis, width / 3, height / 3);

                add(new JLabel(new ImageIcon(fusion(fichier, fichierbis, d))));
                addMouseListener(this);

            } catch (Exception e) {
            }
        }

        /* Ajout de la tuile dit neutre. */
        SacCarteAff(int y, int x, boolean b) {
            tmp = new CarteComplet(Carte.Null);
            this.x = x;
            this.y = y;
            width = scale;
            height = scale;
            String s = "src/main/resources/modeleCarte/" + tmp.getCarte().toString();
            if (b) {
                s += "0.png";
            } else {
                s += "1.png";
            }
            try {
                BufferedImage fichier = ImageIO.read(new File(s));
                fichier = resizeImage(fichier, width, height);
                add(new JLabel(new ImageIcon(fichier)));
                this.setBounds(0, 0, width, height);
                addMouseListener(this);
            } catch (Exception e) {
            }

        }

        /* Création de la tuile fusionnée avec le pion */
        public BufferedImage fusion(BufferedImage img1, BufferedImage img2, DonneeCarte d) throws IOException {
            BufferedImage newImage = new BufferedImage(scale, scale, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = newImage.createGraphics();
            g2.drawImage(img1, null, 0, 0);
            g2.drawImage(img2, null, game.getX(d), game.getY(d));
            g2.dispose();
            return newImage;

        }

        public CarteComplet getCarteComplet() {
            return tmp;
        }

        public void createBorder() {
            Border bord = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            setBorder(bord);
        }

        public void removeBorder() {
            setBorder(null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (modePlacement) {
                removeBorder();
                modePlacement = false;
            } else {
                if (tmp.getCarte() == Carte.Null) {
                    createBorder();
                    modePlacement = true;
                    game.placement(x, y, scale);
                } else {
                    afficherMessage("Case déjà remplis");
                }
            }

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

    /* Fonction en charge de réduire/agrandir la taille de l'image. */
    public BufferedImage resizeImage(BufferedImage imageDepart, int x, int y) throws IOException {
        Image imageFinal = imageDepart.getScaledInstance(x, y, Image.SCALE_DEFAULT);
        BufferedImage imageSortie = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        imageSortie.getGraphics().drawImage(imageFinal, 0, 0, null);
        return imageSortie;
    }

    /* Renvoie sous forme d'une nouvelle fenêtre le message voulu. */
    public void afficherMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

}
