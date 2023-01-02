package at.eca.skyjo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ControllerTwoPlayer implements Initializable {

    @FXML
    public Button nextPlayerButton;
    public Label labelWhoIsPlaying;
    public Label player1Turn;
    public Label player2Turn;
    @FXML
    private Button p1c0;
    @FXML
    private Button p1c1;
    @FXML
    private Button p1c2;
    @FXML
    private Button p1c3;
    @FXML
    private Button p1c4;
    @FXML
    private Button p1c5;
    @FXML
    private Button p1c6;
    @FXML
    private Button p1c7;
    @FXML
    private Button p1c8;
    @FXML
    private Button p1c9;
    @FXML
    private Button p1c10;
    @FXML
    private Button p1c11;

    // player 2

    @FXML
    private Button p2c0;
    @FXML
    private Button p2c1;
    @FXML
    private Button p2c2;
    @FXML
    private Button p2c3;
    @FXML
    private Button p2c4;
    @FXML
    private Button p2c5;
    @FXML
    private Button p2c6;
    @FXML
    private Button p2c7;
    @FXML
    private Button p2c8;
    @FXML
    private Button p2c9;
    @FXML
    private Button p2c10;
    @FXML
    private Button p2c11;

    @FXML
    private Button deckImgFaceDown;

    @FXML
    private Button deckImgFaceUp;
    //@FXML
    //private Button imgRulesBtn;

    // ====


    // label

    @FXML
    private Label labelScorePlayer1;
    @FXML
    private Label labelScorePlayer2;
    @FXML
    private Label labelCardsLeftDeck;
    @FXML
    private Label labelCardsLeftDiscard;

    @FXML
    private Label labelInstructions;


    /*
    @FXML
    Image imgRulesIcon = new Image("/at/eca/skyjo/img/iconRules.png");
    ImageView viewRulesBtn = new ImageView(imgRulesIcon);


     */

    // label ende
    private String viewStart = "/at/eca/skyjo/fxml/sceneStart.fxml";
    //private String viewRules = "/at/eca/skyjo/fxml/sceneRules.fxml";

    Game gameTwoPlayer = new Game(2);

    Deck deck = gameTwoPlayer.getDeck();
    Player player1 = gameTwoPlayer.getPlayers(0);
    Player player2 = gameTwoPlayer.getPlayers(1);





    // ================== methoden ========================

    public ImageView getCardBack(Player player, int cardNumber){
        return player.getCard(cardNumber).getCardViewImage();
    }



    public void clickedButton (Player player, int i, Button button, int cardNumber) throws IOException {
        button.setOnMouseClicked(event1 -> {
            if (gameTwoPlayer.permissionCheck(player, i)) {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    if (!gameTwoPlayer.canPickUp) {
                        if (player == player1) {
                            player1Table(cardNumber, button);
                            gameTwoPlayer.movesLeft--;
                        } else if (player == player2) {
                            player2Table(cardNumber, button);
                            gameTwoPlayer.movesLeft--;
                        }
                    }

                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, button, player, cardNumber);
                    gameTwoPlayer.movesLeft--;
                }
            }
        });
    }

    public void pickUpCardFromDown(ActionEvent actionEvent) {
        deckImgFaceDown.setOnMouseClicked(e -> {
            if (gameTwoPlayer.canPickUp) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    deck.addDeckToDiscardPile();
                    deckImgFaceUp.setGraphic(deck.getDiscardCard().getCardViewImage());
                    labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
                    labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
                    gameTwoPlayer.canPickUp = false;
                }
            }
        });
    }



    public void swapHandDiscard(Deck deck, Button playerButton, Player player, int cardNumber) {
        deck.swap(player, cardNumber);
        gameTwoPlayer.canPickUp = false;
        playerButton.setGraphic(player.getCard(cardNumber).getCardViewImage());
        deckImgFaceUp.setGraphic(deck.getDiscardCard().getCardViewImage());
        if(player == player1) {
            labelScorePlayer1.setText(player1.scoreToString());
        } else if (player == player2) {
            labelScorePlayer2.setText(player2.scoreToString());
        }
    }

    public void nextPlayer(ActionEvent actionEvent) {
        switch (gameTwoPlayer.gamePlay(gameTwoPlayer.getPlayers((gameTwoPlayer.isPlaying-1)), gameTwoPlayer.isPlaying)){
            case 1:
                player1Turn.setText(""+player1.getName()+" ist am Zug!");
                player2Turn.setText("");
                break;
            case 2:
                player2Turn.setText(""+player2.getName()+" ist am Zug!");
                player1Turn.setText("");
                break;
            case 0:
                labelWhoIsPlaying.setText(""+gameTwoPlayer.getPlayers(gameTwoPlayer.finalround).getName()+" hat die Runde gewonnen!");
                player1Turn.setText("");
                player2Turn.setText("");
                break;
            case 99:
                labelWhoIsPlaying.setText("Fehler beim Gameplay!!!");
                player2Turn.setText("");
                player1Turn.setText("");
                break;
        }
    }
    @FXML
    public void buttonGoBackStartScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewStart));
        Parent startView = loader.load();
        Scene sceneStart = new Scene(startView);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneStart);
        window.show();

    }

    /*
    public void buttonRulesChangeScene(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewRules));
        Parent viewRules = loader.load();
        Scene sceneRules = new Scene(viewRules);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneRules);
        window.show();


    }

     */


    // +++++++++++++++ PLAYER ONE Actions ++++++++++++++++++++++++

    /*
    @FXML
    public void playerOneCard00(ActionEvent event) {

        pOneCard00GameTwo.setGraphic((Node) player1.getCard(0).getCardViewImage());
    }
     */

    // Players Table Setup
    public void player1Table(int cardNumber, Button playerButton){
        player1.getCard(cardNumber).flip();
        playerButton.setGraphic(player1.getCard(cardNumber).getCardViewImage());
        labelScorePlayer1.setText(player1.scoreToString());
    }
    public void player2Table(int cardNumber, Button playerButton){
        player2.getCard(cardNumber).flip();
        playerButton.setGraphic(player2.getCard(cardNumber).getCardViewImage());
        labelScorePlayer2.setText(player2.scoreToString());
    }



    // CardButtons Player 1
    public void player1Card0(ActionEvent event) throws IOException {
       clickedButton(player1,1,p1c0,0);
    }
    public void player1Card1(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c1,1);
    }
    public void player1Card2(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c2,2);
    }
    public void player1Card3(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c3,3);
    }
    public void player1Card4(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c4,4);
    }
    public void player1Card5(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c5,5);
    }
    public void player1Card6(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c6,6);
    }
    public void player1Card7(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c7,7);
    }
    public void player1Card8(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c8,8);
    }
    public void player1Card9(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c9,9);
    }
    public void player1Card10(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c10,10);
    }
    public void player1Card11(ActionEvent event) throws IOException {
        clickedButton(player1,1,p1c11,11);
    }



    // CardButtons Player 2
    public void player2Card0(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c0,0);
    }
    public void player2Card1(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c1,1);
    }
    public void player2Card2(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c2,2);
    }
    public void player2Card3(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c3,3);
    }
    public void player2Card4(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c4,4);
    }
    public void player2Card5(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c5,5);
    }
    public void player2Card6(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c6,6);
    }
    public void player2Card7(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c7,7);
    }
    public void player2Card8(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c8,8);
    }
    public void player2Card9(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c9,9);
    }
    public void player2Card10(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c10,10);
    }
    public void player2Card11(ActionEvent event) throws IOException {
        clickedButton(player2,2,p2c11,11);
    }
    // +++++++++++++++ END PLAYER ONE Actions ++++++++++++++++++++++++




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        deck.addToDiscardPile();



        labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
        labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
        labelInstructions.setText("Each player reveals two of their cards. The player with the highest total begins the first round.");
        labelScorePlayer1.setText(player1.scoreToString());
        labelScorePlayer2.setText(player2.scoreToString());
        labelWhoIsPlaying.setText("");

        System.out.println("===================================================================");
        System.out.println("DiscardPile: " + deck.getDiscardPile());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Deck: " + deck.getCards());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand());

        player1Turn.setText(""+player1.getName() + " ist am Zug!");

        deckImgFaceDown.setGraphic(deck.getCardViewBackground());
        deckImgFaceUp.setGraphic(deck.getDiscardCard().getCardViewImage());

        p1c0.setGraphic(player1.getCard(0).getCardViewImage());
        p1c1.setGraphic(player1.getCard(1).getCardViewImage());
        p1c2.setGraphic(player1.getCard(2).getCardViewImage());
        p1c3.setGraphic(player1.getCard(3).getCardViewImage());
        p1c4.setGraphic(player1.getCard(4).getCardViewImage());
        p1c5.setGraphic(player1.getCard(5).getCardViewImage());
        p1c6.setGraphic(player1.getCard(6).getCardViewImage());
        p1c7.setGraphic(player1.getCard(7).getCardViewImage());
        p1c8.setGraphic(player1.getCard(8).getCardViewImage());
        p1c9.setGraphic(player1.getCard(9).getCardViewImage());
        p1c10.setGraphic(player1.getCard(10).getCardViewImage());
        p1c11.setGraphic(player1.getCard(11).getCardViewImage());

        p2c0.setGraphic(player2.getCard(0).getCardViewImage());
        p2c1.setGraphic(player2.getCard(1).getCardViewImage());
        p2c2.setGraphic(player2.getCard(2).getCardViewImage());
        p2c3.setGraphic(player2.getCard(3).getCardViewImage());
        p2c4.setGraphic(player2.getCard(4).getCardViewImage());
        p2c5.setGraphic(player2.getCard(5).getCardViewImage());
        p2c6.setGraphic(player2.getCard(6).getCardViewImage());
        p2c7.setGraphic(player2.getCard(7).getCardViewImage());
        p2c8.setGraphic(player2.getCard(8).getCardViewImage());
        p2c9.setGraphic(player2.getCard(9).getCardViewImage());
        p2c10.setGraphic(player2.getCard(10).getCardViewImage());
        p2c11.setGraphic(player2.getCard(11).getCardViewImage());


        //imgRulesBtn.setGraphic(viewRulesBtn);









        /*
        pOneCard00GameTwo.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                pOneCard00GameTwo.setGraphic((Node) player1.getCard(0).getCardViewImage());
            } else if (e.getButton() == MouseButton.SECONDARY) {

                //pOneCard00GameTwo.setGraphic((Node) player1.getCard(0).getCardViewImage());

                //deck.swapHandDiscard(0, deck, player1);

                deck.swap(player1);

                pOneCard00GameTwo.setGraphic(player1.getCard(0).getCardViewImage());

                deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());

                deck.swap(player1);

            }
        });


         */





    }



}
