package com.treasurehunt.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.BitFont;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 27/10/2559.
 */
public class Score extends Actor {
    private TreasureHunt game;
    private BitFont font, key, stamp, bubble;
    private int stage;

    public Score(TreasureHunt gam, int s) {
        super();
        game = gam;
        stage = s;

        font = new BitFont("Score: "+game.score, Color.WHITE, 30, "VCR");
        key = new BitFont("X "+game.key, Color.WHITE, 30, "VCR");
        stamp = new BitFont("X "+game.stamp, Color.WHITE, 30, "VCR");
        font.setPosition(1000, 680);
        key.setPosition(150, 630);
        stamp.setPosition(350, 630);

        if (stage == 3) {
            bubble = new BitFont("X "+game.cBubble+"/1", Color.WHITE, 30, "VCR");
            bubble.setPosition(550, 630);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, parentAlpha);
        key.draw(batch, parentAlpha);
        stamp.draw(batch, parentAlpha);
        batch.draw(game.coins, 930, 645, 60, 60);
        batch.draw(game.keyt, 90, 595, 50, 50);
        batch.draw(game.stampt, 290, 595, 50, 50);
        if (stage == 3) {
            bubble.draw(batch, parentAlpha);
            batch.draw(game.bubble, 490, 595, 60, 80);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        font.setStuff("Score: "+game.score);
        key.setStuff("X "+game.key);
        stamp.setStuff("X "+game.stamp);
        if (stage == 3) {
            bubble.setStuff("X "+game.cBubble+"/1");
        }
        if (game.score <= 0) {
            game.score = 0;
        }
    }

    @Override
    public boolean remove() {
        return super.remove();
    }
}
