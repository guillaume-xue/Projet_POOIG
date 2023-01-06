package model;

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
    private boolean gameSelection = true;

    public Controler(Menu menu) {
        this.menu = menu;
    }

    public Controler(Jeu_Dominos_IG dominos) {
        this.dominos = dominos;
    }

    public Controler(AffichageCarc aff, AffGestionTuile agt, Game game) {
        this.aff = aff;
        this.agt = agt;
        this.game = game;
    }

    /* Bouton lié au menu */
    public void menuButtonPresed() {
        menu.getButtonJouer().addActionListener(e -> {
            menu.switchMenu(menu.getSelect());
        });
        menu.getDominos().addActionListener(e -> {
            menu.switchMenu(menu.getFond1());
            gameSelection = false;
        });
        menu.getCarcassonne().addActionListener(e -> {
            menu.switchMenu(menu.getFond1());
        });
        menu.getJoueur().addActionListener(e -> {
            if (!gameSelection) {
                launchDominos();
                menu.setVisible(false);
                menu.dispose();
            } else {
                menu.switchMenu(menu.getFond2());
            }
        });
        menu.getJ2().addActionListener(e -> {
            launchCarca(2);
        });
        menu.getJ3().addActionListener(e -> {
            launchCarca(3);
        });
        menu.getJ4().addActionListener(e -> {
            launchCarca(4);
        });

    }

    /* Bouton lié au jeu Dominos */
    public void domimosButtunPresed() {
        dominos.getTurnLeft().addActionListener(e -> {
            dominos.getTuile().turnLeft();
            refreshDominos();
        });
        dominos.getTurnRight().addActionListener(e -> {
            dominos.getTuile().turnRight();
            refreshDominos();
        });
        dominos.getSubmit().addActionListener(e -> {
            if (dominos.getPlateau().sacEmpty() && dominos.getAffPlateau().addTuile(dominos.getTuile())) {
                dominos.setTuile(dominos.getPlateau().nextTuile());
                refreshDominos();
            }
        });
        dominos.getSkip().addActionListener(e -> {
            if (!dominos.getPlateau().sacEmpty()) {
                dominos.setTuile(dominos.getPlateau().nextTuile());
                refreshDominos();
            }
        });

    }

    /* Bouton lié à AffGestionTuile, en charge de la tuile avant sa pose */
    public void carcassonneButtunPresed() {
        agt.getRotG().addActionListener(
                e -> {
                    agt.getCarteComplet().turnLeft();
                    agt.nextTuile(agt.getCarteComplet(), agt.getCarteComplet().getRot());
                });
        agt.getValider().addActionListener(
                e -> {
                    if (game.verifPosition() && aff.getModeMouv()) {
                        aff.ajoutTuile(agt.getCarteComplet());
                        agt.boutonTuile(false);
                        agt.boutonPion(true);
                        agt.getJLabel().setText("Poser un pion ?");
                    } else {
                        // aff.setModeMouv(false);
                        aff.afficherMessage("Emplacement ou sens de la tuile invalide à cette position");
                    }
                });
        agt.getRotD().addActionListener(
                e -> {
                    agt.getCarteComplet().turnRight();
                    agt.nextTuile(agt.getCarteComplet(), agt.getCarteComplet().getRot());
                });
        agt.getRepioche().addActionListener(
                e -> {
                    game.repiocher();
                });
        agt.getPoser().addActionListener(
                e -> {
                    agt.changeMenu(agt.getMenuBis());
                });
        agt.getSkip().addActionListener(
                e -> {
                    agt.nextMove();
                });
        agt.getJNO().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.NORD_OUEST);
                    agt.nextMove();
                });
        agt.getJN().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.NORD);
                    agt.nextMove();
                });
        agt.getJNE().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.NORD_EST);
                    agt.nextMove();
                });
        agt.getJE().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.EST);
                    agt.nextMove();
                });
        agt.getJSE().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.SUD_EST);
                    agt.nextMove();
                });
        agt.getJS().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.SUD);
                    agt.nextMove();
                });
        agt.getJSO().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.SUD_OUEST);
                    agt.nextMove();
                });
        agt.getJO().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.OUEST);
                    agt.nextMove();
                });
        agt.getJC().addActionListener(
                e -> {
                    game.addPion(DonneeCarte.CENTRE);
                    agt.nextMove();
                });
        agt.getAnnuler().addActionListener(
                e -> {
                    agt.nextMove();
                });
    }

    /* Lance Carcassonne avec le nombre de jouueur */
    public void launchCarca(int x) {
        new AffichageCarc(1280, 720, 75, x);
        menu.setVisible(false);
        menu.dispose();
    }

    /* Lance Dominos */
    public void launchDominos() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Jeu_Dominos_IG();
            }
        });
    }

    /* Actualise */
    public void refreshDominos() {
        dominos.getSelectTuile().miseAJourTuile(dominos.getTuile().toString());
        dominos.invalidate();
        dominos.validate();
        dominos.repaint();
    }
}
