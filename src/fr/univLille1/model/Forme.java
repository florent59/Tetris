package fr.univLille1.model;

public abstract class Forme {
	protected GameModel game;
	
	/*Classe interne a la classe abstraite Forme2 sert a representer une partie d'une forme
	 * les formes sont composse de petit carre : pc avec des chacuns leurs propres coordonné
	 * */
	class pc{
		int x;
		int y;
		int c;
		pc(int x, int y, int c){
			this.x=x;
			this.y=y;
			this.c=c;
				
			game.setTrue(x, y,c);
		}
		
		void set(int dx, int dy){
			if(x+dx>=0 && x+dx<11 && y+dy <20){
				game.setFalse(this.x, this.y);
				
				this.x+=dx;
				this.y+=dy;
				
				game.setTrue(x, y,c);
			}
		}
		boolean peut_descendre(){
			//if(x+dx>=0 && x+dx<11 && y+dy <20){ la methode de caseLibre de GameModel le fait déjàe
				return game.caseLibre(x, y+1);
			
		}
		boolean caseLibre(int dx,int dy){
			//System.out.println(" "+(x+dx)+" "+(y+dy));
			if( dx == 0 &&  dy== 0)
				return true;
			return game.caseLibre(x+dx, y+dy);
		}
	}
	
	public abstract boolean descend();
	public abstract boolean peut_descendre();
	
	public abstract boolean droite();
	public abstract boolean gauche();
	public abstract boolean peut_pivoter();

	public boolean tombe(){
		while(descend());
		return true;
	}
	public abstract boolean pivote();
	
}
