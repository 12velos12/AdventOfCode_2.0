package Y2022.D4;

import CustomClasses.RyansFileClass;
import Y2022.D4.Logic;

import java.io.File;
import java.util.ArrayList;

public class P1 {
    public static void main(String[] args) {
        ArrayList<String> puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D4/pInput.txt"));
        System.out.println(Logic.countFullyOverlappedAssignments(puzzleInput));
    }
}
