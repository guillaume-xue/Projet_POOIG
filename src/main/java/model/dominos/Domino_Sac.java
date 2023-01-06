package model.dominos;

import model.Sac;

import java.util.Stack;

public class Domino_Sac implements Sac {

    private final Stack<Domino_Tuile> sac;
    private int taille;

    public Domino_Sac(int taille){
        this.sac = new Stack<>();
        this.taille = taille;
        for(int i=0;i<taille;i++){
            sac.add(new Domino_Tuile());
        }
    }

    @Override
    public Domino_Tuile nextTuile() {
        taille--;
        return sac.pop();
    }

    @Override
    public boolean sacEmpty(){
        return taille != 0;
    }
}
