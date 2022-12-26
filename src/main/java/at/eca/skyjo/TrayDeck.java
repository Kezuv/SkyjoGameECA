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
}
