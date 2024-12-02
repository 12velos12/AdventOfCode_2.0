package CustomClasses;

import java.io.*;
import java.util.ArrayList;

public class RyansFileClass {

    public static ArrayList<String> fileToStringArray(File file){
        ArrayList<String> fileContent = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            while((line = reader.readLine()) != null){
                fileContent.add(line);
            }
            reader.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return fileContent;
    }

    public static ArrayList<String> fileToStringArray(String filePath){
        File file = new File(filePath);
        return fileToStringArray(file);
    }

    public static ArrayList<Integer> fileToIntegerArray(File file){
        ArrayList<String> contentStrings = fileToStringArray(file);
        ArrayList<Integer> contentIntegers = new ArrayList<>();

        try{
            for(String contentString : contentStrings){
                String[] contentStringLineArray = contentString.split(",");
                for(String stringNum : contentStringLineArray){
                    contentIntegers.add(Integer.parseInt(stringNum));
                }
            }
        } catch(NumberFormatException e){
            e.printStackTrace();
        }

        return contentIntegers;
    }

    public static ArrayList<Integer> fileToIntegerArray(String filePath){
        File file = new File(filePath);
        return fileToIntegerArray(file);
    }
}
