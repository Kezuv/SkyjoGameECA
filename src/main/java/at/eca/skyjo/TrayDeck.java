package at.eca.skyjo;

import java.util.ArrayList;
import java.util.List;

public class TrayDeck {

    private List<Card> traycards;

    public TrayDeck() {
        traycards = new ArrayList<Card>();
    }

    public Card draw() {
        if (traycards.isEmpty()) {
            return null;
        }
        return traycards.remove(0);
    }

    public void addCardtoTray(Card withdraw){
        withdraw.flip();
        traycards.add(withdraw);
    }

    public void swapHandDiscard(int intHand, Deck deck, Player player) {
        List<Card> temp = new ArrayList<>();
        temp.add(deck.getDiscardPile().remove(0));
        temp.add(player.getHand().remove(intHand));
        deck.getDiscardPile().add(temp.get(0));
        player.getHand().add(temp.get(intHand));

        //deck.getDiscardPile().add(player.getHand().remove(intHand));


    }
}
