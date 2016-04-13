package com.libgdx.tenwater.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.libgdx.tenwater.utils.AssetsManager;

public class WaterSprite {
    private Image[] waterImg;
    private Image waterExplodeImg;
    private ExplodeListener explodeListener;
    private Group group;
    
    private final int WATER_STATE_NUM = 4;
    private final float EXPLODE_ANIMATION_TIME = 1.0f;
    private final float width = 50f;
    private final float height = 50f;
    private int waterNum;
    
    public WaterSprite() {
        
        // TODO: 设置水滴大小 50 * 50
        group = new Group();
        waterImg = new Image[WATER_STATE_NUM];
        for (int i = 0; i < waterImg.length; i++) {
            waterImg[i] = new Image(AssetsManager.assetsManager.assetsWater.dropWaterTxt[i]);
            group.addActor(waterImg[i]);
        }
        
        waterExplodeImg = new Image(AssetsManager.assetsManager.assetsWater.dropWaterTxt[0]);
        waterExplodeImg.setVisible(false);
        group.addActor(waterExplodeImg);
        group.setSize(width, height);
        
        setWaterNum(0);
    }
    
    public void setWaterNum(int num) {
        waterNum = num;
        
        for (int i = 0; i < WATER_STATE_NUM; i++) {
            waterImg[i].clearActions();
            waterImg[i].setVisible((i + 1) == waterNum);
            
            if (i+1 == waterNum) {
                waterImg[i].setScale(0.8f);
                waterImg[i].addAction(Actions.scaleTo(1.2f, 1.2f, 1f));
            }
        }
    }
    
    public void addWaterSprite() {
        if (waterNum == WATER_STATE_NUM) {
            showExplode();
        } else {
            setWaterNum(waterNum + 1);
        }
    }
    
    public void showExplode() {
        setWaterNum(0);
        
        Array<TextureRegion> explodeArrTxtReg = new Array<TextureRegion>();
        
        for (int i = 0; i < WATER_STATE_NUM; i++) {
            explodeArrTxtReg.add(AssetsManager.assetsManager.assetsWater.explodeWaterTxt[i]);
        }
        Animation animation = new Animation(EXPLODE_ANIMATION_TIME, explodeArrTxtReg);
    }
    
    public void setExplodeListener(ExplodeListener listener) {
        explodeListener = listener;
    }
    
    public void exploadeEnd() {
        waterExplodeImg.setVisible(false);
        explodeListener.onExplodeEnd(this);
    }
    
    public void setPosition(float x, float y) {
        group.setPosition(x, y);
    }
    public void draw(Batch batch, float parentAlpha) {
        group.draw(batch, parentAlpha);
    }
    
}
