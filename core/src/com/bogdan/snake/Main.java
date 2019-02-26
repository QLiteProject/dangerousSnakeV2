package com.bogdan.snake;

import com.badlogic.gdx.Game;
import com.bogdan.snake.view.GameScreen;

public class Main extends Game {
	@Override
	public void create() { setScreen(new GameScreen()); }
}
