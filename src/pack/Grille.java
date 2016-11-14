package pack;
import java.util.ArrayList;
import java.util.Random;

public class Grille{	
	public static final char up ='z';
	public static final char down ='s';
	public static final char right ='d';
	public static final char left ='q';
	
	private ArrayList<ArrayList<Case>> grille;
	private int taille;
	private boolean Premier2048;
	
	/*Condtructeur*/
	Grille(int t, boolean First2048){
		this.taille = t;
		this.Premier2048 = First2048;
		grille = new ArrayList<ArrayList<Case>>();
		for(int i=0;i<t;i++){
			ArrayList<Case> l = new ArrayList<Case>();
			for(int j=0;j<t;j++){
				l.add(new Case(0));
			}
			this.grille.add(l);
		}
		ajouterSiCaseVide();
		ajouterSiCaseVide();
		
	}
	
	/*Affichage de la Grille*/
	void afficheGrille(){
		for (int k = 0; k < 17; k++) {
			System.out.print("_");
		}
		System.out.println("");	
		System.out.println("");
			for(int i=0;i<taille;i++){
				System.out.print("| ");
				for(int j=0;j<taille;j++){
					if(getGrille(i, j)==0){
						System.out.print( "  | ");
					}
					else{
						System.out.print(getGrille(i, j) + " | ");
					}
					
				}
				System.out.println("");
			}
			for (int k = 0; k < 17; k++) {
				System.out.print("_");
			}
			System.out.println("");
		
	}
	

	/*déplacement des cases*/
	public void action(char car) {
		switch(car){
			case up:{
				this.haut();
				break;
			}
			case down:{
				this.bas();
				
			}
			break;
			case left: {
				this.gauche();
				
			}
			break;
			case right:{
				this.droite();
				
			}
			break;
			default:{
				System.out.println("Saisie incorrecte ! Recommencez : ");
			}
			break;
		}
		if(this.gagne()==true){
			System.out.println("Vous avez gagné mais vous pouvez continuer!");
		}
	}
	public int getGrille(int i, int j){
		return grille.get(i).get(j).getVal();
	}
	
	public void setGrille(int i, int j, int val){
		 this.grille.get(i).get(j).setVal(val);
	}
	public void deplacement(int i, int j){
		for(int k= taille-1; k>j; k--){
			if(getGrille(i,k)==0){
				this.setGrille(i, k, getGrille(i, j));
				this.setGrille(i, j, 0);
				break; 
			}	
		}
	}
	

	/*deplacement haut/bas/gauche/droite*/
	public void haut(){
		for (int i = 0; i < 1; i++) {
			rotation();
		}
		droite();
		for (int i = 0; i < 3; i++) {
			rotation();
		}
//		
		
	}
	
	public void bas(){
		for (int i = 0; i < 3; i++) {
			rotation();
		}
		droite();
		for (int i = 0; i < 1; i++) {
			rotation();
		}
//		
	}
	
	public void gauche(){
		for (int i = 0; i < 2; i++) {
			rotation();
		}
		droite();
		for (int i = 0; i < 2; i++) {
			rotation();
		}

	}
	
	
	public void droite(){
		//Tableau de comparaison
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= getGrille(i, j);
				
			}
			
		}
		
		//cherche la case vide située la plus à droite possible
		for (int i = 0; i < taille; i++) {
			for (int j = taille-2; j >=0 ; j--) {
				deplacement(i,j);
			}
		}
		//fusion des cases	
		for (int i = 0; i < taille; i++) {
			for (int j = taille-2; j >=0 ; j--) {
				if(getGrille(i, j)==getGrille(i, j+1)){
					setGrille(i, j+1, getGrille(i, j)*2);
					setGrille(i, j,0);
				}
			}
		}
		//cherche la case vide située la plus à droite possible
		for (int i = 0; i < taille; i++) {
			for (int j = taille-2; j >=0 ; j--) {
				deplacement(i,j);		
			}
		}
		
		//vérifie s'il y a eu un déplacement des cases
		for (int i = 0; i < taille; i++) {
			for (int j = taille-1; j >=0 ; j--) {
				if(getGrille(i, j)!=copie[i][j]){
					egal=false;
				}
			}		
		}
		if(!egal){
			ajouterSiCaseVide();
		}		
	}
			
	//ajout des cases aléatoirement
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
							setGrille(li, col,2);
							
						else
							setGrille(li, col,4);
							
			}
		}else{
			System.out.println("Vous avez perdu !");
		}
		
	}
	
	/*resultat*/
	public boolean gagne(){
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				if(getGrille(i, j)== 2048 && Premier2048){
					Premier2048=false;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean perdu(){
		//on vérifie s'il y a une case vide 
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				if(getGrille(i, j)==0)
					return false;
			}
		}
		//on vérifie s'il y a fusion possible en ligne
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille-1;j++){
				if(getGrille(i, j)==getGrille(i, j+1))
					return false;
			}
		}
		//on vérifie s'il y a fusion possible en colonnes 
		for(int j=0;j<taille;j++){
			for(int i=0;i<taille-1;i++){
				if(getGrille(i, j)==getGrille(+1, j))
					return false;
			}
		}
		
		return true;
		
	}
	/*recommencer*/
	public void reset(boolean First2048){
		
			this.Premier2048=First2048;
			for(int i=0;i<taille;i++){
				for(int j=0;j<taille;j++){
					setGrille(i, j, 0);
					
					
				}
			}
			ajouterSiCaseVide();
			ajouterSiCaseVide();	
	}
	public void rotation(){
		int copie[][]= new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {		
					copie[j][taille-i-1]= getGrille(i, j);
					
					}
					
				}
			
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
					setGrille(i, j, copie[i][j]);
					
											}				
				}
			}

}