package com.treasurehunt.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.treasurehunt.game.TreasureHunt;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 768;
		config.resizable = false;
		config.samples = 2;
		config.vSyncEnabled = true;
		config.title = "Treasure Hunt";
		config.addIcon("icon.png", Files.FileType.Internal);
		//config.fullscreen = true;
		new LwjglApplication(new TreasureHunt(), config);
	}
}
