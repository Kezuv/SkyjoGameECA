package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {


    private String name;
    private final List<Card> hand;
    private Image cardBackground;
    private ImageView cardViewBackground;
    private List<ImageView> cardBack = new ArrayList<>();
    private int score;


    public ImageView getCardViewBackground() {
        return cardViewBackground;
    }

    public List<ImageView> getCardBack() {
        return cardBack;
    }

    public Player(String name, Deck deck) {
        this.name = name;
        hand = new ArrayList<Card>();
        dealCards(deck);
        for (int i = 1; i <= 12; i++) {
            cardBackground = new Image("/at/eca/skyjo/img/cardBackground.png");
            cardViewBackground = new ImageView(cardBackground);
            cardBack.add(cardViewBackground);
        }

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

    public String getName() {
        return name;
    }

    public String scoreToString(){
        int values =  0;
        int notVisible = 0;

        for (Card toCheck : hand) {
            if (!toCheck.isFaceUp()) {
                notVisible = notVisible + 1;
            } else {
                values = values + toCheck.getValue();
            }
        }
        return "" + values + " Points + " + notVisible + " not visible.";
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
    public void setCard (int cardPosition, Card card) {
        hand.set(cardPosition, card);

    }

    public List<Card> getHand() {
        return hand;
    }


    private Deck deck;

    public void swapHandDiscard() {

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
