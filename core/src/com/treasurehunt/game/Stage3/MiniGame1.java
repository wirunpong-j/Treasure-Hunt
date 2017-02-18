package com.treasurehunt.game.Stage3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.treasurehunt.game.Character.Boss;
import com.treasurehunt.game.Character.Player;
import com.treasurehunt.game.TreasureHunt;
import com.treasurehunt.game.UI.EnemyBar;
import com.treasurehunt.game.UI.HealtBar;
import com.treasurehunt.game.UI.Lighting;

/**
 * Created by Bell KunG on 3/11/2559.
 */
public class MiniGame1 {
    private TreasureHunt game;
    private Stage enemyStage, effect;
    private HealtBar healtBar;
    private Player player;
    private Boss boss;
    private EnemyBar enemyBar;
    private Lighting light, lightEnd;

    private boolean isBoss;

    public MiniGame1(TreasureHunt gam, HealtBar hb, Player play) {
        super();
        game = gam;
        healtBar = hb;
        player = play;

        enemyStage = new Stage();
        effect = new Stage();
    }

    public void spawnBoss() {
        if (!isBoss) {
            light = new Lighting(game, 25, 1, game.effectAtlas.findRegion("SpawnBoss"), 0.07f, false, true);
            light.setPosition(790, 100);
            light.setSize(427, 473);
            enemyStage.addActor(light);

            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    boss = new Boss(game, player, healtBar);
                    boss.setSize(427, 473);
                    boss.setPosition(790, 100);
                    enemyStage.addActor(boss);

                    enemyBar = new EnemyBar(game);
                    enemyBar.setPosition(900, 500);
                    enemyBar.setSize(300, 20);
                    enemyStage.addActor(enemyBar.getLife());
                    enemyStage.addActor(enemyBar);

                    isBoss = true;
                }
            },1);
        }
    }

    public void bossDead() {
        lightEnd = new Lighting(game, 10, 1, game.effectAtlas.findRegion("BossDead"), 0.1f, false, true);
        lightEnd.setSize(427, 473);
        lightEnd.setPosition(790, 100);
        enemyStage.addActor(lightEnd);
    }

    public EnemyBar getEnemyBar() {
        return enemyBar;
    }

    public Stage getEnemyStage() {
        return enemyStage;
    }

    public Stage getEffect() {
        return effect;
    }

    public Boss getBoss() {
        return boss;
    }
}
