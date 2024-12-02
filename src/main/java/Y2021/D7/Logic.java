package Y2021.D7;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;
import java.util.Collections;

public class Logic {
    ArrayList<Integer> inputArray;

    Logic(String filePath){
        this.inputArray = RyansFileClass.fileToIntegerArray(filePath);
    }

    public boolean isEven(int num){
        return num%2 == 0;
    }

    public int getMedian(ArrayList<Integer> numbers){
        Collections.sort(numbers);

        if(isEven(numbers.size())){

        } else {
            return numbers.get((numbers.size() - 1)/2);
        }

        return 1;
    }
}
