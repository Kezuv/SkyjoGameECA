package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Player {


    private String name;
    private final List<Card> hand;
    private Image cardBackground;
    private ImageView cardViewBackground;
    private List<ImageView> cardBack = new ArrayList<>();
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


    public void flipAllCards(){
        for (int i = 0; i < hand.size(); i ++){
            hand.get(i).flip();
        }
    }

    public String getTotalScoreToString() {
        for (Card card : hand) {
            score = score + card.getValue();
        }
        return "total score: " + score;
    }

    public int getScore(){
        for (Card card : hand) {
            score = score + card.getValue();
        }
        return score;
    }

    public String getName() {
        return name;
    }

    public String scoreToString() {
        int values = 0;
        int notVisible = 0;

        for (Card toCheck : hand) {
            if (!toCheck.isFaceUp()) {
                notVisible = notVisible + 1;
            } else {
                values = values + toCheck.getValue();
            }
        }
        return "" + values + " Points + " + notVisible + " not flipped.";
    }

    public int startUpPoints() {
        int values = 0;
        int notVisible = 0;

        for (Card toCheck : hand) {
            if (!toCheck.isFaceUp()) {
                notVisible = notVisible + 1;
            } else {
                values = values + toCheck.getValue();
            }
        }
        return values;
    }

    public boolean checkIfFinished() {
        int checkBoolean = 0;
        for (int i = 0; i < hand.size(); i ++){
            if (hand.get(i).isFaceUp()){
                checkBoolean ++;
            }
        } return checkBoolean == 12;
    }

    public Card getCard(int cardPosition) {
        return hand.get(cardPosition);
    }

    public List<Card> getHand() {
        return hand;
    }

}
