package model.carcassonne.Pion;

import java.util.ArrayList;
import java.util.List;

import model.carcassonne.Piece.DonneeCarte;

import java.awt.Color;


public class Joueur {
    private Color c;
    private String s;
    private List<Pion> liste;
    private int compteur;

    public Joueur(int nb, Color c, String s){
        this.c = c;
        this.s = s;
        compteur = nb-1;
        liste = new ArrayList<Pion>();
        for(int i=0; i<nb; i++){
            liste.add(new Pion(c, i));
        }
    }
    /* Indique qu'un pion a été utilisé et met à jour ses coordonnées */
    public void addPionOnBoard(DonneeCarte d, int x, int y){
        liste.get(compteur).majPion(d, x, y);
        usePion();
    }

    public String getColorName(){return s;}
    public Color getColor(){return c;}
    public void usePion(){compteur--;}
    public boolean pionDispo(){return compteur>0;}

}

