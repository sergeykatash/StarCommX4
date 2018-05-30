package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullets {
    Texture imgBullet;
    static  Bullet[] bullets;

    class Bullet{
        Vector2 position;
        float speed;
        boolean active;

        public Bullet(){
            position = new Vector2();
            speed = 14f;
            active = false;
        }

        public void deactivate(){
            active = false;
        }

        public void activate(float x, float y){
            position.set(x , y);
            active = true;
        }

        public void update(){
            position.x += speed;
            if (position.x > Gdx.graphics.getWidth()){
                deactivate();
            }
        }
    }

    public Bullets(){
        imgBullet = new Texture("bukket20.tga");
        bullets = new Bullet[100];
        for (int i = 0; i < bullets.length; i++){
            bullets[i] = new Bullet();
        }
    }

    public void render(SpriteBatch batch){
        for (int i = 0; i < bullets.length; i++){
            if (bullets[i].active){
                batch.draw(imgBullet,
                        bullets[i].position.x - imgBullet.getWidth()/2,
                        bullets[i].position.y - imgBullet.getHeight()/2);
            }
        }
    }

    public void update(){
        for (int i = 0; i < bullets.length; i++){
            if (bullets[i].active){
                bullets[i].update();
                for (int j = 0; j < Asteroids.asteroids.length; j++){
                    if (Asteroids.asteroids[j].hitBox.contains(bullets[i].position)){
                        Asteroids.asteroids[j].recreate();
                        bullets[i].deactivate();
                    }
                }
            }
        }
    }
}
