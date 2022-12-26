package model.carcassonne.Plateau;

import java.util.List;

public enum PlateauContenu{
    CHATEAU, ROUTE, ABBAYE, CHAMPS, AUTRE;

    public static List<PlateauContenu> plateauSansExtension(){
        return List.of(CHATEAU, ROUTE, ABBAYE, CHAMPS);
    }
}