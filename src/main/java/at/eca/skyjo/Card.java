package at.eca.skyjo;

import javafx.scene.image.Image;

public class Card {
    private int value;
    private boolean faceUp;

    private Image imgCard;

    public Card(int value) {
        this.value = value;
        this.faceUp = false;
    }

    public void flip() {
        this.faceUp = !this.faceUp;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Value: " + value;
    }
}
