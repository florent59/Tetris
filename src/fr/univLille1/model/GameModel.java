package fr.univLille1.model;

public class GameModel {
	private int g[][] ;
	private int tX,tY;
	
	/**
	 * |         | 0
	 * |         | 1
	 * |     X   | 2
	 * |    XX   | 3
	 * |XXXXXXXXX| 4
	 * |_________| 5
	 * 0123456789…………
	 * */

	public GameModel(int x,int y){
		tX=x; tY=y;
		g = new int[x][y];
		for(int i=0;i<tX;i++)
			for(int j=0;j<tY;j++)
				g[i][j]=-1;
	}
	/**
	 * permet de savoir si la case demander est libre
	 * @param x la coordonné sur l'axe des abscisse
	 * @param y la coordonné sur l'axe des ordonné
	 * @return true si la case est libre <br/> false sinon
	 */
	public boolean caseLibre(int x, int y){
		if(x >=0 && x < tX && y  < tY){
			return   g[x][y]==-1;
		}
		return false;
	}
	public int getVal(int x,int y){
		return g[x][y];
	}
	public void setFalse(int x, int y){
		g[x][y]=-1;
	}
	
	public void setTrue(int x, int y,int couleur){		
		g[x][y]=couleur;
	}
	
	public int getX(){
		return tX;
	}
	
	public int getY(){
		return tY;
	}
	
	public boolean lignePleine(int y){
		for(int i=0; i<tX; i++){
			if( g[i][y]==-1)
				return false;
		}
		return true;
	}

	public void descentLignes(){
		int tmp[][] = new int[tX][tY];
		int  yy=tY-1;
		//pour chaque ligne de zero a tY
		for(int y =tY-1; y>=0; y--){

			//si la ligne n'est pas pleine la recopier dans tmp a la ligne yy
			if(! lignePleine(y)){
				for(int x=0;x<tX; x++){
					tmp[x][yy]=  g[x][y];
				}
				yy--;
			}
		}
		for(int j=0;j<tX;j++)
			for(int i=yy;i>=0;i--)
				tmp[j][i]=-1;
		g=tmp;
	}
	
	public String toString(){
		String res="";
		for(int i= 0; i< tY;i++){
			res+="|";
			for(int j=0; j<tX; j++)
			{
				if(g[j][i]>0)
					res+=""+g[j][i];
				else
					res+=' ';
			}
			res+="|\n";
		}
		return res;	
	}
	

}
