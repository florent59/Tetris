package fr.univLille1.killthemall;

import fr.univLille1.model.*;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

 
public class Sprite {

       private int width;
       private int height;
       private Forme f;
       private static Paint[] couleur;
       
       static{
    	   couleur = new Paint[8];
    	   for(int i=0;i<8;i++)
    		   couleur[i]= new Paint();
    	   couleur[0].setARGB(255, 153, 0, 0);//rouge
    	   couleur[1].setARGB(255,0, 204,102);//+ert
    	   couleur[2].setARGB(255, 0, 0, 155);//blue
    	   couleur[3].setARGB(255, 255	, 153	, 0);//jaune
    	   couleur[4].setARGB(255, 102, 204, 204);//cyan
    	   couleur[5].setARGB(255,71, 0, 71);//violet
    	   couleur[6].setARGB(255, 255, 255, 255);//blanc
    	   couleur[7].setARGB(255,100,100,100);//cadre gris
    	   
    	   }

       public Forme getF(GameControl control,int n){
    	   Forme ff;
    	   switch(n){
             case 0:
            	 ff=new ess (control.getModel());
            	 break;

             case 1:
             	 ff=new Ligne (control.getModel());
            	 break;

             case 2:
            	 ff=new Pet (control.getModel());
            	 break;

             case 3:
            	 ff=new Zed (control.getModel());//vert
            	 break;
             case 4:
            	 ff=new Carre (control.getModel());
            	 break;

             case 5:
            	 ff=new Tee(control.getModel());
            	 break;
             default:
            	 ff=new Ell(control.getModel());
             }
    	   return ff;
       }
       public Sprite(GameControl control, int n){
    	   this.width =  20;
           this.height =  20;
           f= getF(control,n);
       }
        
       public Sprite(GameControl control ) {
             this.width =  20;
             this.height =  20;
             
             java.util.Random r = new java.util.Random();
             int n= r.nextInt(7);
             f= getF(control,n);        
       }
 
       public void update( ) {
    	    if(  f.peut_descendre())  //ne sert a rien ?
     			   f.descend();   
       }
       
       /**il faudra mettre a jour le canvas lorsque j'appuis sur gauche droite ...*/
       public void gauche(){
    	    f.gauche();
       }
       
       public void droite(){
    	   f.droite() ;
       }
       
       public void tombe(){	
    	   f.tombe();
       }
       public void pivote(){
    	   f.pivote();
       }
       public Forme getForme(){
    	   return f;
       }
       
       public void draw(Canvas canvas,int x, int y,int coul) {
    	   Rect cadre = new Rect(x*width, y*height , x * width + width, y * height +height);
    	   Rect dst=new Rect(x*width+1, y*height+1 , x * width+1 + width-2, y * height+1 +height-2);
    	   
           canvas.drawRect(cadre,couleur[7]);
           canvas.drawRect(dst,couleur[coul]);
          
       }
}