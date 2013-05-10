package fr.univLille1.killthemall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
 

public class GameView extends SurfaceView {

       private SurfaceHolder holder;
       private GameControl gameControl;
       
    @SuppressLint("WrongCall")
	public GameView(Context context) {
             super(context);
             init();
             //      setMeasuredDimension(300,300);
    }
    public GameView(Context c, AttributeSet as, int defStyle){
    	super(c,as,defStyle);
    	init();
    }
    public GameView(Context c, AttributeSet as){
    	super(c,as);
    	init();
    }
    /**J'ai mis les trois constructeur sinon activity_main.xml pas content*/
    
    public void init(){
    	// creation du thread qui redessine l'ecran 
    	 gameControl = new GameControl(this);
    	 holder = getHolder();
    	 holder.setSizeFromLayout();
    	 holder.addCallback(new SurfaceHolder.Callback() {
            	 @Override
                 public void surfaceDestroyed(SurfaceHolder holder) {
                           boolean retry = true;
                           gameControl.setRunning(false);
                           
                           while (retry) {
                                  try {
                                        gameControl.join();
                                        retry = false;
                                  } catch (InterruptedException e) {}
                          }
                    }
 
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                           gameControl.setRunning(true);
                           gameControl.start();
                    }
 
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format,
                                  int width, int height) {
                    }
             });
       }

 
    
    public GameControl getControl(){
    	return gameControl;
    }
    @SuppressLint("WrongCall")//pourquoi ??
	@Override   
	protected void onDraw(Canvas canvas) {
        //  gameControl.majCanvas(canvas);
    }
}