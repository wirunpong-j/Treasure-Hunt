package com.treasurehunt.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 8/11/2559.
 */
public class PlayerMainMenu extends Actor {
    private TreasureHunt game;
    private Animator player;
    private Animation playerGIF;
    private TextureRegion current;
    private float stateTime;

    public PlayerMainMenu(TreasureHunt gam, int coll, int row, TextureRegion tr, float speed, boolean flip) {
        super();
        game = gam;

        player = new Animator(coll, row, tr, speed, flip);
        playerGIF = player.getWalkAnimation();
        current = player.getCurrentFrame();
        stateTime = 0f;

        setBounds(getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(current, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += Gdx.graphics.getDeltaTime();
        current = playerGIF.getKeyFrame(stateTime, true);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

}
