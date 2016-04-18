package com.libgdx.tenwater.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.libgdx.tenwater.TenWaterGame;

public abstract class AbstractBaseScreen extends ScreenAdapter {

    private final String TAG = AbstractBaseScreen.class.getSimpleName();
    
    TenWaterGame game;
    
    private boolean keyHandled;   // ������Screen����Ҫ�����һЩ���鶼���� BaseScreen����
                                  // ���粶׽���أ� menu�˵�event
    public AbstractBaseScreen() { }
    
    public AbstractBaseScreen(TenWaterGame game) {
        this.game = game;
        
        keyHandled = false; // ��Ϊrenderˢ���ٶȷǳ��죬�����Ҫһ���������ı�״̬��������δ���
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    }
    
    @Override
    public void show() { }

    @Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (Gdx.input.isKeyPressed(Keys.BACK)) {
            if (keyHandled) {
                return;
            }
            handledBackPress();
            keyHandled = true;
        } else {
            keyHandled = false;
        }
        
        if (Gdx.input.isKeyPressed(Keys.MENU)) {
            if (keyHandled) {
                return;
            }
            handledMenuPress();
            keyHandled = true;
        } else {
            keyHandled = false;
        }
    }

    protected void handledBackPress() {
        Gdx.app.log(TAG, "press Back");
    }
    
    protected void handledMenuPress() {
        Gdx.app.log(TAG, "press Menu");
    }
    
    public void setTenWaterGame(TenWaterGame game) {
    	this.game = game;
    }
    
    public TenWaterGame geTenWaterGame() {
    	return this.game;
    }
}
