package model.dominos;

import java.util.LinkedList;

public class Tuile {

    int[][] tuile;

    public Tuile(){
        tuile = new int[4][3];
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                tuile[i][j] = (int) (Math.random() * (9) + 1);
            }
        }
    }

    public void tournerDroite(){
        int[] tmp = tuile[3];
        for(int i=3;i>0;i--){
            tuile[i] = tuile[i-1];
        }
        tuile[0] = tmp;
    }

    public void tournerGauche(){
        int[] tmp = tuile[0];
        for(int i=1;i<4;i++){
            tuile[i-1] = tuile[i];
        }
        tuile[3] = tmp;
    }


    public void affiche(){
        System.out.println(
                " "+tuile[0][0]+"  "+tuile[0][1]+"  "+tuile[0][2]+"\n"+
                tuile[3][2]+"       "+tuile[1][0]+"\n"+
                tuile[3][1]+"       "+tuile[1][1]+"\n"+
                tuile[3][0]+"       "+tuile[1][2]+"\n"+
                " "+tuile[2][2]+"  "+tuile[2][1]+"  "+tuile[2][0]
        );
    }

    public static void main(String[] args) {
        Tuile t = new Tuile();
        t.affiche();
        t.tournerGauche();
        t.affiche();
        t.tournerDroite();
        t.affiche();
    }
}
