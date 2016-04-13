package com.libgdx.tenwater.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;

public class ClassicGameMenu extends AbstractBaseScreen {

    private final int ROW_NUM = 6;
    private final int COL_NUM = 6;
    private final int TOTAL_NUM = ROW_NUM * COL_NUM;
    private final int OFFSET_X = 30;
    private final int OFFSET_Y = 30;
    private final int GRID_WIDTH = 52;
    private final int GRID_HEIGHT = 52;
    private final int NO_WATER = 0;
    
    private final int ROTATION_RIGHT = 0;
    private final int ROTATION_LEFT = 180;
    private final int ROTATION_DOWN = 90;
    private final int ROTATION_UP = -90;
    
    private int originX;
    private int originY;
    
    private Stage stage;
    private ImageButton backBtn, resetBtn;
    private Image[] testImage = new Image[TOTAL_NUM];
    WaterSprite waterSprite;
    
    public ClassicGameMenu(TenWaterGame game) {
        super(game);
    }

    @Override
    public void show() {
        
        stage = new Stage(game.viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        
        backBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.backBtnTxt[0]), 
                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.backBtnTxt[1]));
        backBtn.setPosition(20f, 15f);
        backBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
            
        });
        
       resetBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.resetBtnTxt));
       resetBtn.setPosition(game.VIRTUAL_WORLD_WIDTH - resetBtn.getWidth() - 20f, 15f);
       
       stage.addActor(backBtn);
       stage.addActor(resetBtn);
       
       for (int i = 0; i < testImage.length; i++) {
           testImage[i] = new Image(AssetsManager.assetsManager.assetsWater.dropWaterTxt[0]);
       }
       
       waterSprite = new WaterSprite();
       waterSprite.setPosition(100f, 100f);
       
       originX = game.VIRTUAL_WORLD_WIDTH - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() >> 1;
       originY = game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1;
    }
    
    public void onClickGrid() {
        
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(AssetsManager.assetsManager.assetsBg.game_bgTxt, 0, 0);
        game.batch.draw(AssetsManager.assetsManager.assetsBtn.levelBtnTxt, game.VIRTUAL_WORLD_WIDTH >> 1, 
                game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBtn.levelBtnTxt.getRegionHeight() - 20f);
        game.batch.draw(AssetsManager.assetsManager.assetsBg.cellTxt, originX, originY);
        game.batch.end();
        
        game.batch.begin();
        waterSprite.draw(game.batch, 1.0f);
        game.batch.end();
        
//        stage.act(Math.min(delta, 1/60f));
//        stage.draw();
        
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        super.hide();
    }

}
