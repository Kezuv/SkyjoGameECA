package at.eca.skyjo;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private String name;
    private final List<Card> hand;




    private boolean statusPlayer;
    private int score;

    public int getGameScore() {
        return gameScore;
    }

    public void addGameScore(int score) {
        gameScore += score;
    }

    private int gameScore;




    public Player(String name, Deck deck) {
        this.name = name;
        hand = new ArrayList<Card>();
        dealCards(deck);


        statusPlayer = false;
        score = 0;
    }

    public void dealCards(Deck deck) {
        for (int i = 0; i < 12; i++) {
            Card card = deck.draw();
            hand.add(card);
        }
    }


    public void swapCard(Card card, int place, TrayDeck tray) {
        Card swapped = hand.get(place);
        hand.set(place, card);
        tray.addCardtoTray(swapped);

    }

    public void setStatusPlayer(boolean statusPlayer) {
        this.statusPlayer = statusPlayer;
    }

    public boolean getStatusPlayer() {
        return statusPlayer;
    }

    public void changeStatusPlayer() {
        statusPlayer = !statusPlayer;
    }

    public void addToScore(int cardNumber) {

        score += hand.get(cardNumber).getValue();
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String scoreToString(){
        int values =  0;
        int notVisible = 0;

        for (Card toCheck : hand) {
            if (!toCheck.getFace()) {
                notVisible = notVisible + 1;
            } else {
                values = values + toCheck.getValue();
            }
        }
        return "" + values + " Points | " + notVisible + " Cards not flipped.";
    }

    public boolean checkIfFinished(){
        int checkBoolean = 0;
        for (int i = 0; i < hand.size(); i++){
            if (!hand.get(i).getFace()){
                checkBoolean ++;
            }
        }
        if (checkBoolean!=0){
            return false;
        } else {
            return true;
        }
    }

    public Card getCard (int cardPosition){
        return hand.get(cardPosition);
    }
    public void setCard (int cardPosition, Card card) {
        hand.set(cardPosition, card);

    }

    public List<Card> getHand() {
        return hand;
    }


    private Deck deck;

    public void three(Deck deck) {


        /*
        for (int i = 0; i < getHand().size(); i+= 3) {
        int i = 0;
            Card first = getHand().get(i);
            Card second = getHand().get(i + 1);
            Card third = getHand().get(i + 2);
            if (first.getValue() == second.getValue() && second.getValue() == third.getValue()) {
                deck.getDiscardPile().add(0, first);
                deck.getDiscardPile().add(0, second);
                deck.getDiscardPile().add(0, third);
                getHand().remove(0);
                getHand().remove(0);
                getHand().remove(0);


            }
        }

         */



        if(getHand().get(0).getValue() == getHand().get(1).getValue() && getHand().get(1).getValue() == getHand().get(2).getValue()) {
            deck.getDiscardPile().add(0, getHand().get(0));
            deck.getDiscardPile().add(0, getHand().get(1));
            deck.getDiscardPile().add(0, getHand().get(2));
            getHand().remove(getHand().get(0));
            getHand().remove(getHand().get(0));
            getHand().remove(getHand().get(0));
        }

        if(getHand().size() >= 6 && getHand().get(3).getValue() == getHand().get(4).getValue() && getHand().get(4).getValue() == getHand().get(5).getValue()) {
            deck.getDiscardPile().add(0, getHand().get(3));
            deck.getDiscardPile().add(0, getHand().get(4));
            deck.getDiscardPile().add(0, getHand().get(5));
            getHand().remove(getHand().get(3));
            getHand().remove(getHand().get(3));
            getHand().remove(getHand().get(3));
        }

        if(getHand().size() >= 9 && getHand().get(6).getValue() == getHand().get(7).getValue() && getHand().get(7).getValue() == getHand().get(8).getValue()) {
            deck.getDiscardPile().add(0, getHand().get(6));
            deck.getDiscardPile().add(0, getHand().get(7));
            deck.getDiscardPile().add(0, getHand().get(8));
            getHand().remove(getHand().get(6));
            getHand().remove(getHand().get(6));
            getHand().remove(getHand().get(6));
        }

        if(getHand().size() >= 12 && getHand().get(9).getValue() == getHand().get(10).getValue() && getHand().get(10).getValue() == getHand().get(11).getValue()) {
            deck.getDiscardPile().add(0, getHand().get(9));
            deck.getDiscardPile().add(0, getHand().get(10));
            deck.getDiscardPile().add(0, getHand().get(11));
            getHand().remove(getHand().get(9));
            getHand().remove(getHand().get(9));
            getHand().remove(getHand().get(9));
        }





        /*
        deck.getDiscardPile().add(0, getHand().get(0));

        deck.getDiscardPile().add(0, getHand().get(1));

        deck.getDiscardPile().add(0, getHand().get(2));
        getHand().remove(getHand().get(0));
        getHand().remove(getHand().get(0));
        getHand().remove(getHand().get(0));

         */
    }






    /*

    public void threeOfAKind(TrayDeck tray){
        if ((hand.get(0).getValue() == hand.get(4).getValue() && hand.get(0).getValue() == hand.get(8).getValue()) &&
                (hand.get(0).getFace() == hand.get(4).getFace() == hand.get(8).getFace())){

            Card replaceCard = new Card(true);
            swapCard(replaceCard,0, tray);
            swapCard(replaceCard,4,tray);
            swapCard(replaceCard,8,tray);

        } else if ((hand.get(1).getValue() == hand.get(5).getValue() && hand.get(1).getValue() == hand.get(9).getValue()) &&
                (hand.get(1).getFace() == hand.get(5).getFace() == hand.get(9).getFace())) {


            Card replaceCard = new Card(true);
            swapCard(replaceCard,1, tray);
            swapCard(replaceCard,5,tray);
            swapCard(replaceCard,9,tray);
        } //else if () {

      //  } else if () {

      //  }

    }

     */

}
