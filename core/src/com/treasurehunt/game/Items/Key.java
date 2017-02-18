package com.treasurehunt.game.Items;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 2/11/2559.
 */
public class Key extends Actor {
    private TreasureHunt game;
    private Rectangle rect;

    public Key(TreasureHunt gam) {
        super();
        game = gam;

        rect = new Rectangle();
        rect.setPosition(getX(), getY());
        rect.setSize(getWidth(), getHeight());

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(game.keyt, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() - 500*delta);
        rect.setPosition(getX(), getY());

        if (getX() < -1000) {
            this.remove();
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
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

    public Rectangle getRect() {
        return rect;
    }

}
