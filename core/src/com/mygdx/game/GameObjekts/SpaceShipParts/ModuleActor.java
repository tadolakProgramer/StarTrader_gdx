package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class ModuleActor extends Actor {

    public TextureRegion region;

    public final static String FILE_CARGO_ATLAS = "cargo.atlas";
    public TextureAtlas textureAtlas;

    public ModuleActor(final MyGdxGame game, float scale) {
        super();
        textureAtlas = new TextureAtlas(FILE_CARGO_ATLAS);
        region = new TextureRegion();

        setWidth(textureAtlas.findRegion("CargoFuel").packedWidth);
        setHeight(textureAtlas.findRegion("CargoFuel").packedHeight);
        region.setRegion(textureAtlas.findRegion("CargoFuel"));


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
