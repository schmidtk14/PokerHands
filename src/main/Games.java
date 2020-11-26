package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Games {
    private ArrayList<String> gamesList;

    /**
     * read any number of games from the console and save them to the gamesList field
     */
    private void readGames(){
        Scanner sc = new Scanner(System.in);
        String inputLine;
        while(sc.hasNextLine()) {
            inputLine = sc.nextLine();
            if(inputLine.isEmpty()){
                break;
            }
            gamesList.add(inputLine);
        }
    }

    /**
     * run each game in games list and compare hands to find winner
     */
    private void runGames(){
        for(String game: gamesList){
            CompareHands compare = new CompareHands(game);
            compare.findWinner();
        }
    }

    public static void main(String[] args) {
        Games games = new Games();
        games.readGames();
        games.runGames();
    }
}
