package at.eca.skyjo;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<Player> players;
    private Deck deck;

    private boolean firstRound = true;
    private int isPlaying = 1;
    private int movesLeft = 2;
    private int finalround = 99;
    private boolean canPickUp = false;


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
        // Check if FirstRound
        if (firstRound){
            if (isPlaying != maxPlayer){
                movesLeft = 2;
                isPlaying = (hasPlayed+1);
                return isPlaying;
            } else {
                movesLeft = 1;
                int maxPlayerPoints = players.get(maxPlayer-1).startUpPoints();
                int nextPlayer = maxPlayer;
                for (int i = (maxPlayer-1); i >= 0; i--){
                    if (maxPlayerPoints < players.get(i).startUpPoints()) {
                        maxPlayerPoints = players.get(i).startUpPoints();
                        nextPlayer = (i + 1);
                    }
                    if (maxPlayerPoints == players.get(i).startUpPoints()){
                        nextPlayer = (i+1);
                    }
                }
                isPlaying = nextPlayer;
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
                    if (maxPlayer == hasPlayed){
                        finalround = (hasPlayed-1);
                    }else {
                        finalround = (maxPlayer - hasPlayed);
                    }
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
                finalround --;
                if (finalround == 0 ){
                    movesLeft = 0;
                    canPickUp = false;
                    return 0;}
                else {
                    movesLeft = 1;
                    canPickUp = true;
                    if (hasPlayed == maxPlayer) {
                        isPlaying = 1;
                        return isPlaying;
                    } else {
                        isPlaying = (hasPlayed + 1);
                        return isPlaying;
                    }
                }
            }

        }
        return 99;
    }
    public int whoWonTheRound(){
        int maxScore = 500;
        int hasWonTheRound=0;
        for (int i = 0; i < players.size(); i ++){
            if (maxScore > players.get(i).getScore()){
                maxScore = players.get(i).getScore();
                hasWonTheRound = i;
            }
        }
        return hasWonTheRound;
    }
    public Game(int playerCount) {
        players = new ArrayList<>();
        deck = new Deck();

        for (int i = 1; i < (playerCount+1); i++) {
            players.add(new Player("Player " + i, this.deck));
        }
    }
    public Game(String player1Name, String player2Name){
        players = new ArrayList<>();
        deck = new Deck();
        players.add(new Player(player1Name, this.deck));
        players.add(new Player(player2Name, this.deck));
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
    public Player getPlayers(int playerNumber) {
        return players.get(playerNumber);
    }
    public Deck getDeck() {
        return deck;
    }
    public String whoIsPlayingText(){
        return "It is "+players.get(isPlaying-1).getName()+"'s turn!";
    }
    public String instructionLabelText(){
        if (firstRound){
                return "" + players.get(isPlaying - 1).getName() + " have to reveal two cards.\n" +
                        "The player with the highest score begins the first round!";
        }
        else if (finalround == 99){
            if (canPickUp) {
            return ""+players.get(isPlaying-1).getName()+" can swap with the discard card \n"+
                    "or pick up a new one";
            } else if (movesLeft != 0) {
                return "" + players.get(isPlaying - 1).getName() + " can swap with the discard card \n" +
                        "or flip one of your own.";
            } else {
                return "" + players.get(isPlaying - 1).getName() + " you finished your turn.\n" +
                        "Please press the \"Next Player\" - Button!";
            }
        }
        else if (finalround == 0) {
            switch (players.size()){
                case 2:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString();
                case 3:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString()+"\n"+
                            players.get(2).getName()+" "+ players.get(2).getTotalScoreToString();
                case 4:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString()+"\n"+
                            players.get(2).getName()+" "+ players.get(2).getTotalScoreToString()+"\n"+
                            players.get(3).getName()+" "+ players.get(3).getTotalScoreToString();
                case 5:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString()+"\n"+
                            players.get(2).getName()+" "+ players.get(2).getTotalScoreToString()+"\n"+
                            players.get(3).getName()+" "+ players.get(3).getTotalScoreToString()+"\n"+
                            players.get(4).getName()+" "+ players.get(4).getTotalScoreToString();
                case 6:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString()+"\n"+
                            players.get(2).getName()+" "+ players.get(2).getTotalScoreToString()+"\n"+
                            players.get(3).getName()+" "+ players.get(3).getTotalScoreToString()+"\n"+
                            players.get(4).getName()+" "+ players.get(4).getTotalScoreToString()+"\n"+
                            players.get(5).getName()+" "+ players.get(5).getTotalScoreToString();
                case 7:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString()+"\n"+
                            players.get(2).getName()+" "+ players.get(2).getTotalScoreToString()+"\n"+
                            players.get(3).getName()+" "+ players.get(3).getTotalScoreToString()+"\n"+
                            players.get(4).getName()+" "+ players.get(4).getTotalScoreToString()+"\n"+
                            players.get(5).getName()+" "+ players.get(5).getTotalScoreToString()+"\n"+
                            players.get(6).getName()+" "+ players.get(6).getTotalScoreToString();
                case 8:
                    return "Round is over! \n"+
                            players.get(whoWonTheRound()).getName()+" has won this round! \n"+
                            players.get(0).getName()+" "+ players.get(0).getTotalScoreToString()+"\n"+
                            players.get(1).getName()+" "+ players.get(1).getTotalScoreToString()+"\n"+
                            players.get(2).getName()+" "+ players.get(2).getTotalScoreToString()+"\n"+
                            players.get(3).getName()+" "+ players.get(3).getTotalScoreToString()+"\n"+
                            players.get(4).getName()+" "+ players.get(4).getTotalScoreToString()+"\n"+
                            players.get(5).getName()+" "+ players.get(5).getTotalScoreToString()+"\n"+
                            players.get(6).getName()+" "+ players.get(6).getTotalScoreToString()+"\n"+
                            players.get(7).getName()+" "+ players.get(7).getTotalScoreToString();
            }
        } else {
            if (canPickUp) {
                return "Final round! \n"
                        +players.get(isPlaying-1).getName()+" can swap with the discard card \n"+
                        "or pick up a new one the last time!";
            } else if (movesLeft != 0){
                return "Final round! \n"
                        +players.get(isPlaying-1).getName()+" can swap with the discard card \n"+
                        "or flip one of your own the last time!";
            } else {
                return "" + players.get(isPlaying - 1).getName() + " you finished your turn.\n" +
                        "Please press the \"Next Player\" - Button and see who won this round!";
            }
        }

            return "Failure by instructionLabelText - Game Class!";
    }
    public boolean isFirstRound() {
        return firstRound;
    }
    public int getIsPlaying() {
        return isPlaying;
    }
    public int getMovesLeft() {
        return movesLeft;
    }
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }
    public boolean isCanPickUp() {
        return canPickUp;
    }
    public void setCanPickUp(boolean canPickUp) {
        this.canPickUp = canPickUp;
    }
}