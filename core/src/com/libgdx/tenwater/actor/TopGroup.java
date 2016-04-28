package com.libgdx.tenwater.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.actor.base.BaseGroup;
import com.libgdx.tenwater.utils.AssetsManager;

public class TopGroup extends BaseGroup {

	//  top level 和 current level
	Image levelImg;
	Label topLevel, currentLevel;
	static Label remainLabel;
	RemainWaterActor remainWaterActor;
	
	public TopGroup(TenWaterGame game) {
		super(game);
		init();
	}
	
	private void init() {
		remainWaterActor = new RemainWaterActor();
		remainWaterActor.setX(20f);
		
		levelImg = new Image(AssetsManager.assetsManager.assetsBtn.levelBtnTxt);
		// 设置的坐标是相对于Group来说的
		levelImg.setX(getGame().VIRTUAL_WORLD_WIDTH >> 1);
//		设置levelImage为半透明
//		levelImg.getColor().a = 0.5f;
		
		
		// 设置Group的大小
		setSize(getGame().VIRTUAL_WORLD_WIDTH, levelImg.getHeight());
				
		Label.LabelStyle style = new Label.LabelStyle();
		style.font = AssetsManager.assetsManager.assetsfont.defaultFont;
		
		currentLevel = new Label("current", style);
		currentLevel.setX(levelImg.getX() + levelImg.getWidth());
		topLevel = new Label("top", style);
		topLevel.setPosition(currentLevel.getX(), currentLevel.getY() + currentLevel.getHeight());
		remainLabel = new Label("" + RemainWaterActor.getWaterNum(), style);
		remainLabel.setX(remainWaterActor.getX() + remainWaterActor.getWidth() / 2 - remainLabel.getWidth() / 2);
		remainLabel.setY(remainWaterActor.getY() + remainWaterActor.getHeight() / 2 - remainLabel.getHeight() / 2);
		
		addActor(remainWaterActor);
		addActor(levelImg);
		addActor(currentLevel);
		addActor(topLevel);
		addActor(remainLabel);
	}
	
	public void setTopLevel(String text) {
		topLevel.setText(text);
	}
	
	public void setCurrentLevel(String text) {
		currentLevel.setText(text);
	}
	
	public static void setRemainWaterNum(int num) {
	    if (num  < 21) {
	        RemainWaterActor.setWaterNum(num);
	        remainLabel.setText(""+RemainWaterActor.getWaterNum());
        }
	}
	
	public static int getRemainWaterNum() {
	    return RemainWaterActor.getWaterNum();
	}
}
