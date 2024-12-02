package Y2022.D5;

import CustomClasses.RyansFileClass;
import Y2022.D5.Logic;

import java.io.File;
import java.util.ArrayList;

public class P1 {
    public static void main(String[] args) {
        ArrayList<String> puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D5/pInput.txt"));
        Logic logic = new Logic();

        System.out.println(logic.go(puzzleInput,1));
    }
}
