package Y2019.D1;

import java.util.ArrayList;

public class Logic {
    public static int calculateFuelForMass(int mass){
        int fuel = (int) Math.floor(mass/3) - 2;
        return fuel > 0 ? fuel : 0;
    }

    public static int calculateFuelForMultipleMasses(ArrayList<Integer> masses){
        int totalFuel = 0;
        for(int mass : masses){
            totalFuel += calculateFuelForMass(mass);
        }
        return totalFuel;
    }

    public static int calculateCompoundingFuelForMass(int mass){
        int totalFuel = 0;
        int uncalculatedMassRemaining = mass;

        do{
            int addedFuel = calculateFuelForMass(uncalculatedMassRemaining);
            totalFuel += addedFuel;
            uncalculatedMassRemaining = addedFuel;
        } while (uncalculatedMassRemaining > 0);

        return totalFuel;
    }

    public static int calculateCompoundingFuelForMultipleMasses(ArrayList<Integer> masses){
        int totalFuel = 0;

        for(int mass : masses){
            totalFuel += calculateCompoundingFuelForMass(mass);
        }

        return totalFuel;
    }
}
