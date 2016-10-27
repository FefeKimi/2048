/*jeu*/
package pack;


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Jeu2048{	
	private ArrayList<ArrayList<Case>> grille;
	private int taille;
	
	Jeu2048(int t){
		this.taille = t;
		grille = new ArrayList<ArrayList<Case>>();
		for(int i=0;i<t;i++){
			ArrayList l = new ArrayList<Case>();
			for(int j=0;j<t;j++){
				l.add(new Case(0));
			}
			this.grille.add(l);
		}
		
	}
	
	void afficheGrille(){
		for(int i=0;i<grille.size();i++){
			for(int j=0;j<grille.size();j++){
				System.out.print(grille.get(i).get(j).getVal()+"\t");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[]args){
		Jeu2048 jeu = new Jeu2048(4);
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
	public void action(char car){
		if(car=='z'){
			this.haut();
		}
		else if(car=='s'){
			this.bas();
		}
		else{
			this.haut();
		}
	}
//	public void action(char car) {
//		switch(car){
//			case 'z':{
//				this.haut();
//				break;
//			}
//			case 's':{
//				this.bas();
//				
//			}
//			break;
//			case 'q': {
//				this.gauche();
//				
//			}
//			break;
//			case 'd':{
//				this.droite();
//				
//			}
//			break;
//			default:{
//				
//			}
//			break;
//		}
//	}

	/*deplacement*/
	public void haut(){
		ajouterSiCaseVide();
	}
	public void bas(){
		ajouterSiCaseVide();
	}
	public void gauche(){
		ajouterSiCaseVide();
	}
	public void droite(){
		ajouterSiCaseVide();
	}
	
	public void ajouterSiCaseVide(){
		Random r = new Random();
		
		int col = Math.abs(r.nextInt()%4);
		int li = Math.abs(r.nextInt()%4);
		if(this.perdu() == false){
			while(  grille.get(li).get(col).estVide() == false) {
				col = Math.abs(r.nextInt()%4);
				li = Math.abs(r.nextInt()%4);
			}
			if(grille.get(li).get(col).estVide()){
						int x= r.nextInt();
						if(x%2==0)
							grille.get(li).get(col).setVal(2);
						else
							grille.get(li).get(col).setVal(4);
			}
		}else{
			System.out.println("Vous avez perdu !");
		}
		
	}
	
	/*resultat*/
	public boolean gagne(){
		for(int i=0;i<grille.size();i++){
			for(int j=0;j<grille.size();j++){
				if(grille.get(i).get(j).getVal()==2048)
					return true;
			}
		}
		return false;
	}
	
	public boolean perdu(){
		for(int i=0;i<grille.size();i++){
			for(int j=0;j<grille.size();j++){
				if(grille.get(i).get(j).getVal()==0)
					return false;
			}
		}
		return true;
		
	}
	
	/*recommencer*/
	public void reset(){
		
	}
	
	public int get(int i,int j){
		return 0;
	}

}