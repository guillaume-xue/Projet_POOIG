package model.carcassonne.Piece;

import java.util.HashMap;
import java.util.Map;

import static model.carcassonne.Piece.DonneeCarte.*;

import model.carcassonne.Plateau.PlateauContenu;

public class CarteComplet {
    private Carte carte;
    private Map<DonneeCarte, PlateauContenu> listDonnee;
    private int rotation;

    public CarteComplet(Carte carte){
        rotation = 0;
        this.carte = carte;
        listDonnee = new HashMap<>();
        for(int i=0; i<DonneeCarte.values().length; i++){
            listDonnee.put(DonneeCarte.values()[i], carte.getParamPlat()[i]);
        }
    }

    public Map<DonneeCarte, PlateauContenu> getNord(){
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_OUEST, listDonnee.get(NORD_OUEST));
        tmp.put(NORD, listDonnee.get(NORD));
        tmp.put(NORD_EST, listDonnee.get(NORD_EST));
        return tmp;
    }

    public Map<DonneeCarte, PlateauContenu> getEst(){
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_EST, listDonnee.get(NORD_EST));
        tmp.put(EST, listDonnee.get(EST));
        tmp.put(SUD_EST, listDonnee.get(SUD_EST));
        return tmp;
    }

    public Map<DonneeCarte, PlateauContenu> getSud(){
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(SUD_OUEST, listDonnee.get(SUD_OUEST));
        tmp.put(SUD, listDonnee.get(SUD));
        tmp.put(SUD_EST, listDonnee.get(SUD_EST));
        return tmp;
    }

    public Map<DonneeCarte, PlateauContenu> getOuest(){
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_OUEST, listDonnee.get(NORD_OUEST));
        tmp.put(OUEST, listDonnee.get(OUEST));
        tmp.put(SUD_OUEST, listDonnee.get(SUD_OUEST));
        return tmp;
    }

    public Map<DonneeCarte, PlateauContenu> getValPlus(){
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD, listDonnee.get(NORD));
        tmp.put(EST, listDonnee.get(EST));
        tmp.put(SUD, listDonnee.get(SUD));
        tmp.put(OUEST, listDonnee.get(OUEST));
        return tmp;
    }    

    public Map<DonneeCarte, PlateauContenu> getValDiag(){
        Map<DonneeCarte, PlateauContenu> tmp = new HashMap<>();
        tmp.put(NORD_EST, listDonnee.get(NORD_EST));
        tmp.put(SUD_EST, listDonnee.get(SUD_EST));
        tmp.put(SUD_OUEST, listDonnee.get(SUD_OUEST));
        tmp.put(NORD_OUEST, listDonnee.get(NORD_OUEST));
        return tmp;
    }  

    public void rotationGauche(){
        //Map<DonneeCarte, PlateauContenu> tmp1 = getValPlus();
        //Map<DonneeCarte, PlateauContenu> tmp2 = getValDiag();
        PlateauContenu tmp = listDonnee.get(NORD);
        listDonnee.replace(NORD, listDonnee.get(EST));
        listDonnee.replace(EST, listDonnee.get(SUD));
        listDonnee.replace(SUD, listDonnee.get(OUEST));
        listDonnee.replace(OUEST, tmp);
        tmp = listDonnee.get(NORD_EST);
        listDonnee.replace(NORD_EST, listDonnee.get(SUD_EST));
        listDonnee.replace(SUD_EST, listDonnee.get(SUD_OUEST));
        listDonnee.replace(SUD_OUEST, listDonnee.get(NORD_OUEST));
        listDonnee.replace(NORD_OUEST, tmp);
        increRota(-1);
    }

    public void rotationDroite(){
        //Map<DonneeCarte, PlateauContenu> tmp1 = getValPlus();
        //Map<DonneeCarte, PlateauContenu> tmp2 = getValDiag();
        PlateauContenu tmp = listDonnee.get(NORD);
        listDonnee.replace(NORD, listDonnee.get(OUEST));
        listDonnee.replace(OUEST, listDonnee.get(SUD));
        listDonnee.replace(SUD, listDonnee.get(EST));
        listDonnee.replace(EST, tmp);
        tmp = listDonnee.get(NORD_EST);
        listDonnee.replace(NORD_EST, listDonnee.get(NORD_OUEST));
        listDonnee.replace(NORD_OUEST, listDonnee.get(SUD_OUEST));
        listDonnee.replace(SUD_OUEST, listDonnee.get(SUD_EST));
        listDonnee.replace(SUD_EST, tmp);
        increRota(1);
    }

    private void increRota(int x){
        if(x == 1){
            rotation++;
        }else{
            rotation += 3;
        }
        rotation = rotation%4;
    }

    public Carte getCarte(){
        return carte;
    }

    public int getRot(){
        return rotation;
    }

    public boolean comparaison(CarteComplet carteBis, int i, int j){
        if(i==2 && j==4){
            return comparGD(carteBis);
        }else if(i==4 && j == 2){
            return comparDG(carteBis);
        }else if(i==3 && j==1){
            return comparHB(carteBis);
        }else{
            return comparBH(carteBis);
        }
    }

    public boolean comparDG(CarteComplet carteBis){
        Map<DonneeCarte, PlateauContenu> tmp1 = getEst();
        Map<DonneeCarte, PlateauContenu> tmp2 = carteBis.getOuest();
        PlateauContenu[] tab = new PlateauContenu[6];
        int index = 0;
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp1.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp2.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(int i=0; i<3; i++){
            if(tab[i] != tab[i+3]){
                return false;
            }
        }
        return true;
    }
    public boolean comparGD(CarteComplet carteBis){
        Map<DonneeCarte, PlateauContenu> tmp1 = getOuest();
        Map<DonneeCarte, PlateauContenu> tmp2 = carteBis.getEst();
        PlateauContenu[] tab = new PlateauContenu[6];
        int index = 0;
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp1.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp2.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(int i=0; i<3; i++){
            if(tab[i] != tab[i+3]){
                return false;
            }
        }
        return true;
    }
    public boolean comparHB(CarteComplet carteBis){
        Map<DonneeCarte, PlateauContenu> tmp1 = getSud();
        Map<DonneeCarte, PlateauContenu> tmp2 = carteBis.getNord();
        PlateauContenu[] tab = new PlateauContenu[6];
        int index = 0;
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp1.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp2.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(int i=0; i<3; i++){
            if(tab[i] != tab[i+3]){
                return false;
            }
        }
        return true;
        
    }
    public boolean comparBH(CarteComplet carteBis){
        Map<DonneeCarte, PlateauContenu> tmp1 = getNord();
        Map<DonneeCarte, PlateauContenu> tmp2 = carteBis.getSud();
        PlateauContenu[] tab = new PlateauContenu[6];
        int index = 0;
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp1.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(Map.Entry<DonneeCarte, PlateauContenu> tmp : tmp2.entrySet()){
            tab[index] = tmp.getValue();
            index++;
        }
        for(int i=0; i<3; i++){
            if(tab[i] != tab[i+3]){
                return false;
            }
        }
        return true;
    }
}
