package com.treasurehunt.game.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 7/11/2559.
 */
public class Ground extends Actor {
    private TreasureHunt game;
    private Rectangle recBG2, recBG3, recBG4;
    private Texture texture;
    private int speed;
    private int testBG;

    public Ground(TreasureHunt gam, Texture txt, int sp) {
        super();
        game = gam;
        texture = txt;
        speed = sp;

        setBounds(getX(), getY(), getWidth(), getHeight());

        recBG2 = new Rectangle();
        recBG3 = new Rectangle();
        recBG4 = new Rectangle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, recBG2.x, recBG2.y, recBG2.width, recBG2.height);
        batch.draw(texture, recBG3.x, recBG3.y, recBG3.width, recBG3.height);
        batch.draw(texture, recBG4.x, recBG4.y, recBG4.width, recBG4.height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        recBG2.setX(recBG2.getX()-speed*delta);
        recBG3.setX(recBG3.getX()-speed*delta);
        recBG4.setX(recBG4.getX()-speed*delta);
        if (recBG3.getX() <= 0 && testBG == 0) { recBG2.setX(recBG3.getX()+2560); testBG = 1; }
        if (recBG2.getX() <= 0 && testBG == 1) { recBG3.setX(recBG4.getX()+2560); testBG = 2; }
        if (recBG2.getX() <= 0 && testBG == 2) { recBG4.setX(recBG2.getX()+2560); testBG = 0; }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public void setRecBG2(int x, int y, int width, int height) {
        this.recBG2.setPosition(x, y);
        this.recBG2.setSize(width, height);
    }

    public void setRecBG3(int x, int y, int width, int height) {
        this.recBG3.setPosition(x, y);
        this.recBG3.setSize(width, height);
    }

    public void setRecBG4(int x, int y, int width, int height) {
        this.recBG4.setPosition(x, y);
        this.recBG4.setSize(width, height);
    }
}