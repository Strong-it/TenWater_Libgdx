package com.libgdx.tenwater.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;

public class MiddleGroup extends BaseGroup {

	// ˮ�ε�������
	private static final int WATER_ROW_NUM = 6;
	private static final int WATER_COL_NUM = 6;
	
	Image gridImg;
	
	public MiddleGroup(TenWaterGame game) {
		super(game);
		init();
	}

	public void init() {
		gridImg = new Image(AssetsManager.assetsManager.assetsBg.cellTxt);
		addActor(gridImg);
		
		setSize(gridImg.getWidth(), gridImg.getHeight());
	}
	
	/**
	 * �������Դ����ã��鿴gridImage������
	 */
	public Image getGridImage() {
		return gridImg;
	}
}
