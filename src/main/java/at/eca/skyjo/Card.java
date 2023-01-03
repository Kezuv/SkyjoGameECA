package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private int value;
    private boolean face;
    private ImageView cardViewImage, cardViewBack;

    public Card(int value) {
        this.value = value;
        this.face = false;
        String fileName = "Card_" + value + ".png";
        Image cardImage = new Image("/at/eca/skyjo/img/" + fileName);
        cardViewImage = new ImageView(cardImage);
        Image cardBack = new Image("/at/eca/skyjo/img/cardBackground.png");
        cardViewBack = new ImageView(cardBack);
    }

    public Card(boolean willBeReplaced) {
        this.value = 0;
        this.face = true;
   //     this.imgCard = imgCard;
    }

    public ImageView getCardViewImage() {
        face = true;
        return cardViewImage;
    }

    public ImageView getCardViewBack() {
        face = false;
        return cardViewBack;
    }

    public void flip() {
        setFace(true);
        //face = !face;
        //this.faceUp = !this.faceUp;
    }

    public boolean getFace(){
        return face;
    }
    public void setFace(boolean face) {
        this.face = face;
    }
    public int getValue() {
        return this.value;
    }



    @Override
    public String toString() {
        return "" + value;
    }


}
