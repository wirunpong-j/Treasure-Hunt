package com.treasurehunt.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.treasurehunt.game.Character.PlayerMainMenu;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 31/10/2559.
 */
public class BackgroundMain extends Actor {
    private TreasureHunt game;
    private PlayerMainMenu player, bird;
    private Ground place;
    private Animator bg;
    private Animation bgGIF;
    private TextureRegion current1;
    private float stateTime1;

    public BackgroundMain(TreasureHunt gam) {
        super();
        game = gam;

        bg = new Animator(2, 1, game.bgStar, 0.5f, false);
        bgGIF = bg.getWalkAnimation();
        current1 = bg.getCurrentFrame();
        stateTime1 = 0f;

        stateTime1 += Gdx.graphics.getDeltaTime();
        current1 = bgGIF.getKeyFrame(stateTime1, true);

        // Player & Bird
        running();

        place = new Ground(game, game.placeTexture, 50);
        place.setRecBG2(0, 254, 1280, 117);
        place.setRecBG3(1280, 254, 1280, 117);
        place.setRecBG4(2560, 254, 1280, 117);

        setBounds(getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(current1, getX(), getY(), getWidth(), getHeight());
        batch.draw(game.mountainTexture, 0, 254, 1280, 205);
        batch.draw(game.groundMenu, 0, 0, 1280, 254);
        place.draw(batch, parentAlpha);
        player.draw(batch, parentAlpha);
        bird.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime1 += Gdx.graphics.getDeltaTime();
        current1 = bgGIF.getKeyFrame(stateTime1, true);
        place.act(delta);
        player.act(delta);
        bird.act(delta);
    }

    private void running(){
        player = new PlayerMainMenu(game, 10, 1, game.playerAtlas.findRegion("PlayerRun"), 0.07f, false);
        player.setPosition(-100, 254);
        player.setSize(85, 90);

        MoveToAction ma1 = new MoveToAction();
        ma1.setPosition(600, 254);
        ma1.setDuration(5f);
        MoveToAction ma2 = new MoveToAction();
        ma2.setPosition(600, 254);
        ma2.setDuration(10f);
        MoveToAction ma3 = new MoveToAction();
        ma3.setPosition(1500, 315);
        ma3.setDuration(3f);
        MoveToAction ma4 = new MoveToAction();
        ma4.setPosition(1500, 400);
        ma4.setDuration(5f);
        MoveToAction ma5 = new MoveToAction();
        ma5.setPosition(-100, 254);
        ma5.setDuration(0f);

        bird = new PlayerMainMenu(game, 4, 1, game.birdAtlas.findRegion("Bird1"), 0.07f, true);
        bird.setPosition(-100, 254);
        bird.setSize(80, 50);

        MoveToAction ma6 = new MoveToAction();
        ma6.setPosition(-100, 400);
        ma6.setDuration(12f);
        MoveToAction ma7 = new MoveToAction();
        ma7.setPosition(600, 340);
        ma7.setDuration(3f);
        MoveToAction ma8 = new MoveToAction();
        ma8.setPosition(1500, 400);
        ma8.setDuration(3f);
        MoveToAction ma9 = new MoveToAction();
        ma9.setPosition(1500, 400);
        ma9.setDuration(5f);
        MoveToAction ma10 = new MoveToAction();
        ma10.setPosition(-100, 400);
        ma10.setDuration(0f);

        SequenceAction action1 = new SequenceAction();
        action1.addAction(ma1);
        action1.addAction(ma2);
        action1.addAction(ma3);
        action1.addAction(ma4);
        action1.addAction(ma5);
        player.addAction(Actions.forever(action1));

        SequenceAction action2 = new SequenceAction();
        action2.addAction(ma6);
        action2.addAction(ma7);
        action2.addAction(ma8);
        action2.addAction(ma9);
        action2.addAction(ma10);
        bird.addAction(Actions.forever(action2));
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

}
