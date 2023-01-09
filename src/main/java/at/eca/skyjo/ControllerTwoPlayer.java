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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ControllerTwoPlayer implements Initializable {

    @FXML
    public Button themeBtn;

    @FXML
    public AnchorPane anchorPane;

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
    private Label labelPlayer1Name;
    @FXML
    private Label labelPlayer2Name;
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

    public void clickedButton (Player player, int i, Button button, int cardNumber) throws IOException {
        button.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    if (gameTwoPlayer.permissionCheck(player, i, cardNumber)) {
                        if (!gameTwoPlayer.canPickUp) {
                            if (player == player1) {
                                player1Table(cardNumber, button);
                                gameTwoPlayer.movesLeft--;
                            } else if (player == player2) {
                                player2Table(cardNumber, button);
                                gameTwoPlayer.movesLeft--;
                            }
                        }
                    }

                } else if (event1.getButton() == MouseButton.SECONDARY) {
                        if (gameTwoPlayer.permissionCheck(player, i, cardNumber) && !gameTwoPlayer.firstRound) {
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
                    labelInstructions.setText(gameTwoPlayer.instructionLabelText());
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

    public void nextPlayer(ActionEvent actionEvent) throws IOException {



        if (gameTwoPlayer.movesLeft == 0) {
            switch (gameTwoPlayer.gamePlay(gameTwoPlayer.getPlayers((gameTwoPlayer.isPlaying - 1)), gameTwoPlayer.isPlaying)) {
                case 1 -> {
                    refresh();
                    player1Turn.setText(gameTwoPlayer.whoIsPlayingText());
                    player2Turn.setText("");
                    labelInstructions.setText(gameTwoPlayer.instructionLabelText());
                }
                case 2 -> {
                    refresh();
                    player2Turn.setText(gameTwoPlayer.whoIsPlayingText());
                    player1Turn.setText("");
                    labelInstructions.setText(gameTwoPlayer.instructionLabelText());
                }
                case 0 -> {
                    refresh();
                    player1Turn.setText("");
                    player2Turn.setText("");
                    labelScorePlayer1.setText("Total: "+player1.getScore());
                    labelScorePlayer2.setText("Total: "+player2.getScore());
                    labelInstructions.setText(gameTwoPlayer.instructionLabelText());
                }
                case 99 -> {
                    refresh();
                    labelWhoIsPlaying.setText("Fehler beim Gameplay!!!");
                    player2Turn.setText("");
                    player1Turn.setText("");
                }
            }
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

    public void buttonSetup(Player player) throws IOException {
        if (player == player1) {
            clickedButton(player1, 1, p1c0, 0);
            clickedButton(player1, 1, p1c1, 1);
            clickedButton(player1, 1, p1c2, 2);
            clickedButton(player1, 1, p1c3, 3);
            clickedButton(player1, 1, p1c4, 4);
            clickedButton(player1, 1, p1c5, 5);
            clickedButton(player1, 1, p1c6, 6);
            clickedButton(player1, 1, p1c7, 7);
            clickedButton(player1, 1, p1c8, 8);
            clickedButton(player1, 1, p1c9, 9);
            clickedButton(player1, 1, p1c10, 10);
            clickedButton(player1, 1, p1c11, 11);

            clickedButton(player2, 2, p2c0, 0);
            clickedButton(player2, 2, p2c1, 1);
            clickedButton(player2, 2, p2c2, 2);
            clickedButton(player2, 2, p2c3, 3);
            clickedButton(player2, 2, p2c4, 4);
            clickedButton(player2, 2, p2c5, 5);
            clickedButton(player2, 2, p2c6, 6);
            clickedButton(player2, 2, p2c7, 7);
            clickedButton(player2, 2, p2c8, 8);
            clickedButton(player2, 2, p2c9, 9);
            clickedButton(player2, 2, p2c10, 10);
            clickedButton(player2, 2, p2c11, 11);

        }
    }
    public void refresh(){
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
    }

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


        deck.addDeckToDiscardPile();



        labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
        labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
        labelInstructions.setText(gameTwoPlayer.instructionLabelText());
        labelScorePlayer1.setText(player1.scoreToString());
        labelScorePlayer2.setText(player2.scoreToString());
        labelPlayer1Name.setText(player1.getName());
        labelPlayer2Name.setText(player2.getName());
        labelWhoIsPlaying.setText("");

        System.out.println("===================================================================");
        System.out.println("DiscardPile: " + deck.getDiscardPile());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Deck: " + deck.getCards());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand());



        player1Turn.setText(""+player1.getName() + " ist am Zug!");
        player2Turn.setText("");

        deckImgFaceDown.setGraphic(deck.getCardViewBackground());
        deckImgFaceUp.setGraphic(deck.getDiscardCard().getCardViewImage());


        try {
            buttonSetup(player1);
            buttonSetup(player2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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


        






    }



}
