package model.carcassonne.Piece;

import java.util.HashMap;
import java.util.Map;

import static model.carcassonne.Piece.DonneeCarte.*;

import model.Tuile;
import model.carcassonne.Plateau.PlateauContenu;

public class CarteComplet implements Tuile {
    private Carte carte;
    private Map<DonneeCarte, PlateauContenu> listDonnee;
    private int rotation;

    /*
     * Création de la tuile
     * avec tout ses données finals.
     */
    public CarteComplet(Carte carte) {
        rotation = 0;
        this.carte = carte;
        listDonnee = new HashMap<>();
        for (int i = 0; i < DonneeCarte.values().length; i++) {
            listDonnee.put(DonneeCarte.values()[i], carte.getParamPlat()[i]);
        }
    }

    /* Renvoie ses données sur le côté haut de la tuile. */
    public Map<DonneeCarte, PlateauContenu> getNord() {
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_OUEST, listDonnee.get(NORD_OUEST));
        tmp.put(NORD, listDonnee.get(NORD));
        tmp.put(NORD_EST, listDonnee.get(NORD_EST));
        return tmp;
    }

    /* Renvoie ses données sur le côté droit de la tuile. */
    public Map<DonneeCarte, PlateauContenu> getEst() {
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_EST, listDonnee.get(NORD_EST));
        tmp.put(EST, listDonnee.get(EST));
        tmp.put(SUD_EST, listDonnee.get(SUD_EST));
        return tmp;
    }

    /* Renvoie ses données sur le côté bas de la tuile. */
    public Map<DonneeCarte, PlateauContenu> getSud() {
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(SUD_OUEST, listDonnee.get(SUD_OUEST));
        tmp.put(SUD, listDonnee.get(SUD));
        tmp.put(SUD_EST, listDonnee.get(SUD_EST));
        return tmp;
    }

    /* Renvoie ses données sur le côté gauche de la tuile. */
    public Map<DonneeCarte, PlateauContenu> getOuest() {
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_OUEST, listDonnee.get(NORD_OUEST));
        tmp.put(OUEST, listDonnee.get(OUEST));
        tmp.put(SUD_OUEST, listDonnee.get(SUD_OUEST));
        return tmp;
    }

    /* Renvoie ses données sur les point cardinaux haut de la tuile. */
    public Map<DonneeCarte, PlateauContenu> getValPlus() {
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD, listDonnee.get(NORD));
        tmp.put(EST, listDonnee.get(EST));
        tmp.put(SUD, listDonnee.get(SUD));
        tmp.put(OUEST, listDonnee.get(OUEST));
        return tmp;
    }

    /* Renvoie ses données sur les coins en diagonale haut de la tuile. */
    public Map<DonneeCarte, PlateauContenu> getValDiag() {
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_EST, listDonnee.get(NORD_EST));
        tmp.put(SUD_EST, listDonnee.get(SUD_EST));
        tmp.put(SUD_OUEST, listDonnee.get(SUD_OUEST));
        tmp.put(NORD_OUEST, listDonnee.get(NORD_OUEST));
        return tmp;
    }

    /* Effectue la rotation vers la Gauche. */
    @Override
    public void turnLeft() {
        PlateauContenu tmp = listDonnee.get(NORD);
        listDonnee.replace(NORD, listDonnee.get(EST));
        listDonnee.replace(EST, listDonnee.get(SUD));
        listDonnee.replace(SUD, listDonnee.get(OUEST));
        listDonnee.replace(OUEST, tmp);
        tmp = listDonnee.get(NORD_EST);
        listDonnee.replace(NORD_EST, listDonnee.get(SUD_EST));
        listDonnee.replace(SUD_EST, listDonnee.get(SUD_OUEST));
        listDonnee.replace(SUD_OUEST, listDonnee.get(NORD_OUEST));
        listDonnee.replace(NORD_OUEST, tmp);
        increRota(-1);
    }

    /* Effectue la rotation vers la droite. */
    @Override
    public void turnRight() {
        PlateauContenu tmp = listDonnee.get(NORD);
        listDonnee.replace(NORD, listDonnee.get(OUEST));
        listDonnee.replace(OUEST, listDonnee.get(SUD));
        listDonnee.replace(SUD, listDonnee.get(EST));
        listDonnee.replace(EST, tmp);
        tmp = listDonnee.get(NORD_EST);
        listDonnee.replace(NORD_EST, listDonnee.get(NORD_OUEST));
        listDonnee.replace(NORD_OUEST, listDonnee.get(SUD_OUEST));
        listDonnee.replace(SUD_OUEST, listDonnee.get(SUD_EST));
        listDonnee.replace(SUD_EST, tmp);
        increRota(1);
    }

    /* Incrémente le compteur de rotation(utile lors de l'affichage graphique). */
    private void increRota(int x) {
        if (x == 1) {
            rotation++;
        } else {
            rotation += 3;
        }
        rotation = rotation % 4;
    }

    /*
     * Compare les côtés gauche et droite des deux tuiles concernées,
     * permet de verifier s'ils sont autorisés à être placées l'une
     * à côté de l'autre.
     */
    public boolean comparDG(CarteComplet carteBis) {
        Map<DonneeCarte, PlateauContenu> tmp1 = getOuest();
        Map<DonneeCarte, PlateauContenu> tmp2 = carteBis.getEst();
        if (verifOther(tmp1.get(NORD_OUEST), tmp2.get(NORD_EST))
                && verifOther(tmp1.get(OUEST), tmp2.get(EST))
                && verifOther(tmp1.get(SUD_OUEST), tmp2.get(SUD_EST))) {
            return true;
        }
        return false;
    }

    /* Même fonction que la précédente mais vérifie le côté haut et bas. */
    public boolean comparHB(CarteComplet carteBis) {
        Map<DonneeCarte, PlateauContenu> tmp1 = getSud();
        Map<DonneeCarte, PlateauContenu> tmp2 = carteBis.getNord();
        if (verifOther(tmp1.get(SUD_OUEST), tmp2.get(NORD_OUEST))
                && verifOther(tmp1.get(SUD), tmp2.get(NORD))
                && verifOther(tmp1.get(SUD_EST), tmp2.get(NORD_EST))) {
            return true;
        }
        return false;

    }

    /*
     * Fait les comparaisons et vérifie si parmis les données séléctionnées
     * s'il n'y a pas une donné "AUTRE", équivalent d'un joker.
     */
    public boolean verifOther(PlateauContenu p1, PlateauContenu p2) {
        if (p1 == p2) {
            return true;
        } else if (p1 == PlateauContenu.AUTRE || p2 == PlateauContenu.AUTRE) {
            return true;
        } else {
            return false;
        }
    }

    /* Affichage à utilier que pendant la conception du jeu (debug) */
    public void affichage() {
        System.out.println();
        for (Map.Entry<DonneeCarte, PlateauContenu> entry : listDonnee.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public Carte getCarte() {
        return carte;
    }

    public int getRot() {
        return rotation;
    }

}
