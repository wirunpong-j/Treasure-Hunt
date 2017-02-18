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
 * Created by Bell KunG on 26/10/2559.
 */
public class Potion extends Actor {
    private Animator potion;
    private Animation potionGIF;
    private TextureRegion currentPotion;
    private float stateTime_Potion;

    private Rectangle rect;

    public Potion() {
        super();
        potion = new Animator(8, 1, "Items/potion.png", 0.07f, false);
        potionGIF = potion.getWalkAnimation();
        currentPotion = potion.getCurrentFrame();
        stateTime_Potion = 0f;

        rect = new Rectangle();

        stateTime_Potion += Gdx.graphics.getDeltaTime();
        currentPotion = potionGIF.getKeyFrame(stateTime_Potion, true);

        setBounds(getX(), getY(), rect.getWidth(), rect.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(currentPotion, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime_Potion += Gdx.graphics.getDeltaTime();
        currentPotion = potionGIF.getKeyFrame(stateTime_Potion, true);
        setX(getX() - 500*delta);
        rect.setPosition(getX(), getY());

        if (getX() < -1000) {
            this.remove();
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        rect.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        rect.setSize(width, height);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
    }

}