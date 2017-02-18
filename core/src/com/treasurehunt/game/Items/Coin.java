package com.treasurehunt.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.Animator;

/**
 * Created by Bell KunG on 2/11/2559.
 */
public class Coin extends Actor{
    private Animator coin;
    private Animation coinGIF;
    private TextureRegion current;
    private float stateTime;
    private Rectangle rect;

    public Coin() {
        super();

        coin = new Animator(16, 1, "Items/Coin.png", 0.07f, false);
        coinGIF = coin.getWalkAnimation();
        current = coin.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = coinGIF.getKeyFrame(stateTime, true);

        setBounds(getX(), getY(), getWidth(), getHeight());

        rect = new Rectangle();
        rect.setSize(50, 50);
        rect.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += Gdx.graphics.getDeltaTime();
        current = coinGIF.getKeyFrame(stateTime, true);
        setX(getX() - 500*delta);
        rect.setPosition(getX(), getY());

        if (getX() < -1000) {
            this.remove();
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
    }
}
