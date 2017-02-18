package com.treasurehunt.game.Stage3;

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
import com.treasurehunt.game.Tools.*;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Bell KunG on 7/11/2559.
 */
public class Stage3 implements Screen, InputProcessor {
    private TreasureHunt game;
    private GameOver gameover;
    private GamePause gamePause;
    private Transition transition;
    private InputMultiplexer im;

    private Player player;
    private Stage stage, bgStage1, bgStage2, uiStage, groundStage, items, pauseStage, transStage;
    private HealtBar healtbar;
    private Score scoreUI;
    private Ground ground, ceiling, wall;
    private MiniGame1 mini1;
    private Spawner sp;
    private BackGroundStage aura;
    private ButtonIMG pauseButton;

    private ArrayList<Obstacle> allObs;
    private ArrayList<Potion> allPotion;
    private ArrayList<Fireball> allFireball;
    private ArrayList<Key> allKeys;
    private ArrayList<Coin> allCoin;
    private ArrayList<Item> allBall;

    private int randomMiniGame;
    private boolean isBoss, shooting, end, isPause, isGameOver;
    private long potiontime;

    public Stage3(TreasureHunt gam) {
        super();
        game = gam;

        game.key = 0;
        game.stamp = 0;
        game.cBubble = 0;

        game.musicStage3.setLooping(true);
        game.musicStage3.play();
        game.musicStage3.setVolume(0.5f);

        gameover = new GameOver(game);
        gamePause = new GamePause(game, this);

        stage = new Stage();
        pauseStage = new Stage();
        bgStage1 = new Stage();
        bgStage2 = new Stage();
        groundStage = new Stage();
        items = new Stage();
        uiStage = new Stage();
        transStage = new Stage();

        allKeys = new ArrayList<Key>();
        allCoin = new ArrayList<Coin>();
        allBall = new ArrayList<Item>();
        allPotion = new ArrayList<Potion>();

        end = true;
        isGameOver = true;
        isBoss = false;
        shooting = true;

        // Wall
        wall = new Ground(game, game.wall, 25);
        wall.setRecBG2(0, 0, 1280, 768);
        wall.setRecBG3(1280, 0, 1280, 768);
        wall.setRecBG4(2560, 0, 1280, 768);
        bgStage1.addActor(wall);

        // Sand Pillar
        bgStage1.addActor(new Spawner(30, 5, 1280, 0) {
            @Override
            public void spawn(int x, int y) {
                BackGroundStage temp = new BackGroundStage(game, game.sandPillar, 25, true, false);
                temp.setSize(300, 768);
                temp.setPosition(x, y);
                bgStage1.addActor(temp);
                temp.setZIndex(1);
            }
        });

        // Back Gold
        bgStage1.addActor(new Spawner(15, 5, 1280, 110){
            @Override
            public void spawn(int x, int y) {
                int rd = (int)(1+Math.random()*6);
                BGAnimation temp = new BGAnimation(game, game.goldAtlas, "BackGold"+rd, 2, 1, 0.15f);
                temp.setSize(1000, 500);
                temp.setPosition(x, y);
                temp.setSpeedShift(50);
                bgStage1.addActor(temp);
            }
        });

        // Front Gold
        bgStage2.addActor(new Spawner(15, 5, 1280, 110){
            @Override
            public void spawn(int x, int y) {
                int rd = (int)(1+Math.random()*6);
                BGAnimation temp = new BGAnimation(game, game.goldAtlas, "FrontGold"+rd, 2, 1, 0.15f);
                temp.setSize(1500, 350);
                temp.setPosition(x, y);
                temp.setSpeedShift(100);
                bgStage2.addActor(temp);
            }
        });

        // Ground
        ground = new Ground(game, game.ground3, 500);
        ground.setRecBG2(0, 0, 1280, 120);
        ground.setRecBG3(1280, 0, 1280, 120);
        ground.setRecBG4(2560, 0, 1280, 120);

        // Ceiling
        ceiling = new Ground(game, game.ceiling3, 500);
        ceiling.setRecBG2(0, 680, 1280, 80);
        ceiling.setRecBG3(1280, 680, 1280, 80);
        ceiling.setRecBG4(2560, 680, 1280, 80);

        groundStage.addActor(ground);
        groundStage.addActor(ceiling);

        // Aura Gold
        aura = new BackGroundStage(game, game.auraGold, 0, false, false);
        aura.setPosition(0, 0);
        aura.setSize(1280, 768);
        bgStage1.addActor(aura);

        // Items -> Key
        sp = new Spawner(5, 10, 1280, 120) {
            @Override
            public void spawn(int x, int y) {
                if (!isBoss) {
                    Key temp = new Key(game);
                    temp.setSize(60, 71);
                    temp.setPosition(x, (int)(150+Math.random()*200));
                    items.addActor(temp);
                    allKeys.add(temp);
                }
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

        // Item to Attack
        items.addActor(new Spawner(5, 5, 1280, 150) {
            @Override
            public void spawn(int x, int y) {
                if (isBoss && game.cBubble == 0) {
                    Item temp = new Item(game, 16, 1, game.effectAtlas.findRegion("BallItem"), 0.07f, false);
                    temp.setSize(100, 150);
                    temp.setPosition(x, (int)(150+Math.random()*200));
                    temp.setSpeedup(500);
                    items.addActor(temp);
                    allBall.add(temp);
                }
            }
        });

        // Obstacle
        allObs = new ArrayList<Obstacle>();
        stage.addActor(new Spawner(0.5f, 3, 1280, 107){
            @Override
            public void spawn(int x, int y) {
                int test = (int)(Math.random()*2);
                // Potion
                if(potiontime < TimeUtils.millis()){
                    potiontime = TimeUtils.millis() + 30000;
                    Potion potion = new Potion();
                    potion.setSize(150, 120);
                    potion.setPosition(x, (int)(150+Math.random()*200));
                    stage.addActor(potion);
                    allPotion.add(potion);
                }
                else {
                    if (test == 0) {
                        Obstacle temp = new Obstacle(game, 3);
                        temp.setSpeed(500);
                        temp.setSize(150, 150);
                        temp.setPosition(x, y);
                        stage.addActor(temp);
                        allObs.add(temp);
                    }
                    else {
                        Obstacle temp = new Obstacle(game, 3, 4, 1, "Snake", 0.3f);
                        temp.setSpeed(700);
                        temp.setSize(200, 100);
                        temp.setPosition(x, y);
                        stage.addActor(temp);
                        allObs.add(temp);
                    }
                }
            }
        });

        // Player & Spear
        allFireball = new ArrayList<Fireball>();
        player = new Player(game);
        player.setSize(100,100);
        player.setPosition(150, 100);
        player.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.UP) {
                    player.jumpping();
                }
                else if (keycode == Input.Keys.DOWN) {
                    player.setSlide(true);
                    player.sliding();
                }

                else if (mini1 != null && keycode == Input.Keys.SPACE && game.cBubble == 1) {
                    Fireball fireball = new Fireball(game, 8, 1, game.effectAtlas.findRegion("Spear"), 0.2f, true, 300);
                    fireball.setSize(481,223);
                    fireball.setPosition(player.getX() + player.getWidth()-50, player.getY() + player.getHeight()/2 - 10);
                    stage.addActor(fireball);
                    allFireball.add(fireball);
                    game.cBubble = 0;
                    game.spearSound.play();
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

        // Pause Stage
        pauseButton = new ButtonIMG(game.buttonAtlas, "P4-click", "P4-Unclick");
        pauseButton.setPosition(860, 650);
        pauseButton.setSize(45, 45);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gamePause = new GamePause(game, Stage3.this);
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

        // UI
        healtbar = new HealtBar(game, player);
        uiStage.addActor(healtbar.getLife());
        uiStage.addActor(healtbar);
        scoreUI = new Score(game, 3);
        uiStage.addActor(scoreUI);

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

/************************************* Key to MiniGame ****************************************/
        // Check Keys
        Iterator<Key> item5 = allKeys.iterator();
        while (item5.hasNext()) {
            final Key temp = item5.next();
            if (player.getRect().overlaps(temp.getRect())) {
                game.key++;
                game.keySound.play();
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
            mini1 = new MiniGame1(game, healtbar, player);
            mini1.spawnBoss();
            isBoss = true;
            game.cBubble = 0;
            randomMiniGame = 1;
            game.key = 0;
        }

/***************************** MiniGame1 : Boss ***********************************************/
        // Fireball Check!!!
        if (mini1 != null) {
            Iterator<Fireball> item3 = allFireball.iterator();
            while (item3.hasNext()) {
                final Fireball temp = item3.next();
                if (mini1.getBoss() != null) {
                    if (mini1.getBoss().getRect().overlaps(temp.getRect())) {
                        game.bossCrushSound.play();
                        if (mini1.getEnemyBar().getLife().getValue() <= 0) {
                            if (end) {
                                mini1.bossDead();
                                game.score += 500;
                                end = false;
                            }
                            else if (isBoss) {
                                isBoss = false;
                                Timer timer = new Timer();
                                timer.scheduleTask(new Timer.Task() {
                                    @Override
                                    public void run() {
                                        mini1.getEnemyStage().clear();
                                        end = true;
                                        mini1 = null;
                                        game.cBubble = 0;
                                        sp.resetTime();
                                    }
                                },1);
                            }
                            break;
                        } else {
                            mini1.getEnemyBar().decreaseLife();
                            mini1.getBoss().callLighting();
                        }
                        temp.remove();
                        item3.remove();
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                temp.remove();
                                allKeys.remove(temp);
                            }
                        });
                    }
                    if (temp.getX() > 1500) {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                temp.remove();
                                allKeys.remove(temp);
                            }
                        });
                    }
                }
            }
        }

        if (isBoss && shooting) {
            shooting = false;
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    shooting = true;
                    try {
                        mini1.getBoss().setReady(true);
                        game.bossLaser.play();
                    } catch (Exception e) {

                    }
                }
            },(int)(5+Math.random()*5));
        }

