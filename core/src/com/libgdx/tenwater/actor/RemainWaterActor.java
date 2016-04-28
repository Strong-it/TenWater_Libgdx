package com.libgdx.tenwater.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.libgdx.tenwater.utils.AssetsManager;

public class RemainWaterActor extends Actor {
    TextureRegion bgRegion;
    TextureRegion[] curWaterRegion = AssetsManager.assetsManager.assetsWater.containerWaterTxt;
    static int waterNum;
    
    public RemainWaterActor() {
        bgRegion = AssetsManager.assetsManager.assetsWater.sbackTxt;
        setSize(bgRegion.getRegionWidth(), bgRegion.getRegionHeight());
        
        waterNum = 5;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        batch.draw(bgRegion, getX(), getY(), 
                getOriginX(), getOriginY(), 
                getWidth(), getHeight(), 
                getScaleX(), getScaleY(), 
                getRotation());
        batch.draw(curWaterRegion[waterNum], getX(), getY(), 
                getOriginX(), getOriginY(), 
                getWidth(), getHeight(), 
                getScaleX(), getScaleY(), 
                getRotation());
    }

    public static void setWaterNum(int num) {
        waterNum = num;
    }
    
    public static int getWaterNum() {
        return waterNum;
    }

    
}
