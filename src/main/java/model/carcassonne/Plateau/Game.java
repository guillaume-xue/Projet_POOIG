package model.carcassonne.Plateau;

import model.carcassonne.AffGestionTuile;
import model.carcassonne.AffichageCarc;
import model.carcassonne.Piece.CarteComplet;
import model.carcassonne.Piece.SacCarte;

public class Game {
    AffichageCarc aff;
    AffGestionTuile agt;

    SacCarte sac;
    CarteComplet prochainePioche;

    private int x, y, scale;

    public Game(AffichageCarc aff, AffGestionTuile agt){
        this.aff = aff;
        this.agt = agt;
        sac = new SacCarte();
        prochaineTuile();
        agt.nextTuile(prochainePioche, prochainePioche.getRot());

    }

    public void prochainTour(){
        prochaineTuile();
        agt.nextTuile(prochainePioche, prochainePioche.getRot());
    }

    public void prochaineTuile(){
        prochainePioche = sac.getSac().pop();
    }

    public CarteComplet getPP(){
        return prochainePioche;
    }

    public void placement(int x, int y, int scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public boolean verifPosition(){
        boolean status = true;
        if(aff.getCompo(x-50, y) != null){
            if(!aff.getCompo(x-50, y).getCarteComplet().comparaison(prochainePioche, 2, 4)){
                status = false;
            }
        }
        if(aff.getCompo(x+50, y) != null){
           if(!aff.getCompo(x+50, y).getCarteComplet().comparaison(prochainePioche, 4, 2)){
                status = false;
           }
        }
        if(aff.getCompo(x, y-50) != null){
            if(!aff.getCompo(x, y-50).getCarteComplet().comparaison(prochainePioche, 3, 1)){
                status = false;
            }
        }
        if(aff.getCompo(x, y+50) != null){
            if(!aff.getCompo(x, y+50).getCarteComplet().comparaison(prochainePioche, 1, 3)){
                status = false;
            }
        }
        return status;
    }

    private boolean finJeu(){
        if(sac.getSac().size() == 0){
            return true;
        }
        return false;
    }

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    //public boolean validationPos()
}
