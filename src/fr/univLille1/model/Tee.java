package fr.univLille1.model;

public class Tee extends Forme{
	pc[] tab;
	int pos;
	private static final int COULEUR=5;

	public Tee(GameModel game){
		super.game=game;
		pos=0;
		tab=new pc[4];
		
		tab[0]=new pc(game.getX()/2-1,0,COULEUR);
		tab[1]=new pc(game.getX()/2,0,COULEUR);
		tab[2]=new pc(game.getX()/2+1,0,COULEUR);
		tab[3]=new pc(game.getX()/2,1,COULEUR);
	}

	@Override
	public boolean descend() {
		if(!peut_descendre())
			return false;
		//a voir si deb max ajout en variable tmp
		if(pos==0|| pos==1)
			for(int i=3;i>=0;i--	)
				tab[i].set(0, 1);
		else
			for(int i=0;i<4;i++)
				tab[i].set(0, 1);
	
		return true;
	}

	@Override
	public boolean peut_descendre() {
		switch(pos){
		case 0: return (tab[0].caseLibre(0, 1) && tab[3].caseLibre(0, 1) && tab[2	].caseLibre(0, 1));
		case 1: return tab[2].caseLibre(0, 1) && tab[3].caseLibre(0, 1);
		case 2: return tab[0].caseLibre(0, 1) && tab[1].caseLibre(0, 1) && tab[2].caseLibre(0, 1);
		default: return tab[0].caseLibre(0, 1) && tab[3].caseLibre(0, 1);
		}
	}

	@Override
	public boolean droite() {
		switch(pos){
		case 0: if(!( tab[2].caseLibre(1, 0 ) && tab[3].caseLibre(1, 0) )) return false;
		break;
	
		case 1: if(!( tab[0].caseLibre(1, 0) && tab[1].caseLibre(1, 0) && tab[2].caseLibre(1, 0) )) return false;
		break;
		
		case 2: if(!( tab[0].caseLibre(1, 0) && tab[3].caseLibre(1, 0) )) return false;
		break;

		default: if(!( tab[0].caseLibre(1, 0) && tab[2].caseLibre(1, 0) && tab[3].caseLibre(1, 0) )) return false;
		}
		System.out.println(pos+"aaieaeia");
		if(pos==0|| pos==3){
			for(int i=3;i>=0;i--)
				tab[i].set(1, 0);
		}else{
			for(int i=0;i<4;i++)
				tab[i].set(1, 0);
		}
		return true;
		}

	@Override
	public boolean gauche() {
		switch(pos){
		case 0: if(! ( tab[0].caseLibre(-1, 0 ) && tab[3].caseLibre(-1, 0) )) return false;
		break;

		case 1: if(!( tab[0].caseLibre(-1, 0) && tab[2].caseLibre(-1, 0) && tab[3].caseLibre(-1, 0) ))	return false;
		break;

		case 2: if(!( tab[2].caseLibre(-1, 0) && tab[3].caseLibre(-1, 0) )) 	return false;
		break;

		default: if(!( tab[0].caseLibre(-1, 0) && tab[1].caseLibre(-1, 0) && tab[2].caseLibre(-1, 0) ))
			return false;
		}
		if(pos==1|| pos==2)
			for(int i=3;i>=0;i--)
				tab[i].set(-1, 0);
		else
			for(int i=0;i<4;i++)
				tab[i].set(-1, 0);
	
		return true;
	}

	@Override
	public boolean peut_pivoter() {
		switch(pos){
		case 0: return tab[0].caseLibre(1, -1);
		case 1: return tab[0].caseLibre(1, 1);
		case 2: return tab[0].caseLibre(-1, 1);
		default: return tab[0].caseLibre(-1, -1);
		}
	}

	@Override
	public boolean pivote() {
		if(!peut_pivoter())
			return false;
		int coord[][][]={
				{ {1,-1},{0,0},{-1,-1},{-1, 1} },
				{ {1,1},{0,0},{1,-1},{-1,-1}  },
				{ {-1,1},{0,0},{1,1},{1,-1}  },
				{ {-1,-1},{0,0},{-1,1},{1,1} }
				
		};
		tab[0].set( coord[pos][0][0]  , coord[pos][0][1] );
		tab[1].set( coord[pos][1][0]  , coord[pos][1][1] );
		tab[3].set( coord[pos][2][0]  , coord[pos][2][1] );
		tab[2].set( coord[pos][3][0]  , coord[pos][3][1] );

		pos++;
		pos%=4;
		return true;
	}
}
