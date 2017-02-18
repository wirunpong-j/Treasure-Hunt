package com.treasurehunt.game.Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 28/10/2559.
 */
public class Fireball extends Actor{
    private TreasureHunt game;
    private Animator fireball;
    private Animation fireballGIF;
    private TextureRegion current;
    private float stateTime;
    private Rectangle rect;
    private ShapeRenderer shape;
    private int speed;

    public Fireball(TreasureHunt gam, int coll, int row, TextureRegion tr, float s, boolean now, int sp) {
        game = gam;
        speed = sp;

        fireball = new Animator(coll, row, tr, s, now);
        fireballGIF = fireball.getWalkAnimation();
        current = fireball.getCurrentFrame();
        stateTime = 0f;

        stateTime += Gdx.graphics.getDeltaTime();
        current = fireballGIF.getKeyFrame(stateTime, true);

        setBounds(getX(), getY(), getWidth(), getHeight());

        rect = new Rectangle();
        shape = new ShapeRenderer();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        /*batch.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(1,1,1,1);
        shape.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        shape.end();
        batch.begin();*/
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += Gdx.graphics.getDeltaTime();
        current = fireballGIF.getKeyFrame(stateTime, true);
        setX(getX() + speed*delta);
        rect.setPosition(getX(), getY());

        if (getX() > 1500 && speed > 0) {
            this.remove();
        }
        else if (getX() < -1000 && speed < 0) {
            this.remove();
        }
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        rect.setSize(width-100, height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        rect.setPosition(x, y);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
    }

}
