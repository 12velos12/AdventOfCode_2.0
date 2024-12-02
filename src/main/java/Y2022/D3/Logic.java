package Y2022.D3;

import java.util.ArrayList;

public class Logic {

    public static int sumPriorityOfBadges(ArrayList<String> rucksackArray){
        int prioritySum = 0;

        for(int i = 0; i < rucksackArray.size() - 2; i+=3){
            prioritySum += getItemPriority(determineBadge(rucksackArray,i));
        }

        return prioritySum;
    }

    public static int sumPriorityOfMisplacedItems(ArrayList<String> rucksackArray){
        int prioritySum = 0;

        for(String rucksack : rucksackArray){
            prioritySum += getItemPriority(determineMisplacedItem(rucksack));
        }

        return prioritySum;
    }

    public static char determineBadge(ArrayList<String> rucksackArray, int startingIndex){
        ArrayList<Character> uniqueItemsValidator = new ArrayList<>();
        for(char item : rucksackArray.get(startingIndex).toCharArray()){
            if(!uniqueItemsValidator.contains(item)){
                uniqueItemsValidator.add(item);
            }
        }
        ArrayList<Character> comparisonArray = new ArrayList<>();
        for(char item : rucksackArray.get(startingIndex + 1).toCharArray()){
            if(uniqueItemsValidator.contains(item) && !comparisonArray.contains(item)){
                comparisonArray.add(item);
            }
        }
        for(char item : rucksackArray.get(startingIndex + 2).toCharArray()){
            if(comparisonArray.contains(item)){
                return item;
            }
        }

        throw new RuntimeException("Error - determineMisplacedItem(String rucksack) - Item could not be determined");

    }

    public static char determineMisplacedItem(String rucksack){
        String[] compartments = new String[]{rucksack.substring(0,rucksack.length()/2),rucksack.substring(rucksack.length()/2)};

        ArrayList<Character> uniqueItemsValidator = new ArrayList<>();
        for(char item : compartments[0].toCharArray()){
            if(!uniqueItemsValidator.contains(item)){
                uniqueItemsValidator.add(item);
            }
        }
        for(char item : compartments[1].toCharArray()){
            if(uniqueItemsValidator.contains(item)){
                return item;
            }
        }

        throw new RuntimeException("Error - determineMisplacedItem(String rucksack) - Item could not be found");
    }

    public static int getItemPriority(char item){
        int itemASCII = item;
        int lowercaseOffset = 96;
        int uppercaseOffset = 38;

        if(itemASCII > 95) // If lowercase
            return itemASCII - lowercaseOffset;
        else               //uppercase
            return itemASCII - uppercaseOffset;
    }
}
