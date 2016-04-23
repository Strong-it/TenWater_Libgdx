package com.libgdx.tenwater.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.libgdx.tenwater.TenWaterGame;
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
    
    TenWaterGame game;
    Rectangle rectangle;
    Vector3 touchedPostion;
    
    public WaterActor() { }
    
    public WaterActor(int keyFrameIndex, TenWaterGame game) {
        this.keyFrameIndex = keyFrameIndex;
        this.region = waterDrop[keyFrameIndex];
        
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
        
        isPlayAnimation = false;
        isAnimationFinished = false;
        stateTime = 0;
        initAnimation();
        
        this.game = game;
        rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        touchedPostion = new Vector3();
    }
    
    public TextureRegion getRegion() {
        return region;
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
    
    public void setPlayAnimation(boolean isPlayAnimation) {
        this.isPlayAnimation = isPlayAnimation;
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        if (region == null || !isVisible()) {
            return;
        }
        
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
        
        if (isPlayAnimation) {
            stateTime += delta;
            region = animation.getKeyFrame(stateTime);
            if (animation.isAnimationFinished(stateTime) && !isAnimationFinished) {
                isAnimationFinished = true;
                setVisible(false);
            }
        } else {
            if (keyFrameIndex < 4) {
                region = waterDrop[keyFrameIndex];
            }
        }
            
        if (Gdx.input.justTouched()) {
             rectangle.set(getX(), getY(), getWidth(), getHeight());
             touchedPostion.set(Gdx.input.getX(), Gdx.input.getY(), 0);
             touchedPostion = game.camera.unproject(touchedPostion);
             if (rectangle.contains(touchedPostion.x, touchedPostion.y)) {
                keyFrameIndex++;
                if (keyFrameIndex == 4) {
                    setPlayAnimation(true);
                }
            }
        }
    }

    
}
