package com.mygdx.game.Screenns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;


public class GameScreen extends AbstractScreen implements InputProcessor {

    private Planet planet;

    private Hud hud;
    private Background background;

    public GameScreen(final MyGdxGame game) {
        super(game);

        planet = new Planet(game, 120, 300);
        planet.setTexture(new Texture("planet_10.png"));
        planet.setRotation(127f);
        planet.setSpaceObjectName("Planeta");
        background = new Background(game, camera);
        Gdx.input.setInputProcessor(this);
        init();
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if ((camera.position.x - Gdx.input.getDeltaX() < GAME_WIDTH / 2) || (camera.position.x + Gdx.input.getDeltaX() > 2000)){
            camera.translate(-Gdx.input.getDeltaX(), 0);
            background.moveX(-Gdx.input.getDeltaX());
        }
        if ((camera.position.y + Gdx.input.getDeltaY() < GAME_HEIGHT / 2) || (camera.position.y + Gdx.input.getDeltaY() > 2000)) {
            camera.translate(0, Gdx.input.getDeltaY());
            background.moveY(Gdx.input.getDeltaY());
        }

            return true;
        }


    private void init() {
        initSpaceShipPlayer();
        initLabelMoney();
    }

    private void initLabelMoney() {
        hud = new Hud(spriteBatch);
            }

    private void initSpaceShipPlayer() {

        stage.addActor(background);
        stage.addActor(game.spaceShipPlayer);
        stage.addActor(planet);

    }

    @Override
    public void render(float delta){
        super.render(delta);
        update(delta);
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

    }

    private void update(float delta) {
        hud.update(delta);
        stage.act();
        }



    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
