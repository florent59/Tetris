package fr.univLille1.model;

public class Pet extends Forme{
	private static final int COULEUR=4;

	private pc[] tab;
	private int pos;
	
	public Pet(GameModel game){
		super.game= game;
		tab=new pc[4];
		tab[0]=new pc(game.getX()/2+1,0,COULEUR);
		tab[1]=new pc(game.getX()/2,0,COULEUR);
		tab[2]=new pc(game.getX()/2,1,COULEUR);
		tab[3]=new pc(game.getX()/2,2,COULEUR);
		pos=0;
	}
	@Override
	public boolean descend() {
		if(!peut_descendre())
			return false;
		if(pos%3==0){
			for(int i=3; i>=0;i--)
				tab[i].set(0, 1);
		}else{
			for(int i=0;i<4;i++	)
				tab[i].set(0, 1);
		}
		return true;
	}

	@Override
	public boolean peut_descendre() {
		switch(pos){
		case 0:
			return tab[3].caseLibre(0, 1) && tab[0].caseLibre(0, 1);
		case 1:
			return tab[3].caseLibre(0,1) && tab[2].caseLibre(0,1) && tab[0].caseLibre(0,1);
		case 2:
			return tab[1].caseLibre(0, 1) && tab[0].caseLibre(0, 1);
		default:
			return tab[1].caseLibre(0,1) && tab[2].caseLibre(0,1) && tab[3].caseLibre(0,1);
		}
	}

	@Override
	public boolean droite() {
		switch( pos){
		case 0:	
			if(! (tab[0].caseLibre(1,0) && tab[2].caseLibre(1,0) && tab[3].caseLibre(1,0) )) return false;
			for(int i=0; i<4;i++)
				tab[i].set(1, 0);
			break;

		case 1:
			if(! (tab[0].caseLibre(1,0) && tab[1].caseLibre(1,0) )) return false;
			for(int i=0; i<4;i++)
				tab[i].set(1, 0);
			break;

		case 2:
			if(! (tab[1].caseLibre(1,0) && tab[2].caseLibre(1,0) && tab[3].caseLibre(1,0)) ) return false;
			for(int i=3; i>=0;i--)
				tab[i].set(1, 0);
			break;

		default:
			if(!( tab[0].caseLibre(1,0) && tab[3].caseLibre(1,0)) ) return false;
			for(int i=3; i>=0;i--)
				tab[i].set(1, 0);
		}
		return true;
	}

	@Override
	public boolean gauche() {
		switch( pos){
		case 0:	
			if(! (tab[1].caseLibre(-1,0) && tab[2].caseLibre(-1,0) && tab[3].caseLibre(-1,0)) ) return false;
			for(int i=3; i>=0;i--)
				tab[i].set(-1, 0);
			break;

		case 1: 
			if(! (tab[0].caseLibre(-1,0) && tab[3].caseLibre(-1,0 )) ) return false;
			for(int i=3; i>=0;i--)
				tab[i].set(-1, 0);
			break;

		case 2:
			if(! (tab[3].caseLibre(-1,0) && tab[2].caseLibre(-1,0) && tab[0].caseLibre(-1,0 )) ) return false;
			for(int i=0; i<4;i++)
				tab[i].set(-1, 0);
			break;

		default:
			if(! (tab[0].caseLibre(-1,0) && tab[1].caseLibre(-1,0) )) return false;
			for(int i=0; i<4;i++)
				tab[i].set(-1, 0);
		}
		return true;
	}

	@Override
	public boolean peut_pivoter() {
		switch(pos){
		case 0 :			return tab[0].caseLibre(1, 0) && tab[0].caseLibre(1, 1);
		case 1 :			return tab[0].caseLibre(0,1) && tab[0].caseLibre(-1,1);
		case 2 :			return tab[0].caseLibre(-1,0) && tab[0].caseLibre(-2,0);
		default:			return tab[0].caseLibre(0,-1) && tab[0].caseLibre(1,-1);
		}
	}

	@Override
	public boolean pivote() {
		if(!peut_pivoter())
			return false;
		int [][]coord={{1,1},{2,0},{1,-1},{-0,-2},{-1,-1},{-2,0},{-1,1},{0,2}};
		
		switch(pos){
		case 0 :	
			for(int i=0;i<4;i++)
				tab[i].set(coord[i][0], coord[i][1]);
			pos++;
			break;
			
		case 1 :			
			for(int i=0;i<4;i++)
				tab[i].set(coord[(i+6)%8][0], coord[(i+6)%8][1]);
			pos++;
			break;
		case 2 :			
			for(int i=0;i<4;i++)
				tab[i].set(coord[i+4][0], coord[i+4][1]);
			pos++;
			break;
			
		default:		
			for(int i=0;i<4;i++)
				tab[i].set(coord[2+i][0], coord[i+2][1]);
			pos=0;
			break;
			
		}
		
		return false;
	}

}
