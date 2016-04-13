package com.libgdx.tenwater.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

public class AssetsManager implements Disposable, AssetErrorListener {
    
    public static final String TAG = AssetsManager.class.getSimpleName();
    public static final AssetsManager assetsManager = new AssetsManager();

    public AssetsBg assetsBg;
    public AssetsLevel assetsLevel;
    public AssetsBtn assetsBtn;
    public AssetsWater assetsWater;
    private AssetManager assetManager;
    
    private final int LEVEL_NUM = 11;
    
    private AssetsManager() { }
    
    public void loadGameResources(AssetManager assetManager) {
        
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);  // 如果加载资源出错,程序将会暂停在哪里，不会退出，并且调用error方法
        
        assetManager.load(Resources.GameImage.main_bgImg, Texture.class);
        assetManager.load(Resources.GameImage.upgrade_bgImg, Texture.class);
        assetManager.load(Resources.GameImage.game_bgImg, Texture.class);
        assetManager.load(Resources.GameImage.dialogBg_winImg, Texture.class);
        assetManager.load(Resources.GameImage.dialogBg_pauseImg, Texture.class);
        assetManager.load(Resources.GameImage.dialogBg_loseImg, Texture.class);
        assetManager.load(Resources.GameImage.classic_winFgImg, Texture.class);
        assetManager.load(Resources.GameImage.classic_loseFgImg, Texture.class);
        assetManager.load(Resources.GameImage.cellImg, Texture.class);
        
        // 加载游戏选关的背景， 路径未定义在Resources类里面
        for (int i = 0; i < LEVEL_NUM; i++) {
            assetManager.load("selectLevel/level" + (i + 1) + ".png", Texture.class);
        }
        
        assetManager.load(Resources.GameImage.button_path, TextureAtlas.class);
        assetManager.load(Resources.GameImage.water_path, TextureAtlas.class);
//        assetManager.finishLoading();  此处不应该有此函数，否则会block 线程
        
        
    }
   
    /**
     * get资源要放在manager.update里面执行
     */
    public void init() {
        assetsBg = new AssetsBg(assetManager);
        assetsLevel = new AssetsLevel(assetManager);
        
        TextureAtlas atlas = assetManager.get(Resources.GameImage.button_path, TextureAtlas.class);
        assetsBtn = new AssetsBtn(atlas);
        
        atlas = assetManager.get(Resources.GameImage.water_path, TextureAtlas.class);
        assetsWater = new AssetsWater(atlas);
    }
    
    public class AssetsBg {
        public final Texture main_bgTxt;
        public final Texture upgrade_bgTxt;
        public final Texture game_bgTxt;
        public final Texture dialogBg_winTxt;
        public final Texture dialogBg_pauseTxt;
        public final Texture dialogBg_loseTxt;
        public final Texture classic_winFgTxt;
        public final Texture classic_loseFgTxt;
        public final Texture cellTxt;
        
        // 内部类可以直接访问外部类的成员，其实这里也可以直接用 assetManager
        public AssetsBg(AssetManager am) {
            main_bgTxt = am.get(Resources.GameImage.main_bgImg, Texture.class);
            upgrade_bgTxt = am.get(Resources.GameImage.upgrade_bgImg, Texture.class);
            game_bgTxt = am.get(Resources.GameImage.game_bgImg, Texture.class);
            dialogBg_winTxt = am.get(Resources.GameImage.dialogBg_winImg, Texture.class);
            dialogBg_pauseTxt = am.get(Resources.GameImage.dialogBg_winImg, Texture.class);
            dialogBg_loseTxt = am.get(Resources.GameImage.dialogBg_loseImg, Texture.class);
            classic_winFgTxt = am.get(Resources.GameImage.classic_winFgImg, Texture.class);
            classic_loseFgTxt = am.get(Resources.GameImage.classic_loseFgImg, Texture.class);
            cellTxt = am.get(Resources.GameImage.cellImg, Texture.class);
        }
    }
    
    public class AssetsLevel {
        public final Texture[] levelTxt = new Texture[LEVEL_NUM];
        
        public AssetsLevel(AssetManager am) {
            for (int i = 0; i < 11; i++) {
                levelTxt[i] = am.get("selectLevel/level" + (i + 1) + ".png", Texture.class);
            }
        }
    }

    public class AssetsBtn {
        public final AtlasRegion[] classicBtnTxt = new AtlasRegion[2];
        public final AtlasRegion[] shengjiBtnTxt = new AtlasRegion[2];
        public final AtlasRegion[] musicBtnTxt = new AtlasRegion[2];
        public final AtlasRegion[] helpBtnTxt = new AtlasRegion[2];
        public final AtlasRegion[] backBtnTxt = new AtlasRegion[2];
        
        public final AtlasRegion resetBtnTxt;
        public final AtlasRegion levelBtnTxt;
        
        public AssetsBtn(TextureAtlas atlas) {
            classicBtnTxt[0] = atlas.findRegion("classic");
            classicBtnTxt[1] = atlas.findRegion("classic_down");
            shengjiBtnTxt[0] = atlas.findRegion("shengji");
            shengjiBtnTxt[1] = atlas.findRegion("shengji_down");
            musicBtnTxt[0] = atlas.findRegion("musicon");
            musicBtnTxt[1] = atlas.findRegion("musicoff");
            helpBtnTxt[0] = atlas.findRegion("help");
            helpBtnTxt[1] = atlas.findRegion("help_down");
            backBtnTxt[0] = atlas.findRegion("back");
            backBtnTxt[1] = atlas.findRegion("back_down");
            
            resetBtnTxt = atlas.findRegion("btn_reset");
            levelBtnTxt = atlas.findRegion("level");
        }
    }
    
    public class AssetsWater {
        public final AtlasRegion[] dropWaterTxt = new AtlasRegion[5];
        public final AtlasRegion[] explodeWaterTxt = new AtlasRegion[4];
        public final AtlasRegion[] containerWaterTxt = new AtlasRegion[20];
        public final AtlasRegion[] wallDropWaterTxt = new AtlasRegion[4];
        public final AtlasRegion sbackTxt;
        
        public AssetsWater(TextureAtlas atlas) {
            for (int i = 0; i < dropWaterTxt.length; i++) {
                dropWaterTxt[i] = atlas.findRegion("drop");
            }
            
            for (int i = 0; i < explodeWaterTxt.length; i++) {
                explodeWaterTxt[i] = atlas.findRegion("explode");
            }
            
            for (int i = 0; i < containerWaterTxt.length; i++) {
                containerWaterTxt[i] = atlas.findRegion("s" + (i+1));
            }
            
            for (int i = 0; i < wallDropWaterTxt.length; i++) {
                wallDropWaterTxt[i] = atlas.findRegion("wall_drop");
            }
            
            sbackTxt = atlas.findRegion("sback");
        }
    }
    
    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "couldn't load asset file:" + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        
    }
}
