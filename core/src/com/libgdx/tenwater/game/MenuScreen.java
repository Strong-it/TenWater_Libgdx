package com.libgdx.tenwater.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.libgdx.tenwater.TenWaterGame;
import com.libgdx.tenwater.game.stage.ChooseLevelStage;
import com.libgdx.tenwater.utils.AssetsManager;

public class MenuScreen extends AbstractBaseScreen {
    private final String TAG = MenuScreen.class.getSimpleName();
    
    private ChooseLevelStage levelStage;
    private Stage stage;
    private Table tabel;
    private ImageButton classicBtn, shengjiBtn, helpBtn, musicBtn;
    private boolean showLevelStage;
    
    public MenuScreen() {}
    
    public MenuScreen(TenWaterGame game) {
        super(game);
    }

    @Override
    public void show() {
        showLevelStage = false;
        levelStage = new ChooseLevelStage(game.viewport, game.batch);
        levelStage.backBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                showLevelStage = false;
                Gdx.input.setInputProcessor(stage);
            }
        });
        
        stage = new Stage(game.viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        
        classicBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.classicBtnTxt[0]),
                                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.classicBtnTxt[1]));
        /**
         * ��������Listener���ImageButtonʱimageUp��imageDown�Ż��л�
         */
        classicBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ClassicGameScreen(game));
            }
            
        });

        
        shengjiBtn = new ImageButton(new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.shengjiBtnTxt[0]),
                new TextureRegionDrawable(AssetsManager.assetsManager.assetsBtn.shengjiBtnTxt[1]));
        shengjiBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                showLevelStage = true;
                Gdx.input.setInputProcessor(levelStage);
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
        tabel.add(helpBtn).bottom().left().padBottom(15f).padLeft(20f);
        tabel.add(musicBtn).bottom().right().padBottom(15f).padRight(20f);
        
        tabel.setFillParent(true);
        tabel.pack();
        
        stage.addActor(tabel);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        game.batch.begin();
        game.batch.disableBlending();
        game.batch.draw(AssetsManager.assetsManager.assetsBg.main_bgTxt, 0, 0, game.VIRTUAL_WORLD_WIDTH, game.VIRTUAL_WORLD_HEIGHT);
        game.batch.enableBlending();
        game.batch.end();
        
        if (showLevelStage) {
            levelStage.act(Math.min(delta, 1/60f));
            levelStage.draw();
        } else {
            stage.act(Math.min(delta, 1/60f));
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        stage.dispose();
        levelStage.dispose();
    }

}
