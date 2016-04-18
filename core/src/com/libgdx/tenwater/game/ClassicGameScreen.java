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

public class ClassicGameScreen extends AbstractBaseScreen {

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
    private Image bgImg, gridImg;
    
    public ClassicGameScreen() { }
    
    public ClassicGameScreen(TenWaterGame game) {
        super(game);
    }

    @Override
    public void show() {
        
        initGameData();
    }
    
    private void initGameData() {
    	stage = new Stage(game.viewport, game.batch);
    	stage.setDebugAll(game.debug);
        Gdx.input.setInputProcessor(stage);
        
        bgImg = new Image(AssetsManager.assetsManager.assetsBg.game_bgTxt);
        bgImg.setFillParent(true);
        
        gridImg = new Image(AssetsManager.assetsManager.assetsBg.cellTxt);
        originX = game.VIRTUAL_WORLD_WIDTH - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() >> 1;
        originY = (game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1) + 20 ;
        gridImg.setPosition(originX, originY);
        
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
       
       TopGroup topGroupg = new TopGroup(game);
       
       stage.addActor(bgImg);
       stage.addActor(gridImg);
       stage.addActor(backBtn);
       stage.addActor(resetBtn);
       stage.addActor(topGroupg);
       
       for (int i = 0; i < testImage.length; i++) {
           testImage[i] = new Image(AssetsManager.assetsManager.assetsWater.dropWaterTxt[0]);
       }
       
      
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        stage.act(Math.min(delta, 1/60f));
        stage.draw();
        
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        super.hide();
    }

}
