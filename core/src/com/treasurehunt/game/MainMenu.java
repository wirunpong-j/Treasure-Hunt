package com.treasurehunt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.treasurehunt.game.Stage1.Stage1;
import com.treasurehunt.game.Tools.BackgroundMain;
import com.treasurehunt.game.Tools.HardcoreButton;
import com.treasurehunt.game.UI.LogoGame;

/**
 * Created by Bell KunG on 25/10/2559.
 */
public class MainMenu implements Screen{
    private TreasureHunt game;
    private Stage stage;
    private Music mainMusic;
    private BackgroundMain bg;
    private HardcoreButton hb1, hb2, hb3, hb4, hb5;
    private LogoGame nameBG, credit, howToPlay;

    public MainMenu(TreasureHunt gam) {
        super();
        game = gam;
        stage = new Stage();

        bg = new BackgroundMain(game);
        bg.setSize(1280, 768);
        bg.setPosition(0,0);
        stage.addActor(bg);

        // Credit
        credit = new LogoGame(game, game.credit);
        credit.setPosition(0, 0);
        credit.setSize(1280, 768);
        credit.setColor(1,1,1,0);
        stage.addActor(credit);

        // How to Play
        howToPlay = new LogoGame(game, game.htp);
        howToPlay.setPosition(0, 0);
        howToPlay.setSize(1280, 768);
        howToPlay.setColor(1,1,1,0);
        stage.addActor(howToPlay);

        hb1 = new HardcoreButton("NEW GAME");
        hb1.setPosition(-2000, 170);
        hb1.setSize(1280, 50);
        MoveToAction st1 = new MoveToAction();
        st1.setPosition(0, 170);
        st1.setDuration(0.5f);
        hb1.addAction(st1);
        hb1.setColor(Color.BLACK);
        hb1.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.buttonSound.play();
                MoveToAction ma = new MoveToAction();
                ma.setPosition(-1280, 170);
                ma.setInterpolation(Interpolation.exp5Out);
                ma.setDuration(0.75f);
                hb1.addAction(ma);

                MoveToAction ma2 = new MoveToAction();
                ma2.setPosition(-1280, 110);
                ma2.setInterpolation(Interpolation.exp5Out);
                ma2.setDuration(0.5f);
                hb2.addAction(ma2);

                MoveToAction ma3 = new MoveToAction();
                ma3.setPosition(-1280, 50);
                ma3.setInterpolation(Interpolation.exp5Out);
                ma3.setDuration(0.25f);
                hb3.addAction(ma3);

                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        MoveToAction ma4 = new MoveToAction();
                        ma4.setPosition(0, 170);
                        ma4.setInterpolation(Interpolation.exp5Out);
                        ma4.setDuration(0.5f);
                        hb5.addAction(ma4);

                        AlphaAction aa = new AlphaAction();
                        aa.setAlpha(1);
                        aa.setDuration(1f);
                        howToPlay.addAction(aa);

                        AlphaAction aa2 = new AlphaAction();
                        aa2.setAlpha(0);
                        aa2.setDuration(1f);
                        nameBG.addAction(aa2);

                    }
                }, 1.5f);

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5Out);
                ca.setEndColor(Color.RED);
                hb1.addAction(ca);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5In);
                ca.setEndColor(Color.BLACK);
                hb1.addAction(ca);
            }
        });
        stage.addActor(hb1);

        // Credit Button
        hb2 = new HardcoreButton("CREDIT");
        hb2.setPosition(-2000, 110);
        hb2.setSize(1280, 50);
        MoveToAction st2 = new MoveToAction();
        st2.setPosition(0, 110);
        st2.setDuration(0.75f);
        hb2.addAction(st2);
        hb2.setColor(Color.BLACK);
        hb2.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.buttonSound.play(1);
                MoveToAction ma1 = new MoveToAction();
                ma1.setPosition(-1280, 170);
                ma1.setDuration(0.75f);
                hb1.addAction(ma1);

                MoveToAction ma2 = new MoveToAction();
                ma2.setPosition(-1280, 110);
                ma2.setDuration(0.5f);
                hb2.addAction(ma2);

                MoveToAction ma3 = new MoveToAction();
                ma3.setPosition(-1280, 50);
                ma3.setDuration(0.25f);
                hb3.addAction(ma3);

                AlphaAction aa1 = new AlphaAction();
                aa1.setAlpha(0);
                aa1.setDuration(1f);
                nameBG.addAction(aa1);

                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        MoveToAction ma4 = new MoveToAction();
                        ma4.setPosition(0, 110);
                        ma4.setDuration(0.5f);
                        hb4.addAction(ma4);

                        AlphaAction aa2 = new AlphaAction();
                        aa2.setAlpha(1);
                        aa2.setDuration(1f);
                        credit.addAction(aa2);

                    }
                }, 1f);

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5Out);
                ca.setEndColor(Color.RED);
                hb2.addAction(ca);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5In);
                ca.setEndColor(Color.BLACK);
                hb2.addAction(ca);
            }
        });
        stage.addActor(hb2);

        hb3 = new HardcoreButton("EXIT");
        hb3.setPosition(-2000, 50);
        hb3.setSize(1280, 50);
        MoveToAction st3 = new MoveToAction();
        st3.setPosition(0, 50);
        st3.setDuration(1f);
        hb3.addAction(st3);
        hb3.setColor(Color.BLACK);
        hb3.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.buttonSound.play();
                MoveToAction ma3 = new MoveToAction();
                ma3.setPosition(-1280, 50);
                ma3.setDuration(0.5f);
                hb3.addAction(ma3);

                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        Gdx.app.exit();
                    }
                }, 0.5f);

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5Out);
                ca.setEndColor(Color.RED);
                hb3.addAction(ca);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5In);
                ca.setEndColor(Color.BLACK);
                hb3.addAction(ca);
            }
        });
        stage.addActor(hb3);

        hb4 = new HardcoreButton("BACK");
        hb4.setPosition(-2000, 110);
        hb4.setSize(1280, 50);
        hb4.setColor(Color.BLACK);
        hb4.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.buttonSound.play();
                MoveToAction ma4 = new MoveToAction();
                ma4.setPosition(-1280, 110);
                ma4.setDuration(0.25f);
                hb4.addAction(ma4);

                AlphaAction aa2 = new AlphaAction();
                aa2.setAlpha(0);
                aa2.setDuration(0.5f);
                credit.addAction(aa2);

                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        AlphaAction aa1 = new AlphaAction();
                        aa1.setAlpha(1);
                        aa1.setDuration(3f);
                        nameBG.addAction(aa1);

                        MoveToAction ma1 = new MoveToAction();
                        ma1.setPosition(0, 170);
                        ma1.setDuration(0.5f);
                        hb1.addAction(ma1);

                        MoveToAction ma2 = new MoveToAction();
                        ma2.setPosition(0, 110);
                        ma2.setDuration(0.75f);
                        hb2.addAction(ma2);

                        MoveToAction ma3 = new MoveToAction();
                        ma3.setPosition(0, 50);
                        ma3.setDuration(1f);
                        hb3.addAction(ma3);
                    }
                }, 0.75f);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5Out);
                ca.setEndColor(Color.RED);
                hb4.addAction(ca);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5In);
                ca.setEndColor(Color.BLACK);
                hb4.addAction(ca);
            }
        });
        stage.addActor(hb4);

        //  Play
        hb5 = new HardcoreButton("Play");
        hb5.setPosition(-2000, 170);
        hb5.setSize(1280, 50);
        hb5.setColor(Color.BLACK);
        hb5.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.buttonSound.play();
                MoveToAction ma = new MoveToAction();
                ma.setPosition(-1280, 170);
                ma.setInterpolation(Interpolation.exp5Out);
                ma.setDuration(0.5f);

                RunnableAction ra = new RunnableAction();
                ra.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        dispose();
                        game.setScreen(new Stage1(game));
                    }
                });

                SequenceAction action = new SequenceAction();
                action.addAction(ma);
                action.addAction(ra);
                hb5.addAction(action);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5Out);
                ca.setEndColor(Color.RED);
                hb5.addAction(ca);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                ColorAction ca = new ColorAction();
                ca.setDuration(0.1f);
                ca.setInterpolation(Interpolation.exp5In);
                ca.setEndColor(Color.BLACK);
                hb5.addAction(ca);
            }
        });
        stage.addActor(hb5);

        // Logo Game
        nameBG = new LogoGame(game, game.nameGame);
        nameBG.setPosition(400, 500);
        nameBG.setSize(541, 130);

        AlphaAction aa1 = new AlphaAction();
        aa1.setAlpha(0);
        aa1.setDuration(0f);
        AlphaAction aa2 = new AlphaAction();
        aa2.setAlpha(1);
        aa2.setDuration(3f);

        SequenceAction action = new SequenceAction();
        action.addAction(aa1);
        action.addAction(aa2);
        nameBG.addAction(action);

        stage.addActor(nameBG);

        Gdx.input.setInputProcessor(stage);

        // Play Music
        mainMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Dandandanganronpa!.mp3"));
        mainMusic.setLooping(true);
        mainMusic.play();
        mainMusic.setVolume(0.5f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.clear();
        stage.dispose();
        mainMusic.dispose();

        nameBG.clear();
        credit.clear();
        bg.clear();
        hb1.clear();
        hb2.clear();
        hb3.clear();
        hb4.clear();
        hb5.clear();

        bg.remove();
        hb1.remove();
        hb2.remove();
        hb3.remove();
        hb4.remove();
        hb5.remove();
        howToPlay.remove();
        credit.remove();
        nameBG.remove();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
