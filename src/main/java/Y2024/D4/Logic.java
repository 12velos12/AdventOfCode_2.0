package Y2024.D4;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    ArrayList<String> wordSearchMap;

    public Logic(String filePath) {
        this.wordSearchMap = RyansFileClass.fileToStringArray(filePath);
    }

    private int getWordSearchMatches(String word){
        int wordSearchMatches = 0;
        for (int colCoord = 0; colCoord < wordSearchMap.size(); colCoord++){
            for (int rowCoord = 0; rowCoord < wordSearchMap.get(colCoord).length(); rowCoord++){
                wordSearchMatches += getNumberOfMatchesAtCoord(word,rowCoord,colCoord);
            }
        }
        return wordSearchMatches;
    }

    private int getNumberOfMatchesAtCoord(String word, int rowCoord, int colCoord){
        int numberOfMatches = 0;
        if (word.charAt(0) != wordSearchMap.get(colCoord).charAt(rowCoord))
            return 0;
        if

    }

    private boolean coordHasStartOfMatchingWord(String word, int rowCoord, int colCoord){
        if (word.charAt(0) != wordSearchMap.get(colCoord).charAt(rowCoord))
            return false;
        if (word.length() == 1 && word.charAt(0) == wordSearchMap.get(colCoord).charAt(rowCoord)){
            return true;
        }
        return
    }

    public void runP1Program(){
    }

    public void runP2Program(){
    }
}
