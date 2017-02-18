package com.treasurehunt.game.Stage2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.treasurehunt.game.Tools.ButtonIMG;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.Lighting;

/**
 * Created by Bell KunG on 6/11/2559.
 */
public class MiniGame3 extends Actor{
    private TreasureHunt game;
    private Stage stage;
    private Lighting light;
    private TextureRegion show;
    private ButtonIMG chest1, chest2, chest3;

    private boolean isDead;
    private int test, click;

    public MiniGame3(TreasureHunt gam) {
        super();
        game = gam;

        stage = new Stage();

        isDead = false;
        test = 0;
        click = 0;

        chest1 = new ButtonIMG(game.chest, "Chest1-1", "Chest1-2");
        chest1.setPosition(250, 350);
        chest1.setSize(200, 150);
        chest1.getTextButton().setStyle(chest1.getStyle2());
        chest1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click = 1;
                test = 1;
                luckyDraw();
                light = new Lighting(game, 5, 1, game.effectAtlas.findRegion("OpenBox"), 0.2f, false, true);
                light.setPosition(175, 275);
                light.setSize(350, 300);
                stage.addActor(light);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                chest1.getTextButton().setStyle(chest1.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                chest1.getTextButton().setStyle(chest1.getStyle2());
            }
        });

        chest2 = new ButtonIMG(game.chest, "Chest2-1", "Chest2-2");
        chest2.setPosition(500, 350);
        chest2.setSize(200, 150);
        chest2.getTextButton().setStyle(chest2.getStyle2());
        chest2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click = 2;
                test = 1;
                luckyDraw();
                light = new Lighting(game, 5, 1, game.effectAtlas.findRegion("OpenBox"), 0.2f, false, true);
                light.setPosition(425, 275);
                light.setSize(350, 300);
                stage.addActor(light);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                chest2.getTextButton().setStyle(chest2.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                chest2.getTextButton().setStyle(chest2.getStyle2());
            }
        });

        chest3 = new ButtonIMG(game.chest, "Chest3-1", "Chest3-2");
        chest3.setPosition(750, 350);
        chest3.setSize(200, 150);
        chest3.getTextButton().setStyle(chest3.getStyle2());
        chest3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click = 3;
                test = 1;
                luckyDraw();
                light = new Lighting(game, 5, 1, game.effectAtlas.findRegion("OpenBox"), 0.2f, false, true);
                light.setPosition(675, 275);
                light.setSize(350, 300);
                stage.addActor(light);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                chest3.getTextButton().setStyle(chest3.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                chest3.getTextButton().setStyle(chest3.getStyle2());
            }
        });

        stage.addActor(chest1);
        stage.addActor(chest2);
        stage.addActor(chest3);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stage.draw();
        batch.begin();
        switch (click) {
            case 1: batch.draw(show, chest1.getX(), chest1.getY(), chest1.getWidth(), chest1.getHeight()); break;
            case 2: batch.draw(show, chest2.getX(), chest2.getY(), chest2.getWidth(), chest2.getHeight()); break;
            case 3: batch.draw(show, chest3.getX(), chest3.getY(), chest3.getWidth(), chest3.getHeight()); break;
        }
        batch.end();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stage.act();
        if (test == 1) {
            if (!light.isAlive()) {
                isDead = true;
            }
        }
    }

    private void luckyDraw() {
        int luckynum = (int)(Math.random()*4);
        if (luckynum == 0) {
            int rd = (int)(Math.random()*2);
            switch (rd) {
                case 0: game.score -= 10;
                    show = game.gachaAtlas.findRegion("Point10");
                    break;
                case 1: game.score += 100;
                    show = game.gachaAtlas.findRegion("Point100");
                    break;
            }
        }
        else if (luckynum == 1) {
            int rd = (int)(Math.random()*2);
            switch (rd) {
                case 0: game.life.setValue(game.life.getValue()-100);
                    show = game.gachaAtlas.findRegion("Heal100");
                    break;
                case 1: game.life.setValue(game.life.getValue()+500);
                    show = game.gachaAtlas.findRegion("Heal500");
                    break;
            }
        }
        else if (luckynum == 2 || luckynum == 3) {
            show = game.gachaAtlas.findRegion("Key");
            game.stamp++;
        }
    }

    @Override
    public boolean remove() {
        chest1.remove();
        chest2.remove();
        chest3.remove();
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
