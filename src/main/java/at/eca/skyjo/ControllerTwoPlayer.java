package at.eca.skyjo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
public class ControllerTwoPlayer implements Initializable {



    @FXML
    private Button pOneCard00GameTwo;
    @FXML
    private Button pOneCard10GameTwo;
    @FXML
    private Button pOneCard20GameTwo;
    @FXML
    private Button pOneCard30GameTwo;
    @FXML
    private Button pOneCard01GameTwo;
    @FXML
    private Button pOneCard11GameTwo;
    @FXML
    private Button pOneCard21GameTwo;
    @FXML
    private Button pOneCard31GameTwo;
    @FXML
    private Button pOneCard02GameTwo;
    @FXML
    private Button pOneCard12GameTwo;
    @FXML
    private Button pOneCard22GameTwo;
    @FXML
    private Button pOneCard32GameTwo;

    // player 2

    @FXML
    private Button pTwoCard00GameTwo;
    @FXML
    private Button pTwoCard10GameTwo;
    @FXML
    private Button pTwoCard20GameTwo;
    @FXML
    private Button pTwoCard30GameTwo;
    @FXML
    private Button pTwoCard01GameTwo;
    @FXML
    private Button pTwoCard11GameTwo;
    @FXML
    private Button pTwoCard21GameTwo;
    @FXML
    private Button pTwoCard31GameTwo;
    @FXML
    private Button pTwoCard02GameTwo;
    @FXML
    private Button pTwoCard12GameTwo;
    @FXML
    private Button pTwoCard22GameTwo;
    @FXML
    private Button pTwoCard32GameTwo;

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

    // Player 1 Table Setup
    public void player1Table(int cardNumber, Button playerButton){
        playerButton.setGraphic((Node) player1.getCard(cardNumber).getCardViewImage());
        labelScorePlayer1.setText(player1.scoreToString());
    }

