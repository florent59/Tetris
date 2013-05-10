package fr.univLille1.killthemall;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;

import fr.univLille1.model.GameModel;

public class GameControl extends Thread{
	
	GameView vue;
	GameModel model;
	static final long FPS = 10;
    private boolean running = false;
    Sprite sprite;
      
 	 
	public GameControl(GameView vue){
		this.vue=vue;
		model=new GameModel(10,20);
		sprite = new Sprite(this );
	}
	
	public GameModel getModel(){
		return model;
	}
	
	public void vaADroite() {	
		sprite.droite();
		Canvas c=vue.getHolder().lockCanvas();
		draw(c);
		if (c != null)
			vue.getHolder().unlockCanvasAndPost(c);	
	}
	
	public void pivote(){
		sprite.pivote();
	//	Canvas c=vue.getHolder().lockCanvas();
	//	if(c != null)
		//	vue.getHolder().unlockCanvasAndPost(c);
	}
	
	public void vaAGauche(){
		sprite.gauche();
		Canvas c=vue.getHolder().lockCanvas();
		draw(c);
		if (c != null) 
			vue.getHolder().unlockCanvasAndPost(c);
	}
	
	public void tombe(){
		sprite.tombe();
	}
	
	//dessine un a un les carre sur le canvas 
	//dessine un un a un les carre sur le canvas 
	//va falloir retirer la methode draw de sprite car l'objet sprite ne sert a rien 
	//ne represente rien dailleurs
	public void draw(Canvas canvas){
 		canvas.drawColor(Color.BLACK); // peint en noir l'écran 

		for(int i=0; i<10 ; i++){
			for(int j=0; j<20 ;j++){
				if(! model.caseLibre(i, j) ){
		           sprite.draw(canvas,i,j,model.getVal(i, j));
				}
			}
		}
	}
	
	public void majCanvas(Canvas canvas){
		//creation d'une nouvelle forme
		if(! sprite.getForme().peut_descendre()){
			model.descentLignes();
			sprite = new Sprite(this);
		}
		draw(canvas);
		sprite.update(); 
	}  

	
	//methode thead loop game
	public void setRunning(boolean run) {
	   running = run;
	}	
	
	@SuppressLint("WrongCall")
	@Override
	public void run() {
     long ticksPS = 1000 / FPS;
            long startTime;
            long sleepTime;

            while (running) {
            	Canvas c = null;
            	startTime = System.currentTimeMillis();
            	try {
            		c = vue.getHolder().lockCanvas();
            		synchronized (vue.getHolder()) {
            			//vue.onDraw(c);
            			majCanvas(c);
            		}
            	}finally {
            		if (c != null) {
            			vue.getHolder().unlockCanvasAndPost(c);
            		}
               	}
            	sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            	try{
            		if (sleepTime > 0)
            		//	sleep(sleepTime);
            			sleep(500);
            		else
            			sleep(10);
                    }catch (Exception e) {}             
            }
       }	       
			
}