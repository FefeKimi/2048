/*fab*/
package pack;


import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class Jeu2048{	
	public static final char up ='z';
	public static final char down ='s';
	public static final char right ='d';
	public static final char left ='q';

	
	private ArrayList<ArrayList<Case>> grille;
	private int taille;
	private boolean Premier2048;
	
	/*Condtructeur*/
	Jeu2048(int t, boolean First2048){
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
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				System.out.print(grille.get(i).get(j).getVal()+"\t");
			}
			System.out.println("");
		}
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

	/*deplacement haut/bas/gauche/droite*/
	public void haut(){
		
		//Tableau de comparaison
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
	
		//recherche de la  case vide située le plus haut possible
		for (int j = 0; j < taille; j++) {

			for (int i = 0; i <taille ; i++) {
				
					for(int k=0; k<i; k++){
						if(grille.get(k).get(j).getVal()==0){
							grille.get(k).get(j).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
		}
		//fusion des cases
		for (int j = 0; j < taille; j++) {

			for (int i = 1; i <taille ; i++) {
				if(grille.get(i-1).get(j).getVal()==grille.get(i).get(j).getVal()){
					grille.get(i-1).get(j).setVal(grille.get(i-1).get(j).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
		//recherche de la  case vide située le plus haut possible
		for (int j = 0; j < taille; j++) {

			for (int i = 0; i <taille ; i++) {
				
					for(int k=0; k<i; k++){
						if(grille.get(k).get(j).getVal()==0){
							grille.get(k).get(j).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
		}
		
		//vérifie s'il y a eu un déplacement des cases
		for (int i = 0; i < taille; i++) {

			for (int j = taille-1; j >=0 ; j--) {
				if(grille.get(i).get(j).getVal()!=copie[i][j]){
					egal=false;
				}
			}
			
		}
		
		if(!egal){
			ajouterSiCaseVide();
		}
		
	}
	
	public void bas(){
		//Tableau de comparaison
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
	
		//recherche la case vide située le plus pas possible
		for (int j = 0; j < taille; j++) {
			
			for (int i = taille-1; i >= 0 ; i--) {
				
					for(int k=taille-1; k>i; k--){
						if(grille.get(k).get(j).getVal()==0){
							grille.get(k).get(j).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
			
		}
		//fusion des cases
		for (int j = 0; j < taille; j++) {

			for (int i = taille-2; i >=0 ; i--) {
				if(grille.get(i).get(j).getVal()==grille.get(i+1).get(j).getVal()){
					grille.get(i+1).get(j).setVal(grille.get(i).get(j).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
		//recherche la case vide située le plus pas possible
		for (int j = 0; j < taille; j++) {

			for (int i = taille-1; i >=0 ; i--) {
				
					for(int k=taille-1; k>i; k--){
						if(grille.get(k).get(j).getVal()==0){
							grille.get(k).get(j).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
			
		}
		//vérifie s'il y a eu un déplacement des cases
		for (int i = 0; i < taille; i++) {

			for (int j = taille-1; j >=0 ; j--) {
				if(grille.get(i).get(j).getVal()!=copie[i][j]){
					egal=false;
				}
			}
			
		}
		
		if(!egal){
			ajouterSiCaseVide();
		}
	}
	
	public void gauche(){
		//Tableau de comparaison
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
		
		//cherche la case vide la plus à gauche possible
		for (int i = 0; i < taille; i++) {

			for (int j = 0; j <taille ; j++) {
				
					for(int k=0; k<j; k++){
						if(grille.get(i).get(k).getVal()==0){
							grille.get(i).get(k).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
		}
		//fusion des cases
		for (int i = 0; i < taille; i++) {

			for (int j = 1; j <taille ; j++) {
				if(grille.get(i).get(j-1).getVal()==grille.get(i).get(j).getVal()){
					grille.get(i).get(j-1).setVal(grille.get(i).get(j-1).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
		//cherche la case vide la plus à gauche possible
		for (int i = 0; i < taille; i++) {

			for (int j = 0; j <taille ; j++) {
				
					for(int k=0; k<j; k++){
						if(grille.get(i).get(k).getVal()==0){
							grille.get(i).get(k).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
		}
		
		//vérifie s'il y a eu un déplacement des cases
		for (int i = 0; i < taille; i++) {

			for (int j = taille-1; j >=0 ; j--) {
				if(grille.get(i).get(j).getVal()!=copie[i][j]){
					egal=false;
				}
			}
			
		}
	
		if(!egal){
			ajouterSiCaseVide();
		}
	}
	
	
	public void droite(){
		//Tableau de comparaison
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
		
		//cherche la case vide située la plus à droite possible
		for (int i = 0; i < taille; i++) {
			for (int j = taille-2; j >=0 ; j--) {
				for(int k= taille-2; k>j; k--){
					if(grille.get(i).get(k).getVal()==0){
						grille.get(i).get(k).setVal(grille.get(i).get(j).getVal());
						grille.get(i).get(j).setVal(0);
						break;
					}
				}
			}
		}
		//fusion des cases	
		for (int i = 0; i < taille; i++) {
			for (int j = taille-2; j >=0 ; j--) {
				if(grille.get(i).get(j).getVal()==grille.get(i).get(j+1).getVal()){
					grille.get(i).get(j+1).setVal(grille.get(i).get(j).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
		//cherche la case vide située la plus à droite possible
		for (int i = 0; i < taille; i++) {
			for (int j = taille-2; j >=0 ; j--) {
				for(int k= taille-1; k>j; k--){
					if(grille.get(i).get(k).getVal()==0){
						grille.get(i).get(k).setVal(grille.get(i).get(j).getVal());
						grille.get(i).get(j).setVal(0);
						break;
					}
				}
			}
		}
		
		//vérifie s'il y a eu un déplacement des cases
		for (int i = 0; i < taille; i++) {
			for (int j = taille-1; j >=0 ; j--) {
				if(grille.get(i).get(j).getVal()!=copie[i][j]){
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
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				if(grille.get(i).get(j).getVal()== 2048 && Premier2048){
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
				if(grille.get(i).get(j).getVal()==0)
					return false;
			}
		}
		//on vérifie s'il y a fusion possible en ligne
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille-1;j++){
				if(grille.get(i).get(j).getVal()==grille.get(i).get(j+1).getVal())
					return false;
			}
		}
		//on vérifie s'il y a fusion possible en colonnes 
		for(int j=0;j<taille;j++){
			for(int i=0;i<taille-1;i++){
				if(grille.get(i).get(j).getVal()==grille.get(i+1).get(j).getVal())
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
					grille.get(i).get(j).setVal(0);
					
				}
			}
			ajouterSiCaseVide();
			ajouterSiCaseVide();
			
		
	}

}