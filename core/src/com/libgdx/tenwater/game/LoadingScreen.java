package com.libgdx.tenwater.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;
import com.libgdx.tenwater.utils.Resources;

public class LoadingScreen extends AbstractBaseScreen {

    public Texture backgroundImg, loading_LogoImg, progressBarImg, progressBarBaseImg;
    
    Vector2 logoPos, progressBarPos;
    
    public LoadingScreen(TenWaterGame game) {
        super(game);
        
        loadFlashResources();
        
        logoPos = new Vector2();
        logoPos.set(game.VIRTUAL_WORLD_WIDTH - loading_LogoImg.getWidth() >> 1, (game.VIRTUAL_WORLD_HEIGHT - loading_LogoImg.getHeight() >> 1) + 100);
        
        progressBarPos = new Vector2();
        progressBarPos.set(logoPos.x + 20, logoPos.y - loading_LogoImg.getHeight());
        
        AssetsManager.assetsManager.loadGameResources(game.assetManager);
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        
        game.batch.setProjectionMatrix(game.camera.combined);
        game. batch.begin();
        game.batch.draw(backgroundImg, 0, 0);
        game.batch.end();
        
        game.batch.begin();
        game.batch.draw(loading_LogoImg, logoPos.x, logoPos.y);
        game.batch.draw(progressBarBaseImg, progressBarPos.x, progressBarPos.y,
                progressBarBaseImg.getWidth() - 40, progressBarBaseImg.getHeight());
        game. batch.draw(progressBarImg, progressBarPos.x, progressBarPos.y, 
                progressBarImg.getWidth() * game.assetManager.getProgress() - 40, progressBarImg.getHeight());
        game.batch.end();
        
        if (game.assetManager.update()) {
            AssetsManager.assetsManager.init();
            game.setScreen(new MenuScreen(game));
        }
    }

    public void loadFlashResources() {
        
        game.assetManager.load(Resources.GameImage.loading_background, Texture.class);
        game.assetManager.load(Resources.GameImage.loading_logo, Texture.class);
        game.assetManager.load(Resources.GameImage.loading_progress_bar, Texture.class);
        game.assetManager.load(Resources.GameImage.loading_progress_bar_base, Texture.class);
        
        game.assetManager.finishLoading();
        backgroundImg = game.assetManager.get(Resources.GameImage.loading_background, Texture.class);
        loading_LogoImg = game.assetManager.get(Resources.GameImage.loading_logo, Texture.class);
        progressBarImg = game.assetManager.get(Resources.GameImage.loading_progress_bar, Texture.class);
        progressBarBaseImg = game.assetManager.get(Resources.GameImage.loading_progress_bar_base, Texture.class);
    }
}
