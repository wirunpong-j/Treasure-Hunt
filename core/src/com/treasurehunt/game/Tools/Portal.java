package com.treasurehunt.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 13/11/2559.
 */
public class Portal extends Actor {
    private TreasureHunt game;
    private Rectangle rect;

    private Animator portal;
    private Animation portalGIF;
    private TextureRegion current;
    private float stateTime;
    private ShapeRenderer shape;

    public Portal(TreasureHunt gam) {
        super();
        game = gam;

        portal = new Animator(7, 1, game.effectAtlas.findRegion("Door"), 0.07f, false);
        portalGIF = portal.getWalkAnimation();
        current = portal.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = portalGIF.getKeyFrame(stateTime, true);

        rect = new Rectangle();
        rect.setPosition(getX(), getY());
        rect.setSize(getWidth(), getHeight());

        shape = new ShapeRenderer();
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
        current = portalGIF.getKeyFrame(stateTime, true);

        setX(getX() - 500*delta);
        rect.setPosition(getX(), getY());
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
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
}
