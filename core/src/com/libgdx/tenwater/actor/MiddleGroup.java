package com.libgdx.tenwater.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.actor.SmallWaterActor.SmallWaterMoveDirection;
import com.libgdx.tenwater.actor.base.BaseGroup;
import com.libgdx.tenwater.utils.AssetsManager;

public class MiddleGroup extends BaseGroup {

	// 水滴的行列数
	private static final int WATER_ROW_NUM = 6;
	private static final int WATER_COL_NUM = 6;
	
	Image gridImg;
	WaterActor waterActor;
	boolean isS = false;
	
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
		
		// 水珠大小
		float waterWidth = waterActor.getWidth();
		float waterHeight = waterActor.getHeight();
		
		// 计算水珠之间的间隙大小
        float horizontalInterval = (gridImg.getWidth() - waterActor.getWidth() * WATER_ROW_NUM) / (WATER_ROW_NUM + 1);
        float verticalInterval = (gridImg.getHeight() - waterActor.getHeight() * WATER_COL_NUM) / (WATER_COL_NUM + 1);
        waterActor.setX(gridImgX + horizontalInterval);
        waterActor.setY(gridImgY + verticalInterval);
	}
	
	/**
	 * 仅作测试代码用，查看gridImage的坐标
	 */
	public Image getGridImage() {
		return gridImg;
	}

    @Override
    public void act(float delta) {
        super.act(delta);
        
        if (waterActor.checkAnimationFinished() && !isS) {
            creteSmallWaterActor();
        }
    }

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
        
        isS = true;
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
	
}
