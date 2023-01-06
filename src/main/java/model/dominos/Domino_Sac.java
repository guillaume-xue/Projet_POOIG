package model.dominos;

import java.util.Stack;

public class Domino_Sac {

    private final Stack<Domino_Tuile> sac;
    private int taille;

    public Domino_Sac(int taille){
        this.sac = new Stack<>();
        this.taille = taille;
        for(int i=0;i<taille;i++){
            sac.add(new Domino_Tuile());
        }
    }

    public Domino_Tuile nextTuile() {
        taille--;
        return sac.pop();
    }

    public boolean sacVide(){
        return taille == 0;
    }

    public int getTaille() {
        return taille;
    }
}
