package gui.dominos;

import model.dominos.Domino_Plateau;
import model.dominos.Domino_Tuile;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu_Dominos_Terminal {

    private Scanner scanner;
    private Domino_Plateau plateau;
    private Domino_Tuile tuile;
    private int x, y;
    private String str;
    private boolean abandon;

    public Jeu_Dominos_Terminal(){
        this.scanner = new Scanner(System.in);
        this.plateau = new Domino_Plateau(5);
        this.tuile = this.plateau.nextTuile();
        this.x = 0;
        this.y = 0;
        this.abandon = true;
    }

    public void rotation(){     // Tourner la tuile vers là droite ou vers là gauche
        System.out.println("\"d\" pour tourner à droite ou \"g\" pour tourner à gauche :");
        str = scanner.nextLine();
        if(str.equals("d")){
            this.tuile.turnRight();
            this.tuile.affiche();
        }else if(str.equals("g")){
            this.tuile.turnLeft();
            this.tuile.affiche();
        }else{
            System.out.println("Valeur non reconnu");
            rotation();
        }
    }

    public void valide(){       // Placer la tuile sur le plateau aux coordonnées x, y
        try {
            System.out.println("Entrer un le coordonne x :");
            x = scanner.nextInt();
            System.out.println("Entrer un le coordonne y :");
            y = scanner.nextInt();
            if(this.plateau.addVerif(x,y,this.tuile)){
                this.plateau.addTuile(x,y,this.tuile);
                this.tuile = this.plateau.nextTuile();
            }else{
                valide();
            }
        }catch (InputMismatchException e){
            valide();
        }

    }

    /*
        Les actions qu'on veut effectuer (tourner la tuile, poser la tuile, passer et abandonner la partie).
     */
    public void action(){
        System.out.println("\"r\" pour rotation\n\"v\" pour valide\n\"p\" pour passer\n\"a\" pour abandon");
        str = scanner.nextLine();
        System.out.println();
        if(str.equals("r")){
            rotation();
        }else if(str.equals("v")){
            valide();
        }else if(str.equals("p")){
            this.tuile = this.plateau.nextTuile();
        }else if(str.equals("a")){
            this.abandon = false;
        }else{
            System.out.println("Valeur non reconnu");
            action();
        }
    }

    public void jeu(){
        do{
            this.plateau.affiche();
            System.out.println("Tuile actuelle :");
            this.tuile.affiche();
            action();
        }while (this.plateau.sacEmpty() && this.abandon);
        System.out.println("Fin du jeu !");
    }

    public static void main(String[] args) {
        Jeu_Dominos_Terminal dominos = new Jeu_Dominos_Terminal();
        dominos.jeu();
    }
}
