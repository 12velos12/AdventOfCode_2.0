package Y2024.D2;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
    private ArrayList<ProductPair> productPairList = new ArrayList<>();
    private ArrayList<ProductPair> productPairListWithOperators = new ArrayList<>();

    private String mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)";
    private String mulRegexWithOperators = "(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don't\\(\\))";

    public Logic(String filePath) {
        ArrayList<String> rawInput = RyansFileClass.fileToStringArray(filePath);
        this.productPairList = initProductPairList(rawInput);
        this.productPairListWithOperators = initProductPairListWithOperators(rawInput);
    }

    private ArrayList<ProductPair> initProductPairList(ArrayList<String> rawInput){
        ArrayList<ProductPair> productPairList = new ArrayList<>();
        for (String rawLine : rawInput){
            ArrayList<ProductPair> productPairsInLine = convertRawLineToProductPairList(rawLine);
            for (ProductPair productPair : productPairsInLine){
                productPairList.add(productPair);
            }
        }
        return productPairList;
    }

    private ArrayList<ProductPair> initProductPairListWithOperators(ArrayList<String> rawInput){
        ArrayList<ProductPair> productPairList = new ArrayList<>();
        String rawInputString = "";
        for (String rawLine : rawInput){
            rawInputString += rawLine;
        }
        ArrayList<ProductPair> productPairsInLine = convertRawLineToProductPairListWithOperators(rawInputString);
        for (ProductPair productPair : productPairsInLine){
            productPairList.add(productPair);
        }
        return productPairList;
    }

    private ArrayList<ProductPair> convertRawLineToProductPairListWithOperators(String rawLine){
        ArrayList<ProductPair> productPairList = new ArrayList<>();
        ArrayList<String> mulStringListWithOperators = getMulStringListWithOperators(rawLine);
        Boolean addMuls = true;
        for (String mulString : mulStringListWithOperators){
            if (mulString.matches("do\\(\\)"))
                addMuls = true;
            else if (mulString.matches("don't\\(\\)"))
                addMuls = false;
            else if (addMuls)
                productPairList.add(convertMulStringToProductPair(mulString));
        }
        return productPairList;
    }

    private ArrayList<ProductPair> convertRawLineToProductPairList(String rawLine){
        ArrayList<ProductPair> productPairList = new ArrayList<>();
        ArrayList<String> mulStringList = getMulStringList(rawLine);
        for (String mulString : mulStringList){
            productPairList.add(convertMulStringToProductPair(mulString));
        }
        return productPairList;
    }

    private ArrayList<String> getMulStringList(String rawLine){
        ArrayList<String> mulStringList = new ArrayList<>();
        Matcher m = Pattern.compile(mulRegex).matcher(rawLine);
        while (m.find()) {
            mulStringList.add(m.group());
        }
        return mulStringList;
    }

    private ArrayList<String> getMulStringListWithOperators(String rawLine){
        ArrayList<String> mulStringList = new ArrayList<>();
        Matcher m = Pattern.compile(mulRegexWithOperators).matcher(rawLine);
        while (m.find()) {
            mulStringList.add(m.group());
        }
        return mulStringList;
    }

    private ProductPair convertMulStringToProductPair(String mulString){
        mulString = mulString.replaceAll("mul\\(","");
        mulString = mulString.replaceAll("\\)","");
        String[] productPair = mulString.split(",");
        return new ProductPair(Integer.parseInt(productPair[0]),Integer.parseInt(productPair[1]));
    }

    private int multiplyThenAddAllProductPairs(ArrayList<ProductPair> productPairs){
        int sum = 0;
        for (ProductPair productPair : productPairs){
            sum += productPair.getNumberX() * productPair.getNumberY();
        }
        return sum;
    }

    public void runP1Program(){
        System.out.println(multiplyThenAddAllProductPairs(this.productPairList));
    }

    public void runP2Program(){
        System.out.println(multiplyThenAddAllProductPairs(this.productPairListWithOperators));
    }
}
