package model.dominos;

import java.util.LinkedList;

public class Tuile {

    LinkedList<int[]> tuile;

    public Tuile(){
        tuile = new LinkedList<>();
    }

    public void tourner(){
        tuile.add(tuile.pop());
    }

    public void affiche(){
        for(int[] t : tuile){
            for(int i=0;i<t.length;i++){
                System.out.print(t[i]+" ");
            }
            System.out.print(",");
        }
    }

    public static void main(String[] args) {
        Tuile t = new Tuile();
        for(int i=0;i<4;i++){
            int[] k = new int[3];
            for(int j=0;j<3;j++){
                k[j] = (int) (Math.random() * (9)+1);
            }
            t.tuile.add(k);
        }
        t.affiche();
        System.out.println();
        t.tourner();
        t.affiche();
    }

}
