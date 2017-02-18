package com.treasurehunt.game.Tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Bell KunG on 3/11/2559.
 */
public class ButtonIMG extends Actor {
    private TextButton textButton;
    private BitmapFont font;
    private TextButton.TextButtonStyle style;
    private TextButton.TextButtonStyle style2;
    private Skin skin;

    public ButtonIMG(TextureAtlas tr, String up, String down) {
        super();
        skin = new Skin();
        skin.addRegions(tr);

        font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable(down);
        style.down = skin.getDrawable(down);
        style.font = font;

        style2 = new TextButton.TextButtonStyle();
        style2.up = skin.getDrawable(up);
        style2.down = skin.getDrawable(up);
        style2.font = font;

        textButton = new TextButton("", style);

        setBounds(getX(), getY(), getWidth(), getHeight());

        textButton.setSize(getWidth(), getHeight());
        textButton.setPosition(getX(), getY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        textButton.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public boolean remove() {
        return super.remove();

    }

    public TextButton getTextButton() {
        return textButton;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        textButton.setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        textButton.setPosition(x, y);
    }

    public TextButton.TextButtonStyle getStyle() {
        return style;
    }

    public TextButton.TextButtonStyle getStyle2() {
        return style2;
    }
}
