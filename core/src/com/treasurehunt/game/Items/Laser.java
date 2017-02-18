package com.treasurehunt.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import com.treasurehunt.game.Tools.Animator;

/**
 * Created by Bell KunG on 31/10/2559.
 */
public class Laser extends Actor {
    private Animator laser;
    private Texture burst;
    private Sprite burstLaser;
    private Rectangle rect;

    private Animation laserGIF;
    private TextureRegion current;
    private float stateTime;

    private long start, disposeTime;
    private boolean attact;

    public Laser() {
        super();
        laser = new Animator(1, 4, "Items/Laser.png", 0.07f, false);
        laserGIF = laser.getWalkAnimation();
        current = laser.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = laserGIF.getKeyFrame(stateTime, true);

        burst = new Texture(Gdx.files.internal("Items/GreenBurst.png"));
        burstLaser = new Sprite(burst);
        burstLaser.setSize(100, 100);
        burstLaser.setOriginCenter();

        disposeTime = TimeUtils.millis() + 3000;
        start = TimeUtils.millis() + 1000;

        attact = false;

        setBounds(getX(), getY(), getWidth(), getHeight());

        rect = new Rectangle();
        rect.setSize(getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (attact == true) {
            rect.setPosition(getX(), getY());
        }

        if (start < TimeUtils.millis()) {
            batch.draw(current, getX(), getY()-getHeight()/2, getWidth(), getHeight());
            attact = true;
        }
        burstLaser.draw(batch);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += Gdx.graphics.getDeltaTime();
        current = laserGIF.getKeyFrame(stateTime, true);
        burstLaser.setPosition(getX(), getY());
        burstLaser.setRotation((TimeUtils.millis()/3)%360);
        burstLaser.setPosition(getX()+getWidth() - 50, getY()-getHeight()/2 );

        if(disposeTime < TimeUtils.millis()){
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

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        burstLaser.setPosition(x, y);
        rect.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        rect.setSize(width, height);
    }
}

