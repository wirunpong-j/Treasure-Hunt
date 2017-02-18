package com.treasurehunt.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.Lighting;

/**
 * Created by Bell KunG on 25/10/2559.
 */
public class Player extends Actor {
    private TreasureHunt game;
    private Rectangle rect;
    private Lighting light, getKey;

    private boolean isSlide;
    private boolean isJump;


    public Player(TreasureHunt gam) {
        super();
        game = gam;

        isJump = false;
        rect = new Rectangle();

        game.stateTime_Player += Gdx.graphics.getDeltaTime();
        game.currentPlayer = game.playerGIF.getKeyFrame(game.stateTime_Player, true);

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setZIndex(getStage().getActors().toArray().length-1);
        if (isSlide) {
            batch.draw(game.slide, getX(), getY(), getWidth()+30, getHeight()-30);
        }
        else if (isJump) {
            batch.draw(game.jumpp, getX(), getY(), getWidth()-20, getHeight()-5);
        }

        else {
            batch.draw(game.currentPlayer, getX(), getY(), getWidth(), getHeight());
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        game.stateTime_Player += Gdx.graphics.getDeltaTime();
        game.currentPlayer = game.playerGIF.getKeyFrame(game.stateTime_Player, true);
        if (isSlide) {
            rect.setSize(100, 30);
            rect.setPosition(getX()+20, getY()+20);
        }
        else {
            rect.setSize(55, 70);
            rect.setPosition(getX()+25, getY()+20);
        }

        if (light != null) {
            light.setSize(getWidth()+100, getHeight()+50);
            light.setPosition(getX()-40, getY()-5);
        }
        if (getKey != null) {
            getKey.setSize(getWidth()+200, getHeight()+200);
            getKey.setPosition(getX()-100, getY()-80);
        }
    }

    public void jumpping() {
        if (!isJump) {
            game.sound.play();
            isJump = true;
            MoveToAction ma = new MoveToAction();
            ma.setDuration(0.35f);
            ma.setInterpolation(Interpolation.exp5Out);
            ma.setPosition(getX(), 400);

            MoveToAction mad = new MoveToAction();
            mad.setDuration(0.35f);
            mad.setInterpolation(Interpolation.exp5In);
            mad.setPosition(getX(), getY());

            RunnableAction ra = new RunnableAction();
            ra.setRunnable(new Runnable() {
                @Override
                public void run() {
                    isJump = false;
                }
            });

            SequenceAction sa = new SequenceAction();
            sa.addAction(ma);
            sa.addAction(mad);
            sa.addAction(ra);

            addAction(sa);
        }
    }

    public void sliding() {
        game.slideSound.play();
    }

    public void callLighting() {
        light = new Lighting(game, 11, 1, game.effectAtlas.findRegion("GetPotion"), 0.1f, false, true);
        light.setSize(getWidth(), getHeight());
        light.setPosition(getX(), getY());
        getStage().addActor(light);
    }

    public void callGetKey() {
        getKey = new Lighting(game, 16, 1, game.effectAtlas.findRegion("GetKey"), 0.1f, false, true);
        getKey.setSize(getWidth(), getHeight());
        getKey.setPosition(getX(), getY());
        getStage().addActor(getKey);
    }


    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setSlide(boolean slide) {
        this.isSlide = slide;
    }
}