/*********************************************************************************************/

/***********************************************************************************************/
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

        // Spawn Ball
        Iterator<Item> item7 = allBall.iterator();
        while (item7.hasNext()) {
            final Item temp = item7.next();
            if (player.getRect().overlaps(temp.getRect())) {
                if (game.cBubble == 0) {
                    game.cBubble++;
                }
                temp.remove();
                item7.remove();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allBall.remove(temp);
                    }
                });
            }

            if (temp.getX() < -500) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        temp.remove();
                        allBall.remove(temp);
                    }
                });
            }
        }

/**********************************************************************************************/
        // Act & Draw All Stage
        if (game.life.getValue() <= 0 && isGameOver) {
            gameover.act();
            game.musicStage3.stop();
            if (!gameover.isAlive()) {
                gameover.dispose();
                dispose();
                game.setScreen(new MainMenu(game));
            }
        } else {
            if (isPause && gamePause.isPause()) { gamePause.act();}
            else {
                if (randomMiniGame == 1 && mini1 != null) {
                    mini1.getEffect().act();
                    mini1.getEnemyStage().act(delta);
                }
                bgStage1.act();
                bgStage2.act();
                groundStage.act();
                items.act();
                stage.act();
                uiStage.act();
                pauseStage.act();
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

        if (randomMiniGame == 1 && mini1 != null && !isPause) {
            mini1.getEffect().draw();
            mini1.getEnemyStage().draw();
        }

        if (game.life.getValue() <= 0) {
            gameover.draw();
        }

        transStage.act();
        transStage.draw();
    }

    @Override
    public void dispose() {
        try {
            pauseStage.dispose();
        } catch (Exception e) {

        }
        stage.dispose();
        bgStage1.dispose();
        bgStage2.dispose();
        uiStage.dispose();
        groundStage.dispose();
        items.dispose();
        game.musicStage3.dispose();
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
            gamePause = new GamePause(game, Stage3.this);
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
