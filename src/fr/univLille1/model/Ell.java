package fr.univLille1.model;

public class Ell extends Forme{
	private static final int COULEUR=1;

	pc[] tab;
	int pos;
	
	public Ell(GameModel game){
		super.game= game;
		tab=new pc[4];
		pos=0;
		
		tab[0]=new pc(game.getX()/2 ,0,COULEUR);
		tab[1]=new pc(game.getX()/2+1 ,0,COULEUR);
		tab[2]=new pc(game.getX()/2+1,1,COULEUR);
		tab[3]=new pc(game.getX()/2+1,2,COULEUR);
	}
	
	@Override
	public boolean descend() {
		if(! peut_descendre())
			return false;
		if(pos==0 || pos == 1){
			for(int i=3;i>=0;i--)
				tab[i].set(0, 1);
			return true;
		}
		
		for(int i=0;i<4;i++)
			tab[i].set(0, 1);
		return true;
	}

	@Override
	public boolean peut_descendre() {
		switch(pos){
		case 0: 	return tab[0].caseLibre(0, 1) && tab[3].caseLibre(0, 1);
		case 1:		return tab[3].caseLibre(0, 1 ) && tab[2].caseLibre(0,1) && tab[1].caseLibre(0, 1);
		case 2:		return tab[0].caseLibre(0, 1) && tab[1].caseLibre(0, 1);
		default: 	return tab[3].caseLibre(0, 1 ) && tab[2].caseLibre(0,1) && tab[0].caseLibre(0, 1);
		}
	}

	@Override
	public boolean droite() {
		
		switch(pos){
		case 0: 	if(!(tab[2].caseLibre(1, 0) && tab[1].caseLibre(1, 0) && tab[3].caseLibre(1, 0) )) return false;
		break;
	
		case 1:		if(!(tab[0].caseLibre(1, 0) && tab[1].caseLibre(1, 0) )) return false;
		break;

		case 2:		if(!(tab[0].caseLibre(1, 0) && tab[2].caseLibre(1, 0) && tab[3].caseLibre(1,0) )) return false;
		break;

		default: 	if(!(tab[0].caseLibre(1, 0) && tab[3].caseLibre(1, 0) )) return false;
		}	
		if(pos%3==0)
			for(int i=3;i>=0;i--)
				tab[i].set(1, 0);
		else
			for(int i=0;i<4; i++)
				tab[i].set(1, 0);
		
		return true;
	}

	@Override
	public boolean gauche() {
		switch(pos){
		case 0: 	if(!( tab[0].caseLibre(-1, 0) && tab[2].caseLibre(-1, 0) && tab[3].caseLibre(-1,0) )) return false;
		break;

		case 1:		if(!( tab[0].caseLibre(-1, 0) && tab[3].caseLibre(-1, 0) )) return false;
		break;

		case 2:		if(!( tab[1].caseLibre(-1, 0) && tab[2].caseLibre(-1, 0) && tab[3].caseLibre(-1,0) )) return false;
		break;

		default: 	if(!( tab[0].caseLibre(-1, 0) && tab[1].caseLibre(-1, 0) )) return false;
		}	
		if(pos%3==0)
			for(int i=0;i<4;i++)
				tab[i].set(-1, 0);
		else
			for(int i=3; i>=0;i--)
				tab[i].set(-1, 0);
		
		return true;
	}

	@Override
	public boolean peut_pivoter() {
		switch(pos){
		case 0: 	return tab[3].caseLibre(-1,0) && tab[3].caseLibre( -2,0);
		case 1:		return tab[3].caseLibre(0,-2) && tab[3].caseLibre(0,-1);
		case 2:		return tab[3].caseLibre(2, 0) && tab[3].caseLibre(1, 0);
		default: 	return tab[3].caseLibre(0, 1) && tab[3].caseLibre(0,2);
		}
	}

	@Override
	public boolean pivote() {
		if(!peut_pivoter())
			return false;
		int coord[][]={{1,1},{0,2},{-1,1},{-2,0},{-1,-1},{0,-2},{1,-1},{2,0}};
		int dec;
		switch(pos){
		case 0:
//			tab[3].set(-2, 0); tab[2].set(-1, 1); tab[1].set(0, 2); tab[0].set(1,1 );pos++;
			dec=0;
//			return true;
			break;
		case 1:
//			tab[3].set(0,-2); tab[2].set(-1, -1); tab[1].set(-2, 0); tab[0].set(-1,1 );pos++;
//			return true;
			dec=2;
			break;
		case 2:		
//			tab[3].set(2,0); tab[2].set( 1, -1); tab[1].set(0, -2); tab[0].set(-1,-1 );pos++;
//			return true;
			dec=4;
			break;
		default:
//			tab[3].set(0,2); tab[2].set( 1, 1); tab[1].set(2, 0); tab[0].set(1,-1 );
//			pos=0;
//			return true;
			dec=6;
			break;
		}	
		for(int i=3;i>=0;i--)
				tab[i].set(coord[(dec+i)%8][0], coord[(i+dec)%8][1]);
		pos++;
		pos%=4;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}