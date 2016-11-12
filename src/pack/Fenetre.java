package pack;
import java.awt.FlowLayout;

import javax.swing.*;

public class Fenetre {
	
	private JFrame fenetre;
	private Grille grille;
	
	 public static void main(String[] args)
	 {
		 Grille grille = new Grille(4,true);
		 Fenetre f = new Fenetre(grille);
	 }
	 
	 /*création de la fenetre*/
	 public Fenetre(Grille g){
		 fenetre = new JFrame();
		 grille = g;
		 fenetre.setTitle("Jeu2048");
		 fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 /*Ajout de bouton*/
		 fenetre.getContentPane().setLayout(new FlowLayout());
		 fenetre.getContentPane().add(new JButton("New game"));
		 fenetre.getContentPane().add(new JButton("Quitter"));
		 fenetre.setVisible(true);
		 fenetre.pack();
	 }
}
