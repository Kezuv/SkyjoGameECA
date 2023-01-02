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

    private Image imageBack;
    private ImageView viewBackground;
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
        imageBack =  new Image("/at/eca/skyjo/img/CardBackground.png");
        viewBackground = new ImageView(imageBack);
        //for (int i = 1; i <= 150; i++) {
        //    cardBackground = new Image("/at/eca/skyjo/img/cardBackground.png");
        //    cardViewBackground = new ImageView(cardBackground);
       //     cardBack.add(cardViewBackground);
       // }
        //discardPile = new ArrayList<>();
    }

    public List<ImageView> getCardBack() {
        return cardBack;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public void addToDiscardPile() {
        Card tmp = cards.get(0);
        tmp.flip();
        discardPile.add(tmp);
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
    
    public ImageView getCardViewBackground() {
        if (cards.size() == 0){
            return null;
        } else {
        return viewBackground;}

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

    public void swap(Player player, int cardNumber) {

        Card discardCard = discardPile.get(0);
        discardPile.remove(0);
        discardCard.flip();
        Card playerCard = player.getCard(cardNumber);
        playerCard.flip();
        player.getHand().remove(cardNumber);

        player.getHand().add(cardNumber, discardCard);
        discardPile.add(playerCard);

    }


    public void addDeckToDiscardPile() {

        Card temp = cards.get(0);
        cards.remove(0);
        temp.flip();
        discardPile.add(0, temp);

    }
}