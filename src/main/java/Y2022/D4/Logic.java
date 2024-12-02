package Y2022.D4;

import java.util.ArrayList;

public class Logic {


    public static int countOverlappedAssignments(ArrayList<String> assignmentPairs){
        int count = 0;

        for(String assignmentPair : assignmentPairs){
            if(hasOverlappedAssignments(assignmentPair))
                count++;
        }

        return count;
    }

    public static int countFullyOverlappedAssignments(ArrayList<String> assignmentPairs){
        int count = 0;

        for(String assignmentPair : assignmentPairs){
            if(hasFullyOverlappedAssignments(assignmentPair))
                count++;
        }

        return count;
    }

    public static boolean hasOverlappedAssignments(String assignmentPair){
        int pairOneLowerBound = parseLowerBoundOfSectionRange(assignmentPair.split(",")[0]);
        int pairOneUpperBound = parseUpperBoundOfSectionRange(assignmentPair.split(",")[0]);
        int pairTwoLowerBound = parseLowerBoundOfSectionRange(assignmentPair.split(",")[1]);
        int pairTwoUpperBound = parseUpperBoundOfSectionRange(assignmentPair.split(",")[1]);

        return ((pairOneLowerBound <= pairTwoLowerBound && pairTwoLowerBound <= pairOneUpperBound)
                || (pairOneLowerBound <= pairTwoUpperBound && pairTwoUpperBound <= pairOneUpperBound)
                || (pairTwoLowerBound <= pairOneLowerBound && pairOneLowerBound <= pairTwoUpperBound)
                || (pairTwoLowerBound <= pairOneUpperBound && pairOneUpperBound <= pairTwoUpperBound));

    }

    public static boolean hasFullyOverlappedAssignments(String assignmentPair){
        int pairOneLowerBound = parseLowerBoundOfSectionRange(assignmentPair.split(",")[0]);
        int pairOneUpperBound = parseUpperBoundOfSectionRange(assignmentPair.split(",")[0]);
        int pairTwoLowerBound = parseLowerBoundOfSectionRange(assignmentPair.split(",")[1]);
        int pairTwoUpperBound = parseUpperBoundOfSectionRange(assignmentPair.split(",")[1]);

        return ((pairOneLowerBound <= pairTwoLowerBound && pairOneUpperBound >= pairTwoUpperBound) || (pairOneLowerBound >= pairTwoLowerBound && pairOneUpperBound <= pairTwoUpperBound));
    }

    public static int parseLowerBoundOfSectionRange(String range){
        return Integer.parseInt(range.split("-")[0]);
    }

    public static int parseUpperBoundOfSectionRange(String range){
        return Integer.parseInt(range.split("-")[1]);
    }
}
