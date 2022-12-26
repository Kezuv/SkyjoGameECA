package at.eca.skyjo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private List<Card> hand;
    private int score;

    public Player(String name, Deck deck) {
        this.name = name;
        hand = new ArrayList<Card>(12);
        dealCards(deck);
        this.score = 0;
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

        threeOfAKind(tray);
    }

    public void addToScore(int value) {
        score += value;
    }

    public int getScore() {
        return score;
    }

    public String scoreToString(){
        int values =  0;
        int notvisible = 0;

        for (int i = 0; i<hand.size(); i++){
            Card tocheck = hand.get(i);

            if (!tocheck.isFaceUp()){
                notvisible = notvisible + 1;
            } else {
                values = values + tocheck.getValue();
            }
        }
        return "" + values + " Points + " + notvisible + " not visible.";
    }

    public boolean checkIfFinished(){
        int checkBoolean = 0;
        for (int i = 0; i < hand.size(); i++){
            if (!hand.get(i).isFaceUp()){
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

    public void threeOfAKind(TrayDeck tray){
        if ((hand.get(0).getValue() == hand.get(4).getValue() && hand.get(0).getValue() == hand.get(8).getValue()) &&
                (hand.get(0).isFaceUp() == hand.get(4).isFaceUp() == hand.get(8).isFaceUp())){

            Card replaceCard = new Card(true);
            swapCard(replaceCard,0, tray);
            swapCard(replaceCard,4,tray);
            swapCard(replaceCard,8,tray);

        } else if ((hand.get(1).getValue() == hand.get(5).getValue() && hand.get(1).getValue() == hand.get(9).getValue()) &&
                (hand.get(1).isFaceUp() == hand.get(5).isFaceUp() == hand.get(9).isFaceUp())) {

            Card replaceCard = new Card(true);
            swapCard(replaceCard,1, tray);
            swapCard(replaceCard,5,tray);
            swapCard(replaceCard,9,tray);
        } //else if () {

      //  } else if () {

      //  }

    }
}
