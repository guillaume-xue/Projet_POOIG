package model;

import model.dominos.Domino_Tuile;

public interface Plateau {

    boolean addVerif(int x, int y, Domino_Tuile tuile);
    boolean addVerifTop(int x, int y, Domino_Tuile tuile);
    boolean addVerifBottum(int x, int y, Domino_Tuile tuile);
    boolean addVerifLeft(int x, int y, Domino_Tuile tuile);
    boolean addVerifRight(int x, int y, Domino_Tuile tuile);

}
