package model.dominos;

public class Plateau {

    private Tuile[][] plateau;
    private Sac sac;

    public Plateau(int x){
        plateau = new Tuile[x][x];
        sac = new Sac(x*x);
    }

    public Tuile getTuile(){
        return sac.getSac();
    }

    public void addPlateau(int x, int y, Tuile tuile){
        plateau[x][y] = tuile;
    }

}
