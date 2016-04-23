package com.libgdx.tenwater.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.actor.BottomGroup;
import com.libgdx.tenwater.actor.MiddleGroup;
import com.libgdx.tenwater.actor.TopGroup;
import com.libgdx.tenwater.utils.AssetsManager;

public class ClassicGameScreen extends AbstractBaseScreen {
	private static final String TAG = ClassicGameScreen.class.getSimpleName();
	
    private Stage stage;
    private Image bgImg;
    
    TopGroup topGroup;
    BottomGroup bottomGroup;
    MiddleGroup middleGroup;
    
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
        
       // Top按钮 记录分数
       topGroup = new TopGroup(game);
       float topGroupY = game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBtn.levelBtnTxt.getRegionHeight() - 20f;
       topGroup.setY(topGroupY);
       
       bottomGroup = new BottomGroup(game);
       // 统一设置ButtomGroup里面Actor的Y坐标
       bottomGroup.setY(15f);
       Gdx.app.log(TAG, "ButtomGroup(x,y)=(" + bottomGroup.getX() +","+ bottomGroup.getY() + 
		   		")  ButtomGroup width=" + bottomGroup.getWidth() + " Height=" + bottomGroup.getHeight());
       
       // 游戏的中间部分，包括网格，水滴的
       middleGroup = new MiddleGroup(game);
//       为了方便获取坐标，将坐标放入MiddleGroup里面
//       float middleGroupX = game.VIRTUAL_WORLD_WIDTH / 2 - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() / 2;
//       float middleGroupY = (game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1) + 20;
//       middleGroup.setPosition(middleGroupX, middleGroupY);
//       Gdx.app.log(TAG, "gridImage X=" + middleGroup.getGridImage().getX() + "  gridY=" + middleGroup.getY());
       
       stage.addActor(bgImg);
       stage.addActor(middleGroup);
       stage.addActor(topGroup);
       stage.addActor(bottomGroup);
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
