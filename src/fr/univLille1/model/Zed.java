package fr.univLille1.model;

/*  pos0:	pos1:
 *   012      012
 * 0 		0  X
 * 1 XX		1 XX
 * 2  XX	2 X
 * 
 * */
public class Zed extends Forme {
	private static final int COULEUR=6;

	private int pos;
	private pc[] tab;
	
	public Zed(GameModel game){
		super.game=game;
		tab=new pc[4];
		tab[0]= new pc(game.getX()/2+1,0,COULEUR);
		tab[1]= new pc(game.getX()/2+1,1,COULEUR);
		tab[2]= new pc(game.getX()/2,1,COULEUR);
		tab[3]= new pc(game.getX()/2,2,COULEUR);
		pos=1;
	}
	@Override
	public boolean descend() {
		if(!peut_descendre())
			return false;
		for(int i=3;i>=0;i--){
			tab[i].set(0, 1);
		}
		return true;
	}

	@Override
	public boolean peut_descendre() {
		if(pos==1)
			return(tab[1].caseLibre(0, 1) && tab[3].caseLibre(0, 1));
		else
			return( tab[0].caseLibre(0, 1) && tab[2].caseLibre(0, 1) && tab[3].caseLibre(0, 1));
	}

	@Override
	public boolean droite() {
		if(pos==1){
			if(!(tab[0].caseLibre(1, 0) && tab[1].caseLibre(1,0 ) && tab[3].caseLibre(1,0)))
				return false;
			for(int i=0;i<4;i++)
				tab[i].set(1, 0);
		}else {
			if(!(tab[1].caseLibre(1, 0) && tab[3].caseLibre(1,0 )))
				return false;
			for(int i=3;i>=0;i--)
				tab[i].set(-1, 0);
		}
		return true;
	
	}

	@Override
	public boolean gauche() {
		if(pos==1){
			if(!(tab[0].caseLibre(-1, 0) && tab[2].caseLibre(-1,0 ) && tab[3].caseLibre(-1,0)))
				return false;
			for(int i=3;i>=0;i--)
				tab[i].set(-1, 0);
		}else {
			if(!(tab[0].caseLibre(-1, 0) && tab[2].caseLibre(-1,0 )))
				return false;
			for(int i=0;i<4;i++)
				tab[i].set(-1, 0);
		}
		return true;
	}

	@Override
	public boolean peut_pivoter() {
		if(pos == 1 )
			return tab[3].caseLibre(1, 0) && tab[3].caseLibre(2, 0);
		else
			return tab[0].caseLibre(1, -1) && tab[0].caseLibre(0,1);
	}


	@Override
	public boolean pivote() {
		if(!peut_pivoter())
			return false;
		
		int sens=1;
		int[][]coordonne={{-1,1},{0,0},{ 1, 1},{ 2,0}};
		if(pos==0)
			sens=-1;
		if(pos==1)
		{
			for(int i=3;i>=0;i--){
				tab[i].set(sens * coordonne[i][0],sens * coordonne[i][1]);
			}
			pos=0;
			return true;

		}
		for(int i=0;i<4;i++	){
			tab[i].set(sens * coordonne[i][0],sens * coordonne[i][1]);
		}
		pos=1;
		return true;
	}
	
}