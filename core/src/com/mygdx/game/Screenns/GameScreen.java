package com.mygdx.game.Screenns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.GameObjekts.SpaceObjekt.Planet;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.Helper.ReadXML;
import com.mygdx.game.MyGdxGame;
import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;


public class GameScreen extends AbstractScreen implements InputProcessor {


    public List<Planet> planets = new ArrayList<Planet>();
    public List<Planet> viewPlanets = new ArrayList<>();
    public SpaceShipPlayer spaceShipPlayer;
    private Planet planet;

    private Hud hud;
    private Background background;
    private OrthographicCamera backgroundCam;
    public InputMultiplexer multiplexer;



    public GameScreen(final MyGdxGame game) {
        super(game);
        multiplexer = new InputMultiplexer(this, stage);
        Gdx.input.setInputProcessor(multiplexer);
        init();
    }
    private void init() {
        backgroundInit();
        initSpaceShipPlayer();
        initHUD();
        spaceInit();
    }

    private void backgroundInit() {
        backgroundCam = new OrthographicCamera();
        backgroundCam.setToOrtho(false, MyGdxGame.GAME_WIDTH, MyGdxGame.GAME_HEIGHT);
        background = new Background();
    }

    private void spaceInit(){
        //CreateXmlFile.crateSpace(game);
        ReadXML.readPlanets(game, this, hud);
        for (int i = 0; i<=planets.size()-1; i++){
            stage.addActor(planets.get(i));
        }

        stage.addActor(spaceShipPlayer);
    }

    private void initHUD() {
        hud = new Hud(this, spriteBatch);
    }

    private void initSpaceShipPlayer() {
        spaceShipPlayer = new SpaceShipPlayer(game, this);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if ((camera.position.x - Gdx.input.getDeltaX() > GAME_WIDTH / 2) || (camera.position.x + Gdx.input.getDeltaX() < 20000)){
            camera.translate(-Gdx.input.getDeltaX(), 0);
            backgroundCam.translate(-Gdx.input.getDeltaX()/4, 0);
                    }
        if ((camera.position.y + Gdx.input.getDeltaY() < GAME_HEIGHT / 2) || (camera.position.y + Gdx.input.getDeltaY() < 20000)) {
            camera.translate(0, Gdx.input.getDeltaY());
            backgroundCam.translate(0, Gdx.input.getDeltaY()/4);
            }

                   return true;
        }



    @Override
    public void render(float delta){
        super.render(delta);
        //System.out.println("KLIK: " + delta);
        spriteBatch.setProjectionMatrix(hud.stage.getCamera().combined);

        background.render(backgroundCam);

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

        hud.stage.draw();

        update(delta);
        }

        private void setViewPlanets(){

            float x1 = camera.position.x;
            float y1 = camera.position.y;
            float w = GAME_WIDTH;
            float h = GAME_HEIGHT;

            for (int i=0; i<planets.size(); i++){
                if ((planets.get(i).getPositionCX() - planets.get(i).getActualWidth()/2 >= x1 + w/2) ||
                        (planets.get(i).getPositionCX() + planets.get(i).getActualWidth()/2 <= x1 - w/2) ||
                        (planets.get(i).getPositionCY() - planets.get(i).getActualHight()/2 >= y1 + h/2) ||
                        (planets.get(i).getPositionCY() + planets.get(i).getActualHight()/2 <= y1 - h/2)
                        )
                {
                    planets.get(i).setVisible(false);
                    //viewPlanets.add(planets.get(i));
                }
                else{
                    planets.get(i).setVisible(true);
                    //viewPlanets.remove(planets.get(i));
                    }
                }
            }


    private void update(float delta) {

        backgroundCam.update();
        hud.update(delta);
        stage.act();
        setViewPlanets();

        //System.out.println("Cam_pos: "+x1+"  "+y1);
        }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);
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
