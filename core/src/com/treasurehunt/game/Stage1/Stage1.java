package com.treasurehunt.game.Stage1;

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
import com.treasurehunt.game.Stage2.Stage2;
import com.treasurehunt.game.Tools.*;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Bell KunG on 25/10/2559.
 */
public class Stage1 implements Screen, InputProcessor {
    private TreasureHunt game;
    private InputMultiplexer im;
    private GameOver gameover;
    private GamePause gamePause;
    private Transition transition;
    private Portal portal;
    private Lighting complete;

    private Player player;
    private Stage stage, bgStage1, bgStage2, uiStage, groundStage, items, pauseStage, transStage, portalStage;
    private HealtBar healtbar;
    private Score scoreUI;
    private Ground ground;
    private MiniGame2 mini2;
    private Spawner sp;
    private BackGroundStage sky;
    private ButtonIMG pauseButton;

    private ArrayList<Obstacle> allObs;
    private ArrayList<Potion> allPotion;
    private ArrayList<Key> allKeys;
    private ArrayList<Coin> allCoin;

    private int randomMiniGame;
    private boolean isPause, isGameOver, change;
    private long potiontime;

    public Stage1(TreasureHunt gam) {
        super();
        game = gam;

        game.score = 0;
        game.key = 0;
        game.stamp = 0;

        game.musicStage1.setLooping(true);
        game.musicStage1.play();
        game.musicStage1.setVolume(0.50f);

        gameover = new GameOver(game);
        stage = new Stage();
        bgStage1 = new Stage();
        bgStage2 = new Stage();
        groundStage = new Stage();
        items = new Stage();
        uiStage = new Stage();
        pauseStage = new Stage();
        transStage = new Stage();
        portalStage = new Stage();

        allPotion = new ArrayList<Potion>();
        allKeys = new ArrayList<Key>();
        allObs = new ArrayList<Obstacle>();
        allCoin = new ArrayList<Coin>();

        isGameOver = true;
        change = false;
        potiontime = 0;

        // Sky & Cloud & Back Mountain
        sky = new BackGroundStage(game, game.sky, 0, false, false);
        sky.setPosition(0, 0);
        sky.setSize(1280, 768);
        bgStage1.addActor(sky);

        bgStage1.addActor(new Spawner(5, 5, 1280, 400){
            @Override
            public void spawn(int x, int y) {
                BackGroundStage temp = new BackGroundStage(game, game.cloud.get((int)(Math.random()*1)), 100, true, false);
                temp.setSize(360, 30);
                temp.setPosition(x, (int)((Math.random()*150)+y));
                bgStage1.addActor(temp);
            }
        });

        bgStage1.addActor(new Spawner(15, 5, 1280, 120){
            @Override
            public void spawn(int x, int y) {
                BackGroundStage temp = new BackGroundStage(game, game.back_mountain.get((int)(Math.random()*3)), 50, true, false);
                temp.setSize(1000, 500);
                temp.setPosition(x, y);
                bgStage1.addActor(temp);
            }
        });

        // Front Mountain
        bgStage2.addActor(new Spawner(15, 5, 1280, 120){
            @Override
            public void spawn(int x, int y) {
                BackGroundStage temp = new BackGroundStage(game, game.front_mountain.get((int)(Math.random()*3)), 100, true, false);
                temp.setSize(1500, 350);
                temp.setPosition(x, y);
                bgStage2.addActor(temp);
            }
        });

        // Ground
        ground = new Ground(game, game.groundtxt, 500);
        ground.setRecBG2(0, 0, 1300, 300);
        ground.setRecBG3(1280, 0, 1300, 300);
        ground.setRecBG4(2560, 0, 1300, 300);
        groundStage.addActor(ground);

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

        // Coin
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
                int test = (int) (Math.random() * 2);

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
                else{
                    if (test == 0) {
                        Obstacle temp = new Obstacle(game, 1);
                        temp.setSpeed(500);
                        temp.setSize(120, 150);
                        temp.setPosition(x, y);
                        stage.addActor(temp);
                        allObs.add(temp);
                    } else {
                        Obstacle temp = new Obstacle(game, 1, 4, 1, "bird", 0.1f);
                        temp.setSpeed(700);
                        temp.setSize(100, 50);
                        temp.setPosition(x, y + 368);
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
                if (keycode == Input.Keys.UP && mini2 == null) {
                    player.jumpping();
                }
                else if (keycode == Input.Keys.DOWN && mini2 == null) {
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
        scoreUI = new Score(game, 1);
        uiStage.addActor(scoreUI);

        // Pause Stage
        pauseButton = new ButtonIMG(game.buttonAtlas, "P4-click", "P4-Unclick");
        pauseButton.setPosition(860, 650);
        pauseButton.setSize(45, 45);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gamePause = new GamePause(game, Stage1.this);
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
        transStage.addActor(transition);
        transition.fadeIn();

        // Portal next to Stage
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

        // Obstacle Check & Remove!!!
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

/***************************** Key to MiniGame ***********************************************/
        // Check Keys
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
            mini2 = new MiniGame2(game);
            im.addProcessor(mini2.getStage());
            game.key = 0;
            randomMiniGame = 2;
        }
/***********************************************************************************************/
        // Coin Check!!!
        Iterator<Coin> item6 = allCoin.iterator();
        while (item6.hasNext()) {
            final Coin temp = item6.next();
            if (player.getRect().overlaps(temp.getRect())) {
                game.score++;
                game.coinSound.play(0.5f);
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
/*******************************************************************************************/
        // Act & Draw All Stage
        if (game.life.getValue() <= 0 && isGameOver) {
            gameover.act();
            game.musicStage1.stop();
            if (!gameover.isAlive()) {
                gameover.dispose();
                dispose();
                game.setScreen(new MainMenu(game));
            }
        }
        else {
            if (isPause && gamePause.isPause()) { gamePause.act();}
            else {
                if (randomMiniGame == 2 && mini2 != null) {
                    mini2.act(delta);
                    if(mini2.isDead()){
                        mini2.remove();
                        mini2 = null;
                        sp.resetTime();
                    }
                }
                else {
                    bgStage1.act();
                    bgStage2.act();
                    groundStage.act();
                    items.act();
                    stage.act();
                    uiStage.act();
                    pauseStage.act();
                }
            }
        }
        bgStage1.draw();
        bgStage2.draw();
        groundStage.draw();
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

        if (randomMiniGame == 2 && mini2 != null) {
            mini2.draw(game.batch, 1);
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
                        game.setScreen(new Stage2(game));
                    }
                },1);
            }
        }

        transStage.act();
        transStage.draw();
        if (!isPause && mini2 == null) {
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
        stage.clear();
        uiStage.clear();
        bgStage1.clear();
        bgStage2.clear();
        groundStage.clear();
        items.clear();
        portalStage.clear();
        transStage.clear();

        stage.dispose();
        uiStage.dispose();
        bgStage1.dispose();
        bgStage2.dispose();
        groundStage.dispose();
        items.dispose();
        game.musicStage1.dispose();
        portalStage.dispose();
        transStage.dispose();
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
            gamePause = new GamePause(game, Stage1.this);
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
