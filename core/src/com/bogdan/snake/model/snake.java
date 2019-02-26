package com.bogdan.snake.model;

import com.badlogic.gdx.graphics.Texture;

public class snake extends GameObject{
    public snake(int x, int y, int width, int height, Texture shakeImage) {
        super(x, y, width, height, shakeImage);
    }

    public void move(byte direction, int step){
        switch (direction){
            case 1: frameObj.x -= step; break;
            case 2: frameObj.y += step; break;
            case 3: frameObj.x += step; break;
            case 4: frameObj.y -= step; break;
        }
    }

    public int getX(){ return (int)this.frameObj.getX(); }
    public int getY(){ return (int)this.frameObj.getY(); }
}
