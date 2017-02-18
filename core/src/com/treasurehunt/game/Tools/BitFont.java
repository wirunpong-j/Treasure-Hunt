package com.treasurehunt.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Bell KunG on 16/10/2559.
 */
public class BitFont extends Actor {
    private BitmapFont text;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private String stuff;
    public BitFont(String s, Color color, int size, String directory) {
        super();
        text = new BitmapFont();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/"+directory+".ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        text = generator.generateFont(parameter);
        text.setColor(color);
        stuff = s;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        text.draw(batch, stuff, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }
}
