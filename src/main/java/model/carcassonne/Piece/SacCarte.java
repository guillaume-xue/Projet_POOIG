package model.carcassonne.Piece;

import java.util.Collections;
import java.util.Stack;

public class SacCarte {
    public static Stack<Carte> sac;

    public SacCarte(){
        sac = new Stack<>();
        Carte[] listeCarte = Carte.values();
        for(Carte c : listeCarte){
            for(int i=0; i<c.getNbCopie(); i++){
                sac.add(c);
            }
        }
        Collections.shuffle(sac);
    }

    public Stack<Carte> getSac(){
        return sac;
    }
}
