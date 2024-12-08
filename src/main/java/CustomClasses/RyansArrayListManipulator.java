package CustomClasses;

import java.util.ArrayList;

public class RyansArrayListManipulator {
    public static ArrayList<Integer> createIntArrayCopy(ArrayList<Integer> arrayList){
        ArrayList<Integer> arrayListCopy = new ArrayList<>();
        for (int element : arrayList){
            arrayListCopy.add(element);
        }
        return arrayListCopy;
    }

    public static ArrayList<String> createStringArrayCopy(ArrayList<String> arrayList){
        ArrayList<String> arrayListCopy = new ArrayList<>();
        for (String element : arrayList){
            arrayListCopy.add(element);
        }
        return arrayListCopy;
    }
}
