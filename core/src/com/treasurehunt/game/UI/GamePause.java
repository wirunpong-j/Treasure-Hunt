package com.treasurehunt.game.UI;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.treasurehunt.game.MainMenu;
import com.treasurehunt.game.Tools.BackGroundStage;
import com.treasurehunt.game.Tools.ButtonIMG;
import com.treasurehunt.game.TreasureHunt;

/**
 * Created by Bell KunG on 12/11/2559.
 */
public class GamePause extends Stage {
    private TreasureHunt game;
    private BackGroundStage pausePage;
    private ButtonIMG resumeButton, homeButton;
    private boolean isPause;
    private Screen screen;

    public GamePause(TreasureHunt gam, Screen sc) {
        super();
        game = gam;

        isPause = true;
        screen = sc;

        pausePage = new BackGroundStage(game, game.gamePause.findRegion("P1"), 0, false, true);
        pausePage.setPosition(200, 80);
        pausePage.setSize(800, 574);
        addActor(pausePage);

        resumeButton = new ButtonIMG(game.buttonAtlas, "P3-click", "P3-Unclick");
        resumeButton.setPosition(748, 55);
        resumeButton.setSize(190, 247);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resumeButton.getTextButton().setStyle(resumeButton.getStyle2());
                isPause = false;
                GamePause.this.dispose();
                super.clicked(event, x, y);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                resumeButton.getTextButton().setStyle(resumeButton.getStyle2());
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                resumeButton.getTextButton().setStyle(resumeButton.getStyle());
                super.exit(event, x, y, pointer, toActor);
            }
        });
        addActor(resumeButton);

        homeButton = new ButtonIMG(game.buttonAtlas, "P2-click", "P2-Unclick");
        homeButton.setPosition(308, 55);
        homeButton.setSize(449, 188);
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                homeButton.getTextButton().setStyle(homeButton.getStyle2());
                screen.dispose();
                GamePause.this.dispose();
                game.setScreen(new MainMenu(game));
                super.clicked(event, x, y);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                homeButton.getTextButton().setStyle(homeButton.getStyle2());
                super.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                homeButton.getTextButton().setStyle(homeButton.getStyle());
                super.exit(event, x, y, pointer, toActor);
            }
        });
        addActor(homeButton);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isPause() {
        return isPause;
    }

}
