package com.bogdan.snake.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
    protected Rectangle frameObj;
    protected Sprite object;

    public GameObject(int x, int y, int width, int height, Texture shakeImage){
        frameObj = new Rectangle(x, y, width, height);
        object = new Sprite(shakeImage);
    }

    public void draw(SpriteBatch shell){
        object.setBounds(frameObj.x, frameObj.y, frameObj.width, frameObj.height);
        object.draw(shell);
    }

    public void resetPosition(int x, int y){ frameObj.x = x; frameObj.y = y; }

    public boolean checkСontact(int centerX, int cenetrY){
        if(centerX == getCenterX()) if(cenetrY == getCenterY()) return true;
        return false;
    }

    public int getCenterX(){ return (int)((frameObj.getX() + frameObj.getHeight()) / 2); }
    public int getCenterY(){ return (int)((frameObj.getY() + frameObj.getHeight()) / 2); }
}
