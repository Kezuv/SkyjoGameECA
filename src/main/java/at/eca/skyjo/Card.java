package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private int value;
    private boolean faceUp;

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
        this.faceUp = false;
        String fileName = "Card_" + value + ".png";
        cardImage = new Image("/at/eca/skyjo/img/" + fileName);
        cardViewImage = new ImageView(cardImage);
        cardBack = new Image("/at/eca/skyjo/img/CardBackground.png");
        cardViewBack = new ImageView(cardBack);
    }

    public Card(boolean willBeReplaced) {
        this.value = 0;
        this.faceUp = true;
   //     this.imgCard = imgCard;
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

   public Image getCardImage() {
        return cardImage;
   }

    //@Override
   // public String toString() {
    //    return "" + value;
   // }


}
