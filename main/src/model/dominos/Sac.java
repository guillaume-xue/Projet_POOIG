package model.dominos;

import java.util.Stack;

public class Sac {

    private Stack<Tuile> sac;
    private static int taille;

    public Sac(int taille){
        this.sac = new Stack<>();
        this.taille = taille;
        for(int i=0;i<taille;i++){
            sac.add(new Tuile());
        }
    }

    public Tuile getSac() {
        return sac.pop();
    }
}
