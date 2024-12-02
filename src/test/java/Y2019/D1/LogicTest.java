package Y2019.D1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    @Test
    void calculateFuelForMass() {
        assertAll(() -> assertEquals(2, Logic.calculateFuelForMass(12)),
                () -> assertEquals(2, Logic.calculateFuelForMass(14)),
                () -> assertEquals(654, Logic.calculateFuelForMass(1969)),
                () -> assertEquals(33583, Logic.calculateFuelForMass(100756)));
    }

    @Test
    void calculateFuelForMultipleMasses(){
        assertEquals(34241, Logic.calculateFuelForMultipleMasses(new ArrayList<Integer>(Arrays.asList(12,14,1969,100756))));
    }

    @Test
    void calculateCompoundingFuelForMass() {
        assertAll(() -> assertEquals(2, Logic.calculateCompoundingFuelForMass(14)),
                () -> assertEquals(966, Logic.calculateCompoundingFuelForMass(1969)),
                () -> assertEquals(50346, Logic.calculateCompoundingFuelForMass(100756)));
    }

    @Test
    void calculateCompoundingFuelForMultipleMasses() {
        assertEquals(51314, Logic.calculateCompoundingFuelForMultipleMasses(new ArrayList<Integer>(Arrays.asList(14,1969,100756))));
    }
}