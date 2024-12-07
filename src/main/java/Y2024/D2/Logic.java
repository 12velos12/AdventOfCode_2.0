package Y2024.D2;

import CustomClasses.RyansFileClass;
import java.util.ArrayList;

public class Logic {
    ArrayList<ArrayList<Integer>> reportArray = new ArrayList<>();

    public Logic(String filePath) {
        ArrayList<String> rawInput = RyansFileClass.fileToStringArray(filePath);
        this.reportArray = buildReportArray(rawInput);
    }

    private ArrayList<ArrayList<Integer>> buildReportArray(ArrayList<String> rawInput){
        ArrayList<ArrayList<Integer>> reportArray = new ArrayList<>();
        for(String rawReport : rawInput){
            ArrayList<Integer> report = new ArrayList();
            String[] stringReport = rawReport.split(" ");
            for(String level : stringReport){
                report.add(Integer.parseInt(level));
            }
            reportArray.add(report);
        }

        return reportArray;
    }

    private boolean levelsIncreased(int levelOne, int levelTwo){
        return levelOne < levelTwo;
    }

    private boolean levelDifferenceIsBetweenOneAndThreeInclusive(int levelOne, int levelTwo){
        return Math.abs(levelOne - levelTwo) >= 1 && Math.abs(levelOne - levelTwo) <= 3;
    }

    private boolean levelChangeIsSafe(int levelOne, int levelTwo, boolean levelsIncreasing){
        if (!levelDifferenceIsBetweenOneAndThreeInclusive(levelOne, levelTwo)){
            return false;
        }
        if (levelsIncreased(levelOne,levelTwo) != levelsIncreasing){
            return false;
        }
        return true;
    }

    private boolean reportIsSafe(ArrayList<Integer> report){
        boolean levelsIncreasing = true;
        if (report.get(0) > report.get(1))
            levelsIncreasing = false;

        for(int i = 1; i < report.size(); i++){
            int levelOne = report.get(i-1);
            int levelTwo = report.get(i);
            if (!levelChangeIsSafe(levelOne, levelTwo, levelsIncreasing)) return false;
        }

        return true;
    }

    private ArrayList<Integer> copyArrayWithoutSpecifiedIndex(ArrayList<Integer> originalArray, int indexToRemove){
        ArrayList<Integer> copiedArray = new ArrayList<>();
        for(int i = 0; i < originalArray.size(); i++){
            if (indexToRemove == i) continue;
            copiedArray.add(originalArray.get(i));
        }

        return copiedArray;
    }

    private int getProblemLevelIndex(ArrayList<Integer> report){
        boolean levelsIncreasing = true;
        if (report.get(0) > report.get(1))
            levelsIncreasing = false;

        for(int i = 1; i < report.size(); i++){
            int levelOne = report.get(i-1);
            int levelTwo = report.get(i);
            if (!levelChangeIsSafe(levelOne, levelTwo, levelsIncreasing)) return i;
        }
        return -1;
    }

    private boolean reportIsSafeWithProblemDampener(ArrayList<Integer> report){
        if (reportIsSafe(report)) return true;
        if (reportIsSafe(copyArrayWithoutSpecifiedIndex(report,0))) return true;
        int problemLevelIndex = getProblemLevelIndex(report);
        return reportIsSafe(copyArrayWithoutSpecifiedIndex(report,problemLevelIndex)) || reportIsSafe(copyArrayWithoutSpecifiedIndex(report,problemLevelIndex - 1));
    }

    private int getSafeReportsCountWithoutProblemDampener(){
        int safeReportsCount = 0;
        for (ArrayList<Integer> report : reportArray){
            if (reportIsSafe(report)){
                safeReportsCount++;
            }
        }
        return safeReportsCount;
    }

    private int getSafeReportsCountWithProblemDampener(){
        int safeReportsCount = 0;
        for (ArrayList<Integer> report : reportArray){
            if (reportIsSafeWithProblemDampener(report)){
                safeReportsCount++;
            }
        }
        return safeReportsCount;
    }

    public int runP1Program(){
        return getSafeReportsCountWithoutProblemDampener();
    }

    public int runP2Program(){
        return getSafeReportsCountWithProblemDampener();
    }
}
