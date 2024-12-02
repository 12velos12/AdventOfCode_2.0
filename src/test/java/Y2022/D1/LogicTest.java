package Y2022.D1;

import CustomClasses.RyansFileClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void getHighestCalorieCount() {
        ArrayList<String> rawCalorieList = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D1/e1Input.txt"));
        assertEquals(24000,Logic.getHighestCalorieCount(rawCalorieList));
        rawCalorieList = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D1/pInput.txt"));
        assertEquals(69281,Logic.getHighestCalorieCount(rawCalorieList));
    }

    @Test
    void createSummedCalorieList() {
        ArrayList<String> rawCalorieList = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D1/e1Input.txt"));
        ArrayList<Integer> expectedSumCalorieList = new ArrayList<>(Arrays.asList(6000,4000,11000,24000,10000));
        assertEquals(expectedSumCalorieList,Logic.createSummedCalorieList(rawCalorieList));
    }

    @Test
    void calculateSum() {
        assertEquals(6000,Logic.calculateSum(new ArrayList<Integer>(Arrays.asList(1000,2000,3000))));
        assertEquals(11000,Logic.calculateSum(new ArrayList<Integer>(Arrays.asList(5000,6000))));
        assertEquals(24000,Logic.calculateSum(new ArrayList<Integer>(Arrays.asList(7000,8000,9000))));
    }

    @Test
    void getCalorieCountFromTopThreeElves() {
        ArrayList<String> rawCalorieList = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D1/e1Input.txt"));
        assertEquals(45000,Logic.getCalorieCountFromTopThreeElves(rawCalorieList));
        rawCalorieList = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D1/pInput.txt"));
        assertEquals(201524,Logic.getCalorieCountFromTopThreeElves(rawCalorieList));
    }
}