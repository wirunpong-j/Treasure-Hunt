package com.treasurehunt.game.Character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 9/11/2559.
 */
public class Knife extends Actor {
    private TreasureHunt game;
    private Texture texture;
    private int speed;

    public Knife(TreasureHunt gam, Texture txt, int sp) {
        super();
        game = gam;
        texture = txt;
        speed = sp;

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() - speed*delta);

        if (getX() < -500) {
            this.remove();
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
