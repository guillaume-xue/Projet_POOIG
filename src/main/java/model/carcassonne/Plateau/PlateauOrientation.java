package model.carcassonne.Plateau;

public enum PlateauOrientation {
    LEFT(-1),
    RIGHT(1);

    int val;

    PlateauOrientation(int val){
        this.val = val;
    }

    public int getVal(){
        return val;
    }
}
