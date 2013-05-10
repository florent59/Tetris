package fr.univLille1.killthemall;

import com.edu4java.android.killlthemall.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Main extends Activity {

	private GameControl gameControl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		gameControl= ((GameView) findViewById(R.id.vue)).getControl();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//methode relative au bouton :
	public void tombe(View v){
		gameControl.tombe();
	}
	public void gauche(View v){
		gameControl.vaAGauche();
	} 
	public void pivote(View v){
		gameControl.pivote();
	}
	public void droite(View v){
		gameControl.vaADroite();
	} 

}
