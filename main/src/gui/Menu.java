package gui;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    private JPanel body;
    private JButton buttonJouer;

    public Menu(){
        this.setTitle("Projet_POOIG");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        body = new JPanel();
        body.setBackground(new Color(229, 229, 229, 255));

        buttonJouer = new JButton("JOUER");
        body.add(buttonJouer);
        buttonJouer.setHorizontalAlignment(SwingConstants.CENTER);


        this.getContentPane().setLayout(new GridLayout());
        this.getContentPane().add(body);
    }

}
