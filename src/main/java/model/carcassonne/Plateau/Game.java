package model.carcassonne.Plateau;

import model.carcassonne.AffGestionTuile;
import model.carcassonne.AffichageCarc;
import model.carcassonne.Piece.CarteComplet;
import model.carcassonne.Piece.SacCarte;

public class Game {
    private AffichageCarc aff;
    private AffGestionTuile agt;

    private SacCarte sac;
    private CarteComplet prochainePioche;

    private int x, y, scale;

    /* Class en charge des tours de la partie. */
    public Game(AffichageCarc aff, AffGestionTuile agt){
        this.aff = aff;
        this.agt = agt;
        sac = new SacCarte();
        prochaineTuile();
        //agt.nextTuile(prochainePioche, prochainePioche.getRot());

    }

    /* Met à jour la prochaine tuile pioché et actualise
     * la fenêtre de l'éditeur.
     */
    public void prochainTour(){
        if(!finJeu()){
            prochaineTuile();
            agt.nextTuile(prochainePioche, prochainePioche.getRot());
        }else{
            aff.afficherMessage("La partie est terminé");
        }

    }

    /* Pioche la prochaine tuile. */
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

    /* Importe les 4 tuiles autour de l'emplacement séléctionner
     * puis les envoie en comparaison pour détérminer si cette
     * position est valide pour la tuile en main.
     */
    public boolean verifPosition(){
        boolean status = true;
        if(aff.getCompo(x+1, y) != null){
            if(!aff.getCompo(x+1, y).getCarteComplet().comparDG(prochainePioche)){
                status = false;
            }
        }
        if(aff.getCompo(x-1, y) != null){
           if(!prochainePioche.comparDG(aff.getCompo(x-1, y).getCarteComplet())){
                status = false;
           }
        }
        if(aff.getCompo(x, y-1) != null){
            if(!aff.getCompo(x, y-1).getCarteComplet().comparHB(prochainePioche)){
                status = false;
            }
        }
        if(aff.getCompo(x, y+1) != null){
            if(!prochainePioche.comparHB(aff.getCompo(x, y+1).getCarteComplet())){
                status = false;
            }
        }
        return status;
    }

    /* Le jeu s'arrête dès que la pioche est vide. */
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

    /* Renvoie la tuile actuel dans la pioche
     * puis en repioche une.
     */
    public void repiocher(){
        sac.retourDansSac(prochainePioche);
        prochainTour();
    }
}
