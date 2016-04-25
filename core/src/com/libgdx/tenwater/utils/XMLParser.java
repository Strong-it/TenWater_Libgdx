package com.libgdx.tenwater.utils;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XMLParser {

    public static final String TAG = XMLParser.class.getSimpleName();
    
    public LevelData[] levelData;
    
    private XmlReader reader;
    private Element root;
    
    public XMLParser() {
        reader = new XmlReader();
    }
    
    public void initData(FileHandle fileHandle) {
        try {
            root = reader.parse(fileHandle);
            levelData = new LevelData[root.getChildCount()];
            
            for(int i=0; i < levelData.length; i++) {
                levelData[i] = new LevelData();
                levelData[i].setNumber(Integer.valueOf(root.getChild(i).getAttribute("number")));
                levelData[i].setComplexity(Integer.valueOf(root.getChild(i).getAttribute("complexity")));
                levelData[i].parseZappers(root.getChild(i).getAttribute("zappers"));
                levelData[i].parseSolutions(root.getChild(i).getAttribute("solutions"));
                levelData[i].setTapsCount(Integer.valueOf(root.getChild(i).getAttribute("tapsCount")));
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
