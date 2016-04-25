package com.libgdx.tenwater.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.libgdx.tenwater.utils.AssetsManager;

public class WaterActor extends Actor {

    private TextureRegion region;
    private Animation animation;
    private AtlasRegion[] waterDrop = AssetsManager.assetsManager.assetsWater.dropWaterTxt;
    private boolean isPlayAnimation;
    private boolean isAnimationFinished;
    private float stateTime;
    private float frameDuration = 0.05f;
    private int keyFrameIndex;
    
    public WaterActor() { 
        this(0);
    }
    
    public WaterActor(int keyFrameIndex) {
        this.keyFrameIndex = keyFrameIndex;
        this.region = waterDrop[keyFrameIndex];
        
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
        
        isPlayAnimation = false;
        isAnimationFinished = false;
        stateTime = 0;
        initAnimation();
        
        addListener(new EventListenerImpl());
    }
    
    public TextureRegion getRegion() {
        return region;
    }
    
    public void setKeyFrameIndex(int keyFrameIndex) {
        this.keyFrameIndex = keyFrameIndex;
    }
    
    public void setRegion(TextureRegion region) {
        this.region = region;
        if (this.region != null) {
            setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
        } else {
            setSize(0, 0);
        }
    }

    public void initAnimation() {
        animation = new Animation(frameDuration, AssetsManager.assetsManager.assetsWater.explodeWaterTxt);
    }
    
    public Animation getAnimation() {
        return this.animation;
    }
    
    public void setPlayAnimation(boolean isPlayAnimation) {
        this.isPlayAnimation = isPlayAnimation;
    }
    
    public boolean checkAnimationFinished() {
        if (animation.isAnimationFinished(stateTime) && !isAnimationFinished) {
            isAnimationFinished = true;
            setVisible(false);
            return isAnimationFinished;
        }
        
        return isAnimationFinished;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        checkState();
        
        getColor().a *= parentAlpha;
        
        batch.draw(region, getX(), getY(),
                    getOriginX(), getOriginY(), 
                    getWidth(), getHeight(), 
                    getScaleX(), getScaleY(), 
                    getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        checkState();
        
        if (isPlayAnimation) {
            stateTime += delta;
            region = animation.getKeyFrame(stateTime);
            checkAnimationFinished();
        } else {
            if (keyFrameIndex < 4) {
                region = waterDrop[keyFrameIndex];
            }
        }
    }

    private void checkState() {
        if (region == null && !isVisible()) {
            return;
        }
    }
    
    private class EventListenerImpl extends ClickListener {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            keyFrameIndex++;
            if (keyFrameIndex == 4) {
                setPlayAnimation(true);
            }
            super.clicked(event, x, y);
        }
        
    }
}
