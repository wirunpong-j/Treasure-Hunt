package com.treasurehunt.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 10/11/2559.
 */
public class Item extends Actor {
    private TreasureHunt game;

    private Animator item;
    private Animation itemGIF;
    private TextureRegion current;
    private float stateTime;
    private Rectangle rect;

    private float speed;
    private int speedup;

    public Item(TreasureHunt gam, int coll, int row, TextureRegion tr, float sp, boolean flip) {
        super();
        game = gam;
        speed = sp;

        item = new Animator(coll, row, tr, speed, flip);
        itemGIF = item.getWalkAnimation();
        current = item.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = itemGIF.getKeyFrame(stateTime, true);

        rect = new Rectangle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() - speedup*delta);
        rect.setPosition(getX(), getY());
        stateTime += Gdx.graphics.getDeltaTime();
        current = itemGIF.getKeyFrame(stateTime, true);

        if (getX() < -500) {
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

    public void setSpeedup(int speedup) {
        this.speedup = speedup;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        rect.setSize(width, height);

    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        rect.setPosition(x, y);
    }
}
