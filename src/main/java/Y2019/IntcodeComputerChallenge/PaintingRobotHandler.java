package Y2019.IntcodeComputerChallenge;

import java.util.ArrayList;
import java.util.HashMap;

public class PaintingRobotHandler {
    private static HashMap<String, PaintingRobot.PanelColor> paintedHullPlan = new HashMap<>();

    private PaintingRobotHandler(){

    }

    public static HashMap<String, PaintingRobot.PanelColor> generatePaintedHullPlan(ArrayList<Long> intcodeProgram){
        PaintingRobot paintingRobot = new PaintingRobot(intcodeProgram);
        boolean haltFlagIsEnabled = false;

        while(!haltFlagIsEnabled){
            int[] currentRobotPosition = new int[]{paintingRobot.getxPos(),paintingRobot.getyPos()};
            PaintingRobot.PanelColor currentPanelColor = getPanelColor(currentRobotPosition);
            PaintingRobotReport paintingRobotReport = paintingRobot.performSingleOperation(currentPanelColor);
            paintedHullPlan.put(String.valueOf(currentRobotPosition[0]) + String.valueOf(currentRobotPosition[1]), paintingRobotReport.getPanelColor());
            haltFlagIsEnabled = paintingRobotReport.isHaltFlagIsEnabled();
//            System.out.println(currentRobotPosition[0] + "," + currentRobotPosition[1]);
        }

        return paintedHullPlan;
    }

    public static PaintingRobot.PanelColor getPanelColor(int[] panelLocation){
        String compressedPanelLocation = String.valueOf(panelLocation[0]) + String.valueOf(panelLocation[1]);
//        System.out.println(compressedPanelLocation);
        if(paintedHullPlan.get(compressedPanelLocation) == null){
            return PaintingRobot.PanelColor.BLACK;
        } else {
            return paintedHullPlan.get(compressedPanelLocation);
        }
    }
}
