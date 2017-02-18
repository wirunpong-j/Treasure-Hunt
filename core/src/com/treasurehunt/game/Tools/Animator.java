package com.treasurehunt.game.Tools;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Bell KunG on 1/10/2559.
 */
public class Animator implements ApplicationListener {

    private static int FRAME_COLS;
    private static int FRAME_ROWS;

    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    SpriteBatch spriteBatch;
    TextureRegion currentFrame;

    public Animator(int col, int row, Texture txt, float speed, boolean flip) {
        this.FRAME_COLS = col;
        this.FRAME_ROWS = row;
        walkSheet = txt;
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                tmp[i][j].flip(flip, false);
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(speed, walkFrames);
        spriteBatch = new SpriteBatch();
    }

    public Animator(int col, int row, String directory, float speed, boolean flip) {
        this.FRAME_COLS = col;
        this.FRAME_ROWS = row;
        walkSheet = new Texture(Gdx.files.internal(directory));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                tmp[i][j].flip(flip, false);
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(speed, walkFrames);
        spriteBatch = new SpriteBatch();
    }

    public Animator(int col, int row, TextureRegion tr, float speed, boolean flip) {
        this.FRAME_COLS = col;
        this.FRAME_ROWS = row;
        TextureRegion[][] tmp = tr.split(tr.getRegionWidth()/FRAME_COLS, tr.getRegionHeight()/FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                tmp[i][j].flip(flip, false);
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(speed, walkFrames);
        spriteBatch = new SpriteBatch();
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        walkSheet.dispose();
    }


}
