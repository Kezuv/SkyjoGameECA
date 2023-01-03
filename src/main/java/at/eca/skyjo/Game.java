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
    int finalround = 99;
    boolean canPickUp = false;


    public boolean permissionCheck (Player player, int playerID, int cardNumber){
        if (player == players.get((playerID-1))) {
            if (isPlaying != playerID) {
                return false;
            } else if (movesLeft == 0) {
                return false;
            } else if (player.getCard(cardNumber).isLocked()) {
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
        int whoWins = (hasPlayed+1);
            if (whoWins > players.size()){
                whoWins = 1;
            }

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
            if (finalround == 99) {
                // Player finished?
                if (player.checkIfFinished()){
                    threeOfAKind(player);
                    finalround = hasPlayed-2;
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
                    threeOfAKind(player);
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
            if (finalround != 0){
                player.flipAllCards();
                threeOfAKind(player);
                movesLeft = 1;
                finalround --;
                canPickUp = true;
                if (hasPlayed == maxPlayer){
                    isPlaying = 1;
                    return isPlaying;
                } else { isPlaying = (hasPlayed+1);
                return isPlaying;}
            }
            if (finalround == 0 ){
                movesLeft = 0;
                canPickUp = false;
                return 0;}
        }
        return 99;
    }

    public Game(int playerCount) {
        players = new ArrayList<>();
        deck = new Deck();
        trayDeck = new TrayDeck();

        for (int i = 1; i < (playerCount+1); i++) {
            players.add(new Player("Spieler " + i, this.deck));
        }

    }

    public void threeOfAKind(Player player){
        if (((player.getCard(0).getValue() == player.getCard(1).getValue()) && (player.getCard(0).getValue() == player.getCard(2).getValue())) &&
                ((player.getCard(0).isFaceUp() == player.getCard(1).isFaceUp()) && (player.getCard(0).isFaceUp() == player.getCard(2).isFaceUp()))){
            if (!player.getCard(0).isLocked()){
            Card tmp = player.getCard(0);
            player.getHand().set(0,new Card(true));
            deck.addOnDiscardPile(tmp);
            tmp = player.getCard(1);
            player.getHand().set(1,new Card(true));
            deck.addOnDiscardPile(tmp);
            tmp = player.getCard(2);
            player.getHand().set(2, new Card(true));
            deck.addOnDiscardPile(tmp);}
        }
        else if (((player.getCard(3).getValue() == player.getCard(4).getValue()) && (player.getCard(3).getValue() == player.getCard(5).getValue())) &&
                ((player.getCard(3).isFaceUp() == player.getCard(4).isFaceUp()) && (player.getCard(3).isFaceUp() == player.getCard(5).isFaceUp()))) {
            if (!player.getCard(3).isLocked()){
                Card tmp = player.getCard(3);
                player.getHand().set(3,new Card(true));
                deck.addOnDiscardPile(tmp);
                tmp = player.getCard(4);
                player.getHand().set(4,new Card(true));
                deck.addOnDiscardPile(tmp);
                tmp = player.getCard(5);
                player.getHand().set(5, new Card(true));
                deck.addOnDiscardPile(tmp);}
        }
        else if (((player.getCard(6).getValue() == player.getCard(7).getValue()) && (player.getCard(6).getValue() == player.getCard(8).getValue())) &&
                ((player.getCard(6).isFaceUp() == player.getCard(7).isFaceUp()) && (player.getCard(6).isFaceUp() == player.getCard(8).isFaceUp()))) {
            if (!player.getCard(6).isLocked()) {
                Card tmp = player.getCard(6);
                player.getHand().set(6, new Card(true));
                deck.addOnDiscardPile(tmp);
                tmp = player.getCard(7);
                player.getHand().set(7, new Card(true));
                deck.addOnDiscardPile(tmp);
                tmp = player.getCard(8);
                player.getHand().set(8, new Card(true));
                deck.addOnDiscardPile(tmp);
            }
        }
        else if (((player.getCard(9).getValue() == player.getCard(10).getValue()) && (player.getCard(9).getValue() == player.getCard(11).getValue())) &&
                ((player.getCard(9).isFaceUp() == player.getCard(10).isFaceUp()) && (player.getCard(9).isFaceUp() == player.getCard(11).isFaceUp()))) {
            if (!player.getCard(9).isLocked()) {
                Card tmp = player.getCard(9);
                player.getHand().set(9, new Card(true));
                deck.addOnDiscardPile(tmp);
                tmp = player.getCard(10);
                player.getHand().set(10, new Card(true));
                deck.addOnDiscardPile(tmp);
                tmp = player.getCard(11);
                player.getHand().set(11, new Card(true));
                deck.addOnDiscardPile(tmp);
            }
        }
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