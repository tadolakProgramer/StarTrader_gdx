package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class ModuleActor extends Actor {

    public TextureRegion region;

    public ModuleActor(final MyGdxGame game, float scale) {
        super();

        region = new TextureRegion();
        Texture texture1 = new Texture("SH_Element_HM.png");
        setWidth(texture1.getWidth());
        setHeight(texture1.getHeight());
        region.setRegion(texture1);
        setScale(scale);
        setPosition(349,270);
    }

    public void act(float dt){
        super.act(dt);
        //labelName.act(dt);
        update(dt);
    }

    public void update(float dt) {

    }

    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw(region, getX(), getY(), getOriginX(),
                    getOriginY(),
                    getWidth(), getHeight(), getScaleX(),
                    getScaleY(), getRotation());

    }
}
