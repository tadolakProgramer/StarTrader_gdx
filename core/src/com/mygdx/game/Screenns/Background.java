package com.mygdx.game.Screenns;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public class Background extends Actor implements Disposable {


    private MyGdxGame game;
    public TextureRegion region;
    private Image space;
    private Texture texture;
    private Camera camera;

    public Background(final MyGdxGame game, Camera camera) {
        super();
        this.game = game;
        this.camera = camera;
        texture = new Texture("space.jpg");
        region = new TextureRegion();
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        region.setRegion(texture);
}

    public void moveX(float x){
        moveBy(x/2,0);
    }

    public void moveY (float y){
        moveBy(0, y/2);
    }

    public void draw(Batch batch, float parentAlpha){
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw( region, getX(), getY(), getOriginX(),
                    getOriginY(),
                    getWidth(), getHeight(), getScaleX(),
                    getScaleY(), getRotation() );
    }

    public void act(float dt) {
        super.act(dt);
    }

    @Override
    public void dispose() {

    }

}
