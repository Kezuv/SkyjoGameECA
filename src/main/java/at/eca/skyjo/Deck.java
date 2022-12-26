package at.eca.skyjo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

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

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    public int getSize(){
        return cards.size();
    }
}