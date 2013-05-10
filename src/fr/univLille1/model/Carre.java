package fr.univLille1.model;

public class Carre extends Forme {
	
	
	private static final int COULEUR=0;
	private pc[][] tab_pc;
	
	public Carre(GameModel g){
		super.game = g; 
		tab_pc = new pc[2][2];
		for(int i=0; i<2 ;i++){
			for(int j=0; j<2; j++){
				tab_pc[i][j]=new pc(game.getX()/2+i,j,COULEUR);
			}
		}
	}
	@Override
	public boolean descend() {
		if(peut_descendre()){
			for(int i=1; i>=0 ;i--){
				for(int j=1; j>=0; j--){
					tab_pc[i][j].set(0, 1);
				}
			}
			return true;
		}
		return false;
	}

	public boolean peut_pivoter(){
		return true;
	}
	
	
	@Override
	public boolean peut_descendre() {
		// TODO Auto-generated method stub
		return tab_pc[1][1].peut_descendre() && tab_pc[0][1].peut_descendre();
	}

	@Override
	public boolean droite() {
		if(tab_pc[1][0].caseLibre(1, 0) && tab_pc[1][1].caseLibre(1,0)){
			for(int i=1; i>=0 ;i--){
				for(int j=0; j<2; j++){
					tab_pc[i][j].set(1, 0);
				}
			}
			return true;
		}			
		return false;
	}

	@Override
	public boolean gauche() {
		if(tab_pc[0][0].caseLibre(-1, 0) && tab_pc[0][1].caseLibre(-1,0)){
			for(int i=0; i<2 ;i++){
				for(int j=0; j<2; j++){
					tab_pc[i][j].set(-1, 0);
				}
			}
			return true;
		}			
		return false;
	}

	@Override
	public boolean tombe() {
		while(descend());
		return false;
	}
	@Override
	public boolean pivote() {
		return false;
	}
	
}
