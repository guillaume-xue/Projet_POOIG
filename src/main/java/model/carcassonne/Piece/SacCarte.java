package model.carcassonne.Piece;

import java.util.Collections;
import java.util.Stack;

public class SacCarte {
    public static Stack<CarteComplet> sac;

    /* Créaction de la pioche avec tout les types
     * de cartes disponibles avec la quantité indiqué
     * dans la Class Carte.
     */
    public SacCarte(){
        sac = new Stack<>();
        Carte[] listeCarte = Carte.values();
        for(Carte c : listeCarte){
            for(int i=0; i<c.getNbCopie(); i++){
                sac.add(new CarteComplet(c));
            }
        }
        Collections.shuffle(sac);
    }

    /* Renvoie la tuile actuel dans la pioche,
     * puis on le mélange avant de repiocher.
     */
    public void retourDansSac(CarteComplet c){
        sac.add(c);
        Collections.shuffle(sac);
    }

    public Stack<CarteComplet> getSac(){
        return sac;
    }
}
