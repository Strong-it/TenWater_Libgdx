package com.libgdx.tenwater;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.tenwater.game.LoadingScreen;

public class TenWaterGame extends Game {

	private final String TAG = TenWaterGame.class.getSimpleName();

	// 对于一些游戏属性，整个游戏都会用到，建议在Game类中初始化
	public SpriteBatch batch;
	public Viewport viewport;
	public Camera camera;
	public AssetManager assetManager;

	public final int VIRTUAL_WORLD_WIDTH = 480;
	public final int VIRTUAL_WORLD_HEIGHT = 640;

	@Override
	public void create() {
		Gdx.app.log(TAG, "create");
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		viewport = new FitViewport(VIRTUAL_WORLD_WIDTH, VIRTUAL_WORLD_HEIGHT,
				camera);
		viewport.apply(true);

		assetManager = new AssetManager();

		LoadingScreen loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

    @Override
    public void dispose() {
        super.dispose();
        Gdx.app.log(TAG, "dispose");
        batch.dispose();
        assetManager.dispose();
    }
    
    @Override
    public void pause() {
        super.pause();
    }
    
    @Override
    public void resume() {
        super.resume();
    }
    
    @Override
    public void render() {
        super.render();
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
