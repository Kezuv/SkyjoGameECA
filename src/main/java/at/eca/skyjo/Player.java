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
}