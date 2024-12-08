package Y2024.D5;

import CustomClasses.RyansArrayListManipulator;
import CustomClasses.RyansFileClass;

import java.util.ArrayList;
import java.util.HashMap;

public class Logic {
    ArrayList<String> rawPageOrderingRules = new ArrayList<>();
    HashMap<Integer, ArrayList<Integer>> pageOrderingRules;
    ArrayList<ArrayList<Integer>> rawPageUpdates = new ArrayList<>();

    public Logic(String filePath) {
        ArrayList<String> rawInput = RyansFileClass.fileToStringArray(filePath);
        int i;
        for (i = 0; rawInput.get(i).length() != 0; i++){
            this.rawPageOrderingRules.add(rawInput.get(i));
        }
        i++;
        while (i < rawInput.size()){
            ArrayList numberArray = new ArrayList();
            String[] rawNumberArray = rawInput.get(i).split(",");
            for (String number : rawNumberArray){
                numberArray.add(Integer.parseInt(number));
            }
            this.rawPageUpdates.add(numberArray);
            i++;
        }
        this.pageOrderingRules = initPageOrderingRules();
    }

    private HashMap<Integer, ArrayList<Integer>> initPageOrderingRules(){
        HashMap<Integer, ArrayList<Integer>> pageOrderingRules = new HashMap<>();
        for (String pageOrderingRule : rawPageOrderingRules){
            String[] pageOrderingRuleStringArray = pageOrderingRule.split("\\|");
            int key = Integer.parseInt(pageOrderingRuleStringArray[0]);
            int value = Integer.parseInt(pageOrderingRuleStringArray[1]);
            if (pageOrderingRules.containsKey(key)) {
                pageOrderingRules.get(key).add(value);
            } else {
                ArrayList<Integer> valueArray = new ArrayList<>();
                valueArray.add(value);
                pageOrderingRules.put(key,valueArray);
            }
        }
        return pageOrderingRules;
    }

    private int getMiddlePageNumber(ArrayList<Integer> pageUpdates) {
        return pageUpdates.get(pageUpdates.size()/2);
    }

    private int getMiddlePageNumber(int pageUpdateIndex) {
        return getMiddlePageNumber(rawPageUpdates.get(pageUpdateIndex));
    }

    private boolean pageUpdatesAreInOrder(ArrayList<Integer> pageUpdates) {
        for (int pageIndex = 0; pageIndex < pageUpdates.size(); pageIndex++){
            if (pageHasInvalidPageNumberBefore(pageUpdates, pageIndex))
                return false;
        }
        return true;
    }

    private boolean pageUpdatesAreInOrder(int pageUpdateIndex) {
        return pageUpdatesAreInOrder(rawPageUpdates.get(pageUpdateIndex));
    }

    private boolean pageHasInvalidPageNumberBefore(ArrayList<Integer> pageUpdates, int pageIndex) {
        if (!pageOrderingRules.containsKey(pageUpdates.get(pageIndex))) return false;
        ArrayList<Integer> invalidPageNumbers = pageOrderingRules.get(pageUpdates.get(pageIndex));
        for (int i = 0; i < pageIndex; i++){
            if (invalidPageNumbers.contains(pageUpdates.get(i)))
                return true;
        }
        return false;
    }

    private int getInvalidPageNumberBefore(ArrayList<Integer> pageUpdates, int pageIndex) {
        if (!pageOrderingRules.containsKey(pageUpdates.get(pageIndex))) return -1;
        ArrayList<Integer> invalidPageNumbers = pageOrderingRules.get(pageUpdates.get(pageIndex));
        for (int i = 0; i < pageIndex; i++){
            if (invalidPageNumbers.contains(pageUpdates.get(i)))
                return i;
        }
        return -1;
    }

    private void movePageNumberToIndex(ArrayList<Integer> pageNumbers, int pageNumberIndexToMove, int indexToMoveTo) {
        int pageNumber = pageNumbers.get(pageNumberIndexToMove);
        pageNumbers.remove(pageNumberIndexToMove);
        pageNumbers.add(indexToMoveTo, pageNumber);
    }

    private ArrayList<Integer> orderPageUpdates(ArrayList<Integer> pageUpdates) {
        ArrayList<Integer> pageUpdatesCopy = RyansArrayListManipulator.createIntArrayCopy(pageUpdates);
        while (!pageUpdatesAreInOrder(pageUpdatesCopy)) {
            for (int i = 0; i < pageUpdatesCopy.size(); i++){
                if (pageHasInvalidPageNumberBefore(pageUpdatesCopy, i)) {
                    movePageNumberToIndex(pageUpdatesCopy, i,getInvalidPageNumberBefore(pageUpdatesCopy,i));
                    break;
                }
            }
        }
        return pageUpdatesCopy;
    }

    private int sumMiddlePageNumbersOfPageUpdatesInCorrectOrder() {
        int middlePageNumberSum = 0;
        for (ArrayList<Integer> pageUpdates : rawPageUpdates){
            if (pageUpdatesAreInOrder(pageUpdates)) {
                middlePageNumberSum += getMiddlePageNumber(pageUpdates);
            }
        }
        return middlePageNumberSum;
    }

    private int sumMiddlePageNumbersOfPageUpdatesInIncorrectOrderAfterOrdering() {
        int middlePageNumberSum = 0;
        for (ArrayList<Integer> pageUpdates : rawPageUpdates){
            if (!pageUpdatesAreInOrder(pageUpdates)) {
                middlePageNumberSum += getMiddlePageNumber(orderPageUpdates(pageUpdates));
            }
        }
        return middlePageNumberSum;
    }

    public void runP1Program(){
        System.out.println(sumMiddlePageNumbersOfPageUpdatesInCorrectOrder());
    }

    public void runP2Program(){
        System.out.println(sumMiddlePageNumbersOfPageUpdatesInIncorrectOrderAfterOrdering());
    }
}
