package model.dominos;

public class Domino_Plateau implements model.Plateau {

    private Domino_Tuile[][] plateau;
    private Domino_Sac sac;
    private int nbManche;

    /*
        Constructeur qui doit créer un tableau de tuile de taille x+2, +2 pour facilité la verife par la suite.
     */
    public Domino_Plateau(int x){
        plateau = new Domino_Tuile[x+2][x+2];
        sac = new Domino_Sac(x*x);
        nbManche = 0;
    }

    public String espace(int n){
        return Domino_Tuile.espace(n);
    }

    /*
        Affiche le Domino_Plateau sur le terminal avec les tuiles poser et les coordonnées.
     */
    public void affiche(){
        for(int i=1;i<plateau.length-1;i++){
            String l1 = "";
            String l2 = "";
            String l3 = "";
            String l4 = "";
            String l5 = "";
            for(int j=1;j<plateau[i].length-1;j++) {
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

    public boolean sameArray(int[] t1, int[] t2){
        for(int i=0;i<t1.length;i++){
            if(t1[i] != t2[i]) return false;
        }
        return true;
    }

    @Override
    public boolean addVerifTop(int x, int y, Domino_Tuile tuile){
        if(plateau[x-1][y] == null) return true;
        return sameArray(tuile.tuile[0], plateau[x-1][y].tuile[2]);
    }

    @Override
    public boolean addVerifRight(int x, int y, Domino_Tuile tuile){
        if(plateau[x][y+1] == null) return true;
        return sameArray(tuile.tuile[1], plateau[x][y+1].tuile[3]);
    }

    @Override
    public boolean addVerifBottum(int x, int y, Domino_Tuile tuile){
        if(plateau[x+1][y] == null) return true;
        return sameArray(tuile.tuile[2], plateau[x+1][y].tuile[0]);
    }

    @Override
    public boolean addVerifLeft(int x, int y, Domino_Tuile tuile){
        if(plateau[x][y-1] == null) return true;
        return sameArray(tuile.tuile[3], plateau[x][y-1].tuile[1]);
    }

    @Override
    public boolean addVerif(int x, int y, Domino_Tuile tuile){
        if(this.plateau[x][y] == null){
            if(!(this.plateau[x+1][y+1] != null && this.plateau[x+1][y-1] != null && this.plateau[x-1][y-1] != null && this.plateau[x-1][y+1] != null)){
                if(addVerifTop(x,y,tuile) && addVerifBottum(x,y,tuile) && addVerifLeft(x,y,tuile) && addVerifRight(x,y,tuile)){
                    System.out.println("chiffre dif");
                    return false;
                }
                return true;
            }else{
                System.out.println("Pas de tuile au tour.");
                return false;
            }
        }else{
            System.out.println("Il y a déjà une tuile.");
            return false;
        }
    }

    public void addTuile(int x, int y, Domino_Tuile tuile){
        if(nbManche++ == 0) plateau[x][y] = tuile;
        else{
            if(addVerif(x,y,tuile)) plateau[x][y] = tuile;
        }
    }

    public Domino_Tuile getTuile(){
        return sac.getSac();
    }

    public static void main(String[] args) {
        Domino_Plateau p = new Domino_Plateau(5);
        int [] t1 = {1,2,3};
        int [] t2 = {3,2,1};
        int [] t3 = {1,2,3};
        int [] t4 = {3,2,1};
        Domino_Tuile tu1 = new Domino_Tuile(t1,t2,t3,t4);
        Domino_Tuile tu2 = new Domino_Tuile(t1,t4,t2,t1);
        Domino_Tuile tu3 = new Domino_Tuile(t4,t4,t2,t1);
        p.addTuile(3,3,tu1);
        p.affiche();
        System.out.println("-------------------------------");
        p.addTuile(3,4,tu2);
        p.affiche();
        System.out.println("-------------------------------");
        p.addTuile(4,3,tu3);
        p.affiche();
    }

}
