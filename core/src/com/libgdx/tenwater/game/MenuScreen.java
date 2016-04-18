package com.libgdx.tenwater.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.utils.AssetsManager;

public class MenuScreen extends AbstractBaseScreen {
    private final String TAG = MenuScreen.class.getSimpleName();
    
    private Stage stage;
    private Image image;
    private Table tabel;
    private ImageButton classicBtn, shengjiBtn, helpBtn, musicBtn;
    
    public MenuScreen() {}
    
    public MenuScreen(TenWaterGame game) {
        super(game);
    }

    @Override
    public void show() {
        
        stage = new Stage(game.viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        
        image = new Image(AssetsManager.assetsManager.assetsBg.main_bgTxt);
        image.setFillParent(true);
        stage.addActor(image);
        
        classicBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.classicBtnTxt[0]),
                                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.classicBtnTxt[1]));
        /**
         * ��������Listener���ImageButtonʱimageUp��imageDown�Ż��л�
         */
        classicBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "classic");
                game.setScreen(new ClassicGameScreen(game));
            }
            
        });

        
        shengjiBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.shengjiBtnTxt[0]),
                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.shengjiBtnTxt[1]));
        shengjiBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "shengji");
            }
            
        });
        
        helpBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.helpBtnTxt[0]),
                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.helpBtnTxt[1]));
        
        ImageButtonStyle styleMusic = new ImageButtonStyle();
        styleMusic.up = new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.musicBtnTxt[0]);
        styleMusic.checked = new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.musicBtnTxt[1]);
        musicBtn = new ImageButton(styleMusic);
        // ������checkedͼƬ֮���Զ�����toggle����
        musicBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "Music" + musicBtn.isChecked());   
            }
            
        });
        
        tabel = new Table();
        tabel.setDebug(game.debug);  // ����debugģʽ����������Actor������Ʋ�ͬ��ɫ����������
        tabel.row().padTop(150f);  // ��һ��cell����top�ľ���
        tabel.add(classicBtn).colspan(2).expand();
        tabel.row();
        tabel.add(shengjiBtn).top().colspan(2).expand();
        tabel.row();
        tabel.add(helpBtn).bottom().left().padBottom(20f).padLeft(20f);
        tabel.add(musicBtn).bottom().right().padBottom(20f).padRight(20f);
        
        tabel.setFillParent(true);
        tabel.pack();
        
        stage.addActor(tabel);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        stage.act(Math.min(delta, 1/60f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

}
