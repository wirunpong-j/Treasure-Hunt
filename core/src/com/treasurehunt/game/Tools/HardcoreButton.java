package com.treasurehunt.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Bell KunG on 4/11/2559.
 */
public class HardcoreButton extends Actor {
    private ShapeRenderer shape;
    private String text;
    private BitFont font;

    public HardcoreButton(String txt) {
        super();
        shape = new ShapeRenderer();

        text = txt;
        setBounds(getX(), getY(), getWidth(), getHeight());
        font = new BitFont(text, getColor(), 30, "8-BIT WONDER");
        font.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(getColor().r, getColor().g, getColor().b, 0.7f);
        shape.rect(getX(), getY(), getWidth(), getHeight());
        shape.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);
        batch.begin();
        font.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        font.setPosition(getX()+30, (getY() + getHeight()/2)+10);
    }

    @Override
    public boolean remove() {
        font.remove();
        shape.dispose();
        return super.remove();
    }
}
