package fr.univLille1.model;


public class Ligne extends Forme {
	private static final int COULEUR=3;

	/*
	 *  pos 1 	 pos 2   itab 
	 *					 
	 *   0123	  0123  
	 * 0  X 	0  		 O	
	 * 1  X		1  		 1
	 * 2  X		2 XXXX   2
	 * 3  X		3		 3
	 * 
	 * */

	pc[] tab;
	int pos;
	
	public Ligne(GameModel g){
		
		super.game= g;
		pos=1;
		tab=new pc[4];
		
		for(int i=0;i<4;i++){
			tab[i]=new pc(game.getX()/2, i,COULEUR);
			
		}
	}

	@Override
	public boolean descend() {
		if(peut_descendre()){	
			for(int i=3; i>=0; i--)
				tab[i].set(0, 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean peut_descendre() {
		if(pos == 1 ){
			if(tab[3].peut_descendre() )
				return true;
			else return false;
		}else {
			for(int i=3; i>=0; i--)
				if(!tab[i].peut_descendre())
					return false;
			return true;
		}
	}

	@Override
	public boolean droite() {
		if(pos==1){//debout
			for(int i=0;i<4;i++)
				if(!tab[i].caseLibre(1,0))
					return false;
		}else{//couche
			if(!tab[0].caseLibre(1, 0))
				return false;
		}
		for(int i=0;i<4	;i++){
			tab[i].set(1,0);
		}
		return true;
	}

	@Override
	public boolean gauche() {
 		if(pos==1){//debout
 			for(int i=0;i<4;i++)
 				if(!tab[i].caseLibre(-1, 0))
 					return false;
 		}else{//couche
 			if(!tab[3].caseLibre(-1, 0))
				return false;
 		}
 		for(int i=3;i>=0;i--){
			tab[i].set(-1,0);
		}
		return true;
	}

	@Override
	public boolean peut_pivoter() {
		int ajout=1;
		int deb=-1;
		
		if(pos==0){
			ajout=-1;
			deb=1;
		}
		
		for(int x=deb ,i=3 ;i>=0 ;x+=ajout ,i--){
				if(!tab[i].caseLibre(x, x))	
					return false;
		}return true;
	}

	@Override
	public boolean pivote() {
		if(!peut_pivoter())
			return false;

		int ajout=1;
		int deb=-1;
		
		if(pos==0){
			ajout=-1;
			deb=1;
		}
		
		for(int x=deb ,i=3 ;i>=0 ;x+=ajout ,i--){
			tab[i].set(x, x);
		}
		pos+=deb;
		return true;
	}
}

