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



    public ImageView getCardViewImage() {
        if (isFaceUp()){
        return cardViewImage;}
        else {return cardViewBack;}
    }

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

    public Card(boolean locked) {
        this.locked = locked;
        faceUp = true;
        value = 0;
        cardViewImage = new ImageView();
    }

    public void flip() {
        this.faceUp = true;
    }

    public boolean isFaceUp(){
        return faceUp;
    }
    public int getValue() {
        return this.value;
    }

    public boolean isLocked() {
        return locked;
    }

    public Image getCardImage() {
        return cardImage;
   }

}
