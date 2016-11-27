package pl.com.berobasket.gdxpayissue.desktop;

import pl.com.berobasket.gdxpayissue.ActionResolver;

public class DesktopActionResolver implements ActionResolver {

	public void showToast(String message) {
		System.out.println(message);
	}

}
