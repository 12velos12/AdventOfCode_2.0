package Y2022.D6;

import CustomClasses.RyansFileClass;

import java.io.File;
import java.util.ArrayList;

public class P2 {
    public static void main(String[] args) {
        ArrayList<String> puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D6/pInput.txt"));

        System.out.println(Logic.p2Go(puzzleInput));
    }
}
