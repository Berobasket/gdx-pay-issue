package pl.com.berobasket.gdxpayissue;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGdxGame extends Game {
	
	public static String LOG = "MyGdxGame"; 
	
	private ActionResolver _actionResolver;
	private InAppPurchaseSystem _inAppPurchaseSystem;
	
	private Skin _skin;
	
	public MyGdxGame(ActionResolver actionResolver){
		_actionResolver = actionResolver;
	}
	
	@Override
	public void create () {
		setScreen(new MainScreen(this));
	}

	public InAppPurchaseSystem getInAppPurchaseSystem()
	{
		return _inAppPurchaseSystem;
	}
	
	public ActionResolver getActionResolver()
	{
		return _actionResolver;
	}
	
	public void initializeInAppBillingForAndroid() {
		_inAppPurchaseSystem = new InAppPurchaseSystem();
		
		PurchaseObserver purchaseObserver = createPurchaseObserver(true);
		
    	_inAppPurchaseSystem.initializeIAPForAndroid(purchaseObserver);		
	}
	
	public void initializeInAppBillingForIOS() {
		_inAppPurchaseSystem = new InAppPurchaseSystem();
		
		PurchaseObserver purchaseObserver = createPurchaseObserver(false);
		
    	_inAppPurchaseSystem.initializeIAPForIOS(purchaseObserver);		
	}
	
	private PurchaseObserver createPurchaseObserver(final boolean restorePurchaseAfterInstall)
	{
		PurchaseObserver purchaseObserver = new PurchaseObserver() 
		{
		    @Override
		    public void handleRestore (Transaction[] transactions) 
		    {
	    		Gdx.app.log(LOG, "Restoring transactions - length: " + transactions.length);
		    	
		        for (int i = 0; i < transactions.length; i++) 
		        	checkTransaction(transactions[i].getIdentifier());
		    }

		    @Override
		    public void handleRestoreError (Throwable e) 
		    {
		    	String message = "PurchaseObserver: handleRestoreError!: " + e.getMessage();
				Gdx.app.error(LOG, message);
				_actionResolver.showToast(message);			
		    }

		    @Override
		    public void handleInstall () 
		    {
				Gdx.app.log(LOG, "handleInstall successfully..");
				
				if( restorePurchaseAfterInstall )
					_inAppPurchaseSystem.requestPurchaseRestore();
		    }

		    @Override
		    public void handleInstallError (Throwable e) 
		    {
		    	String message = "PurchaseObserver: handleInstallError!: " + e.getMessage();
				Gdx.app.error(LOG, message);
				_actionResolver.showToast(message);			
		    }

		    @Override
		    public void handlePurchase (Transaction transaction) 
		    {
	    		Gdx.app.log(LOG, "Purchasing: " + transaction.toString());
		    	
		        checkTransaction(transaction.getIdentifier());
		    }

		    @Override
		    public void handlePurchaseError (Throwable e) 
		    {
		    	String message = "Handle purchase error: " + e.getMessage();	    	
				Gdx.app.error(LOG, message);
				_actionResolver.showToast(message);			
		    }

		    @Override
		    public void handlePurchaseCanceled () 
		    {
		    	String message = "Purchase canceled by the user";	    	
				Gdx.app.error(LOG, message);
				_actionResolver.showToast(message);			
		    }
		};			

		return purchaseObserver;
	}	
	
	protected boolean checkTransaction (String ID) 
	{
		String message = "Item " + ID + " was bought";
		Gdx.app.log(LOG, message);
		_actionResolver.showToast(message);
		return true;
	}		
	
    protected Skin getSkin()
    {
        if( _skin == null ) 
            _skin = new Skin( Gdx.files.internal( "uiskin.json" ) );
        
        return _skin;
    }	
	
	@Override
	public void dispose () {
		if( _skin != null )
			_skin.dispose();
	}
}
