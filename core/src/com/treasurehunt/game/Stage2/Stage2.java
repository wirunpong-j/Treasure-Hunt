package com.treasurehunt.game.Stage2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.treasurehunt.game.Character.Obstacle;
import com.treasurehunt.game.Character.Player;
import com.treasurehunt.game.Items.*;
import com.treasurehunt.game.MainMenu;
import com.treasurehunt.game.Stage3.Stage3;
import com.treasurehunt.game.Tools.*;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Bell KunG on 30/10/2559.
 */
public class Stage2 implements Screen, InputProcessor {
    private InputMultiplexer im;
    private TreasureHunt game;
    private GameOver gameover;
    private GamePause gamePause;
    private Transition transition;
    private Portal portal;
    private Lighting complete;
    private Stage stage, background, uiStage, items, pauseStage, transStage, portalStage;

    private Ground brick, ground;
    private Player player;
    private HealtBar healtbar;
    private Score scoreUI;
    private BackGroundStage shadow;
    private Spawner sp;
    private MiniGame3 mini3;
    private ButtonIMG pauseButton;

    private ArrayList<Potion> allPotion;
    private ArrayList<Key> allKeys;
    private ArrayList<Coin> allCoin;
    private ArrayList<Obstacle> allObs;

    private int minigame;
    private long potiontime;
    private boolean isPause, isGameOver, change;

    public Stage2(TreasureHunt gam) {
        super();
        game = gam;

        game.key = 0;
        game.stamp = 0;
        game.musicStage2.setLooping(true);
        game.musicStage2.play();
        game.musicStage2.setVolume(0.50f);

        gameover = new GameOver(game);
        gamePause = new GamePause(game, this);

        stage = new Stage();
        items = new Stage();
        background = new Stage();
        pauseStage = new Stage();
        uiStage = new Stage();
        transStage = new Stage();
        portalStage = new Stage();

        allKeys = new ArrayList<Key>();
        allCoin = new ArrayList<Coin>();
        allObs = new ArrayList<Obstacle>();
        allPotion = new ArrayList<Potion>();

        change = false;
        isGameOver = true;
        potiontime = 0;

        // Brick
        brick = new Ground(game, game.brick, 25);
        brick.setRecBG2(0, 0, 1280, 768);
        brick.setRecBG3(1280, 0, 1280, 768);
        brick.setRecBG4(2560, 0, 1280, 768);
        background.addActor(brick);

        // Pillar
        background.addActor(new Spawner(30, 5, 1280, 0) {
            @Override
            public void spawn(int x, int y) {
                BackGroundStage temp = new BackGroundStage(game, game.pillar, 25, true, false);
                temp.setSize(300, 768);
                temp.setPosition(x, y);
                background.addActor(temp);
                temp.setZIndex(1);
            }
        });

        // Bottles
        background.addActor(new Spawner(15, 5, 1280, -30){
            @Override
            public void spawn(int x, int y) {
                BackGroundStage temp = new BackGroundStage(game, game.bottles.get((int)(Math.random()*7)), 30, true, false);
                temp.setSize(300, 700);
                temp.setPosition(x, y);
                background.addActor(temp);
                temp.setZIndex(2);
            }
        });

        // Ground
        ground = new Ground(game, game.runway, 500);
        ground.setRecBG2(0, 0, 1280, 120);
        ground.setRecBG3(1280, 0, 1280, 120);
        ground.setRecBG4(2560, 0, 1280, 120);
        background.addActor(ground);

        // Shadow
        shadow = new BackGroundStage(game, game.shadow, 0, false, false);
        shadow.setPosition(0, 0);
        shadow.setSize(1280, 768);
        background.addActor(shadow);

        // Items -> Key
        sp = new Spawner(5, 10, 1280, 120) {
            @Override
            public void spawn(int x, int y) {
                Key temp = new Key(game);
                temp.setSize(60, 71);
                temp.setPosition(x, (int)(150+Math.random()*200));
                items.addActor(temp);
                allKeys.add(temp);
            }
        };
        items.addActor(sp);

        // Items -> Coin
        items.addActor(new Spawner(0.5f, 2, 1280, 150) {
            @Override
            public void spawn(int x, int y) {
                Coin temp = new Coin();
                temp.setSize(50, 50);
                temp.setPosition(x, (int)(150+Math.random()*200));
                items.addActor(temp);
                allCoin.add(temp);
            }
        });

        // Obstacle & Potion
        stage.addActor(new Spawner(0.5f, 3, 1280, 107){
            @Override
            public void spawn(int x, int y) {
                int rd = (int)(Math.random()*2);

                // Potion
                if(potiontime < TimeUtils.millis()){
                    potiontime = TimeUtils.millis() + 30000;
                    Potion potion = new Potion();
                    potion.setSize(150, 120);
                    potion.setPosition(x, (int)(150+Math.random()*200));
                    stage.addActor(potion);
                    allPotion.add(potion);
                }

                // Obstacle
                else {
                    if (rd == 1) {
                        Obstacle temp = new Obstacle(game, 2);
                        temp.setSize(120, 150);
                        temp.setPosition(x, y);
                        temp.setSpeed(500);
                        stage.addActor(temp);
                        allObs.add(temp);
                    }
                    else {
                        Obstacle temp = new Obstacle(game, 2, 5, 1, "tomp", 0.2f);
                        temp.setSize(120, 150);
                        temp.setPosition(x, y);
                        temp.setSpeed(500);
                        stage.addActor(temp);
                        allObs.add(temp);
                    }
                }
            }
        });

        // Player
        player = new Player(game);
        player.setSize(100,100);
        player.setPosition(150, 100);
        player.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.UP && mini3 == null) {
                    player.jumpping();
                }
                else if (keycode == Input.Keys.DOWN && mini3 == null) {
                    player.setSlide(true);
                    player.sliding();
                }

