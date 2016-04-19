package com.libgdx.tenwater.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;

public class TopGroup extends BaseGroup {

	private static final String TAG = TopGroup.class.getSimpleName();
	
	//  top level �� current level
	Image levelImg;
	Label topLevel, currentLevel;
	
	public TopGroup(TenWaterGame game) {
		super(game);
		init();
	}
	
	private void init() {
		// ����Group�Ĵ�С
		setSize(getGame().VIRTUAL_WORLD_WIDTH, getGame().VIRTUAL_WORLD_HEIGHT);
		
		levelImg = new Image(AssetsManager.assetsManager.assetsBtn.levelBtnTxt);
		levelImg.setPosition(getGame().VIRTUAL_WORLD_WIDTH >> 1 - 30, 
				getGame().VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBtn.levelBtnTxt.getRegionHeight() - 20f);
//		����levelImageΪ��͸��
//		levelImg.getColor().a = 0.5f;
		
		Gdx.app.log(TAG, "width=" + levelImg.getWidth() + "  ImageWidth=" + levelImg.getImageWidth()
				    + " maxWidth=" + levelImg.getMaxWidth() + "  miniWidth=" + levelImg.getMinWidth()
				    + "  preWidth=" + levelImg.getPrefWidth());
		// imageX��Image������(0,0) getX��Image�����world����ϵ��λ������
		Gdx.app.log(TAG, "imageX=" + levelImg.getImageX() + "  x=" + levelImg.getX());
		
		Label.LabelStyle style = new Label.LabelStyle();
		style.font = AssetsManager.assetsManager.assetsfont.defaultFont;
		
		currentLevel = new Label("current", style);
		currentLevel.setPosition(levelImg.getX() + levelImg.getWidth() + 30,  levelImg.getY());
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
