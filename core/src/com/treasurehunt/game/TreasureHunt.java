package com.treasurehunt.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.treasurehunt.game.Tools.Animator;

import java.util.ArrayList;

/**
 * Created by Bell KunG on 25/10/2559.
 */
public class TreasureHunt extends Game {
    public ProgressBar life;
    public SpriteBatch batch;

    public ArrayList<Texture> cloud;
    public ArrayList<Texture> back_mountain;
    public ArrayList<Texture> front_mountain;
    public ArrayList<Texture> obstacle;
    public ArrayList<Texture> obstacle2;
    public ArrayList<Texture> back_gold;
    public ArrayList<Texture> front_gold;
    public ArrayList<Texture> obstacle3;
    public ArrayList<Texture> bottles;

    public Texture brick;
    public Texture wall;
    public Texture runway;
    public Texture groundtxt;
    public Texture pillar;
    public Texture shadow;
    public Texture ground3;
    public Texture ceiling3;
    public Texture sandPillar;
    public Texture auraGold;
    public Texture sky;
    public Texture nameGame;
    public Texture credit;
    public Texture coins;
    public Texture keyt;
    public Texture stampt;
    public Texture htp;
    public Texture r,p,s;
    public Texture jumpp, slide;
    public Texture bubble;
    public Texture groundMenu;
    public Texture placeTexture;
    public Texture mountainTexture;
    public Texture bgStar;

    public TextureAtlas goldAtlas;
    public TextureAtlas birdAtlas;
    public TextureAtlas playerAtlas;
    public TextureAtlas effectAtlas;
    public TextureAtlas buttonAtlas;
    public TextureAtlas bossAtlas;
    public TextureAtlas gachaAtlas;
    public TextureAtlas gamePause;
    public TextureAtlas packBar;
    public TextureAtlas packRPS;
    public TextureAtlas chest;

    public Sound sound, slideSound, crushSound, bossCrushSound, bossLaser;
    public Sound coinSound, buttonSound, keySound, spearSound;
    public Sound gameoverSound;
    public Music musicStage1, musicStage2, musicStage3;

    public String vs;
    public int key, stamp, cBubble;
    public long score;

    // Animation
    public Animator start;
    public Animation startGIF;
    public TextureRegion current0;
    public float stateTime0;

    public Animator myPlayer;
    public Animation myPlayerGIF;
    public TextureRegion current1;
    public float stateTime1;

    public Animator pc;
    public Animation npcGIF;
    public TextureRegion current2;
    public float stateTime2;

    public Animator win;
    public Animation winGIF;
    public TextureRegion current3;
    public float stateTime3;

    public Animator lose;
    public Animation loseGIF;
    public TextureRegion current4;
    public float stateTime4;

    public Animator draw;
    public Animation drawGIF;
    public TextureRegion current5;
    public float stateTime5;

    public Animator player;
    public Animation playerGIF;
    public TextureRegion currentPlayer;
    public float stateTime_Player;

    public Animator gameOver;
    public Animation gameOverGIF;
    public TextureRegion currentOver;
    public float stateTimeOver;

