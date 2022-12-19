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
            String l1 = "";
            String l2 = "";
            String l3 = "";
            String l4 = "";
            String l5 = "";
            for(int j=0;j<plateau[i].length;j++) {
                if (plateau[i][j] != null) {
                    l1 += espace(2) + plateau[i][j].tuile[0][0] + espace(2) + plateau[i][j].tuile[0][1] + espace(2) + plateau[i][j].tuile[0][2] + espace(3);
                    l2 += plateau[i][j].tuile[3][2] + espace(9) + plateau[i][j].tuile[1][0]+ espace(1);
                    l3 += plateau[i][j].tuile[3][1] + espace(9) + plateau[i][j].tuile[1][1]+ espace(1);
                    l4 += plateau[i][j].tuile[3][0] + espace(9) + plateau[i][j].tuile[1][2]+ espace(1);
                    l5 += espace(2) + plateau[i][j].tuile[2][2] + espace(2) + plateau[i][j].tuile[2][1] + espace(2) + plateau[i][j].tuile[2][0] + espace(3);
                } else {
                    l1 += espace(12);
                    l2 += espace(12);
                    l3 += espace(4)+i+"/"+j+espace(5);
                    l4 += espace(12);
                    l5 += espace(12);
                }
            }
            System.out.println(l1);
            System.out.println(l2);
            System.out.println(l3);
            System.out.println(l4);
            System.out.println(l5);
        }
    }

    public void addTuile(int x, int y, Tuile tuile){
        plateau[x][y] = tuile;
    }

    public Tuile getTuile(){
        return sac.getSac();
    }

    public static void main(String[] args) {
        Plateau p = new Plateau(5);
        Sac c = new Sac(10);
        p.addTuile(0,0,c.getSac());
        p.addTuile(0,1,c.getSac());
        p.addTuile(0,2,c.getSac());
        p.addTuile(1,2,c.getSac());
        p.addTuile(2,3,c.getSac());
        p.addTuile(4,1,c.getSac());
        p.affiche();
    }

}
