package com.libgdx.tenwater.actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.game.MenuScreen;
import com.libgdx.tenwater.utils.AssetsManager;

public class BottomGroup extends BaseGroup{

	private ImageButton backBtn, resetBtn;
	
	public BottomGroup(TenWaterGame game) {
		super(game);
		init();
	}

	private void init() {
		setSize(getGame().VIRTUAL_WORLD_WIDTH, getGame().VIRTUAL_WORLD_HEIGHT);
		
		backBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.backBtnTxt[0]), 
                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.backBtnTxt[1]));
        backBtn.setX(20f);
        backBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                getGame().setScreen(new MenuScreen(getGame()));
            }
            
        });
        
       resetBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.resetBtnTxt));
       resetBtn.setX(getGame().VIRTUAL_WORLD_WIDTH - resetBtn.getWidth() - 20f);
       resetBtn.addListener(new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			// 处理重置按钮
			System.out.println("press reset button");
		}
    	   
       });
       
       addActor(backBtn);
       addActor(resetBtn);
	}
}
