package com.treasurehunt.game.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 28/10/2559.
 */
public class EnemyBar extends Actor {
    private TreasureHunt game;
    private ProgressBar life;
    private ProgressBar.ProgressBarStyle style;
    private Skin skin;
    private TextureRegion barLeft;

    private final int MAX_LIFE = 1000;

    public EnemyBar(TreasureHunt gam) {
        super();
        game = gam;
        skin = new Skin();

        skin.addRegions(game.packBar);
        barLeft = new TextureRegion(skin.getRegion("bar_red_left"));

        style = new ProgressBar.ProgressBarStyle(null, skin.getDrawable("bar_red_right"));
        style.knobBefore = skin.getDrawable("bar_red_mid");

        life = new ProgressBar(0, MAX_LIFE, 1, false, style);
        life.setPosition(getX(), getY());
        life.setSize(getWidth(), getHeight());
        life.setAnimateDuration(0);
        life.setValue(MAX_LIFE);
        life.setAnimateInterpolation(Interpolation.exp5Out);
        life.setAnimateDuration(0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(barLeft, 894, 497, 6, 26);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        life.setValue(life.getValue());
    }

    public void decreaseLife() {
            life.setValue(life.getValue() - 100);
    }

    public ProgressBar getLife() {
        return life;
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        life.setPosition(getX(), getY());
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        life.setSize(getWidth(), getHeight());
    }
}
