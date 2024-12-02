package Y2021.D1;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    private ArrayList<Integer> fileToIntegerArray;

    public Logic(String fileName){
        this.fileToIntegerArray = RyansFileClass.fileToIntegerArray(fileName);
    }

    public int countDepthIncreases(){
        int depthIncreases = 0;
        for(int i = 1; i < fileToIntegerArray.size(); i++){
            if(depthIncreased(i)){
                depthIncreases++;
            }
        }
        return depthIncreases;
    }

    public int countThreeWindowDepthIncreases(){
        int depthIncreases = 0;
        for(int i = 1; i < fileToIntegerArray.size() - 2; i++){
            if(sumThreeMeasurementWindow(i - 1) < sumThreeMeasurementWindow(i)){
                depthIncreases++;
            }
        }
        return depthIncreases;
    }

    private boolean depthIncreased(int depthIndex){
        if(depthIndex <= 0 || fileToIntegerArray.size() <= depthIndex){
            System.out.println(String.format("Error - depth: %s is outside scope of array", depthIndex));
            return false;
        }

        int previousDepth = fileToIntegerArray.get(depthIndex - 1);
        int newDepth = fileToIntegerArray.get(depthIndex);
        return previousDepth < newDepth;
    }

    public int sumThreeMeasurementWindow(int firstDepthIndex){
        if(fileToIntegerArray.size() - 2 <= firstDepthIndex){
            System.out.println(String.format("Error - depth: %s is outside scope of array", firstDepthIndex));
            return 0;
        }

        int firstDepth = fileToIntegerArray.get(firstDepthIndex);
        int secondDepth = fileToIntegerArray.get(firstDepthIndex + 1);
        int thirdDepth = fileToIntegerArray.get(firstDepthIndex + 2);
        return firstDepth + secondDepth + thirdDepth;
    }
}
