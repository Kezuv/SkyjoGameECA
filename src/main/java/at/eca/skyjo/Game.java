package at.eca.skyjo;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<Player> players;
    private Deck deck;



    private TrayDeck trayDeck;
    private Player currentPlayer;


    public Game(int playerCount) {
        players = new ArrayList<>();
        deck = new Deck();
        trayDeck = new TrayDeck();

        for (int i = 1; i < (playerCount+1); i++) {
            players.add(new Player("Player " + i, this.deck));
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