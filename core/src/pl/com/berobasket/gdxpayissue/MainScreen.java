package pl.com.berobasket.gdxpayissue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainScreen implements Screen {

	private MyGdxGame _game;
	private Label _lInfo1;
	private Stage _stage;
	
	private TextButton _tbBuyItem1;	
	
	public MainScreen(MyGdxGame game) {
		_game = game;
		
        _stage = new Stage(new StretchViewport(800, 600));
        
        Gdx.input.setInputProcessor( _stage );
        
        String message = "Purchasing on Android - every purchase is ok\r\n"
        		+ "Purchasing on iOS - every other purchase is NOT ok\r\n" 
        		+ "1. Buy consumable item - it's ok \r\n"
        		+ "2. Buy consumable item - it's NOT ok \r\n"
        		+ "3. Buy consumable item - it's ok \r\n"
        		+ "4. Buy consumable item - it's NOT ok \r\n";
		
		_lInfo1 = new Label(message, _game.getSkin());
		_lInfo1.setPosition(300, 400);
		
		_tbBuyItem1 = new TextButton("Buy item", _game.getSkin());
		_tbBuyItem1.setBounds(300, 100, 100, 50);		
		_tbBuyItem1.addListener( new DefaultButtonListener() 
        {
            @Override
            public void touchUp( InputEvent event, float x, float y, int pointer, int button )
            {
            	super.touchUp(event, x, y, pointer, button);
            	_game.getActionResolver().showToast("Buying item");
                _game.getInAppPurchaseSystem().requestPurchase(InAppPurchaseSystem.SKU_CONSUMABLE_1);
            }
        } );		
				
		_stage.addActor(_lInfo1);
		_stage.addActor(_tbBuyItem1);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        _stage.act( delta );

        // (2) draw the result

        // clear the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // draw the actors
        _stage.draw();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
