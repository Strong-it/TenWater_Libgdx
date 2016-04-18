package com.libgdx.tenwater.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;

public class TopGroup extends Group {

	TenWaterGame game;
	
	//  top level ºÍ current level
	Image levelImg;
	Label topLevel, currentLevel;
	
	public TopGroup(TenWaterGame game) {
		this.game = game;
		init();
	}
	
	private void init() {
		levelImg = new Image(AssetsManager.assetsManager.assetsBtn.levelBtnTxt);
		levelImg.setPosition(game.VIRTUAL_WORLD_WIDTH >> 1, 
                game.VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBtn.levelBtnTxt.getRegionHeight() - 20f);
		
		Label.LabelStyle style = new Label.LabelStyle();
		style.font = AssetsManager.assetsManager.assetsfont.defaultFont;
		
		topLevel = new Label("top", style);
		currentLevel = new Label("current", style);
		
		addActor(levelImg);
	}
	
	public void setTopLevel(String text) {
		topLevel.setText(text);
	}
	
	public void setCurrentLevel(String text) {
		currentLevel.setText(text);
	}
}
