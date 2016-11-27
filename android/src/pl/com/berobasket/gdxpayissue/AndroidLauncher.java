package pl.com.berobasket.gdxpayissue;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.os.Bundle;
import android.widget.Toast;

public class AndroidLauncher extends AndroidApplication implements ActionResolver {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		MyGdxGame game = new MyGdxGame(this); 
		initialize(game, config);
		
		game.initializeInAppBillingForAndroid();
	}

	@Override
	public void showToast(final String message) {
		this.runOnUiThread(new Runnable() 
		{
			  public void run() 
			  {
			    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			  }
		});		
	}
}
