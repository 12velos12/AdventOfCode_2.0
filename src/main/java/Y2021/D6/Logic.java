package Y2021.D6;

import CustomClasses.RyansFileClass;
import java.util.ArrayList;
import java.util.Arrays;

public class Logic {
    long[] fishArray;

    public Logic(String filePath) {
        long[] fishArray = new long[9];
        for(int i = 0; i < fishArray.length; i++){
            fishArray[i] = 0;
        }
        ArrayList<Integer> fishInput = RyansFileClass.fileToIntegerArray(filePath);
        for(int age : fishInput){
            fishArray[age]++;
        }

        this.fishArray = fishArray;
    }

    public void simulateDayCycle(){
        long[] yesterdayFishArray = fishArray.clone();
        for(int i = 1; i < fishArray.length; i++){
            fishArray[i-1] = yesterdayFishArray[i];
        }
        fishArray[8] = yesterdayFishArray[0];
        fishArray[6] += yesterdayFishArray[0];
    }

    public long getFishCount(){
        long fishCount = 0;
        for (long fish : fishArray){
            fishCount += fish;
        }
        return fishCount;
    }

    public void runP1Program(){
        for(int i = 0; i < 80; i++){
            simulateDayCycle();
        }
        System.out.println(getFishCount());
    }

    public void runP2Program(){
        for(int i = 0; i < 256; i++){
            simulateDayCycle();
        }
        System.out.println(getFishCount());
    }


}