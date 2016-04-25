package com.libgdx.tenwater.utils;

public class LevelData {
    private int number;
    private int complexity;
    private int[] zappers;
    private int[] solutions;
    private int tapsCount;
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getComplexity() {
        return complexity;
    }
    
    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
    
    public int[] getZappers() {
        return zappers;
    }
    
    public void parseZappers(String zapper) {
        zappers = new int[zapper.length()];
        for (int i = 0; i < zapper.length(); i++) {
            // 将Character转换为整数
            zappers[i] = zapper.charAt(i) - '0';
        }
    }
    
    public int[] getSolutions() {
        return solutions;
    }
    
    public void parseSolutions(String solution) {
        String[] tmpSolutions;
        tmpSolutions = solution.split(",");
        solutions = new int[tmpSolutions.length];
        for (int i = 0; i < tmpSolutions.length; i++) {
            solutions[i] = Integer.valueOf(tmpSolutions[i]);
        }
    }
    
    public int getTapsCount() {
        return tapsCount;
    }
    
    public void setTapsCount(int tapsCount) {
        this.tapsCount = tapsCount;
    }
    
    
}
