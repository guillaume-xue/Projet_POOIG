package model;

import java.awt.event.WindowEvent;

import gui.Menu;
import gui.carcassonne.AffGestionTuile;
import gui.carcassonne.AffichageCarc;
import gui.dominos.Jeu_Dominos_IG;
import model.carcassonne.Piece.DonneeCarte;
import model.carcassonne.Plateau.Game;

public class Controler {

    private Menu menu;
    private Jeu_Dominos_IG dominos;
    private AffichageCarc aff;
    private AffGestionTuile agt;
    private Game game;

    public Controler(Menu menu){
        this.menu = menu;
    }
    public Controler(Jeu_Dominos_IG dominos){
        this.dominos = dominos;
    }
    public Controler(AffichageCarc aff, AffGestionTuile agt, Game game){
        this.aff = aff;
        this.agt = agt;
        this. game = game;
    }

    public void menuButtonPresed(){
        menu.getButtonJouer().addActionListener(e -> {
            menu.switchMenu(menu.getSelect());
        });
        menu.getDominos().addActionListener(e -> {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {new Jeu_Dominos_IG();}
            });
        });
        menu.getCarcassonne().addActionListener(e -> {
            menu.switchMenu(menu.getFond1());
            
        });
        menu.getJoueur().addActionListener(e -> {
            menu.switchMenu(menu.getFond2());
            
        });
        menu.getJ2().addActionListener(e -> {
            new AffichageCarc(1280, 720, 75, 2);
            menu.setVisible(false);
            menu.dispose();
        });
        menu.getJ3().addActionListener(e -> {
            new AffichageCarc(1280, 720, 75, 3);
            menu.setVisible(false);
            menu.dispose();
        });
        menu.getJ4().addActionListener(e -> {
            new AffichageCarc(1280, 720, 75, 4);
            menu.setVisible(false);
            menu.dispose();
        });
        
    }

    public void domimosButtunPresed(){
        dominos.getTurnLeft().addActionListener(e -> {
            dominos.getTuile().turnLeft();
            dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
            dominos.invalidate();
            dominos.validate();
            dominos.repaint();
        });
        dominos.getTurnRight().addActionListener(e -> {
            dominos.getTuile().turnRight();
            dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
            dominos.invalidate();
            dominos.validate();
            dominos.repaint();
        });
        dominos.getSubmit().addActionListener(e -> {
            if(dominos.getPlateau().sacEmpty() && dominos.getAffPlateau().addTuile(dominos.getTuile())){
                dominos.setTuile(dominos.getPlateau().nextTuile());
                dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
                dominos.invalidate();
                dominos.validate();
                dominos.repaint();
            }
        });
        dominos.getSkip().addActionListener(e -> {
            if(!dominos.getPlateau().sacEmpty()){
                dominos.setTuile(dominos.getPlateau().nextTuile());
                dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
                dominos.invalidate();
                dominos.validate();
                dominos.repaint();
            }
        });

    }

    public void carcassonneButtunPresed(){
        agt.getRotG().addActionListener(
            e -> {
                agt.getCarteComplet().turnLeft();
                agt.nextTuile(agt.getCarteComplet(), agt.getCarteComplet().getRot());
            }
        );
        agt.getValider().addActionListener(
            e -> {
                if(game.verifPosition() && aff.getModeMouv()){
                    aff.ajoutTuile(agt.getCarteComplet());
                    agt.boutonTuile(false);
                    agt.boutonPion(true);
                    agt.getJLabel().setText("Poser un pion ?");
                }else{
                    //aff.setModeMouv(false);
                    aff.afficherMessage("Emplacement ou sens de la tuile invalide à cette position");
                }
            }
        );
        agt.getRotD().addActionListener(
            e -> {
                agt.getCarteComplet().turnRight();
                agt.nextTuile(agt.getCarteComplet(), agt.getCarteComplet().getRot());
            }
        );
        agt.getRepioche().addActionListener(
            e -> {
                game.repiocher();
            }
        );
        agt.getPoser().addActionListener(
            e -> {
                agt.changeMenu(agt.getMenuBis());
            }
        );
        agt.getSkip().addActionListener(
            e -> {
                agt.nextMove();
            }
        );
        agt.getJNO().addActionListener(
            e -> {
                game.addPion(DonneeCarte.NORD_OUEST);
                agt.nextMove();
            }
        );
        agt.getJN().addActionListener(
            e -> {
                game.addPion(DonneeCarte.NORD);
                agt.nextMove();
            }
        );
        agt.getJNE().addActionListener(
            e -> {
                game.addPion(DonneeCarte.NORD_EST);
                agt.nextMove();
            }
        );
        agt.getJE().addActionListener(
            e -> {
                game.addPion(DonneeCarte.EST);
                agt.nextMove();
            }
        );
        agt.getJSE().addActionListener(
            e -> {
                game.addPion(DonneeCarte.SUD_EST);
                agt.nextMove();
            }
        );
        agt.getJS().addActionListener(
            e -> {
                game.addPion(DonneeCarte.SUD);
                agt.nextMove();
            }
        );
        agt.getJSO().addActionListener(
            e -> {
                game.addPion(DonneeCarte.SUD_OUEST);
                agt.nextMove();
            }
        );
        agt.getJO().addActionListener(
            e -> {
                game.addPion(DonneeCarte.OUEST);
                agt.nextMove();
            }
        );
        agt.getJC().addActionListener(
            e -> {
                game.addPion(DonneeCarte.CENTRE);
                agt.nextMove();
            }
        );
        agt.getAnnuler().addActionListener(
            e -> {
                agt.nextMove();
            }
        );
    }

}
