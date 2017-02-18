package com.treasurehunt.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 27/10/2559.
 */
public class Lighting extends Actor {
    private TreasureHunt game;

    private Animator light;
    private Animation lightGIF;
    private TextureRegion current;
    private float stateTime;
    private ShapeRenderer shape;
    private boolean alive;
    private boolean isNow;

    public Lighting(TreasureHunt gam, int coll, int row, TextureRegion tr, float sp, boolean isShift, boolean n) {
        super();
        game = gam;
        isNow = n;

        light = new Animator(coll, row, tr, sp, isShift);
        lightGIF = light.getWalkAnimation();
        current = light.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = lightGIF.getKeyFrame(stateTime, true);
        alive = true;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        batch.begin();
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (alive) {
            stateTime += Gdx.graphics.getDeltaTime();
            current = lightGIF.getKeyFrame(stateTime, true);
        }

        if (lightGIF.isAnimationFinished(stateTime) && isNow) {
            alive = false;
        }

        if (alive == false) {
            this.remove();
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

}
