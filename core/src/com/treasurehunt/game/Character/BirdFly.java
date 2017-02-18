package com.treasurehunt.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 9/11/2559.
 */
public class BirdFly extends Actor {
    private TreasureHunt game;
    private Animator obs;
    private Animation obsGIF;
    private TextureRegion current;
    private float stateTime;

    public BirdFly(TreasureHunt gam, int coll, int row, TextureRegion tr, float speed) {
        super();
        game = gam;

        obs = new Animator(coll, row, tr, speed, false);
        obsGIF = obs.getWalkAnimation();
        current = obs.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = obsGIF.getKeyFrame(stateTime, true);

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() - 700*delta);
        stateTime += Gdx.graphics.getDeltaTime();
        current = obsGIF.getKeyFrame(stateTime, true);

        if (getX() < -200) {
            this.remove();
        }
    }

    @Override
    public boolean remove() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    obs.dispose();
                }
                catch (Exception e) {

                }
            }
        });
        return super.remove();
    }
}
