package at.eca.skyjo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    public int getNum() {
        return num;
    }

    public void incrementNum() {
        num++;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private int num = 0;





    private boolean endOfRound = false;
    private void setEndOfRound(boolean change) {
        endOfRound = change;
    }

    private boolean getEndOfRound() {
        return endOfRound;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private Player currentPlayer;

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }




    public int getRoundCounter() {
        return roundCounter;
    }

    public void incrementRoundCounter() {
        roundCounter++;
    }

    private int roundCounter;
    private int clickCounter;

    public int getClickLeftHandCounter() {
        return clickLeftHandCounter;
    }

    public void incrementClickLeftHandCounter() {
        clickLeftHandCounter++;
    }

    public void resetClickLeftHandCounter() {
        clickLeftHandCounter = 0;
    }

    public int getClickRightHandCounter() {
        return clickRightHandCounter;
    }

    public void incrementClickRightHandCounter() {
        clickRightHandCounter++;
    }

    public void resetClickRightHandCounter() {
        clickRightHandCounter = 0;
    }

    public int getClickDeckCounter() {
        return clickDeckCounter;
    }

    public void incrementClickDeckCounter() {
        clickDeckCounter++;
    }
    public void resetClickDeckCounter() {
        clickDeckCounter = 0;
    }

    private int clickLeftHandCounter;
    private int clickRightHandCounter;
    private int clickDeckCounter;


    public int getPlayerMoveCounter() {
        return playerMoveCounter;
    }
    public void resetPlayerMoveCounter() {
        playerMoveCounter = 0;
    }

    public void incrementPlayerMoveCounter() {
        playerMoveCounter++;
    }

    private int playerMoveCounter;

    public int getPlayersRoundCounter() {
        return playersRoundCounter;
    }

    public void incrementPlayersRoundCounter() {
        playersRoundCounter++;
    }
    public void resetPlayersRoundCounter() {
        playersRoundCounter = 0;
    }

    private int playersRoundCounter;


    private List<Player> players;
    private Deck deck;


    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    private String instruction;



    public Game(int playerCount) {
        Random rand = new Random();
        players = new ArrayList<>();
        deck = new Deck();
        roundCounter = 0;
        clickCounter = 0;
        playerMoveCounter = 0;
        for (int i = 1; i < (playerCount+1); i++) {
            players.add(new Player("Player " + i, this.deck));
        }
        //players.get(0).changeStatusPlayer();
        deck.addToDiscardPile();
        players.get(0).setStatusPlayer(true);

        setInstruction("Attention players! Each player reveals 2 of their own cards. Starting with Player 1. Then click the next Button, to determine who will begin the Game.");




    }

    public void addPlayer(Player player) {
        players.add(player);
    }


    public void startRound() {
        if ( getRoundCounter() == 0 && playerMoveCounter == 0) {
            determineFirstPlayer();
        }
    }
    public void playRound() {

        /*
        if ( getRoundCounter() == 0 && playerMoveCounter == 0) {
            determineFirstPlayer();
        }

         */

        System.out.println("test");
        if (getRoundCounter() > 0) {

            /*
            if (currentPlayer == players.get(0)) {
                setInstruction("Player 1, it's your turn!");
                //setCurrentPlayer(players.get(1));
                //players.get(0).changeStatusPlayer();
            }
            if (currentPlayer == players.get(1)) {
                setInstruction("Player 2, it's your turn!");
                //setCurrentPlayer(players.get(0));
                //players.get(1).changeStatusPlayer();
            }

             */



        }


        playerMoveCounter++;
    }

    public boolean checkIfEndOfRound(Player player) {

        int numOfCardsFaceUp = 0;

            for (int x = 0; x < player.getHand().size(); x++) {
               if (player.getHand().get(x).getFace()) {
                   numOfCardsFaceUp++;
               }
            }

          if (numOfCardsFaceUp == player.getHand().size()) {
              return endOfRound = true;
          } else {
              return endOfRound = false;
          }

    }



    public void incrementClickCounter() {
        clickCounter++;
    }

    public int getClickCounter() {
        return clickCounter;
    }

    public int resetClickCounter() {
        return clickCounter = 0;
    }

    private void determineFirstPlayer() {
        int maxScore = -10;
        for (Player player : players) {
            int score = player.getScore();
            if (score > maxScore) {
                maxScore = score;
            }
        }
        for (Player player : players) {
            if (player.getScore() == maxScore) {
                player.setStatusPlayer(true);
                //player.changeStatusPlayer();
                currentPlayer = player;
                setInstruction(player.getName() + ", it's your turn!");
            }
            if (player.getScore() != maxScore) {
                player.setStatusPlayer(false);
            }
        }

    }






    public List<Player> getAllPlayers() {
        return players;
    }

    public Player getPlayers(int playerNumber) {
        return players.get(playerNumber);
    }



    public Deck getDeck() {
        return deck;
    }



    private void endGame() {

    }

}