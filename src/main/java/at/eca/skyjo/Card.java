package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private int value;
    private boolean faceUp;
    private boolean locked;

    private Image cardImage;
    private Image cardBack;
    private ImageView cardViewImage;
    private ImageView cardViewBack;


    /**
     * Card constructor to initialize card objects
     *
     * @param value the value of the card. Used to get the correct picture.
     */
    public Card(int value) {
        this.value = value;
        faceUp = false;
        locked = false;
        String fileName = "Card_" + value + ".png";
        cardImage = new Image("/at/eca/skyjo/img/" + fileName);
        cardViewImage = new ImageView(cardImage);
        cardBack = new Image("/at/eca/skyjo/img/CardBackground.png");
        cardViewBack = new ImageView(cardBack);
    }

    /**
     * Card constructor to initialize card objects. Used to generate blank cards with no value.
     *
     * @param locked to lock blank cards.
     */
    public Card(boolean locked) {
        this.locked = locked;
        faceUp = true;
        value = 0;
        cardViewImage = new ImageView();
    }

    public ImageView getCardViewImage() {
        if (isFaceUp()){
            return cardViewImage;}
        else {return cardViewBack;}
    }

    /**
     * changes the boolean attribute faceUp.
     */
    public void flip() {
        this.faceUp = true;
    }

    /**
     * shows if the card is face up or down.
     *
     * @return the boolean faceUp
     */
    public boolean isFaceUp(){
        return faceUp;
    }

    /**
     * shows the value of the card.
     *
     * @return the value of the card
     */
    public int getValue() {
        return this.value;
    }

    /**
     * shows if the card is locked or not.
     *
     * @return the boolean locked
     */
    public boolean isLocked() {
        return locked;
    }



}