    public void swapHandDiscard(Deck deck, Button playerButton, Player player, int cardNumber) {
        deck.swap(player, cardNumber);
        playerButton.setGraphic(player.getCard(cardNumber).getCardViewImage());
        deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());
        if(player == player1) {
            labelScorePlayer1.setText(player1.scoreToString());
        } else if (player == player2) {
            labelScorePlayer2.setText(player2.scoreToString());
        }
    }

    // CardButtons Player 1
    public void playerOneCard00(ActionEvent event) throws IOException {
        pOneCard00GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(0,pOneCard00GameTwo);

                System.out.println("===================================================================" + System.lineSeparator() + "flip card of hand");
                System.out.println("DiscardPile: " + deck.getDiscardPile());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Deck: " + deck.getCards());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Player Hand: " + player1.getHand());

            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard00GameTwo, player1, 0);

                System.out.println("===================================================================" + System.lineSeparator() + "swap hand with discard pile");
                System.out.println("DiscardPile: " + deck.getDiscardPile());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Deck: " + deck.getCards());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Player Hand: " + player1.getHand());
            }
        });



    }
    public void playerOneCard01(ActionEvent event) throws IOException {
        pOneCard01GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(1,pOneCard01GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard01GameTwo, player1, 1);
            }
        });
    }
    public void playerOneCard02(ActionEvent event) throws IOException {

        pOneCard02GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(2,pOneCard02GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard01GameTwo, player1, 2);
            }
        });
    }
    public void playerOneCard10(ActionEvent event) throws IOException {
        pOneCard10GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(3,pOneCard10GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard10GameTwo, player1, 3);
            }
        });
    }
    public void playerOneCard11(ActionEvent event) throws IOException {
        pOneCard11GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(4,pOneCard11GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard11GameTwo, player1, 4);
            }
        });
    }
    public void playerOneCard12(ActionEvent event) throws IOException {

        pOneCard12GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(5,pOneCard12GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard12GameTwo, player1, 5);
            }
        });
    }
    public void playerOneCard20(ActionEvent event) throws IOException {

        pOneCard20GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(6,pOneCard20GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard20GameTwo, player1, 6);
            }
        });
    }
    public void playerOneCard21(ActionEvent event) throws IOException {

        pOneCard21GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(7,pOneCard21GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard21GameTwo, player1, 7);
            }
        });
    }
    public void playerOneCard22(ActionEvent event) throws IOException {

        pOneCard22GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(8,pOneCard22GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard22GameTwo, player1, 8);
            }
        });
    }
    public void playerOneCard30(ActionEvent event) throws IOException {

        pOneCard30GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(9,pOneCard30GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard30GameTwo, player1, 9);
            }
        });
    }
    public void playerOneCard31(ActionEvent event) throws IOException {

        pOneCard31GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(10,pOneCard31GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard31GameTwo, player1, 10);
            }
        });
    }
    public void playerOneCard32(ActionEvent event) throws IOException {
        player1Table(11,pOneCard32GameTwo);

        pOneCard32GameTwo.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(11,pOneCard32GameTwo);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, pOneCard32GameTwo, player1, 11);
            }
        });
    }



    // Player  Table Setup
    public void player2Table(int cardNumber, Button playerButton){
        playerButton.setGraphic((Node) player2.getCard(cardNumber).getCardViewImage());
        labelScorePlayer2.setText(player2.scoreToString());
    }

    // CardButtons Player 1
    public void playerTwoCard00(ActionEvent event) throws IOException {
        player2Table(0,pTwoCard00GameTwo);
    }
    public void playerTwoCard01(ActionEvent event) throws IOException {
        player2Table(1,pTwoCard01GameTwo);
    }
    public void playerTwoCard02(ActionEvent event) throws IOException {
        player2Table(2,pTwoCard02GameTwo);
    }
    public void playerTwoCard10(ActionEvent event) throws IOException {
        player2Table(3,pTwoCard10GameTwo);
    }
    public void playerTwoCard11(ActionEvent event) throws IOException {
        player2Table(4,pTwoCard11GameTwo);
    }
    public void playerTwoCard12(ActionEvent event) throws IOException {
        player2Table(5,pTwoCard12GameTwo);
    }
    public void playerTwoCard20(ActionEvent event) throws IOException {
        player2Table(6,pTwoCard20GameTwo);
    }
    public void playerTwoCard21(ActionEvent event) throws IOException {
        player2Table(7,pTwoCard21GameTwo);
    }
    public void playerTwoCard22(ActionEvent event) throws IOException {
        player2Table(8,pTwoCard22GameTwo);
    }
    public void playerTwoCard30(ActionEvent event) throws IOException {
        player2Table(9,pTwoCard30GameTwo);
    }
    public void playerTwoCard31(ActionEvent event) throws IOException {
        player2Table(10,pTwoCard31GameTwo);
    }
    public void playerTwoCard32(ActionEvent event) throws IOException {
        player2Table(11,pTwoCard32GameTwo);
    }
    // +++++++++++++++ END PLAYER ONE Actions ++++++++++++++++++++++++




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        deck.getDiscardPile().add(0,deck.getCards().remove(0));
        deckImgFaceDown.setGraphic((Node) deck.getCardBack().get(24));
        deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());


        labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
        labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
        labelInstructions.setText("Each player reveals two of their cards. The player with the highest total begins the first round.");
        labelScorePlayer1.setText(player1.scoreToString());
        labelScorePlayer2.setText(player2.scoreToString());

        System.out.println("===================================================================");
        System.out.println("DiscardPile: " + deck.getDiscardPile());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Deck: " + deck.getCards());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand());


        pOneCard00GameTwo.setGraphic((Node) deck.getCardBack().get(0));
        pOneCard10GameTwo.setGraphic((Node) deck.getCardBack().get(1));
        pOneCard20GameTwo.setGraphic((Node) deck.getCardBack().get(2));
        pOneCard30GameTwo.setGraphic((Node) deck.getCardBack().get(3));
        pOneCard01GameTwo.setGraphic((Node) deck.getCardBack().get(4));
        pOneCard11GameTwo.setGraphic((Node) deck.getCardBack().get(5));
        pOneCard21GameTwo.setGraphic((Node) deck.getCardBack().get(6));
        pOneCard31GameTwo.setGraphic((Node) deck.getCardBack().get(7));
        pOneCard02GameTwo.setGraphic((Node) deck.getCardBack().get(8));
        pOneCard12GameTwo.setGraphic((Node) deck.getCardBack().get(9));
        pOneCard22GameTwo.setGraphic((Node) deck.getCardBack().get(10));
        pOneCard32GameTwo.setGraphic((Node) deck.getCardBack().get(11));

        pTwoCard00GameTwo.setGraphic((Node) deck.getCardBack().get(12));
        pTwoCard10GameTwo.setGraphic((Node) deck.getCardBack().get(13));
        pTwoCard20GameTwo.setGraphic((Node) deck.getCardBack().get(14));
        pTwoCard30GameTwo.setGraphic((Node) deck.getCardBack().get(15));
        pTwoCard01GameTwo.setGraphic((Node) deck.getCardBack().get(16));
        pTwoCard11GameTwo.setGraphic((Node) deck.getCardBack().get(17));
        pTwoCard21GameTwo.setGraphic((Node) deck.getCardBack().get(18));
        pTwoCard31GameTwo.setGraphic((Node) deck.getCardBack().get(19));
        pTwoCard02GameTwo.setGraphic((Node) deck.getCardBack().get(20));
        pTwoCard12GameTwo.setGraphic((Node) deck.getCardBack().get(21));
        pTwoCard22GameTwo.setGraphic((Node) deck.getCardBack().get(22));
        pTwoCard32GameTwo.setGraphic((Node) deck.getCardBack().get(23));


        //imgRulesBtn.setGraphic(viewRulesBtn);





        deckImgFaceDown.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                //deck.addToDiscardPile();
                //deck.getDiscardPile().add(0, deck.getCards().remove(0));
                deck.addDeckToDiscardPile();
                deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());


                //test
                labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
                labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
                System.out.println("===================================================================" + System.lineSeparator() + "add to discard pile");
                System.out.println("DiscardPile: " + deck.getDiscardPile());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Deck: " + deck.getCards());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Player Hand: " + player1.getHand());
            }
        });



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
