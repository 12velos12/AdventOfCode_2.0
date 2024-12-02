package Y2023.D2;

import CustomClasses.RyansFileClass;

import java.util.ArrayList;

public class Logic {
    private ArrayList<String> gameList;
    private String[] cubeColors  = {"red", "blue", "green"};

    Logic(String filePath){
        this.gameList = RyansFileClass.fileToStringArray(filePath);
    }

    private int readGameId(String gameString){
        return Integer.parseInt(gameString.split(":")[0].replace("Game ",""));
    }

    private ArrayList<Integer> getAllGoodGames(CubeConfiguration cubesInBag){
        ArrayList<Integer> goodGames = new ArrayList<>();
        for (String gameString : gameList){
            if(gameIsGood(gameString, cubesInBag)){
                goodGames.add(readGameId(gameString));
            }
        }

        return goodGames;
    }

    private CubeConfiguration buildCubeConfiguration(String cubeListString){
        String[] cubes = cubeListString.substring(1).split(", ");
        int redCubes = 0;
        int blueCubes = 0;
        int greenCubes = 0;
        for (String cubeType : cubes){
            if (cubeType.contains("red")) {
                redCubes = Integer.parseInt(cubeType.replace(" red", "").replace(" ",""));
            } else if (cubeType.contains("blue")) {
                blueCubes = Integer.parseInt(cubeType.replace(" blue", "").replace(" ",""));
            } else if (cubeType.contains("green")) {
                greenCubes = Integer.parseInt(cubeType.replace(" green", "").replace(" ",""));
            }
        }

        return new CubeConfiguration(redCubes,greenCubes,blueCubes);
    }

    private boolean gameIsGood(String gameString, CubeConfiguration cubesInBag){
        String[] pulledCubesString = gameString.substring(gameString.indexOf(':') + 1).split(";");
        ArrayList<CubeConfiguration> pulledCubeConfigs = new ArrayList<>();

        for(String pulledCubeString : pulledCubesString){
            pulledCubeConfigs.add(buildCubeConfiguration(pulledCubeString));
        }
        for (CubeConfiguration pulledCubeConfig : pulledCubeConfigs){
            if(!cubeConfigIsGood(pulledCubeConfig, cubesInBag)) return false;
        }

        return true;
    }

    private boolean cubeConfigIsGood(CubeConfiguration cubesPulled, CubeConfiguration cubesInBag){
        return cubesPulled.getBlue() <= cubesInBag.getBlue() && cubesPulled.getGreen() <= cubesInBag.getGreen() && cubesPulled.getRed() <= cubesInBag.getRed();
    }

    private CubeConfiguration getMinimumCubesInBag(String gameString){
        String[] pulledCubesString = gameString.substring(gameString.indexOf(':') + 1).split(";");
        ArrayList<CubeConfiguration> pulledCubeConfigs = new ArrayList<>();
        CubeConfiguration minCubesInBag = new CubeConfiguration(0,0,0);

        for(String pulledCubeString : pulledCubesString){
            pulledCubeConfigs.add(buildCubeConfiguration(pulledCubeString));
        }
        for(CubeConfiguration pulledCubeConfig : pulledCubeConfigs){
            if(pulledCubeConfig.getRed() > minCubesInBag.getRed()){
                minCubesInBag.setRed(pulledCubeConfig.getRed());
            }
            if(pulledCubeConfig.getGreen() > minCubesInBag.getGreen()){
                minCubesInBag.setGreen(pulledCubeConfig.getGreen());
            }
            if(pulledCubeConfig.getBlue() > minCubesInBag.getBlue()){
                minCubesInBag.setBlue(pulledCubeConfig.getBlue());
            }
        }
        return minCubesInBag;
    }

    public void runP1Program(){
        ArrayList<Integer> goodGames = getAllGoodGames(new CubeConfiguration(12,13,14));
        int puzzleAnswer = 0;
        for (Integer goodGame : goodGames){
            puzzleAnswer += goodGame;
        }
        System.out.println(puzzleAnswer);
    }

    public void runP2Program(){
        int puzzleAnswer = 0;
        for (String gameString : gameList){
            CubeConfiguration minCubesInBag = getMinimumCubesInBag(gameString);
            puzzleAnswer += minCubesInBag.getBlue() * minCubesInBag.getRed() * minCubesInBag.getGreen();
        }
        System.out.println(puzzleAnswer);
    }
}
