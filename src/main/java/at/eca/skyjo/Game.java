package at.eca.skyjo;

import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<Player> players;
    private Deck deck;
    private TrayDeck trayDeck;
    private Player currentPlayer;

    boolean firstRound = true;
    int isPlaying = 1;
    int movesLeft = 2;
    int finalround = 0;
    boolean canPickUp = false;


    public boolean permissionCheck (Player player, int i){
        if (player == players.get((i-1))) {
            if (isPlaying != i) {
                return false;
            } else if (movesLeft == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println("ERROR - Fehler bei permissionCheck! - Falscher Spieler?");
            return false;
        }

    }

    public int gamePlay (Player player, int hasPlayed) {
        int maxPlayer = players.size();

        // Check if FirstRound
        if (firstRound){
            if (isPlaying != maxPlayer){
                movesLeft = 2;
                isPlaying = (hasPlayed+1);
                return isPlaying;
            } else {
                movesLeft = 1;
                isPlaying = 1;
                firstRound = false;
                canPickUp = true;
                return isPlaying;
            }
        }
        // End of First Round
        else {
            if (finalround == 0){
                // Player finished?
                if (player.checkIfFinished()){
                    finalround = hasPlayed;
                    movesLeft = 1;
                    canPickUp = true;
                    if (hasPlayed == maxPlayer){
                        isPlaying = 1;
                        return isPlaying;
                    } else { isPlaying = (hasPlayed+1);
                        return isPlaying;}
                    //Hier eventuell String als Feedback ausgeben.

                }
                // Usual Move
                else {
                    movesLeft = 1;
                    canPickUp = true;
                    if (hasPlayed == maxPlayer){
                        isPlaying = 1;
                        return isPlaying;
                    } else {isPlaying = (hasPlayed+1);
                        return isPlaying;
                    }
                } // End of the Game!
                // Player finished ended.
            }
            if (finalround !=hasPlayed){
                player.flipAllCards();
                movesLeft = 1;
                canPickUp = true;
                if (hasPlayed == maxPlayer){
                    isPlaying = 1;
                    return isPlaying;
                } else { isPlaying = (hasPlayed+1);
                return isPlaying;}

            }
            movesLeft = 0;
            canPickUp = false;
            return finalround;
        }
    }

    public Game(int playerCount) {
        players = new ArrayList<>();
        deck = new Deck();
        trayDeck = new TrayDeck();

        for (int i = 1; i < (playerCount+1); i++) {
            players.add(new Player("Spieler " + i, this.deck));
        }

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() {

    }

    private void determineFirstPlayer() {
        int maxScore = -1;
        for (Player player : players) {
            int score = player.getScore();
            if (score > maxScore) {
                maxScore = score;
                currentPlayer = player;
            }
        }
    }

    private void playRound() {

    }

    public Player getPlayers(int playerNumber) {
        return players.get(playerNumber);
    }

    public TrayDeck getTrayDeck() {
        return trayDeck;
    }

    public Deck getDeck() {
        return deck;
    }

    private void endGame() {

    }
}