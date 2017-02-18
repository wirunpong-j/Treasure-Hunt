package com.treasurehunt.game.Stage1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.treasurehunt.game.Tools.ButtonIMG;
import com.treasurehunt.game.TreasureHunt;

public class MiniGame2 extends Actor{
    private TreasureHunt game;
    private ButtonIMG rock, paper, scissor;
    private Stage stage;
    private BitmapFont font;
    private Sprite myRPS, npcRPS;
    private TextureRegion gift;

    private int player, npc;
    private long time;
    private boolean isStart, end, isDead;

    public MiniGame2(TreasureHunt gam) {
        super();
        game = gam;
        game.vs = "";

        stage = new Stage();
        font = new BitmapFont();

        isDead = false;
        isStart = false;
        end = false;

        myRPS = new Sprite(game.r);
        myRPS.setPosition(400, 400);
        myRPS.setSize(150, 150);

        npcRPS = new Sprite(game.r);
        npcRPS.setPosition(750, 400);
        npcRPS.setSize(150, 150);

        rock = new ButtonIMG(game.packRPS, "Rock1", "Rock2");
        rock.setPosition(400, 200);
        rock.setSize(100, 100);
        rock.getTextButton().setStyle(rock.getStyle2());
        rock.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 1;
                time = TimeUtils.millis()+2000;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                rock.getTextButton().setStyle(rock.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                rock.getTextButton().setStyle(rock.getStyle2());
            }
        });

        paper = new ButtonIMG(game.packRPS, "Paper1", "Paper2");
        paper.setPosition(600, 200);
        paper.setSize(100, 100);
        paper.getTextButton().setStyle(paper.getStyle2());
        paper.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 2;
                time = TimeUtils.millis()+2000;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                paper.getTextButton().setStyle(paper.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                paper.getTextButton().setStyle(paper.getStyle2());
            }
        });

        scissor = new ButtonIMG(game.packRPS, "Scissor1", "Scissor2");
        scissor.setPosition(800, 200);
        scissor.setSize(100, 100);
        scissor.getTextButton().setStyle(scissor.getStyle2());
        scissor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 3;
                time = TimeUtils.millis()+2000;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                scissor.getTextButton().setStyle(scissor.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                scissor.getTextButton().setStyle(scissor.getStyle2());
            }
        });
        npc = (int)(1+Math.random()*3);

        stage.addActor(rock);
        stage.addActor(paper);
        stage.addActor(scissor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stage.draw();

        batch.begin();
        if (!isStart) {
            batch.draw(game.current0, 450, 300, 470, 282);
            batch.draw(game.current1, 200, 150, 180, 450);
            batch.draw(game.current2, 900, 150, 350, 450);
        }
        else {
            batch.draw(game.current1, 200, 150, 180, 450);
            batch.draw(game.current2, 900, 150, 350, 450);
            myRPS.draw(batch);
            npcRPS.draw(batch);
            if (game.vs.equals("WIN")) {
                batch.draw(game.current3, 500, 300, 314, 345);
                batch.draw(gift, 550, 500, 200, 150);
            }
            else if (game.vs.equals("LOSE")) {
                batch.draw(game.current4, 450, 350, 379, 189);
                batch.draw(gift, 550, 500, 200, 150);
            }
            else if (game.vs.equals("DRAW")) {
                batch.draw(game.current5, 490, 300, 350, 318);
            }
        }

        batch.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (game.startGIF.isAnimationFinished(game.stateTime0)) {
            isStart = true;
            game.stateTime0 = 0;
            game.current0 = game.startGIF.getKeyFrame(game.stateTime0, true);
        }
        if (!isStart) {
            game.stateTime0 += Gdx.graphics.getDeltaTime();
            game.current0 = game.startGIF.getKeyFrame(game.stateTime0, true);

            game.stateTime1 += Gdx.graphics.getDeltaTime();
            game.current1 = game.myPlayerGIF.getKeyFrame(game.stateTime1, true);

            game.stateTime2 += Gdx.graphics.getDeltaTime();
            game.current2 = game.npcGIF.getKeyFrame(game.stateTime2, true);
        }
        else {
            stage.act(delta);

            game.stateTime1 += Gdx.graphics.getDeltaTime();
            game.current1 = game.myPlayerGIF.getKeyFrame(game.stateTime1, true);

            game.stateTime2 += Gdx.graphics.getDeltaTime();
            game.current2 = game.npcGIF.getKeyFrame(game.stateTime2, true);

            switch (player) {
                case 1:
                    switch (npc) {
                        case 1: game.vs = "DRAW"; break;
                        case 2: game.vs = "LOSE"; break;
                        case 3: game.vs = "WIN"; break;
                    } myRPS.setTexture(game.r); break;
                case 2:
                    switch (npc) {
                        case 1: game.vs = "WIN"; break;
                        case 2: game.vs = "DRAW"; break;
                        case 3: game.vs = "LOSE"; break;
                    } myRPS.setTexture(game.p); break;
                case 3:
                    switch (npc) {
                        case 1: game.vs = "LOSE"; break;
                        case 2: game.vs = "WIN"; break;
                        case 3: game.vs = "DRAW"; break;
                    } myRPS.setTexture(game.s); break;
            }
            if (player != 0) {
                switch (npc) {
                    case 1: npcRPS.setTexture(game.r); break;
                    case 2: npcRPS.setTexture(game.p); break;
                    case 3: npcRPS.setTexture(game.s); break;
                }
                if (time < TimeUtils.millis()) {
                    isDead = true;
                }

                if (game.vs.equals("WIN")) {
                    game.stateTime3 += Gdx.graphics.getDeltaTime();
                    game.current3 = game.winGIF.getKeyFrame(game.stateTime3, true);
                    if (!end) {
                        game.stamp++;
                        gift = game.gachaAtlas.findRegion("Key");
                        end = true;
                    }

                }
                else if (game.vs.equals("LOSE")) {
                    game.stateTime4 += Gdx.graphics.getDeltaTime();
                    game.current4 = game.loseGIF.getKeyFrame(game.stateTime4, true);
                    if (!end) {
                        unluckyDraw();
                        end = true;
                    }
                }
                else if (game.vs.equals("DRAW")) {
                    game.stateTime5 += Gdx.graphics.getDeltaTime();
                    game.current5 = game.drawGIF.getKeyFrame(game.stateTime5, true);
                }
            }
            else {
                randomTexture(1);
                randomTexture(2);
            }
        }
    }
    private void randomTexture(int who) {
        int number = (int)(1+Math.random()*3);
        switch (who) {
            case 1: switch (number) {
                case 1: myRPS.setTexture(game.r); break;
                case 2: myRPS.setTexture(game.p); break;
                case 3: myRPS.setTexture(game.s); break;
            }
            case 2: switch (number) {
                case 1: npcRPS.setTexture(game.r); break;
                case 2: npcRPS.setTexture(game.p); break;
                case 3: npcRPS.setTexture(game.s); break;
            }
        }
    }

    private void unluckyDraw() {
        int num = (int)(Math.random()*2);
        switch (num) {
            case 0: gift = game.gachaAtlas.findRegion("Point10"); game.score -= 10; break;
            case 1: gift = game.gachaAtlas.findRegion("Heal100"); game.life.setValue(game.life.getValue()-100); break;
        }
    }

    @Override
    public boolean remove() {
        rock.remove();
        paper.remove();
        scissor.remove();
        stage.dispose();
        return super.remove();
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public boolean isDead() {
        return isDead;
    }

}
