package pl.com.berobasket.gdxpayissue;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * An utility {@link ActorListener} class.
 * <p>
 * Defines the {@link #touchDown(ActorEvent, float, float, int, int)} method
 * returning <code>true</code> by default, so the
 * {@link #touchDown(ActorEvent, float, float, int, int)} method gets invoked.
 */
public abstract class DefaultButtonListener
    extends
        ClickListener
{
    @Override
    public boolean touchDown(
        InputEvent event,
        float x,
        float y,
        int pointer,
        int button )
    {
        return true;
    }
}
