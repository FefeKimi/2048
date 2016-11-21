package pack;

public class Case {
	private int val=0;
	
	public Case(int v){
		val = v;
	}
	
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public boolean estVide(){
		return (val==0);
	}
}
