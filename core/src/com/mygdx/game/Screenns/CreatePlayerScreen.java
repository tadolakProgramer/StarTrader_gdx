package com.mygdx.game.Screenns;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.MyGdxGame;

public class CreatePlayerScreen extends AbstractScreen {

    protected Skin skin;
    private Table tablePlayer;

    private String playerName;
    private double money;

    public CreatePlayerScreen(final MyGdxGame game) {
        super(game);
        init();
    }

    private void init(){
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        createTablePlayer();
    }

    private void createTablePlayer() {

        tablePlayer = new Table();

        tablePlayer.bottom().center().pad(10f);
        //tableCrows.debug();
        tablePlayer.setFillParent(false);

        tablePlayer.row();
        tablePlayer.add(new Label("Your Name Captein:", skin));
        tablePlayer.add(new TextField("",skin));

        stage.addActor(tablePlayer);

    }

    @Override
    public void render(float delta) {

            super.render(delta);
            update();
            spriteBatch.begin();
            stage.draw();
            spriteBatch.end();
    }

    private void update() {
        stage.act();
    }


}
