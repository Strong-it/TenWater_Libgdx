package com.libgdx.tenwater.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.tenwater.utils.AssetsManager;

public class SmallWaterActor extends Actor {

    SmallWaterMoveDirection moveDirection;
    private TextureRegion region;
    private Vector2 temPostion;
    private Image boundGridImg;
    private float moveSpeed = 100f;
    private boolean isExploded = false;

    public SmallWaterActor() { }

    SmallWaterActor(Image boundImg, SmallWaterMoveDirection moveDirection) {
        this.boundGridImg = boundImg;
        region = AssetsManager.assetsManager.assetsWater.speedWaterTxt;
        this.moveDirection = moveDirection;

        setSize(region.getRegionWidth(), region.getRegionHeight());
        temPostion = new Vector2(getX(), getY());
    }

    public void setSmallWaterActorPostion(float x, float y) {
        setPosition(x, y);
        temPostion.set(getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        
        checkState();
        
        if(isExploded) {
            switch (moveDirection) {
            case UP:
                moveUp(delta);
                break;
            case DOWN:
                moveDown(delta);
                break;
            case LEFT:
                moveLeft(delta);
                break;
            case RIGHT:
                moveRight(delta);
                break;
            default:
                break;
            }
            
            checkBounds();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        checkState();
        
        batch.draw(region, getX(), getY(), 
                getOriginX(), getOriginY(), 
                getWidth(), getHeight(), 
                getScaleX(), getScaleY(), 
                getRotation());
    }

    private void moveUp(float delta) {
        temPostion.y += moveSpeed * delta;
        setY(temPostion.y);
    }

    private void moveDown(float delta) {
        temPostion.y -= moveSpeed * delta;
        setY(temPostion.y);
    }

    private void moveLeft(float delta) {
        temPostion.x -= moveSpeed * delta;
        setX(temPostion.x);
    }

    private void moveRight(float delta) {
        temPostion.x += moveSpeed * delta;
        setX(temPostion.x);
    }
    
    private void checkBounds() {
        // 暂时屏蔽检查水滴运动方向
//        if (getX() < boundGridImg.getX() ||
//            getX() + getWidth() > boundGridImg.getX() + boundGridImg.getWidth() - 2 ||
//            getY() < boundGridImg.getY() ||
//            getY() + getHeight() > boundGridImg.getY() + boundGridImg.getHeight() + 2) {
//            setVisible(false);
//        }
    }
    
    public void setExplodedState(boolean isExploded) {
        this.isExploded = isExploded;
    }
    
    private void checkState() {
        if (region == null || !isVisible()) {
            return;
        }
    }
    
    public enum SmallWaterMoveDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
