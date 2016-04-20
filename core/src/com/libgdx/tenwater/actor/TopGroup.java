package com.libgdx.tenwater.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;

public class TopGroup extends BaseGroup {

	//  top level 和 current level
	Image levelImg;
	Label topLevel, currentLevel;
	
	public TopGroup(TenWaterGame game) {
		super(game);
		init();
	}
	
	private void init() {
		
		
		levelImg = new Image(AssetsManager.assetsManager.assetsBtn.levelBtnTxt);
		// 设置的坐标是相对于Group来说的
		levelImg.setX(getGame().VIRTUAL_WORLD_WIDTH >> 1 - 30);
//		设置levelImage为半透明
//		levelImg.getColor().a = 0.5f;
		
		
		// 设置Group的大小
		setSize(getGame().VIRTUAL_WORLD_WIDTH, levelImg.getHeight());
				
		Label.LabelStyle style = new Label.LabelStyle();
		style.font = AssetsManager.assetsManager.assetsfont.defaultFont;
		
		currentLevel = new Label("current", style);
		currentLevel.setX(levelImg.getX() + levelImg.getWidth() + 30);
		topLevel = new Label("top", style);
		topLevel.setPosition(currentLevel.getX(), currentLevel.getY() + currentLevel.getHeight());
		
		addActor(levelImg);
		addActor(currentLevel);
		addActor(topLevel);
	}
	
	public void setTopLevel(String text) {
		topLevel.setText(text);
	}
	
	public void setCurrentLevel(String text) {
		currentLevel.setText(text);
	}
}
