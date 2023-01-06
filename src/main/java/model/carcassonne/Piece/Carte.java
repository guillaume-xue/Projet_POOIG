package model.carcassonne.Piece;

import static model.carcassonne.Plateau.PlateauContenu.CHATEAU;
import static model.carcassonne.Plateau.PlateauContenu.ROUTE;

import static model.carcassonne.Plateau.PlateauContenu.ABBAYE;
import static model.carcassonne.Plateau.PlateauContenu.CHAMPS;
import static model.carcassonne.Plateau.PlateauContenu.AUTRE;

import model.carcassonne.Plateau.PlateauContenu;

public enum Carte {

    // 72 carte, pour le mode a 4 joueur, format : type(quantité, NORD, EST, SUD,
    // OUEST, NORD-EST, SUD-EST, SUD-OUEST, NORD-OUEST, CENTRE)

    Null(0, AUTRE, AUTRE, AUTRE, AUTRE, AUTRE, AUTRE, AUTRE, AUTRE, AUTRE),
    RouteIncurve(9, CHAMPS, CHAMPS, ROUTE, ROUTE, CHAMPS, CHAMPS, CHAMPS, CHAMPS, ROUTE),
    ChateauMur1FaceRouteDroite(3, CHATEAU, ROUTE, ROUTE, CHAMPS, AUTRE, CHAMPS, CHAMPS, AUTRE, ROUTE),
    ChateauDiagAvecRouteEtEmbleme(2, CHATEAU, ROUTE, ROUTE, CHATEAU, AUTRE, CHAMPS, AUTRE, CHATEAU, ROUTE),
    ChateauEntreeCentre(1, CHATEAU, CHATEAU, ROUTE, CHATEAU, CHATEAU, AUTRE, AUTRE, CHATEAU, CHATEAU),
    ChateauEntreeVideEtEmbleme(1, CHATEAU, CHATEAU, CHAMPS, CHATEAU, CHATEAU, AUTRE, AUTRE, CHATEAU, CHATEAU),
    ChateauDiagAvecRoute(3, CHATEAU, ROUTE, ROUTE, CHATEAU, AUTRE, CHAMPS, AUTRE, CHATEAU, ROUTE),
    ChateauMur1FaceJonction(3, CHATEAU, ROUTE, ROUTE, ROUTE, AUTRE, CHAMPS, CHAMPS, AUTRE, ROUTE),
    Route(8, ROUTE, CHAMPS, ROUTE, CHAMPS, CHAMPS, CHAMPS, CHAMPS, CHAMPS, ROUTE),
    RouteT(4, CHAMPS, ROUTE, ROUTE, ROUTE, CHAMPS, CHAMPS, CHAMPS, CHAMPS, ROUTE),
    ChateauMur1Face(5, CHATEAU, CHAMPS, CHAMPS, CHAMPS, AUTRE, CHAMPS, CHAMPS, AUTRE, CHAMPS),
    ChateauCoteColle(2, CHATEAU, CHATEAU, CHAMPS, CHAMPS, AUTRE, AUTRE, CHAMPS, AUTRE, CHAMPS),
    ChateauEntreeVide(3, CHATEAU, CHATEAU, CHAMPS, CHATEAU, CHATEAU, AUTRE, AUTRE, CHATEAU, CHATEAU),
    Abbaye(4, CHAMPS, CHAMPS, CHAMPS, CHAMPS, CHAMPS, CHAMPS, CHAMPS, CHAMPS, ABBAYE),
    AbbayeRoute(2, CHAMPS, CHAMPS, ROUTE, CHAMPS, CHAMPS, CHAMPS, CHAMPS, CHAMPS, ABBAYE),
    ChateauDiagSansRoute(3, CHATEAU, CHAMPS, CHAMPS, CHATEAU, AUTRE, CHAMPS, AUTRE, CHATEAU, CHAMPS),
    ChateauTubeAvecEmbleme(2, CHAMPS, CHATEAU, CHAMPS, CHATEAU, AUTRE, AUTRE, AUTRE, AUTRE, CHATEAU),
    ChateauMur1FaceRoute(4, CHATEAU, ROUTE, CHAMPS, ROUTE, AUTRE, CHAMPS, CHAMPS, AUTRE, ROUTE),
    ChateauMur1FaceRouteGauche(3, CHATEAU, CHAMPS, ROUTE, ROUTE, AUTRE, CHAMPS, CHAMPS, AUTRE, ROUTE),
    ChateauTube(1, CHAMPS, CHATEAU, CHAMPS, CHATEAU, AUTRE, AUTRE, AUTRE, AUTRE, CHATEAU),
    ChateauEntreeCentreEtEmbleme(2, CHATEAU, CHATEAU, ROUTE, CHATEAU, CHATEAU, AUTRE, AUTRE, CHATEAU, CHATEAU),
    ChateauCentreEtEmbleme(1, CHATEAU, CHATEAU, CHATEAU, CHATEAU, CHATEAU, CHATEAU, CHATEAU, CHATEAU, CHATEAU),
    RouteCroisement(1, ROUTE, ROUTE, ROUTE, ROUTE, CHAMPS, CHAMPS, CHAMPS, CHAMPS, ROUTE),
    ChateauDiagSansRouteEtEmbleme(2, CHATEAU, CHAMPS, CHAMPS, CHATEAU, AUTRE, CHAMPS, AUTRE, CHATEAU, CHAMPS),
    ChateauCoteFaceAFace(3, CHAMPS, CHATEAU, CHAMPS, CHATEAU, AUTRE, AUTRE, AUTRE, AUTRE, CHAMPS);

    // 102 carte, pour le mode a 6 joueur (à ajouter ultérieurement)

    private final PlateauContenu[] donnees;
    private final int montant;

    Carte(int montant, PlateauContenu... plateau) {
        this.montant = montant;
        this.donnees = plateau;
    }

    public PlateauContenu[] getParamPlat() {
        return donnees;
    }

    public int getNbCopie() {
        return montant;
    }

}
