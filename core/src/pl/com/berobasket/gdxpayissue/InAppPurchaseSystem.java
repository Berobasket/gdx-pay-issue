package pl.com.berobasket.gdxpayissue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.PurchaseSystem;

public class InAppPurchaseSystem 
{
    public static final String SKU_CONSUMABLE_1 = "pl.com.berobasket.gdxpayissue.consumable1";
    public static final String SKU_CONSUMABLE_2 = "pl.com.berobasket.gdxpayissue.consumable2";
    
	public InAppPurchaseSystem() 
	{   
	}
	
	public void initializeIAPForIOS(PurchaseObserver purchaseObserver)
	{
	    // Disposes static instances in case JVM is re-used on restarts
	    PurchaseSystem.onAppRestarted();
	
		PurchaseManagerConfig purchaseManagerConfig = getConfigForIOS();
		
		installIAP( purchaseObserver, purchaseManagerConfig);
	}

	public void initializeIAPForAndroid(PurchaseObserver purchaseObserver)
	{
	    // Disposes static instances in case JVM is re-used on restarts
	    PurchaseSystem.onAppRestarted();
	
		PurchaseManagerConfig purchaseManagerConfig = getConfigForAndroid();
		
		installIAP( purchaseObserver, purchaseManagerConfig);
	}
	
	private PurchaseManagerConfig getConfigForAndroid()
	{
	    PurchaseManagerConfig purchaseManagerConfig = new PurchaseManagerConfig();
	    purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(SKU_CONSUMABLE_1));
	    purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(SKU_CONSUMABLE_2));
	    
		purchaseManagerConfig.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, "YourGoogleKeyHere");
	   	
	   	return purchaseManagerConfig;
	}
	
	private PurchaseManagerConfig getConfigForIOS()
	{
	    PurchaseManagerConfig purchaseManagerConfig = new PurchaseManagerConfig();
	    purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(SKU_CONSUMABLE_1));
	    purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(SKU_CONSUMABLE_2));
	    
		purchaseManagerConfig.addStoreParam(PurchaseManagerConfig.STORE_NAME_IOS_APPLE, "NoKeyNeeded");
	   	
	   	return purchaseManagerConfig;
	}
	
	private void installIAP(PurchaseObserver purchaseObserver, PurchaseManagerConfig purchaseManagerConfig)
	{
		Gdx.app.log("", "gdx-pay: initializeIAP(): purchaseManager == null => call PurchaseSystem.hasManager()");
		
	    if (PurchaseSystem.hasManager()) 
	    {
	        try
	        {        	
		    	// install and get the manager automatically via reflection
		        PurchaseManager mgr = PurchaseSystem.getManager();
		        PurchaseSystem.install(purchaseObserver, purchaseManagerConfig, false); // install the observer
		        
                String mgrString = "";
                if( mgr != null )
                	mgrString = mgr.toString();
        		
        		Gdx.app.log(MyGdxGame.LOG, "gdx-pay: installed manager: " + mgrString);
	        }
	        catch(Exception e)
	        {
	        	Gdx.app.error(MyGdxGame.LOG, "There was an exception while installing purchase system " + e.getMessage());
	        };
	    }
	}
	
	public void requestPurchase (String productString) 
	{
	    if (PurchaseSystem.hasManager()) 
	    {
		    if (PurchaseSystem.getManager() != null) 
		    {
		        PurchaseSystem.purchase(productString);
		        Gdx.app.log("gdx-pay", "PurchaseSystem.purchase");
		    } 
		    else 
		    {
		        Gdx.app.log("ERROR", "gdx-pay: requestPurchase(): purchaseManager == null");
		    }
	    }
	}
	
	public void requestPurchaseRestore () 
	{
	    if (PurchaseSystem.hasManager()) 
	    {
		    if (PurchaseSystem.getManager() != null) 
		    {
		        PurchaseSystem.purchaseRestore();	
		    } 
		    else 
		    {
		        Gdx.app.log("ERROR", "gdx-pay: requestPurchaseRestore(): purchaseManager == null");
		    }
	    }
	}
}