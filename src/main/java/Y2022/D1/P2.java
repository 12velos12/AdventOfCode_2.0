package Y2022.D1;

import CustomClasses.RyansFileClass;

import java.io.File;
import java.util.ArrayList;

public class P2 {
    public static void main(String[] args){
        ArrayList<String> rawCalorieList = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D1/pInput.txt"));
        System.out.println(Logic.getCalorieCountFromTopThreeElves(rawCalorieList));
    }
}
