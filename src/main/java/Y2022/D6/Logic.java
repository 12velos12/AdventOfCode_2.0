package Y2022.D6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Logic {
    public static int p1Go(ArrayList<String> puzzleInput){
        return getStartOfPacketMarker(puzzleInput.get(0), 4);
    }

    public static int p2Go(ArrayList<String> puzzleInput){
        return getStartOfPacketMarker(puzzleInput.get(0), 14);
    }

    private static int getStartOfPacketMarker(String dataStream, int markerLength){
        if(dataStream.length() < 4) throw new RuntimeException("getStartOfPacketMarker(String dataStream) - datastream is too short: " + dataStream);

        char first;
        char second;
        char third ;
        char fourth;
        int index = 0;

        while(index + 3 < dataStream.length()){
            String sequence = dataStream.substring(index, index + markerLength);

            if(hasUniqueChars(sequence)){
                return index+markerLength;
            }

            index++;
        }

        throw new RuntimeException("getStartOfPacketMarker(String dataStream) - could not find marker");
    }

    private static boolean hasUniqueChars(char first, char second, char third, char fourth){
        return first != second && first != third && first != fourth && second != third && second != fourth && third != fourth;
    }

    private static boolean hasUniqueChars(String string){
        HashSet<Character> verificationSet = new HashSet<>();

        for(char character : string.toCharArray()){
            verificationSet.add(character);
        }

        return verificationSet.size() == string.length();
    }
}
