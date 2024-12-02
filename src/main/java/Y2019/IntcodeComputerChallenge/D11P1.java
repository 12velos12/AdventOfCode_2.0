package Y2019.IntcodeComputerChallenge;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class D11P1 {
    public static void main(String[] args){
        ArrayList<String> rawIntcode = RyansFileClass.fileToStringArray("src/main/java/Y2019/IntcodeComputerChallenge/day11PuzzleInput.txt");
        ArrayList<Long> intcodeProgram = IntcodeComputerHandler.interpretIntcode(rawIntcode.get(0));

        HashMap<String, PaintingRobot.PanelColor> panelColorHashMap = PaintingRobotHandler.generatePaintedHullPlan(intcodeProgram);
        System.out.println(panelColorHashMap.size());
        System.out.println(panelColorHashMap.keySet());
        System.out.println(panelColorHashMap.values());
//        for(int[] ad:panelColorHashMap.keySet()){
//            System.out.println(ad[0]+","+ad[1]);
//        }
    }
}
