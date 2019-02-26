package com.bogdan.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bogdan.snake.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480; config.height = 480; config.resizable = false; config.title = "dangerousSnake by QLiteProject";
		new LwjglApplication(new Main(), config);
	}
}
