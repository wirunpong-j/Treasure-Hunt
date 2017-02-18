package com.treasurehunt.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 7/11/2559.
 */
public class BackGroundStage extends Actor {
    private TreasureHunt game;
    private Texture texture;
    private int speed;
    private boolean isShift;
    private boolean isTR;
    private TextureRegion textureRegion;

    public BackGroundStage(TreasureHunt gam, Texture txt, int sp, boolean shift, boolean itr) {
        super();
        game = gam;
        texture = txt;
        speed = sp;
        isShift = shift;
        isTR = itr;

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public BackGroundStage(TreasureHunt gam, TextureRegion tr, int sp, boolean shift, boolean itr) {
        super();
        game = gam;
        textureRegion = tr;
        speed = sp;
        isTR = itr;
        isShift = shift;

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (isTR) {
            batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
        }
        else {
            batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isShift) {
            setX(getX() - speed*delta);
            if (getX() < -2000) {
                this.remove();
            }
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}