                return super.keyDown(event, keycode);
            }

            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.DOWN) {
                    player.setSlide(false);
                }
                return super.keyDown(event, keycode);
            }
        });
        stage.addActor(player);

        // UI
        healtbar = new HealtBar(game, player);
        uiStage.addActor(healtbar.getLife());
        uiStage.addActor(healtbar);
        scoreUI = new Score(game, 2);
        uiStage.addActor(scoreUI);

        // Pause Stage
        pauseButton = new ButtonIMG(game.buttonAtlas, "P4-click", "P4-Unclick");
        pauseButton.setPosition(860, 650);
        pauseButton.setSize(45, 45);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gamePause = new GamePause(game, Stage2.this);
                im.addProcessor(gamePause);
                isPause = true;
                gamePause.setPause(true);
                pauseButton.getTextButton().setStyle(pauseButton.getStyle2());
                super.clicked(event, x, y);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                pauseButton.getTextButton().setStyle(pauseButton.getStyle2());
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                pauseButton.getTextButton().setStyle(pauseButton.getStyle());
                super.exit(event, x, y, pointer, toActor);
            }
        });
        pauseStage.addActor(pauseButton);

        // Input Processor
        im = new InputMultiplexer();
        im.addProcessor(this);
        im.addProcessor(stage);
        im.addProcessor(pauseStage);
        stage.setKeyboardFocus(player);
        Gdx.input.setInputProcessor(im);

        // Transition
        transition = new Transition();
        transition.setPosition(0, 0);
        transition.setSize(1280, 768);
        transition.fadeIn();
        transStage.addActor(transition);

        // Portal to Next Stage
        portal = new Portal(game);
        portal.setPosition(1500, 110);
        portal.setSize(300, 400);

        // Complete
        complete = new Lighting(game, 4, 6, game.effectAtlas.findRegion("Complete"), 0.07f, false, true);
        complete.setPosition(0, 0);
        complete.setSize(1280, 768);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Obstacle Check!!!
        Iterator<Obstacle> item = allObs.iterator();
        while (item.hasNext()) {
            final Obstacle temp = item.next();
            if (player.getRect().overlaps(temp.getRect())) {
                healtbar.decreaseLife();
            }
            if (temp.getX() < -500) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allObs.remove(temp);
                    }
                });
            }
        }

        // Potion Check!!!
        Iterator<Potion> item2 = allPotion.iterator();
        while (item2.hasNext()) {
            final Potion temp = item2.next();
            if (player.getRect().overlaps(temp.getRect())) {
                healtbar.increaseLife();
                player.callLighting();
                temp.remove();
                item2.remove();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allKeys.remove(temp);
                    }
                });
            }
            if (temp.getX() < -500) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allPotion.remove(temp);
                    }
                });
            }
        }

