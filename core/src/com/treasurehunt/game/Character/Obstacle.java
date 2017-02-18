package com.treasurehunt.game.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.treasurehunt.game.Tools.Animator;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 7/11/2559.
 */
public class Obstacle extends Actor {
    private TreasureHunt game;
    private BirdFly bird;
    private Knife knife;
    private Knife spear;

    private Animator obs;
    private Animation obsGIF;
    private TextureRegion current;
    private float stateTime;

    private Texture texture;
    private Rectangle rect;
    private int currentStage;

    private int number;
    private boolean isAnimation;
    private int speed;

    public Obstacle(TreasureHunt gam, int stage) {
        super();
        game = gam;
        currentStage = stage;
        rect = new Rectangle();

        if (currentStage == 1) {
            number = (int)(Math.random()*4);
            texture = game.obstacle.get(number);
        }
        else if (currentStage == 2) {
            number = (int) (Math.random()*3);
            texture = game.obstacle2.get(number);
            if (number == 2) {
                knife = new Knife(game, texture, 500);
                knife.setPosition(1280, 600);
                knife.setSize(150, 600);
                MoveToAction ma1 = new MoveToAction();
                ma1.setPosition(1000, 170);
                ma1.setDuration(0.25f);
                knife.addAction(ma1);
            }
        }
        else {
            number = (int)(Math.random()*3);
            texture = game.obstacle3.get(number);
            if (number == 2) {
                spear = new Knife(game, texture, 500);
                spear.setPosition(1280, 600);
                spear.setSize(150, 800);
                MoveToAction ma2 = new MoveToAction();
                ma2.setPosition(1000, 170);
                ma2.setDuration(0.25f);
                spear.addAction(ma2);
            }
        }

        setBounds(getX(), getY(), getWidth(), getHeight());
        rect.setPosition(getX(), getY());
        rect.setSize(getWidth(), getHeight());

        isAnimation = false;

    }

    public Obstacle(TreasureHunt gam, int stage, int coll, int row, String directory, float speed) {
        super();
        game = gam;
        currentStage = stage;
        rect = new Rectangle();

        if (currentStage == 1) {
            number = (int)(1+Math.random()*2);
            bird = new BirdFly(game, coll, row, game.birdAtlas.findRegion("Bird"+number), speed);
            bird.setPosition(1280, 475);
            bird.setSize(100, 50);

            MoveToAction ma1 = new MoveToAction();
            ma1.setPosition(500, 180);
            ma1.setDuration(1f);
            bird.addAction(ma1);

        }
        else if (currentStage == 2 || currentStage == 3) {
            obs = new Animator(coll, row, "Obstacle/"+directory+".png", speed, false);
            obsGIF = obs.getWalkAnimation();
            current = obs.getCurrentFrame();
            stateTime = 0f;

            stateTime += Gdx.graphics.getDeltaTime();
            current = obsGIF.getKeyFrame(stateTime, true);
            number = 3;
        }

        setBounds(getX(), getY(), getWidth(), getHeight());

        rect.setPosition(getX(), getY());
        rect.setSize(getWidth(), getHeight());

        isAnimation = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        switch (currentStage) {
            case 1:
                if (isAnimation) { bird.draw(batch, parentAlpha); }
                else {
                    switch (number) {
                        case 0: batch.draw(texture, getX(), getY(), getWidth(), getHeight()); break;
                        case 1: batch.draw(texture, getX(), getY(), getWidth(), getHeight()); break;
                        case 2: batch.draw(texture, getX(), getY(), getWidth()-10, getHeight()-25); break;
                        case 3: batch.draw(texture, getX(), getY(), getWidth()-50, getHeight()-100); break;
                    }
                }
            break;

            case 2: switch (number) {
                case 0: batch.draw(texture, getX(), getY(), getWidth(), getHeight()); break;
                case 1: batch.draw(texture, getX(), getY(), getWidth(), getHeight()); break;
                case 2: knife.draw(batch, parentAlpha); break;
                case 3: batch.draw(current, getX(), getY(), getWidth()+50, getHeight()+50); break;
            } break;

            case 3: switch (number) {
                case 0: batch.draw(texture, getX(), getY()-10, getWidth()+30, getHeight()); break;
                case 1: batch.draw(texture, getX(), getY(), getWidth(), getHeight()); break;
                case 2: spear.draw(batch, parentAlpha); break;
                case 3: batch.draw(current, getX(), getY(), getWidth(), getHeight()); break;
            } break;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        setX(getX() - speed*delta);

        if (currentStage == 1) {
            if (isAnimation) {
                bird.act(delta);
                rect.setPosition(bird.getX(), bird.getY());
            }
            else {
                switch (number) {
                    case 0: rect.setSize(getWidth()-10, getHeight()); break;
                    case 1: rect.setSize(getWidth()-10, getHeight()); break;
                    case 2: rect.setSize(getWidth()-10, getHeight()-25); break;
                    case 3: rect.setSize(getWidth()-60, getHeight()-100); break;
                }
                rect.setPosition(getX(), getY());
            }

        }

        else if (currentStage == 2) {
            if (isAnimation) {
                stateTime += Gdx.graphics.getDeltaTime();
                current = obsGIF.getKeyFrame(stateTime, true);
                rect.setPosition(getX()+30, getY()+40);
                rect.setSize(getWidth()-20, getHeight());
            } else {
                if (number == 2) {
                    knife.act(delta);
                    rect.setPosition(knife.getX(), knife.getY());
                    rect.setSize(knife.getWidth()-120, knife.getHeight());
                }
                else {
                    rect.setPosition(getX(), getY());
                }

            }
        }
        else {
            if (isAnimation) {
                stateTime += Gdx.graphics.getDeltaTime();
                current = obsGIF.getKeyFrame(stateTime, true);
                rect.setSize(getWidth()-50, getHeight()-50);
                rect.setPosition(getX()+50, getY());
            } else {
                if (number == 2) {
                    spear.act(delta);
                    rect.setPosition(spear.getX(), spear.getY());
                    rect.setSize(spear.getWidth(), spear.getHeight());
                }
                else {
                    rect.setPosition(getX(), getY()-20);
                }
            }
        }

        if (getX() < -1000) {
            this.remove();
        }
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        rect.setSize(width-10, height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        rect.setPosition(x, y);
    }

    @Override
    public boolean remove() {
        if (obs != null) {
            obs.dispose();
        }
        return super.remove();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}