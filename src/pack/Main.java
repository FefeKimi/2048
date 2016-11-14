package pack;

import java.io.InputStreamReader;

public class Main {
	private static final char oui = 'o';
	private static final char Oui = 'O';
	private static final char non = 'n';
	private static final char Non = 'N';
	
	/*Execution du jeu*/
	public static void main(String[]args){
		Grille grille = new Grille(4,true);
		char b = oui ;
		while(b== oui || b==Oui){
			grille.afficheGrille();
			while(grille.perdu()==false){
	
				try{
					InputStreamReader saisie = new InputStreamReader(System.in);
					char a =  (char) saisie.read();
	grille.action(a);
					grille.afficheGrille();
				}catch(Exception e){
					System.out.println("Saisie incorrecte ! Recommencez : ");
				}
			}
			do{
				try{
					System.out.println("Perdu! Voulez-vous recommencer? (o/n)");
					InputStreamReader choix = new InputStreamReader(System.in);
					b =  (char) choix.read();
					if(b==Oui || b==oui){
						grille.reset(true);
					}
				}
				catch(Exception e){
					System.out.println("Saisie incorrecte ! Recommencez : ");
				}
			}while(b!= oui && b!= non && b!= Oui && b!= Non);
			
		}
		System.out.println("Fin du jeu.");
	}
}
