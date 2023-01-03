package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    private final List<Card> discardPile = new ArrayList<>();

    private List<Card> temp1 = new ArrayList<>();
    private List<Card> temp2 = new ArrayList<>();

    private Image cardBackground;
    private ImageView cardViewBackground;
    private List<ImageView> cardBack = new ArrayList<>();



    public Deck() {

        //cards = new ArrayList<>();
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
        //discardPile = new ArrayList<>();
    }

    public List<ImageView> getCardBack() {
        return cardBack;
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

    public void addToDiscardPile() {

        discardPile.add(cards.remove(0));
        //return discardPile.get(0);
    }

    public Card getDiscardCard() {
        return discardPile.get(0);
    }







    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public List<Card> getCards() {
        return cards;
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

     /*

    public void swapHandDiscard(int intHand, Deck deck, Player player) {

        temp1.add(0, deck.getDiscardPile().remove(0));
        temp2.add(player.getHand().remove(intHand));
        deck.getDiscardPile().add(0, temp2.remove(0));

        player.getHand().add(0, temp1.remove(intHand));

        //deck.getDiscardPile().add(player.getHand().remove(intHand));


    }

    */

    public void swap(Player player, int cardNumber) {

        /*
        Card temp = discardPile.get(0);
        discardPile.set(0, player.getHand().get(0));
        player.getHand().set(0, temp);

         */

        temp1.add(0, discardPile.remove(0));
        temp2.add(0, player.getHand().remove(cardNumber));
        player.getHand().add(cardNumber, temp1.remove(0));
        discardPile.add(0, temp2.remove(0));

        player.addToScore(cardNumber);



    }



    public void addDeckToDiscardPile() {

        //Card temp = deck.getCards().get(0);
        //deck.getCards().remove(0);
        //deck.getDiscardPile().add(temp);

        discardPile.add(0, cards.remove(0));

    }



}