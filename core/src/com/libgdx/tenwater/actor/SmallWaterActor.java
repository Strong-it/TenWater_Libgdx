package com.libgdx.tenwater.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.libgdx.tenwater.utils.AssetsManager;

public class SmallWaterActor extends Actor {

    SmallWaterMoveDirection moveDirection;
    MiddleGroup group;
    private TextureRegion region;
    private Vector2 temPostion;
    private float moveSpeed = 100f;
    private boolean isExploded = false;

    public SmallWaterActor() {
    }

    SmallWaterActor(MiddleGroup group, SmallWaterMoveDirection moveDirection) {
        this.group = group;
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
            case LEFT:
                moveLeft(delta);
            case RIGHT:
                moveRight(delta);
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
        if (getX() < group.getGridImage().getX() ||
            getX() + getWidth() > group.getGridImage().getX() + group.getGridImage().getWidth() ||
            getY() < group.getGridImage().getY() ||
            getY() + getHeight() > group.getGridImage().getY() + group.getGridImage().getHeight()) {
            setVisible(false);
        }
    }
    
    public void setExplodedState(boolean isExploded) {
        this.isExploded = isExploded;
    }
    
    private void checkState() {
        if (region == null && !isVisible()) {
            return;
        }
    }
    
    public enum SmallWaterMoveDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
