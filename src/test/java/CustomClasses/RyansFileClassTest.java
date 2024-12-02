package CustomClasses;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RyansFileClassTest {

    @Test
    void testFileToStringArray() {
        File file = new File("src/test/java/CustomClasses/testFile.txt");
        assertEquals(new ArrayList<String>(Arrays.asList("15","31","21","45")), RyansFileClass.fileToStringArray(file));
    }

    @Test
    void testFileToIntegerArray() {
        File file = new File("src/test/java/CustomClasses/testFile.txt");
        assertEquals(new ArrayList<Integer>(Arrays.asList(15,31,21,45)), RyansFileClass.fileToIntegerArray(file));
    }

    @Test
    void testStringToStringArray() {
        assertEquals(new ArrayList<String>(Arrays.asList("15","31","21","45")), RyansFileClass.fileToStringArray("src/test/java/CustomClasses/testFile.txt"));
    }

    @Test
    void testStringToIntegerArray() {
        assertEquals(new ArrayList<Integer>(Arrays.asList(15,31,21,45)), RyansFileClass.fileToIntegerArray("src/test/java/CustomClasses/testFile.txt"));
    }
}