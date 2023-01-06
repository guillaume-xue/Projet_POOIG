package model;

import model.dominos.Domino_Tuile;

public interface Plateau {

    boolean addVerif(int x, int y, Domino_Tuile tuile);
    void addTuile(int x, int y, Domino_Tuile tuile);
    int getSize();

}
