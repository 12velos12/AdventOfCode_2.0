package Y2019.IntcodeComputerChallenge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PaintingRobotTest {

    @Test
    void performSingleOperation() {
    }

    @Test
    void determinePanelColor() {
        PaintingRobot paintingRobot = new PaintingRobot(new ArrayList<>(Arrays.asList(103L,0L,104L,0L,103L,0L,104L,1L,99L)));
        PaintingRobot.PanelColor panelColor = paintingRobot.determinePanelColor(PaintingRobot.PanelColor.BLACK);
        assertEquals(PaintingRobot.PanelColor.BLACK,panelColor);
        panelColor = paintingRobot.determinePanelColor(PaintingRobot.PanelColor.BLACK);
        assertEquals(PaintingRobot.PanelColor.WHITE,panelColor);
//        assertAll(() -> assertEquals(-1,paintingRobot.getxPos()),
//                () -> assertEquals(0,paintingRobot.getyPos()),
//                () -> assertEquals(PaintingRobot.Orientation.LEFT,paintingRobot.currentOrientation));
    }

    @Test
    void moveRobot() {
        PaintingRobot paintingRobot = new PaintingRobot(new ArrayList<>(Arrays.asList(104L,0L,104L,0L,104L,1L,99L)));
        paintingRobot.moveRobot();
        assertAll(() -> assertEquals(-1,paintingRobot.getxPos()),
                () -> assertEquals(0,paintingRobot.getyPos()),
                () -> assertEquals(PaintingRobot.Orientation.LEFT,paintingRobot.currentOrientation));
        paintingRobot.moveRobot();
        assertAll(() -> assertEquals(-1,paintingRobot.getxPos()),
                () -> assertEquals(-1,paintingRobot.getyPos()),
                () -> assertEquals(PaintingRobot.Orientation.DOWN,paintingRobot.currentOrientation));
        paintingRobot.moveRobot();
        assertAll(() -> assertEquals(-2,paintingRobot.getxPos()),
                () -> assertEquals(-1,paintingRobot.getyPos()),
                () -> assertEquals(PaintingRobot.Orientation.LEFT,paintingRobot.currentOrientation));
    }

    @Test
    void turnLeft() {
    }

    @Test
    void turnRight() {
    }

    @Test
    void moveForward() {
    }
}