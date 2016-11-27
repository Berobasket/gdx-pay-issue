package pl.com.berobasket.gdxpayissue;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

public class IOSLauncher extends IOSApplication.Delegate {
	
	private MyGdxGame _game;
	
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        _game = new MyGdxGame(new IOSActionResolver());
        return new IOSApplication(_game, config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
    
	@Override
	public boolean didFinishLaunching (UIApplication application, UIApplicationLaunchOptions launchOptions) 
	{
		boolean returnState = super.didFinishLaunching(application, launchOptions);

		_game.initializeInAppBillingForIOS();
				
		return returnState;
	}    
}