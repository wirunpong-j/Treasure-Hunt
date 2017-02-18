package com.treasurehunt.game.Tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

public class Spawner extends Actor {

    private float time;
    private float margin;
    private long lastTime;
    private int xx;
    private int yy;

    public Spawner(float t, float m, int x, int y) {
        super();
        margin = m;
        time = t;
        xx = x;
        yy = y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(lastTime < TimeUtils.millis()){
            lastTime = TimeUtils.millis() + (long)((time + (margin * Math.random()))*1000);
            spawn(xx, yy);
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void spawn(int x, int y){

    }

    public void resetTime() {
        lastTime = TimeUtils.millis() + (long)((time + (margin * Math.random()))*1000);
    }
}