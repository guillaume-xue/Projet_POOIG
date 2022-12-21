package model.dominos;

import java.util.Stack;

public class Domino_Sac {

    private Stack<Domino_Tuile> sac;
    private static int taille;

    public Domino_Sac(int taille){
        this.sac = new Stack<>();
        this.taille = taille;
        for(int i=0;i<taille;i++){
            sac.add(new Domino_Tuile());
        }
    }

    public Domino_Tuile getSac() {
        return sac.pop();
    }
}
