package pack;

import java.io.InputStreamReader;

public class Main {
	/*Execution du jeu*/
	public static void main(String[]args){
		Jeu2048 jeu = new Jeu2048(4,true);
		
		
		char b = 'o';
		while(b=='o' || b=='O'){
			jeu.afficheGrille();
			while(jeu.perdu()==false){
	
				try{
					InputStreamReader saisie = new InputStreamReader(System.in);
					char a =  (char) saisie.read();
					jeu.action(a);
					jeu.afficheGrille();
				}catch(Exception e){
					System.out.println("Saisie incorrecte ! Recommencez : ");
				}
			}
			do{
				try{
					System.out.println("Perdu! Voulez-vous recommencer? (o/n)");
					InputStreamReader choix = new InputStreamReader(System.in);
					b =  (char) choix.read();
					if(b=='o' || b=='O'){
						jeu.reset(true);
					}
				}
				catch(Exception e){
					System.out.println("Saisie incorrecte ! Recommencez : ");
				}
			}while(b!= 'o'&& b!='n' && b!= 'O'&& b!='N');
			
		}
		System.out.println("Fin du jeu.");
	}
}
