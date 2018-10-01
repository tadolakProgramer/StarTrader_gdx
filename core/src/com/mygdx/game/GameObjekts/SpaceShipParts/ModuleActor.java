package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.COKPIT;
import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.EMPTY;

public class ModuleActor extends Actor {

    private String textureName;

    public TextureRegion region;

    public final static String FILE_CARGO_ATLAS = "cargo.atlas";
    public TextureAtlas textureAtlas;

    public ModuleActor(final MyGdxGame game, ModuleType moduleType, int index, float scale) {
        super();
        textureAtlas = new TextureAtlas(FILE_CARGO_ATLAS);
        region = new TextureRegion();

            switch (moduleType) {
                case FUEL: {
                    textureName = "CargoFuel";
                    setTexture(textureName);
                    setScale(scale);
                    setPosition(349 + (index-1)*93.3f,270);
                    break;
                }
                case GAS: {
                    textureName = "CargoGrafen";
                    setTexture(textureName);
                    setScale(scale);
                    setPosition(349 + (index-1)*93.3f,270);
                    break;
                }
                case HOUSING_MODULE: {
                    textureName = "CargoHM";
                    setTexture(textureName);
                    setScale(scale);
                    setPosition(349 + (index-1)*93.3f,270);
                    break;
                }
                default:{
                    textureName = "CargoTitan";
                    setTexture(textureName);
                }

            }

    }

    private void setTexture(String textureName){
        setWidth(textureAtlas.findRegion(textureName).packedWidth);
        setHeight(textureAtlas.findRegion(textureName).packedHeight);
        region.setRegion(textureAtlas.findRegion(textureName));
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
