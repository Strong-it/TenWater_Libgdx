package com.libgdx.tenwater.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.libgdx.tenwater.actor.SmallWaterActor.SmallWaterMoveDirection;
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
    private MiddleGroup group;
    
    public WaterActor(MiddleGroup group) { 
        this(0, group);
    }
    
    public WaterActor(int keyFrameIndex, MiddleGroup group) {
        this.keyFrameIndex = keyFrameIndex;
        this.region = waterDrop[keyFrameIndex];
        this.group = group;
        
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
        if (keyFrameIndex < 0) {
            setVisible(false);
            return;
        }
        this.keyFrameIndex = keyFrameIndex;
        setRegion(waterDrop[keyFrameIndex]);
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
            createSmallWaterAfterExplode();
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
        if (region == null || !isVisible()) {
            return;
        }
    }
    
    private void createSmallWaterAfterExplode() {
        if (getX() - getWidth() / 2.0f > group.getGridImage().getX()) {
            SmallWaterActor smallWaterActor = new SmallWaterActor(group.getGridImage(), SmallWaterMoveDirection.LEFT);
            setSmallWaterActorProperty(smallWaterActor);
            System.out.println("L");
        }
        
        if (getX() + getWidth() * 1.5f < group.getGridImage().getX() + group.getGridImage().getWidth()) {
            SmallWaterActor smallWaterActor = new SmallWaterActor(group.getGridImage(), SmallWaterMoveDirection.RIGHT);
            setSmallWaterActorProperty(smallWaterActor);
            System.out.println("R");
        }
        
        if (getY() - getHeight() / 2.0f > group.getGridImage().getY()) {
            SmallWaterActor smallWaterActor = new SmallWaterActor(group.getGridImage(), SmallWaterMoveDirection.DOWN);
            setSmallWaterActorProperty(smallWaterActor);
        }
        
        if (getY() + getHeight() * 1.5f < group.getGridImage().getY() + group.getGridImage().getHeight()) {
            SmallWaterActor smallWaterActor = new SmallWaterActor(group.getGridImage(), SmallWaterMoveDirection.UP);
            setSmallWaterActorProperty(smallWaterActor);
        }
    }
    
    public void setSmallWaterActorProperty(SmallWaterActor smallWaterActor) {
        float x = getX() + getWidth() / 2 - smallWaterActor.getWidth() / 2;
        float y = getY() +getHeight() / 2 - smallWaterActor.getHeight() / 2;
        smallWaterActor.setSmallWaterActorPostion(x, y);
        smallWaterActor.setOrigin(Align.center);
        if (smallWaterActor.moveDirection == SmallWaterMoveDirection.UP || 
                smallWaterActor.moveDirection == SmallWaterMoveDirection.DOWN) {
            smallWaterActor.setRotation(90);
        }
        
        smallWaterActor.setExplodedState(true);
        group.addActor(smallWaterActor);
    }
    
    public void addKeyFrameIndex() {
        keyFrameIndex++;
        if (keyFrameIndex == 4) {
            setPlayAnimation(true);
        }
    }
    
    private class EventListenerImpl extends ClickListener {

        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (TopGroup.getRemainWaterNum() > 0) {
                addKeyFrameIndex();
                TopGroup.setRemainWaterNum(TopGroup.getRemainWaterNum() - 1);
            }
            
            super.clicked(event, x, y);
        }
        
    }
}
