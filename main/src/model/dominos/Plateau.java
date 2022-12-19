package model.dominos;

public class Plateau {

    private Tuile[][] plateau;
    private Sac sac;

    public Plateau(int x){
        plateau = new Tuile[x][x];
        sac = new Sac(x*x);
    }

    public String espace(int n){
        return Tuile.espace(n);
    }

    public void affiche(){
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[i].length;j++){
                String l1 = "";
                String l2 = "";
                String l3 = "";
                String l4 = "";
                String l5 = "";
                for(int k=0;k<plateau[i][j].tuile.length;k++){
                    for(int l=0;l<plateau[i][j].tuile[k].length;l++){
                        if(plateau[i][j] != null){
                            l1+=espace(2)+plateau[i][j].tuile[0][0]+espace(2)+plateau[i][j].tuile[0][1]+espace(2)+plateau[i][j].tuile[0][2];
                            l2+=plateau[i][j].tuile[3][2]+espace(9)+plateau[i][j].tuile[1][0];
                            l3+=plateau[i][j].tuile[3][1]+espace(9)+plateau[i][j].tuile[1][1];
                            l4+=plateau[i][j].tuile[3][0]+espace(9)+plateau[i][j].tuile[1][2];
                            l5+=espace(2)+plateau[i][j].tuile[2][2]+espace(2)+plateau[i][j].tuile[2][1]+espace(2)+plateau[i][j].tuile[2][0];
                        }else{
                            l1+=espace(11);
                            l2+=espace(11);
                            l3+=espace(11);
                            l4+=espace(11);
                            l5+=espace(11);
                        }
                    }
                    System.out.println(l1);
                    System.out.println(l2);
                    System.out.println(l3);
                    System.out.println(l4);
                    System.out.println(l5);
                }
            }
        }
    }

    public Tuile getTuile(){
        return sac.getSac();
    }

    public void addTuile(int x, int y, Tuile tuile){
        plateau[x][y] = tuile;
    }

    public static void main(String[] args) {
        Plateau p = new Plateau(5);

    }

}
