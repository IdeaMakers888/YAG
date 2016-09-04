package we.can.change.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import we.can.change.YetAnotherGame;

public class MainScreen implements Screen {
	
	private ImageButton imageButton;
	private Sprite buttonSprite;
	private SpriteBatch spriteBatch = new SpriteBatch();
	private TextureAtlas textureAtlas;
	private Stage stage;
	private final YetAnotherGame game;
	
	public MainScreen(YetAnotherGame game){
		super();
		this.game = game;
	}
	
	@Override
	public void show() {
		stage = new Stage();
		
		textureAtlas = new TextureAtlas("img/pack.txt");
		imageButton = new ImageButton(new SpriteDrawable(textureAtlas.createSprite("button_down")), new SpriteDrawable(textureAtlas.createSprite("button_up")));
		imageButton.setPosition( (Gdx.graphics.getWidth()/2)-imageButton.getWidth()/2, (Gdx.graphics.getHeight()/2)-imageButton.getHeight()/2);
		imageButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameLevelOne(game, game.getCamera()));
			}
		});
//		spriteBatch.begin();
//		imageButton.draw(spriteBatch, 1f);
//		spriteBatch.end();
		stage.addActor(imageButton);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.gl.glViewport(0, 0, width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		this.dispose();
		spriteBatch.dispose();
	}

}
