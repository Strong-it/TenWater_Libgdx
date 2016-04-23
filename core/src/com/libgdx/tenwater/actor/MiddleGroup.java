package com.libgdx.tenwater.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.actor.base.BaseGroup;
import com.libgdx.tenwater.utils.AssetsManager;

public class MiddleGroup extends BaseGroup {

	// ˮ�ε�������
	private static final int WATER_ROW_NUM = 6;
	private static final int WATER_COL_NUM = 6;
	
	Image gridImg;
	WaterActor waterActor;
	
	public MiddleGroup(TenWaterGame game) {
		super(game);
		init();
	}

	public void init() {
	    setSize(getGame().VIRTUAL_WORLD_WIDTH, getGame().VIRTUAL_WORLD_HEIGHT);
	    
		gridImg = new Image(AssetsManager.assetsManager.assetsBg.cellTxt);
		float gridImgX = getGame().VIRTUAL_WORLD_WIDTH / 2 - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() / 2;
	    float gridImgY = (getGame().VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1) + 20;
	    gridImg.setPosition(gridImgX, gridImgY);
		addActor(gridImg);
		
		
		waterActor = new WaterActor(1, getGame());
		waterActor.setPosition(gridImg.getX(), gridImg.getY());
		addActor(waterActor);
		
		// ˮ���С
		float waterWidth = waterActor.getWidth();
		float waterHeight = waterActor.getHeight();
		
		// ����ˮ��֮��ļ�϶��С
        float horizontalInterval = (gridImg.getWidth() - waterActor.getWidth() * WATER_ROW_NUM) / (WATER_ROW_NUM + 1);
        float verticalInterval = (gridImg.getHeight() - waterActor.getHeight() * WATER_COL_NUM) / (WATER_COL_NUM + 1);
        waterActor.setX(gridImgX + horizontalInterval);
        waterActor.setY(gridImgY + verticalInterval);
	}
	
	/**
	 * �������Դ����ã��鿴gridImage������
	 */
	public Image getGridImage() {
		return gridImg;
	}
}
