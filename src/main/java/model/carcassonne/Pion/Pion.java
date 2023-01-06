package model.carcassonne.Pion;

import java.awt.Color;

import model.carcassonne.Piece.DonneeCarte;

public class Pion {

    private Color couleur;
    private int id;
    private int posX,posY;
    private DonneeCarte pos;

    public Pion(Color c, int id){
        this.couleur = c;
        this.id = id;
    }

    public void majPion(DonneeCarte d, int x, int y){
        pos = d;
        posX = x;
        posY = y;
    }

}
