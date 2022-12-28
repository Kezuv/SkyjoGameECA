package at.eca.skyjo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private int value;
    private boolean faceUp;

    private Image cardImage;
    private ImageView cardViewImage;

    //private Image cardBackground;
    //private ImageView cardViewBackground;


    public ImageView getCardViewImage() {
        this.faceUp = true;
        return cardViewImage;
    }

    public Card(int value) {
        this.value = value;
        this.faceUp = false;
        String fileName = "Card_" + value + ".png";
        cardImage = new Image("/at/eca/skyjo/img/" + fileName);
        cardViewImage = new ImageView(cardImage);
    }

    public Card(boolean willbereplaced) {
        this.value = 0;
        this.faceUp = true;
   //     this.imgCard = imgCard;
    }

    public void flip() {
        this.faceUp = !this.faceUp;
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



    /*
    @Override
    public String toString() {
        return "Value: " + value;
    }

     */
}
