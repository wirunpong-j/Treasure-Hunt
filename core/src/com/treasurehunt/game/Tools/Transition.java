package com.treasurehunt.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;

/**
 * Created by Bell KunG on 13/11/2559.
 */
public class Transition extends Actor {
    private ShapeRenderer shape;

    public Transition() {
        super();
        shape = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(getColor());
        shape.rect(getX(), getY(), getWidth(), getHeight());
        shape.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void fadeIn() {
        AlphaAction aa = new AlphaAction();
        setColor(Color.BLACK);
        aa.setDuration(1);
        aa.setAlpha(0);
        addAction(aa);
    }

    public void fadeOut() {
        AlphaAction aa = new AlphaAction();
        setColor(new Color(0, 0, 0, 0));
        aa.setDuration(1);
        aa.setAlpha(1);
        addAction(aa);

    }

    @Override
    public boolean remove() {
        return super.remove();
    }

}
