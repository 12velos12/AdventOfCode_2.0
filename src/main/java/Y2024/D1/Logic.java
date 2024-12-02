package Y2024.D1;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Logic {
    private ArrayList<Integer> orderedLeftList = new ArrayList<>();
    private ArrayList<Integer> orderedRightList = new ArrayList<>();

    public Logic(String filePath) {
        ArrayList<String> rawInput = RyansFileClass.fileToStringArray(filePath);
        initLeftAndRightLists(rawInput);
    }

    private void initLeftAndRightLists(ArrayList<String> rawInput){
        for (String rawInputString : rawInput){
            String[] rawInputPair = rawInputString.split("   ");
            orderedLeftList.add(Integer.parseInt(rawInputPair[0]));
            orderedRightList.add(Integer.parseInt(rawInputPair[1]));
        }
    }

    private void orderLists(){
        Collections.sort(orderedLeftList);
        Collections.sort(orderedRightList);
    }

    private int getDistanceByIndexOfLists(int index){
        return Math.abs(orderedLeftList.get(index) - orderedRightList.get(index));
    }

    private int sumDistancesOfEachIndex(){
        int sum = 0;
        for(int i = 0; i < orderedLeftList.size(); i++){
            sum += getDistanceByIndexOfLists(i);
        }
        return sum;
    }

    private int getValueAmountFromOrderedRightList(int value){
        int firstValueOccurence = orderedRightList.indexOf(value);
        if (firstValueOccurence == -1) return 0;
        int valueAmount = 1;
        for (int i = firstValueOccurence + 1; i < orderedRightList.size(); i++){
            if (orderedRightList.get(i) == value){
                valueAmount++;
            } else {
                break;
            }
        }
        return valueAmount;
    }

    private int calculateIndexSimilarityScore(int index){
        int indexValue = orderedLeftList.get(index);
        return indexValue * getValueAmountFromOrderedRightList(indexValue);
    }

    private int calculateSumOfSimilarityScores(){
        int sum = 0;
        for (int i = 0; i < orderedLeftList.size(); i++){
            sum += calculateIndexSimilarityScore(i);
        }
        return sum;
    }

    public int runP1Program(){
        orderLists();
        return sumDistancesOfEachIndex();
    }

    public int runP2Program(){
        orderLists();
        return calculateSumOfSimilarityScores();
    }
}
