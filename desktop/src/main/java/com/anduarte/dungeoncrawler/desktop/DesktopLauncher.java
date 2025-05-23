package com.anduarte.dungeoncrawler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anduarte.dungeoncrawler.Main;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Dungeon Crawler";
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new Main(), config);
    }
}
