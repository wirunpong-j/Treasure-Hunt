package com.treasurehunt.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Items.Fireball;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.HealtBar;
import com.treasurehunt.game.UI.Lighting;

/**
 * Created by Bell KunG on 7/11/2559.
 */
public class Boss extends Actor {
    private TreasureHunt game;
    private Player player;
    private HealtBar healtBar;

    private Animator boss1;
    private Animation boss1GIF;
    private TextureRegion current1;
    private float stateTime1;

    private Animator boss2;
    private Animation boss2GIF;
    private TextureRegion current2;
    private float stateTime2;

    private Rectangle rect;
    private Lighting light;
    private Fireball laser;

    private boolean isReady;

    public Boss(TreasureHunt gam, Player play, HealtBar hb) {
        super();
        game = gam;
        player = play;
        healtBar = hb;

        boss1 = new Animator(5, 5, game.bossAtlas.findRegion("Boss1"), 0.1f, false);
        boss1GIF = boss1.getWalkAnimation();
        current1 = boss1.getCurrentFrame();
        stateTime1 = 0f;

        boss2 = new Animator(5, 5, game.bossAtlas.findRegion("Boss2"), 0.1f, false);
        boss2GIF = boss2.getWalkAnimation();
        current2 = boss2.getCurrentFrame();
        stateTime2 = 0f;

        stateTime1 += Gdx.graphics.getDeltaTime();
        current1 = boss1GIF.getKeyFrame(stateTime1, true);

        setBounds(getX(), getY(), getWidth(), getHeight());

        rect = new Rectangle();
        isReady = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!isReady) {
            batch.draw(current1, getX(), getY(), getWidth(), getHeight());
        }
        else if (isReady) {
            batch.draw(current2, getX(), getY(), getWidth(), getHeight());
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isReady) {
            stateTime1 += Gdx.graphics.getDeltaTime();
            current1 = boss1GIF.getKeyFrame(stateTime1, true);
        }
        else if (isReady) {
            stateTime2 += Gdx.graphics.getDeltaTime();
            current2 = boss2GIF.getKeyFrame(stateTime2, true);
            if (boss2GIF.isAnimationFinished(stateTime2)) {
                stateTime2 = 0f;
                isReady = false;
                laserShooter();
            }
        }

        rect.setSize(getWidth()-100, getHeight()-50);
        rect.setPosition(getX()+30, getY()+10);

        if (light != null) {
            light.setSize(getWidth(), getHeight());
            light.setPosition(getX(), getY());
        }

        if (laser != null) {
            if (player.getRect().overlaps(laser.getRect())) {
                healtBar.decreaseLife();
            }
        }
    }

    public void callLighting() {
        light = new Lighting(game, 12, 1, game.effectAtlas.findRegion("AttactToBoss2"), 0.1f, false, true);
        light.setSize(getWidth(), getHeight());
        light.setPosition(getX(), getY());
        getStage().addActor(light);
    }

    public void laserShooter() {
        laser = new Fireball(game, 5, 1, game.effectAtlas.findRegion("BossLaser"), 0.2f, false, -800);
        laser.setSize(250,116);
        laser.setPosition(getX(), (int)(150+Math.random()*200));
        getStage().addActor(laser);
    }


    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
