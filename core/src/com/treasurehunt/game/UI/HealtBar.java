package com.treasurehunt.game.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;
import com.treasurehunt.game.Character.Player;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 27/10/2559.
 */
public class HealtBar extends Actor {
    private TreasureHunt game;
    private ProgressBar.ProgressBarStyle style;
    private Skin skin;
    private TextureRegion barLeft;

    private final int MAX_LIFE = 6000;
    private int test;
    private Lighting invincible1, invincible2, invincible3;
    private Player player;

    public HealtBar(TreasureHunt gam, Player py) {
        super();
        game = gam;
        player = py;
        skin = new Skin();

        skin.addRegions(game.packBar);
        barLeft = new TextureRegion(skin.getRegion("Heart"));

        style = new ProgressBar.ProgressBarStyle(null, skin.getDrawable("bar_green_right"));
        style.knobBefore = skin.getDrawable("bar_green_mid");

        game.life = new ProgressBar(0, MAX_LIFE, 1, false, style);
        game.life.setPosition(60, 650);
        game.life.setSize(500, 50);
        game.life.setAnimateDuration(0);
        game.life.setValue(MAX_LIFE);
        game.life.setAnimateInterpolation(Interpolation.exp5Out);
        game.life.setAnimateDuration(0.5f);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(barLeft, 35, 652, 54, 50);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        game.life.setValue(game.life.getValue()-30*delta);
        if (test == 1) {
            if (invincible3.isAlive()) {
                invincible3.setSize(player.getWidth()+100, player.getHeight()+50);
                invincible3.setPosition(player.getX()-50, player.getY()-30);
            }
            invincible1.setSize(player.getWidth()+50, player.getHeight()-70);
            invincible1.setPosition(player.getX()-30, player.getY()-20);
            invincible2.setSize(player.getWidth()+100, player.getHeight()+50);
            invincible2.setPosition(player.getX()-50, player.getY()-30);
        }
    }

    public void decreaseLife() {
        if (test == 0) {
            game.life.setValue(game.life.getValue() - (int)(game.life.getMaxValue() * 0.1));
            test = 1;
            game.crushSound.play();
            this.invincible();
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    test = 0;
                    invincible1.setAlive(false);
                    invincible2.setAlive(false);
                }
            },3);
        }
    }

    public void increaseLife() {
        game.life.setValue(game.life.getValue() + game.life.getMaxValue() *0.15f);
    }

    public void invincible() {
        invincible1 = new Lighting(game, 8, 1, game.effectAtlas.findRegion("Invincible1"), 0.1f, false, false);
        invincible1.setPosition(player.getX(), player.getY());
        invincible1.setSize(player.getWidth(), player.getHeight());
        getStage().addActor(invincible1);

        invincible2 = new Lighting(game, 8, 1, game.effectAtlas.findRegion("Invincible2"), 0.1f, false, false);
        invincible2.setPosition(player.getX(), player.getY());
        invincible2.setSize(player.getWidth(), player.getHeight());
        getStage().addActor(invincible2);

        invincible3 = new Lighting(game, 4, 1, game.effectAtlas.findRegion("Damage"), 0.1f, false, true);
        invincible3.setPosition(player.getX(), player.getY());
        invincible3.setSize(player.getWidth(), player.getHeight());
        getStage().addActor(invincible3);
    }

    public ProgressBar getLife() {
        return game.life;
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
