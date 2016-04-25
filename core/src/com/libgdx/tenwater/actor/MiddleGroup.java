package com.libgdx.tenwater.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.actor.SmallWaterActor.SmallWaterMoveDirection;
import com.libgdx.tenwater.actor.base.BaseGroup;
import com.libgdx.tenwater.utils.AssetsManager;
import com.libgdx.tenwater.utils.XMLParser;

public class MiddleGroup extends BaseGroup {

	// ˮ�ε�������
	private static final int WATER_ROW_NUM = 6;
	private static final int WATER_COL_NUM = 6;
	
	// ��ǰ�ؿ�
	private int curLevel = 1;
	private int keyFrameIndex = 0;
	
	Image gridImg;
	WaterActor[][] waterActor = new WaterActor[WATER_ROW_NUM][WATER_COL_NUM];
	
	public MiddleGroup(TenWaterGame game) {
		super(game);
		init();
	}

	public void init() {
	    this.clear();
	    
	    setSize(getGame().VIRTUAL_WORLD_WIDTH, getGame().VIRTUAL_WORLD_HEIGHT);
	    
		gridImg = new Image(AssetsManager.assetsManager.assetsBg.cellTxt);
		float gridImgX = getGame().VIRTUAL_WORLD_WIDTH / 2 - AssetsManager.assetsManager.assetsBg.cellTxt.getWidth() / 2;
	    float gridImgY = (getGame().VIRTUAL_WORLD_HEIGHT - AssetsManager.assetsManager.assetsBg.cellTxt.getHeight() >> 1) + 20;
	    gridImg.setPosition(gridImgX, gridImgY);
		addActor(gridImg);
		
		XMLParser xmlParser = new XMLParser();
		xmlParser.initData(Gdx.files.internal("classic/classic.xml"));
		
		// �������е�ˮ��
		for (int row = 0; row < WATER_ROW_NUM; row++) {
            for (int col = 0; col < WATER_COL_NUM; col++) {
                keyFrameIndex = xmlParser.levelData[curLevel].getZappers()[row * 6 + col];
                if (keyFrameIndex == 4) {
                    keyFrameIndex = 3;
                }
                waterActor[row][col] = new WaterActor(keyFrameIndex);

                addActor(waterActor[row][col]);
            }
        }
		
		// ˮ���С
		float waterWidth = waterActor[0][0].getWidth();
		float waterHeight = waterActor[0][0].getHeight();
		
		// ����ˮ��֮��ļ�϶��С
        float horizontalInterval = (gridImg.getWidth() - waterWidth * WATER_ROW_NUM) / (WATER_ROW_NUM + 1);
        float verticalInterval = (gridImg.getHeight() - waterHeight * WATER_COL_NUM) / (WATER_COL_NUM + 1);
        
        // ���ȵ�����ˮ��
        float waterY = 0;
        for (int row = 0; row < WATER_ROW_NUM; row++) {
            waterY = 0.5f + gridImg.getY() + gridImg.getHeight() - (verticalInterval + waterHeight) * (row + 1);
            for (int col = 0; col < WATER_COL_NUM; col++) {
                waterActor[row][col].setX(0.5f + gridImgX + horizontalInterval + (waterWidth + horizontalInterval) * col);
                waterActor[row][col].setY(waterY);
            }
        }

	}
	
	/**
	 * �������Դ����ã��鿴gridImage������
	 */
	public Image getGridImage() {
		return gridImg;
	}
	
    @Override
    public void act(float delta) {
        super.act(delta);
        
    }

    /**
    private void creteSmallWaterActor() {
        if (waterActor.getX() - waterActor.getWidth() / 2 > gridImg.getX()) {
            SmallWaterActor smallWaterActor = new SmallWaterActor(this, SmallWaterMoveDirection.LEFT);
            setSmallWaterActorProperty(smallWaterActor);
            System.out.println("L");
        }
        
        if (waterActor.getX() + waterActor.getWidth() * 1.5f < gridImg.getX() + gridImg.getWidth()) {
            SmallWaterActor smallWaterActor2 = new SmallWaterActor(this, SmallWaterMoveDirection.RIGHT);
            setSmallWaterActorProperty(smallWaterActor2);
            System.out.println("R");
        }
        
        if (waterActor.getY() - waterActor.getHeight() / 2 > gridImg.getY()) {
            SmallWaterActor smallWaterActor3 = new SmallWaterActor(this, SmallWaterMoveDirection.DOWN);
            setSmallWaterActorProperty(smallWaterActor3);
            System.out.println("D");
        }
        
        if (waterActor.getY() + waterActor.getHeight() * 1.5f < gridImg.getY() + gridImg.getHeight()) {
            SmallWaterActor smallWaterActor4 = new SmallWaterActor(this, SmallWaterMoveDirection.UP);
            setSmallWaterActorProperty(smallWaterActor4);
            System.out.println("U");
        }
        
    }
    
    public void setSmallWaterActorProperty(SmallWaterActor smallWaterActor) {
        float x = waterActor.getX() + waterActor.getWidth() / 2 - smallWaterActor.getWidth() / 2 + 15;
        float y = waterActor.getY() + waterActor.getHeight() / 2 - smallWaterActor.getHeight() / 2;
        smallWaterActor.setSmallWaterActorPostion(x, y);
        if (smallWaterActor.moveDirection == SmallWaterMoveDirection.UP || 
                smallWaterActor.moveDirection == SmallWaterMoveDirection.DOWN) {
            smallWaterActor.setRotation(90);
        }
        
        smallWaterActor.setExplodedState(true);
        addActor(smallWaterActor);
    }
	*/
}
