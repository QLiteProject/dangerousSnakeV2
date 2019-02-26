package com.bogdan.snake.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.bogdan.snake.model.fruit;
import com.bogdan.snake.model.snake;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {
    private snake snakeHead;
    private fruit fruitObj;
    private ArrayList<snake> objBody = new ArrayList<snake>();
    private SpriteBatch batch;

    private float countFrame = 0;
    private int countScore = 0, maxScore = 30;
    private boolean geamplay = true;

    private int bodyH = 24, bodyW = 24;
    private int screenW = Gdx.graphics.getWidth(), screenH = Gdx.graphics.getHeight();

    private int positionX, positionY;

    private byte direction;

    private Random random = new Random();

    @Override
    public void show() {
        batch = new SpriteBatch();

        direction = (byte)(random.nextInt( 4 - 1) + 1);
        positionX = (random.nextInt((screenW / bodyW) - 1) + 1) * bodyW;
        positionY = (random.nextInt((screenH / bodyH) - 1) + 1) * bodyH;

        snakeHead = new snake(positionX, positionY, bodyW, bodyH, new Texture("assets/head.jpg"));

        positionX = (random.nextInt((screenW / bodyW) - 1) + 1) * bodyW;
        positionY = (random.nextInt((screenH / bodyH) - 1) + 1) * bodyH;

        fruitObj = new fruit(positionX, positionY, bodyW, bodyH, new Texture("assets/fruit.png"));

    }

    @Override
    public void render(float v) {
        int bufferX = 0, bufferX2 = 0, bufferY = 0, bufferY2 = 0;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
            for(int i = 0; i < objBody.size(); i++){ objBody.get(i).draw(batch); }
            snakeHead.draw(batch);

            if(geamplay != false){
                fruitObj.draw(batch);

                if (countFrame >= 0.05) {

                    for(int i = 0; i < objBody.size(); i++){
                        if(i == 0){
                            bufferX = objBody.get(i).getX(); bufferY = objBody.get(i).getY();
                            objBody.get(i).resetPosition(snakeHead.getX(), snakeHead.getY());
                        }else{
                            bufferX2 = objBody.get(i).getX(); bufferY2 = objBody.get(i).getY();

                            objBody.get(i).resetPosition(bufferX, bufferY);

                            bufferX = bufferX2; bufferY = bufferY2;
                        }
                    }

                    snakeHead.move(direction, (bodyH + bodyW) / 2);

                    if(snakeHead.getX() < 0)        snakeHead.resetPosition(screenW - bodyW, snakeHead.getY());
                    if(snakeHead.getX() == screenW) snakeHead.resetPosition(0, snakeHead.getY());
                    if(snakeHead.getY() < 0)        snakeHead.resetPosition(snakeHead.getX(), screenH - bodyH);
                    if(snakeHead.getY() == screenH) snakeHead.resetPosition(snakeHead.getX(), 0);

                    for (int i=0; i < objBody.size(); i++){ if(objBody.get(i).checkСontact(snakeHead.getCenterX(), snakeHead.getCenterY()))
                    {
                        geamplay = false;
                        JOptionPane.showMessageDialog(null, "Вы проиграли!");
                    } }

                    if (fruitObj.checkСontact(snakeHead.getCenterX(), snakeHead.getCenterY())) {
                        positionX = (random.nextInt((screenW / bodyW) - 1) + 1) * bodyW;
                        positionY = (random.nextInt((screenH / bodyH) - 1) + 1) * bodyH;

                        fruitObj.resetPosition(positionX, positionY);
                        objBody.add(new snake(snakeHead.getX(), snakeHead.getY(), bodyW, bodyH, new Texture("assets/body.jpg")));

                        countScore++;
                    }

                    if (countScore == maxScore) {
                        geamplay = false;
                        JOptionPane.showMessageDialog(null, "Игра окончена. Поздравляем вас!");
                    }

                    if(Gdx.input.isKeyPressed(29) && direction != 3) direction = 1; else
                    if(Gdx.input.isKeyPressed(51) && direction != 4) direction = 2; else
                    if(Gdx.input.isKeyPressed(32) && direction != 1) direction = 3; else
                    if(Gdx.input.isKeyPressed(47) && direction != 2) direction = 4;

                    countFrame = 0;
                } else {
                    countFrame += v;
                }
            }
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        snakeHead = null;
        fruitObj = null;
        objBody = null;
        batch = null;
        random = null;
    }
}
