package com.libgdx.tenwater.game.stage;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libgdx.tenwater.utils.AssetsManager;

public class ChooseLevelStage extends Stage {
    
    private float worldX;
    private Image[] levelImg;
    public ImageButton backBtn;
    
    private float downX, downY;
    private int middleLevel;
    /** 滑动生效的最小距离 */
    private static final float SLIDE_MIN_DIFF = 20;
    
    public ChooseLevelStage() {
        super();
        initStage();
    }
    
    public ChooseLevelStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        initStage();
    }
    
    private void initStage() {
        levelImg = new Image[11];
        for (int i = 0; i < levelImg.length; i++) {
            levelImg[i] = new Image(AssetsManager.assetsManager.assetsLevel.levelTxt[i]);
            if (i == 0) {
                levelImg[i].setX(getWidth() / 2 - levelImg[i].getWidth() / 2);
                worldX = levelImg[i].getX() / 2;
            } else {
                levelImg[i].setX(levelImg[i -1].getX() + levelImg[i -1].getWidth() + worldX);
            }
            levelImg[i].setY(getHeight() / 2 - levelImg[i].getHeight() / 2);
            levelImg[i].addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("click level image");
                    super.clicked(event, x, y);
                }
                
            });
            addActor(levelImg[i]);
            
            middleLevel = 1;
        }
        
        backBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.backBtnTxt[0]), 
                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.backBtnTxt[1]));
        backBtn.setX(20f);
        backBtn.setY(15f);
        addActor(backBtn);
        
        addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                    int pointer, int button) {
                downX = x;
                downY = y;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y,
                    int pointer, int button) {
                float diffX = x - downX;
                float diffY = y - downY;
                
                if (Math.abs(diffX) >= SLIDE_MIN_DIFF && Math.abs(diffX) * 0.5F > Math.abs(diffY)) {
                    // 左右滑动
                    if (diffX > 0) {
                        addRActions();
                    } else {
                        addLActions();
                    }
                }
            }

        });
    }

    /* 下面这种方法，backBtn的监听函数不会响应，因此改为上面方法
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        downX = screenX;
        downY = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        float diffX = screenX - downX;
        float diffY = screenY - downY;
        
        if (Math.abs(diffX) >= SLIDE_MIN_DIFF && Math.abs(diffX) * 0.5F > Math.abs(diffY)) {
            // 左右滑动
            if (diffX > 0) {
                System.out.println("move to R");
            } else {
                System.out.println("move to L");
            }
        }
        return false;
    }
    */
    
    private void addRActions() {
        if (middleLevel == 1) {
            return;
        }
        for (int i = 0; i < levelImg.length; i++) {
            levelImg[i].addAction(Actions.moveBy(worldX + levelImg[i].getWidth(), 0, 1.0f, Interpolation.bounceOut));
        }
        middleLevel--;
    }
    
    private void addLActions() {
        if (middleLevel == 11) {
            return;
        }
        for (int i = 0; i < levelImg.length; i++) {
            levelImg[i].addAction(Actions.moveBy(-(worldX + levelImg[i].getWidth()), 0, 1.0f, Interpolation.bounceOut));
        }
        middleLevel++;
    }
}
