import java.util.ArrayList;
import java.util.Scanner;

public class Games {
    private ArrayList<String> gamesList;

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

    private void runGames(){
        readGames();
        for(String game: gamesList){
            CompareHands compare = new CompareHands(game);
            compare.findWinner();
        }
    }

    public static void main(String[] args) {
        Games games = new Games();
        games.runGames();
    }
}
