package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private List<Card> discardPile;

    private List<Card> tempPile;

    private Image cardBackground;
    private ImageView cardViewBackground;
    private List<ImageView> cardBack = new ArrayList<>();



    public Deck() {

        cards = new ArrayList<>();
        for (int i = -2; i <= 12; i++) {
            int numCards = 10;
            if (i == -2) {
                numCards = 5;
            } else if (i == 0) {
                numCards = 15;
            }
            for (int j = 0; j < numCards; j++) {
                cards.add(new Card(i));
            }
        }
        Collections.shuffle(cards);

        for (int i = 1; i <= 150; i++) {
            cardBackground = new Image("/at/eca/skyjo/img/cardBackground.png");
            cardViewBackground = new ImageView(cardBackground);
            cardBack.add(cardViewBackground);
        }
        discardPile = new ArrayList<>();
    }

    public List<ImageView> getCardBack() {
        return cardBack;
    }

    public List<Card> getTempPile() {
        return tempPile;
    }




    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public Card addToDiscardPile() {
        if (cards.isEmpty()) {
            return null;
        }
        discardPile.add(cards.remove(0));
        return discardPile.get(0);
    }

    public Card getDiscardCard() {
        return discardPile.get(0);
    }



    public Card addToTemp() {
        tempPile.add(discardPile.remove(0));
        return tempPile.get(0);
    }



    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public Card getDeckCard() {
        return cards.get(0);
    }

    public int getSizeCards(){
        return cards.size();
    }

    public int getSizeDiscardPile() {
        return discardPile.size();
    }

    /*
    public void swapHandDiscard(int intHand, Deck deck, Player player) {
        List<Card> temp = new ArrayList<>();
        temp.add(deck.getDiscardPile().remove(0));
        deck.getDiscardPile().add(player.getHand().remove(intHand));
        player.getHand().add(temp.get(0));

    }

     */

    public void swapHandDiscard(int intHand, Deck deck, Player player) {
        List<Card> temp = new ArrayList<>();
        List<Card> temp2 = new ArrayList<>();
        temp.add(0, deck.getDiscardPile().remove(0));
        temp2.add(player.getHand().remove(intHand));
        deck.getDiscardPile().add(0, temp2.remove(0));
        player.getHand().add(getDiscardPile().size() -1, temp.remove(intHand));

        //deck.getDiscardPile().add(player.getHand().remove(intHand));


    }

}