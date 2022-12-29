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
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ControllerTwoPlayer implements Initializable {

    @FXML
    public Button nextPlayerButton;
    public Label labelWhoIsPlaying;
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



    // Variablen für den Spielablauf

    // Gibt an ob es sich um die erste Runde hält. Wenn nicht = false
    boolean firstRound = true;
    // Gibt an ob es sich um die letzte Runde hälft. Wenn ja = true
    boolean lastRound = false;

    //Gibt an welcher Spieler am Zug ist (Player 1 = 1, Player 2 = 2 usw.)
    private int isPlaying = 1;

    //Gibt an wieviel Spielkarten der Spieler noch umdrehen darf (Startet mit 2)
    private int moveLeft = 2;

    //Gibt an, ob die letze Runde dran ist, und welcher Spieler beendet hat (Player 1 = 1, Player 2 = 2 usw.)
    private int finalround = 0;
    // Limitierung fürs abgeben (Wenn abgehoben wurde = false)
    private boolean pickUp = true;

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

    public boolean permissionCheck (Player player){
        if (player == player1) {
            if (isPlaying != 1) {
                return false;
            } else if (moveLeft == 0) {
                return false;
            } else {
                return true;
            }
        } else if (player == player2) {
            if (isPlaying != 2){
                return false;
            } else if (moveLeft == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println("ERROR - Fehler bei permissionCheck!");
            return false;
        }

    }

    public void clickedButton (Player player, Button button, int cardNumber) throws IOException {
        button.setOnMouseClicked(event1 -> {
            if (permissionCheck(player)) {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    if (player == player1) {
                        player1Table(cardNumber, button);
                    } else if (player == player2) {
                        player2Table(cardNumber, button);
                    }

                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, button, player, cardNumber);
                }
            }
        });
    }

    public void nextPlayer(ActionEvent actionEvent) {

        // Player 1
        if (firstRound){
            if (isPlaying == 1){
                moveLeft = 2;
                isPlaying = 2;
                labelWhoIsPlaying.setText("" + player2.getName() + " ist am Zug!");
            } else if (isPlaying == 2) {
                moveLeft = 1;
                isPlaying = 1;
                firstRound = false;
                labelWhoIsPlaying.setText("" + player1.getName() + " ist am Zug!");
            }
            // hier mehr else if für die Spieleranzahl einfügen...
        }
        // End of First Round
        else {
            //Usual Game
            if (isPlaying == 1) {
                if (finalround == 0){
                    //Player 1 finished?
                    if (player1.checkIfFinished()){
                        finalround = 1;
                        moveLeft = 1;
                        isPlaying = 2;
                        labelWhoIsPlaying.setText("" + player2.getName() + " ist am Zug!");
                    }
                    // Usual turn to next Player
                    else {
                        moveLeft = 1;
                        isPlaying = 2;
                        labelWhoIsPlaying.setText("" + player2.getName() + " ist am Zug!");
                    }
                }
                // Last Round - not initialized by Player 1
                else if (finalround !=1) {
                    player1.flipAllCards();
                    moveLeft = 1;
                    isPlaying = 2;
                    labelWhoIsPlaying.setText("" + player2.getName() + " ist am Zug!");
                }
                // Last Round - initialized by Player 1
                else {
                    moveLeft = 0;
                    labelWhoIsPlaying.setText("Runde vorbei!");
                    // End of Game
                }
            }
            else if (isPlaying == 2) {
                if (finalround == 0){
                    //Player 2 finished?
                    if (player2.checkIfFinished()){
                        finalround = 1;
                        moveLeft = 1;
                        isPlaying = 1;
                        labelWhoIsPlaying.setText("" + player1.getName() + " ist am Zug!");
                    }
                    // Usual turn to next Player
                    else {
                        moveLeft = 1;
                        isPlaying = 1;
                        labelWhoIsPlaying.setText("" + player1.getName() + " ist am Zug!");
                    }
                }
                // Last Round - not initialized by Player 2
                else if (finalround !=2) {
                    player1.flipAllCards();
                    moveLeft = 1;
                    isPlaying = 1;
                    labelWhoIsPlaying.setText("" + player1.getName() + " ist am Zug!");
                }
                // Last Round - initialized by Player 2
                else {
                    moveLeft = 0;
                    labelWhoIsPlaying.setText("Runde vorbei!");
                    // End of Game
                }
            }
            // hier mehr else if für die Spieleranzahl einfügen.
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
        p1c0.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(0, p1c0);

            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c0, player1, 0);
            }
        });



    }
    public void playerOneCard01(ActionEvent event) throws IOException {
        p1c4.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(1, p1c4);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c4, player1, 1);
            }
        });
    }
    public void playerOneCard02(ActionEvent event) throws IOException {

        p1c8.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(2, p1c8);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c4, player1, 2);
            }
        });
    }
    public void playerOneCard10(ActionEvent event) throws IOException {
        p1c1.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(3, p1c1);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c1, player1, 3);
            }
        });
    }
    public void playerOneCard11(ActionEvent event) throws IOException {
        p1c5.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(4, p1c5);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c5, player1, 4);
            }
        });
    }
    public void playerOneCard12(ActionEvent event) throws IOException {

        p1c9.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(5, p1c9);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c9, player1, 5);
            }
        });
    }
    public void playerOneCard20(ActionEvent event) throws IOException {

        p1c2.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(6, p1c2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c2, player1, 6);
            }
        });
    }
    public void playerOneCard21(ActionEvent event) throws IOException {

        p1c6.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(7, p1c6);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c6, player1, 7);
            }
        });
    }
    public void playerOneCard22(ActionEvent event) throws IOException {

        p1c10.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(8, p1c10);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c10, player1, 8);
            }
        });
    }
    public void playerOneCard30(ActionEvent event) throws IOException {

        p1c3.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(9, p1c3);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c3, player1, 9);
            }
        });
    }
    public void playerOneCard31(ActionEvent event) throws IOException {

        p1c7.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(10, p1c7);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c7, player1, 10);
            }
        });
    }
    public void playerOneCard32(ActionEvent event) throws IOException {
        player1Table(11, p1c11);

        p1c11.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                player1Table(11, p1c11);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c11, player1, 11);
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
        player2Table(0, p2c0);
    }
    public void playerTwoCard01(ActionEvent event) throws IOException {
        player2Table(1, p2c4);
    }
    public void playerTwoCard02(ActionEvent event) throws IOException {
        player2Table(2, p2c8);
    }
    public void playerTwoCard10(ActionEvent event) throws IOException {
        player2Table(3, p2c1);
    }
    public void playerTwoCard11(ActionEvent event) throws IOException {
        player2Table(4, p2c5);
    }
    public void playerTwoCard12(ActionEvent event) throws IOException {
        player2Table(5, p2c9);
    }
    public void playerTwoCard20(ActionEvent event) throws IOException {
        player2Table(6, p2c2);
    }
    public void playerTwoCard21(ActionEvent event) throws IOException {
        player2Table(7, p2c6);
    }
    public void playerTwoCard22(ActionEvent event) throws IOException {
        player2Table(8, p2c10);
    }
    public void playerTwoCard30(ActionEvent event) throws IOException {
        player2Table(9, p2c3);
    }
    public void playerTwoCard31(ActionEvent event) throws IOException {
        player2Table(10, p2c7);
    }
    public void playerTwoCard32(ActionEvent event) throws IOException {
        player2Table(11, p2c11);
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
        labelWhoIsPlaying.setText("" + player1.getName() + " ist am Zug!");

        System.out.println("===================================================================");
        System.out.println("DiscardPile: " + deck.getDiscardPile());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Deck: " + deck.getCards());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand());


        p1c0.setGraphic((Node) deck.getCardBack().get(0));
        p1c1.setGraphic((Node) deck.getCardBack().get(1));
        p1c2.setGraphic((Node) deck.getCardBack().get(2));
        p1c3.setGraphic((Node) deck.getCardBack().get(3));
        p1c4.setGraphic((Node) deck.getCardBack().get(4));
        p1c5.setGraphic((Node) deck.getCardBack().get(5));
        p1c6.setGraphic((Node) deck.getCardBack().get(6));
        p1c7.setGraphic((Node) deck.getCardBack().get(7));
        p1c8.setGraphic((Node) deck.getCardBack().get(8));
        p1c9.setGraphic((Node) deck.getCardBack().get(9));
        p1c10.setGraphic((Node) deck.getCardBack().get(10));
        p1c11.setGraphic((Node) deck.getCardBack().get(11));

        p2c0.setGraphic((Node) deck.getCardBack().get(12));
        p2c1.setGraphic((Node) deck.getCardBack().get(13));
        p2c2.setGraphic((Node) deck.getCardBack().get(14));
        p2c3.setGraphic((Node) deck.getCardBack().get(15));
        p2c4.setGraphic((Node) deck.getCardBack().get(16));
        p2c5.setGraphic((Node) deck.getCardBack().get(17));
        p2c6.setGraphic((Node) deck.getCardBack().get(18));
        p2c7.setGraphic((Node) deck.getCardBack().get(19));
        p2c8.setGraphic((Node) deck.getCardBack().get(20));
        p2c9.setGraphic((Node) deck.getCardBack().get(21));
        p2c10.setGraphic((Node) deck.getCardBack().get(22));
        p2c11.setGraphic((Node) deck.getCardBack().get(23));


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
