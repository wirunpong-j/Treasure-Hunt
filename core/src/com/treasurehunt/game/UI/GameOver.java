package com.treasurehunt.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.treasurehunt.game.Tools.BGAnimation;
import com.treasurehunt.game.Tools.BitFont;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 10/11/2559.
 */
public class GameOver extends Stage {
    private TreasureHunt game;
    private BGAnimation gameOver;
    private boolean isAlive;
    private boolean isPlay;
    private ShapeRenderer shape;
    private BitFont font;
    private SpriteBatch batch;

    public GameOver(TreasureHunt gam) {
        super();
        batch = new SpriteBatch();
        game = gam;
        gameOver = new BGAnimation(game, true);
        gameOver.setPosition(150, 150);
        gameOver.setSize(1000, 364);
        addActor(gameOver);
        isAlive = false;
        isPlay = true;
        shape = new ShapeRenderer();
        font = new BitFont("YOUR SCORE : "+game.score, Color.RED, 70, "VCR");
        font.setPosition(250, 580);
    }

    @Override
    public void draw() {
        super.draw();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLACK);
        shape.rect(150, 514, 1000, 100);
        shape.end();
        batch.begin();
        font.draw(batch, 1);
        batch.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        isAlive = gameOver.isAlive();
        font.setStuff("YOUR SCORE : "+game.score);

        if (isPlay) {
            game.gameoverSound.play();
            isPlay = false;
        }

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public boolean isAlive() {
        return isAlive;
    }

}