/************************************** Key Check!!! ************************************/
        // Key Check!!!
        Iterator<Key> item5 = allKeys.iterator();
        while (item5.hasNext()) {
            final Key temp = item5.next();
            if (player.getRect().overlaps(temp.getRect())) {
                game.keySound.play();
                game.key++;
                player.callGetKey();
                temp.remove();
                item5.remove();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allKeys.remove(temp);
                    }
                });
            }
            if (temp.getX() < -500) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allKeys.remove(temp);
                    }
                });
            }
        }

        if (game.key == 3) {
            mini3 = new MiniGame3(game);
            im.addProcessor(mini3.getStage());
            game.key = 0;
            minigame = 3;
        }
/**************************************************************************************/
        // Coin Check!!!
        Iterator<Coin> item6 = allCoin.iterator();
        while (item6.hasNext()) {
            final Coin temp = item6.next();
            if (player.getRect().overlaps(temp.getRect())) {
                game.score++;
                game.coinSound.play(0.5f);
                temp.remove();
                item6.remove();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allCoin.remove(temp);
                    }
                });
            }
            if (temp.getX() < -500) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allCoin.remove(temp);
                    }
                });
            }
        }

/******************************************************************************************/
        //Act & Draw
        if (game.life.getValue() <= 0 && isGameOver) {
            gameover.act();
            game.musicStage2.stop();
            if (!gameover.isAlive()) {
                gameover.dispose();
                dispose();
                game.setScreen(new MainMenu(game));
            }
        } else {
            if (isPause && gamePause.isPause()) { gamePause.act();}
            else {
                if (minigame == 3 && mini3 != null) {
                    mini3.act(delta);
                    if(mini3.isDead()){
                        mini3.remove();
                        mini3 = null;
                        sp.resetTime();
                    }
                }
                else {
                    background.act();
                    items.act();
                    stage.act();
                    uiStage.act();
                    pauseStage.act();
                }
            }
        }

        background.draw();
        items.draw();
        stage.draw();
        uiStage.draw();
        pauseStage.draw();

        if (isPause && gamePause.isPause()) {
            gamePause.draw();
        }
        else if (gamePause != null && !gamePause.isPause()) {
            isPause = false;
            gamePause = null;
        }

        if (game.life.getValue() <= 0) {
            gameover.draw();
        }

        if (minigame == 3 && mini3 != null) {
            mini3.draw(game.batch, 1);
        }

        if (game.stamp == 3 && !change) {
            portalStage.addActor(portal);
            portalStage.addActor(complete);
            if (player.getRect().overlaps(portal.getRect())) {
                transition.fadeOut();
                change = true;

                Timer timer = new Timer();
                timer.scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        dispose();
                        game.setScreen(new Stage3(game));
                    }
                },1);
            }
        }

        transStage.act();
        transStage.draw();
        if (!isPause && mini3 == null) {
            portalStage.act();
        }
        portalStage.draw();
    }

    @Override
    public void dispose() {
        try {
            pauseStage.dispose();
        } catch (Exception e) {

        }
        stage.dispose();
        background.dispose();
        uiStage.dispose();
        items.dispose();
        game.musicStage2.dispose();
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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            game.key++;
        }
        else if (keycode == Input.Keys.S) {
            game.stamp++;
        }
        else if (keycode == Input.Keys.D) {
            game.life.setValue(0);
        }
        else if (keycode == Input.Keys.ESCAPE) {
            gamePause = new GamePause(game, Stage2.this);
            im.addProcessor(gamePause);
            isPause = true;
            gamePause.setPause(true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
