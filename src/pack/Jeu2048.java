/*fab*/
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
		ajouterSiCaseVide();
		ajouterSiCaseVide();
		
	}
	
	void afficheGrille(){
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
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

	public void action(char car) {
		switch(car){
			case 'z':{
				this.haut();
				break;
			}
			case 's':{
				this.bas();
				
			}
			break;
			case 'q': {
				this.gauche();
				
			}
			break;
			case 'd':{
				this.droite();
				
			}
			break;
			default:{
				
			}
			break;
		}
		if(this.gagne()==true){
			System.out.println("Vous avez gagné mais vous pouvez continuer!");
		}
	}

	/*deplacement*/
	public void haut(){
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		
		
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
	
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
		for (int j = 0; j < taille; j++) {

			for (int i = 1; i <taille ; i++) {
				if(grille.get(i-1).get(j).getVal()==grille.get(i).get(j).getVal()){
					grille.get(i-1).get(j).setVal(grille.get(i-1).get(j).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
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
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
	
		for (int j = 0; j < taille; j++) {

			for (int i = 0; i <taille ; i++) {
				
					for(int k=taille-1; k>i; k--){
						if(grille.get(k).get(j).getVal()==0){
							grille.get(k).get(j).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
			
		}
		for (int j = 0; j < taille; j++) {

			for (int i = taille-2; i >=0 ; i--) {
				if(grille.get(i).get(j).getVal()==grille.get(i+1).get(j).getVal()){
					grille.get(i+1).get(j).setVal(grille.get(i).get(j).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
		for (int j = 0; j < taille; j++) {

			for (int i = 0; i <taille ; i++) {
				
					for(int k=taille-2; k>i; k--){
						if(grille.get(k).get(j).getVal()==0){
							grille.get(k).get(j).setVal(grille.get(i).get(j).getVal());
							grille.get(i).get(j).setVal(0);
							break;
						}
					}
			}
			
		}
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
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
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
		for (int i = 0; i < taille; i++) {

			for (int j = 1; j <taille ; j++) {
				if(grille.get(i).get(j-1).getVal()==grille.get(i).get(j).getVal()){
					grille.get(i).get(j-1).setVal(grille.get(i).get(j-1).getVal()*2);
					grille.get(i).get(j).setVal(0);
				}
			}
		}
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
		boolean egal=true;
		int copie[][]= new int[taille][taille];
		
		
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille;j++){
				copie[i][j]= grille.get(i).get(j).getVal();
				
			}
			
		}
		
		
		//On parcourt ArrayList, pas la peine de parcourir jusqu'à la case la plus à droite 	
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
				
				for (int i = 0; i < taille; i++) {

					for (int j = taille-2; j >=0 ; j--) {
						if(grille.get(i).get(j).getVal()==grille.get(i).get(j+1).getVal()){
							grille.get(i).get(j+1).setVal(grille.get(i).get(j).getVal()*2);
							grille.get(i).get(j).setVal(0);
						}
					}
				}
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
				if(grille.get(i).get(j).getVal()==2048)
					return true;
			}
		}
		return false;
	}
	
	public boolean perdu(){
		/*haut*/
		for(int j=0;j<taille;j++){
			for(int i=0;i<taille-1;i++){
				if(grille.get(i).get(j).getVal()==0)
					return false;
			}
		}
		for(int i=0;i<taille;i++){
			for(int j=0;j<taille-1;j++){
				if(grille.get(i).get(j).getVal()==grille.get(i).get(j+1).getVal())
					return false;
			}
		}
		for(int j=0;j<taille;j++){
			for(int i=0;i<taille-1;i++){
				if(grille.get(i).get(j).getVal()==grille.get(i+1).get(j).getVal())
					return false;
			}
		}
		
		return true;
		
	}
	
	public void fusionner(int lign, int col){
		int val= grille.get(lign).get(col).getVal();
		int valSuiv= grille.get(lign).get(col).getVal();
		if(val == valSuiv){
			valSuiv = val+valSuiv;
			val=0;
		}
	}
	
	/*recommencer*/
	public void reset(){
		
	}
	
	public int get(int i,int j){
		return 0;
	}

}