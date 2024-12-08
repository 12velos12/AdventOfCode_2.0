package Y2024.D4;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    ArrayList<String> wordSearchMap;

    public Logic(String filePath) {
        this.wordSearchMap = RyansFileClass.fileToStringArray(filePath);
    }

    private int getNumberOfWordSearchMatches(String word){
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
        if (word.charAt(0) != wordSearchMap.get(rowCoord).charAt(colCoord)) return 0;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, 0, 1)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, 0, -1)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, 1, 1)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, 1, -1)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, 1, 0)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, -1, 0)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, -1, 1)) numberOfMatches++;
        if (coordHasAMatchInDirection(word, rowCoord, colCoord, -1, -1)) numberOfMatches++;
        return numberOfMatches;
    }

    private boolean coordHasAMatchInDirection(String word, int rowCoord, int colCoord, int rowDirection, int colDirection){
        if (rowCoord < 0 || colCoord < 0 || rowCoord >= wordSearchMap.size() || colCoord >= wordSearchMap.size()) return false;
        if (word.length() == 1 && word.charAt(0) == wordSearchMap.get(rowCoord).charAt(colCoord)) return true;
        if (word.charAt(0) == wordSearchMap.get(rowCoord).charAt(colCoord))
            return coordHasAMatchInDirection(word.substring(1),rowCoord + rowDirection,colCoord + colDirection,rowDirection,colDirection);
        return false;
    }

    public int getNumberOfCrossMasMatches() {
        int matches = 0;
        for (int colCoord = 0; colCoord < wordSearchMap.size(); colCoord++){
            for (int rowCoord = 0; rowCoord < wordSearchMap.get(colCoord).length(); rowCoord++){
                if (coordIsCenterOfCrossMas(colCoord, rowCoord)) matches++;
            }
        }
        return matches;
    }

    public boolean coordIsCenterOfCrossMas(int colCoord, int rowCoord) {
        if (wordSearchMap.get(rowCoord).charAt(colCoord) != 'A') return false;
        return coordPairHasMandS(colCoord + 1, rowCoord + 1, colCoord - 1, rowCoord - 1) &&
                coordPairHasMandS(colCoord + 1, rowCoord - 1, colCoord - 1, rowCoord + 1);
    }

    public boolean coordPairHasMandS(int colCoordOne, int rowCoordOne, int colCoordTwo, int rowCoordTwo) {
        if (rowCoordOne < 0
                || colCoordOne < 0
                || rowCoordTwo < 0
                || colCoordTwo < 0
                || rowCoordOne >= wordSearchMap.size()
                || colCoordOne >= wordSearchMap.size()
                || rowCoordTwo >= wordSearchMap.size()
                || colCoordTwo >= wordSearchMap.size())
            return false;
        char coordOneChar = wordSearchMap.get(rowCoordOne).charAt(colCoordOne);
        char coordTwoChar = wordSearchMap.get(rowCoordTwo).charAt(colCoordTwo);
        return (coordOneChar == 'M' && coordTwoChar == 'S') || (coordOneChar == 'S' && coordTwoChar == 'M');
    }

    public void runP1Program(){
        System.out.println(getNumberOfWordSearchMatches("XMAS"));
    }

    public void runP2Program(){
        System.out.println(getNumberOfCrossMasMatches());
    }
}
