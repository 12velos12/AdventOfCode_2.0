package Y2019.D1;

import CustomClasses.RyansFileClass;

import java.io.File;
import java.util.ArrayList;

public class P2 {
    public static void main(String[] args){
        ArrayList<Integer> inputMasses = RyansFileClass.fileToIntegerArray(new File("src/main/java/Y2019/D1/day2PuzzleInput.txt"));
        System.out.println(Logic.calculateCompoundingFuelForMultipleMasses(inputMasses));
    }
}