    @Override
    public void create() {
        batch = new SpriteBatch();
        packBar = new TextureAtlas(Gdx.files.internal("Buttons/HealthBar.pack"));

        // MainMenu
        groundMenu = new Texture(Gdx.files.internal("Background/MainMenu/Ground.png"));
        placeTexture = new Texture(Gdx.files.internal("Background/MainMenu/Place.png"));
        mountainTexture = new Texture(Gdx.files.internal("Background/MainMenu/Mountain.png"));
        bgStar = new Texture(Gdx.files.internal("Background/MainMenu/BG_withStar.png"));
        nameGame = new Texture(Gdx.files.internal("Background/MainMenu/Logo.png"));
        credit = new Texture(Gdx.files.internal("Background/MainMenu/Credit.png"));
        htp = new Texture(Gdx.files.internal("Background/MainMenu/HowToPlay.png"));

        //Items
        coins = new Texture(Gdx.files.internal("Buttons/Coins.png"));
        keyt = new Texture(Gdx.files.internal("Items/KeyT.png"));
        stampt = new Texture(Gdx.files.internal("Items/Stamp.png"));
        bubble = new Texture(Gdx.files.internal("Items/Bubble.png"));

        // TextureAtlas
        goldAtlas = new TextureAtlas(Gdx.files.internal("Background/Stage3/Gold.pack"));
        birdAtlas = new TextureAtlas(Gdx.files.internal("Obstacle/Bird.pack"));
        playerAtlas = new TextureAtlas(Gdx.files.internal("Character/Player.pack"));
        effectAtlas = new TextureAtlas(Gdx.files.internal("Effect/AllEffect.pack"));
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/Pause.pack"));
        bossAtlas = new TextureAtlas(Gdx.files.internal("Character/Boss.pack"));
        gachaAtlas = new TextureAtlas(Gdx.files.internal("Buttons/Gacha.pack"));
        gamePause = new TextureAtlas(Gdx.files.internal("Buttons/Pause.pack"));

        // Character
        score = 0;
        key = 0;
        stamp = 0;
        cBubble = 0;
        jumpp = new Texture(Gdx.files.internal("Character/jump.png"));
        slide = new Texture(Gdx.files.internal("Character/slide.png"));

        // Stage1
        cloud = new ArrayList<Texture>();
        back_mountain = new ArrayList<Texture>();
        front_mountain = new ArrayList<Texture>();
        obstacle = new ArrayList<Texture>();
        sky = new Texture(Gdx.files.internal("Background/Stage1/sky.png"));
        groundtxt = new Texture(Gdx.files.internal("Background/Stage1/Ground.png"));
        for (int i = 1; i < 3; i++) { cloud.add(new Texture(Gdx.files.internal("Background/Stage1/Cloud-0"+i+".png" )));}
        for (int i = 1; i < 4; i++) { back_mountain.add(new Texture(Gdx.files.internal("Background/Stage1/Mountain_black-0"+i+".png")));}
        for (int i = 1; i < 4; i++) { front_mountain.add(new Texture(Gdx.files.internal("Background/Stage1/Mountain_front-0"+i+".png")));}
        for (int i = 1; i < 5; i++) { obstacle.add(new Texture(Gdx.files.internal("Obstacle/obs1-"+i+".png"))); }

        // Stage2
        bottles = new ArrayList<Texture>();
        obstacle2 = new ArrayList<Texture>();
        brick = new Texture(Gdx.files.internal("Background/Stage2/background-WithoutShadow.png"));
        runway = new Texture(Gdx.files.internal("Background/Stage2/MainRunway.png"));
        pillar = new Texture(Gdx.files.internal("Background/Stage2/final_pillar.png"));
        shadow = new Texture(Gdx.files.internal("Background/Stage2/Shadow.png"));
        for (int i = 1; i <= 8; i++) { bottles.add(new Texture(Gdx.files.internal("Background/Stage2/Bottle"+i+".png"))); }
        for (int i = 1; i < 4; i++) { obstacle2.add(new Texture(Gdx.files.internal("Obstacle/obs2-"+i+".png")));}

        // Stage3
        back_gold = new ArrayList<Texture>();
        front_gold = new ArrayList<Texture>();
        obstacle3 = new ArrayList<Texture>();
        ground3 = new Texture(Gdx.files.internal("Background/Stage3/Ground3.png"));
        ceiling3 = new Texture(Gdx.files.internal("Background/Stage3/Beam.png"));
        sandPillar = new Texture(Gdx.files.internal("Background/Stage3/SandPillar.png"));
        wall = new Texture(Gdx.files.internal("Background/Stage3/BG3.png"));
        auraGold = new Texture(Gdx.files.internal("Background/Stage3/AuraGold.png"));
        for (int i = 1; i <= 3; i++) { obstacle3.add(new Texture(Gdx.files.internal("Obstacle/obs3-"+i+".png"))); }

        //Sound
        coinSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Coin.wav"));
        musicStage1 = Gdx.audio.newMusic(Gdx.files.internal("Music/Stage1.mp3"));
        musicStage2 = Gdx.audio.newMusic(Gdx.files.internal("Music/Stage2.mp3"));
        musicStage3 = Gdx.audio.newMusic(Gdx.files.internal("Music/Stage3.mp3"));
        sound = Gdx.audio.newSound(Gdx.files.internal("Sound/jumpping.wav"));
        slideSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Slide.mp3"));
        crushSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Crush.mp3"));
        gameoverSound = Gdx.audio.newSound(Gdx.files.internal("Music/GameOver.mp3"));
        bossCrushSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Boss.wav"));
        bossLaser = Gdx.audio.newSound(Gdx.files.internal("Sound/BossLaser.wav"));
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Button.wav"));
        keySound = Gdx.audio.newSound(Gdx.files.internal("Sound/Key.ogg"));
        spearSound = Gdx.audio.newSound(Gdx.files.internal("Sound/SpearSound.mp3"));

        // MiniGame1
        packRPS = new TextureAtlas(Gdx.files.internal("Buttons/RPS.pack"));
        r = new Texture(Gdx.files.internal("Buttons/Rock.png"));
        p = new Texture(Gdx.files.internal("Buttons/Paper.png"));
        s = new Texture(Gdx.files.internal("Buttons/Scissor.png"));
        vs = "";

        // MiniGame2
        chest = new TextureAtlas(Gdx.files.internal("Buttons/Chest.pack"));

        // Animation
        start = new Animator(16, 1, "Effect/Start.png", 0.15f, false);
        startGIF = start.getWalkAnimation();
        current0 = start.getCurrentFrame();
        stateTime0 = 0f;

        myPlayer = new Animator(4, 1, "Character/FullBody.png", 0.3f, false);
        myPlayerGIF = myPlayer.getWalkAnimation();
        current1 = myPlayer.getCurrentFrame();
        stateTime1 = 0f;

        pc = new Animator(4, 1, "Character/Cactus.png", 0.3f, false);
        npcGIF = pc.getWalkAnimation();
        current2 = pc.getCurrentFrame();
        stateTime2 = 0f;

        win = new Animator(16, 1, "Effect/Win.png", 0.07f, false);
        winGIF = win.getWalkAnimation();
        current3 = win.getCurrentFrame();
        stateTime3 = 0f;

        lose = new Animator(16, 1, "Effect/Lose.png", 0.07f, false);
        loseGIF = lose.getWalkAnimation();
        current4 = lose.getCurrentFrame();
        stateTime4 = 0f;

        draw = new Animator(16, 1, "Effect/Draw.png", 0.07f, false);
        drawGIF = draw.getWalkAnimation();
        current5 = draw.getCurrentFrame();
        stateTime5 = 0f;

        player = new Animator(10, 1, "Character/PlayerRun.png", 0.07f, false);
        playerGIF = player.getWalkAnimation();
        currentPlayer = player.getCurrentFrame();
        stateTime_Player = 0f;

        gameOver = new Animator(10, 20, "Background/GameOver.png", 0.04f, false);
        gameOverGIF = gameOver.getWalkAnimation();
        currentOver = gameOver.getCurrentFrame();
        stateTimeOver = 0f;

        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
