package Y2022.D1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Logic {
    public static int getHighestCalorieCount(ArrayList<String> rawCalorieList){
        ArrayList<Integer> summedCalorieList  = createSummedCalorieList(rawCalorieList);

        int highestCalorieCount = 0;
        for(int value : summedCalorieList){
            if(value > highestCalorieCount)
                highestCalorieCount = value;
        }

        return highestCalorieCount;
    }

    public static int getCalorieCountFromTopThreeElves(ArrayList<String> rawCalorieList){
        ArrayList<Integer> summedCalorieList  = createSummedCalorieList(rawCalorieList);
        Collections.sort(summedCalorieList);

        int sum = 0;
        for (int i = 0; i < 3; i++){
            sum += summedCalorieList.get(summedCalorieList.size() - i - 1);
        }

        return sum;
    }

    public static ArrayList<Integer> createSummedCalorieList(ArrayList<String> rawCalorieList){
        ArrayList<Integer> summedCalorieList = new ArrayList<>();

        int sum = 0;
        for(String value : rawCalorieList){
            if(value.isEmpty()){
                summedCalorieList.add(sum);
                sum = 0;
            }
            else{
                sum += Integer.valueOf(value);
            }
        }
        summedCalorieList.add(sum);

        return summedCalorieList;
    }

    public static int calculateSum(ArrayList<Integer> numList){
        int sum = 0;
        for(int value : numList){
            sum += value;
        }
        return sum;
    }
}
