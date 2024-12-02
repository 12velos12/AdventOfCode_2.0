package Y2022.D4;

import CustomClasses.RyansFileClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {


    @Test
    void countOverlappedAssignments() {
        ArrayList<String> puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D4/e1Input.txt"));
        assertEquals(4,Logic.countOverlappedAssignments(puzzleInput));
        puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D4/pInput.txt"));
        assertEquals(909,Logic.countOverlappedAssignments(puzzleInput));
    }

    @Test
    void countFullyOverlappedAssignments() {
        ArrayList<String> puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D4/e1Input.txt"));
        assertEquals(2,Logic.countFullyOverlappedAssignments(puzzleInput));
        puzzleInput = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D4/pInput.txt"));
        assertEquals(518,Logic.countFullyOverlappedAssignments(puzzleInput));
    }

    @Test
    void hasOverlappedAssignments() {
        assertAll(() -> assertEquals(false, Logic.hasOverlappedAssignments("2-4,6-8")),
                () -> assertEquals(false, Logic.hasOverlappedAssignments("2-3,4-5")),
                () -> assertEquals(true, Logic.hasOverlappedAssignments("5-7,7-9")),
                () -> assertEquals(true, Logic.hasOverlappedAssignments("2-8,3-7")),
                () -> assertEquals(true, Logic.hasOverlappedAssignments("6-6,4-6")),
                () -> assertEquals(true, Logic.hasOverlappedAssignments("2-6,4-8")));
    }

    @Test
    void hasFullyOverlappedAssignments() {
        assertAll(() -> assertEquals(false, Logic.hasFullyOverlappedAssignments("2-4,6-8")),
                () -> assertEquals(false, Logic.hasFullyOverlappedAssignments("2-3,4-5")),
                () -> assertEquals(false, Logic.hasFullyOverlappedAssignments("5-7,7-9")),
                () -> assertEquals(true, Logic.hasFullyOverlappedAssignments("2-8,3-7")),
                () -> assertEquals(true, Logic.hasFullyOverlappedAssignments("6-6,4-6")),
                () -> assertEquals(false, Logic.hasFullyOverlappedAssignments("2-6,4-8")));
    }

    @Test
    void parseLowerBoundOfSectionRange() {
        assertAll(() -> assertEquals(2, Logic.parseLowerBoundOfSectionRange("2-4")),
                () -> assertEquals(2, Logic.parseLowerBoundOfSectionRange("2-3")),
                () -> assertEquals(5, Logic.parseLowerBoundOfSectionRange("5-7")),
                () -> assertEquals(2, Logic.parseLowerBoundOfSectionRange("2-8")),
                () -> assertEquals(6, Logic.parseLowerBoundOfSectionRange("6-6")),
                () -> assertEquals(2, Logic.parseLowerBoundOfSectionRange("2-6")));
    }

    @Test
    void parseUpperBoundOfSectionRange() {
        assertAll(() -> assertEquals(4, Logic.parseUpperBoundOfSectionRange("2-4")),
                () -> assertEquals(3, Logic.parseUpperBoundOfSectionRange("2-3")),
                () -> assertEquals(7, Logic.parseUpperBoundOfSectionRange("5-7")),
                () -> assertEquals(8, Logic.parseUpperBoundOfSectionRange("2-8")),
                () -> assertEquals(6, Logic.parseUpperBoundOfSectionRange("6-6")),
                () -> assertEquals(6, Logic.parseUpperBoundOfSectionRange("2-6")));
    }
}