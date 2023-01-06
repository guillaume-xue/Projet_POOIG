package model.dominos;

import model.Sac;

import java.util.Stack;

public class Domino_Sac implements Sac {

    private final Stack<Domino_Tuile> sac;
    private int taille;

    public Domino_Sac(int n){           // Créer un sac de taille n
        this.sac = new Stack<>();
        this.taille = n;
        for(int i=0;i<n;i++){
            sac.add(new Domino_Tuile());
        }
    }

    @Override
    public Domino_Tuile nextTuile() {   // Réduit la taille du sac et avoir la tuile suivante
        taille--;
        return sac.pop();
    }

    @Override
    public boolean sacEmpty(){
        return taille != 0;
    }   // Sac nom vide
}
