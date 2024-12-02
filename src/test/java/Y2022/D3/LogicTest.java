package Y2022.D3;

import CustomClasses.RyansFileClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void sumPriorityOfBadges() {
        ArrayList<String> rucksackArray = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D3/e1Input.txt"));
        assertEquals(70, Y2022.D3.Logic.sumPriorityOfBadges(rucksackArray));
        System.out.println("P2 Example Passed");
        rucksackArray = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D3/pInput.txt"));
        assertEquals(2780, Y2022.D3.Logic.sumPriorityOfBadges(rucksackArray));
        System.out.println("P2 Puzzle Passed");
    }

    @Test
    void sumPriorityOfMisplacedItems() {
        ArrayList<String> rucksackArray = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D3/e1Input.txt"));
        assertEquals(157, Y2022.D3.Logic.sumPriorityOfMisplacedItems(rucksackArray));
        System.out.println("P1 Example Passed");
        rucksackArray = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D3/pInput.txt"));
        assertEquals(7568, Y2022.D3.Logic.sumPriorityOfMisplacedItems(rucksackArray));
        System.out.println("P1 Puzzle Passed");
    }

    @Test
    void determineMisplacedItem() {
        assertEquals('p', Y2022.D3.Logic.determineMisplacedItem("vJrwpWtwJgWrhcsFMMfFFhFp"));
        assertEquals('L', Y2022.D3.Logic.determineMisplacedItem("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"));
        assertEquals('P', Y2022.D3.Logic.determineMisplacedItem("PmmdzqPrVvPwwTWBwg"));
        assertEquals('v', Y2022.D3.Logic.determineMisplacedItem("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"));
        assertEquals('t', Y2022.D3.Logic.determineMisplacedItem("ttgJtRGJQctTZtZT"));
        assertEquals('s', Y2022.D3.Logic.determineMisplacedItem("CrZsJsPPZsGzwwsLwLmpwMDw"));
    }

    @Test
    void getItemPriority() {
        assertEquals(1, Y2022.D3.Logic.getItemPriority('a'));
        assertEquals(26, Y2022.D3.Logic.getItemPriority('z'));
        assertEquals(27, Y2022.D3.Logic.getItemPriority('A'));
        assertEquals(52, Y2022.D3.Logic.getItemPriority('Z'));
    }

    @Test
    void determineBadge() {
        ArrayList<String> rucksackArray = RyansFileClass.fileToStringArray(new File("src/main/java/Y2022/D3/e1Input.txt"));
        assertEquals('r', Y2022.D3.Logic.determineBadge(rucksackArray,0));
        assertEquals('Z', Y2022.D3.Logic.determineBadge(rucksackArray,3));
    }
}