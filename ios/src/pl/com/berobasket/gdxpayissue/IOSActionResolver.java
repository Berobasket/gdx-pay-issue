package pl.com.berobasket.gdxpayissue;

import org.robovm.apple.uikit.UIAlertView;

public class IOSActionResolver implements ActionResolver {

	public void showToast(String message) {
		UIAlertView uiAlert = new UIAlertView();
	    uiAlert.setTitle("Message");
		uiAlert.setMessage(message);				
		uiAlert.addButton("OK");
		uiAlert.setCancelButtonIndex(0);
		uiAlert.show();												
	}

}
