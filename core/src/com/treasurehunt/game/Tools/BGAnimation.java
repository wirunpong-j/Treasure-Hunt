package com.treasurehunt.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 9/11/2559.
 */
public class BGAnimation extends Actor {
    private TreasureHunt game;

    private Animator bg;
    private Animation bgGIF;
    private TextureRegion current;
    private float stateTime;
    private TextureAtlas currentAt;

    private float speed;
    private int speedShift;
    private boolean isFreeze;
    private boolean alive;

    public BGAnimation(TreasureHunt gam, TextureAtlas ca, String directory, int coll, int roll, float sp) {
        super();
        game = gam;
        speed = sp;
        currentAt = ca;

        bg = new Animator(coll, roll, currentAt.findRegion(directory), speed, false);
        bgGIF = bg.getWalkAnimation();
        current = bg.getCurrentFrame();
        stateTime = 0f;

        setBounds(getX(), getY(), getWidth(), getHeight());

        stateTime += Gdx.graphics.getDeltaTime();
        current = bgGIF.getKeyFrame(stateTime, true);

    }

    public BGAnimation(TreasureHunt gam, boolean freeze) {
        super();
        game = gam;
        isFreeze = freeze;

        setBounds(getX(), getY(), getWidth(), getHeight());

        game.stateTimeOver = 0f;
        game.stateTimeOver += Gdx.graphics.getDeltaTime();
        game.currentOver = game.gameOverGIF.getKeyFrame(game.stateTimeOver, true);
        alive = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!isFreeze) {
            batch.draw(current, getX(), getY(), getWidth(), getHeight());
        }
        else {
            batch.draw(game.currentOver, getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isFreeze) {
            setX(getX() - speedShift*delta);
            stateTime += Gdx.graphics.getDeltaTime();
            current = bgGIF.getKeyFrame(stateTime, true);
            if (getX() < -2000) {
                this.remove();
            }
        }
        else {
            if (alive == true) {
                game.stateTimeOver += Gdx.graphics.getDeltaTime();
                game.currentOver = game.gameOverGIF.getKeyFrame(game.stateTimeOver, true);
            }
            if (game.gameOverGIF.isAnimationFinished(game.stateTimeOver)) {
                alive = false;
            }
        }
    }

    public void setSpeedShift(int speedShift) {
        this.speedShift = speedShift;
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public boolean isAlive() {
        return alive;
    }
}
