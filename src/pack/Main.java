package pack;

import java.io.InputStreamReader;

public class Main {
	/*Execution du jeu*/
	public static void main(String[]args){
		Jeu2048 jeu = new Jeu2048(4,true);
		jeu.afficheGrille();
		while(jeu.perdu()==false){
			char c= 'p';
			try{
				InputStreamReader saisie = new InputStreamReader(System.in);
				char a =  (char) saisie.read();
				jeu.action(a);
				jeu.afficheGrille();
			}catch(Exception e){
				System.out.println("Saisie incorrecte ! Recommencez : ");
			}
		}
		System.out.println("Fin du jeu.");
	}
}
