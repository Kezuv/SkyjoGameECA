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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class ControllerTwoPlayer implements Initializable {



    @FXML
    private Button p1c0, p1c1, p1c2, p1c3, p1c4, p1c5, p1c6, p1c7, p1c8, p1c9, p1c10, p1c11;
    @FXML
    private Button p2c0, p2c1, p2c2, p2c3, p2c4, p2c5, p2c6, p2c7, p2c8, p2c9, p2c10, p2c11;

    @FXML
    private Button deckImgFaceDown, deckImgFaceUp, next;

    // label

    @FXML
    private Label labelRound, labelPlayer1Name, labelPlayer2Name, labelScorePlayer1, labelScorePlayer2, labelScoreGameP1, labelScoreGameP2, labelCardsLeftDeck, labelCardsLeftDiscard, labelInstructions;




    // label ende
    private final String viewStart = "/at/eca/skyjo/fxml/sceneStart.fxml";
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
    public void flipCard(int cardNumber, Button playerButton, Player player, Label label){
        playerButton.setGraphic((Node) player.getCard(cardNumber).getCardViewImage());

        player.addToScore(cardNumber);
        label.setText(player.scoreToString());

    }

    public void swapHandDiscard(Deck deck, Button playerButton, Player player, int cardNumber, Label label) {
        //playerButton.setGraphic((Node) player.getCard(cardNumber).getCardViewImage());

        deck.swap(player, cardNumber);

        playerButton.setGraphic(player.getCard(cardNumber).getCardViewImage());
        deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());

        label.setText(player.scoreToString());
    }

    public void changeHandBtnDisEn(Player player, List<Button> list) {
        for (int i = 0; i < player.getHand().size(); i++) {
            boolean disabled = list.get(i).isDisabled();
            list.get(i).setDisable(!disabled);
        }
    }

    public void setImg(Player player, List<Button> list) {


        deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());
        if(player.getHand().size() == 9) {

            for (int y = 0; y < 12; y++) {
                if ( y < player.getHand().size()) {
                    list.get(y).setGraphic(player.getHand().get(y).getCardViewImage());
                } else {
                    list.get(y).setGraphic(null);
                }

            }
        }
        if(player.getHand().size() == 6) {

            for (int y = 0; y < 12; y++) {
                if ( y < player.getHand().size()) {
                    list.get(y).setGraphic(player.getHand().get(y).getCardViewImage());
                } else {
                    list.get(y).setGraphic(null);
                }

            }
        }
        if(player.getHand().size() == 3) {

            for (int y = 0; y < 12; y++) {
                if ( y < player.getHand().size()) {
                    list.get(y).setGraphic(player.getHand().get(y).getCardViewImage());
                } else {
                    list.get(y).setGraphic(null);
                }

            }
        }


    }

    public void setBackImg(List<Button> list) {

        int step = 0;
        for (int x = 0; x < gameTwoPlayer.getAllPlayers().size(); x++) {

            for (int y = 0; y < 12; y++) {
                list.get(y + step).setGraphic(gameTwoPlayer.getPlayers(x).getHand().get(y).getCardViewBack());

            }
            step = step + 12;

        }

    }

    /*
    public void startPlayersRound(List<Button> list1, List<Button> list2) {
        if (gameTwoPlayer.getClickLeftHandCounter() >= 1) {
            if(gameTwoPlayer.getClickLeftHandCounter() >= 1) {
                changeHandBtnDisEn(player2, btnList2);
                changeHandBtnDisEn(player1, btnList1);
            }
            changeHandBtnDisEn(player2, list2);
            //changeHandBtnDisEn(player1, list1);
            gameTwoPlayer.resetClickLeftHandCounter();
        }
    }

     */


    public void nextPlayer(List<Button> list1, List<Button> list2) {
        System.out.println("clickLeft_Hand: " + gameTwoPlayer.getClickLeftHandCounter() + " | clickRight_Hand: " + gameTwoPlayer.getClickRightHandCounter() + " | clickDeck: " + gameTwoPlayer.getClickDeckCounter() + " | playerMove: " + gameTwoPlayer.getPlayerMoveCounter() + " | playerRound: " + gameTwoPlayer.getPlayersRoundCounter());
        System.out.println("statusPlayer1: " + player1.getStatusPlayer() + " | statusPlayer2: " + player2.getStatusPlayer());

        /*
        for (int i = 0; i < player1.getHand().size(); i++) {
            System.out.println(player1.getHand().get(i).getFace());
        }
        for (int i = 0; i < player2.getHand().size(); i++) {
            System.out.println(player2.getHand().get(i).getFace());
        }

         */


        if (gameTwoPlayer.getPlayersRoundCounter() == 0) {
            if(gameTwoPlayer.getClickLeftHandCounter() == 2) {
                changeHandBtnDisEn(player2, list2);
                changeHandBtnDisEn(player1, list1);
                //player1.changeStatusPlayer();
                //player2.changeStatusPlayer();

                //gameTwoPlayer.incrementPlayerMoveCounter();
                
            }

            if (gameTwoPlayer.getClickLeftHandCounter() == 4) {
                changeHandBtnDisEn(player2, list2);
                //player2.changeStatusPlayer();
                //player1.changeStatusPlayer();
                //gameTwoPlayer.incrementPlayerMoveCounter();
            }


        }

        if (gameTwoPlayer.getPlayersRoundCounter() > 0) {
            if (gameTwoPlayer.getClickRightHandCounter() >= 0) {
               if (player1.getStatusPlayer()) {
                   changeHandBtnDisEn(player1, list1);
                   changeHandBtnDisEn(player2, list2);
                   labelInstructions.setText("click the next button");
               } else if (player2.getStatusPlayer()) {
                   changeHandBtnDisEn(player2, list2);
                   changeHandBtnDisEn(player1, list1);
                   labelInstructions.setText("click the next button");
               }
                //player2.changeStatusPlayer();
                //player1.changeStatusPlayer();
                gameTwoPlayer.resetClickDeckCounter();
                gameTwoPlayer.resetClickLeftHandCounter();
                gameTwoPlayer.resetClickRightHandCounter();
            } else if (gameTwoPlayer.getClickDeckCounter() >= 0) {
                deckImgFaceDown.setDisable(true);
                if (gameTwoPlayer.getClickLeftHandCounter() >= 0) {
                    if (player1.getStatusPlayer()) {
                        changeHandBtnDisEn(player1, list1);
                        changeHandBtnDisEn(player2, list2);
                        labelInstructions.setText("click the next button");
                    } else if (player2.getStatusPlayer()) {
                        changeHandBtnDisEn(player2, list2);
                        changeHandBtnDisEn(player1, list1);
                        labelInstructions.setText("click the next button");
                    }
                    //player2.changeStatusPlayer();
                    //player1.changeStatusPlayer();
                    gameTwoPlayer.resetClickDeckCounter();
                    gameTwoPlayer.resetClickLeftHandCounter();
                    gameTwoPlayer.resetClickRightHandCounter();
                } else if (gameTwoPlayer.getClickRightHandCounter() >= 0) {
                    if (player1.getStatusPlayer()) {
                        changeHandBtnDisEn(player1, list1);
                        changeHandBtnDisEn(player2, list2);
                        labelInstructions.setText("click the next button");
                    } else if (player2.getStatusPlayer()) {
                        changeHandBtnDisEn(player2, list2);
                        changeHandBtnDisEn(player1, list1);
                        labelInstructions.setText("click the next button");
                    }
                    //player2.changeStatusPlayer();
                    //player1.changeStatusPlayer();
                    gameTwoPlayer.resetClickDeckCounter();
                    gameTwoPlayer.resetClickLeftHandCounter();
                    gameTwoPlayer.resetClickRightHandCounter();
                }
            }


        }



    }


    public void playRound(List<Button> list1, List<Button> list2) {
        if (gameTwoPlayer.getPlayersRoundCounter() == 0) {
            gameTwoPlayer.startRound();


            if(gameTwoPlayer.getCurrentPlayer() == player1) {
                changeHandBtnDisEn(player1, list1);
                deckImgFaceDown.setDisable(false);
            } else if (gameTwoPlayer.getCurrentPlayer() == player2) {
                changeHandBtnDisEn(player2, list2);
                deckImgFaceDown.setDisable(false);
            }


        }

        if (gameTwoPlayer.getPlayersRoundCounter() > 0) {
            gameTwoPlayer.playRound();
        }
    }

    public void endRound(List<Button> list1, List<Button> list2) {




        if (gameTwoPlayer.checkIfEndOfRound(player1) || gameTwoPlayer.checkIfEndOfRound(player2)) {




            if (gameTwoPlayer.getNum() == 1) {

                for (int x = 0; x < gameTwoPlayer.getAllPlayers().get(0).getHand().size(); x++) {
                    if (!gameTwoPlayer.getAllPlayers().get(0).getHand().get(x).getFace()) {

                        flipCard(x, list1.get(x), player1, labelScorePlayer1);

                    }
                }
                for (int x = 0; x < gameTwoPlayer.getAllPlayers().get(1).getHand().size(); x++) {
                    if (!gameTwoPlayer.getAllPlayers().get(1).getHand().get(x).getFace()) {

                        flipCard(x, list2.get(x), player1, labelScorePlayer1);

                    }
                }

                labelInstructions.setText("The Round has ended! All your points were added to your game score. Click next to start the next round!");
                gameTwoPlayer.setNum(0);
                gameTwoPlayer.incrementRoundCounter();
                player1.addGameScore(player1.getScore());
                player2.addGameScore(player2.getScore());
                labelScoreGameP1.setText("Game Score: " + player1.getGameScore());
                labelScoreGameP2.setText("Game Score: " + player2.getGameScore());
                labelRound.setText("Round: " + (gameTwoPlayer.getRoundCounter() + 1));


            }
            gameTwoPlayer.setNum(1);


        }

    }









    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(gameTwoPlayer.getPlayers(0).getStatusPlayer());
        System.out.println(gameTwoPlayer.getPlayers(1).getStatusPlayer());


        List<Button> buttonList = new ArrayList<>();
        buttonList.add(p1c0);
        buttonList.add(p1c1);
        buttonList.add(p1c2);
        buttonList.add(p1c3);
        buttonList.add(p1c4);
        buttonList.add(p1c5);
        buttonList.add(p1c6);
        buttonList.add(p1c7);
        buttonList.add(p1c8);
        buttonList.add(p1c9);
        buttonList.add(p1c10);
        buttonList.add(p1c11);
        buttonList.add(p2c0);
        buttonList.add(p2c1);
        buttonList.add(p2c2);
        buttonList.add(p2c3);
        buttonList.add(p2c4);
        buttonList.add(p2c5);
        buttonList.add(p2c6);
        buttonList.add(p2c7);
        buttonList.add(p2c8);
        buttonList.add(p2c9);
        buttonList.add(p2c10);
        buttonList.add(p2c11);


        List<Button> btnList1 = new ArrayList<>();
        btnList1.add(p1c0);
        btnList1.add(p1c1);
        btnList1.add(p1c2);
        btnList1.add(p1c3);
        btnList1.add(p1c4);
        btnList1.add(p1c5);
        btnList1.add(p1c6);
        btnList1.add(p1c7);
        btnList1.add(p1c8);
        btnList1.add(p1c9);
        btnList1.add(p1c10);
        btnList1.add(p1c11);

        List<Button> btnList2 = new ArrayList<>();
        btnList2.add(p2c0);
        btnList2.add(p2c1);
        btnList2.add(p2c2);
        btnList2.add(p2c3);
        btnList2.add(p2c4);
        btnList2.add(p2c5);
        btnList2.add(p2c6);
        btnList2.add(p2c7);
        btnList2.add(p2c8);
        btnList2.add(p2c9);
        btnList2.add(p2c10);
        btnList2.add(p2c11);


        System.out.println(gameTwoPlayer.getPlayersRoundCounter());



        deckImgFaceDown.setGraphic((Node) deck.getCards().get(0).getCardViewBack());
        deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());
        deckImgFaceDown.setDisable(true);
        deckImgFaceUp.setDisable(true);
        changeHandBtnDisEn(player2, btnList2);
        //startPlayersRound(btnList1, btnList2);







        //labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
        //labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
        labelInstructions.setText(gameTwoPlayer.getInstruction());
        //labelInstructions.setText("Each player reveals two of their cards. The player with the highest total begins the first round.");
        labelScorePlayer1.setText(player1.scoreToString());
        labelScorePlayer2.setText(player2.scoreToString());

        System.out.println("===================================================================");
        System.out.println("DiscardPile: " + deck.getDiscardPile());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Deck: " + deck.getCards());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + deck.getDiscardPile().get(0).getFace());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand().get(0).getFace());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand().get(3).getFace());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Hand: " + player1.getHand().get(7).getFace());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player status: " + gameTwoPlayer.getPlayers(0).getStatusPlayer());
        System.out.println("Player status: " + gameTwoPlayer.getPlayers(1).getStatusPlayer());




        setBackImg(buttonList);





        deckImgFaceDown.setOnMouseClicked(e -> {
            gameTwoPlayer.incrementClickCounter();
            System.out.println(gameTwoPlayer.getClickCounter());
            if (e.getButton() == MouseButton.PRIMARY) {
                deck.addDeckToDiscardPile();
                deckImgFaceUp.setGraphic((Node) deck.getDiscardPile().get(0).getCardViewImage());
                //test
                //labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
                //labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
                System.out.println("===================================================================" + System.lineSeparator() + "add to discard pile");
                System.out.println("DiscardPile: " + deck.getDiscardPile());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Deck: " + deck.getCards());
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Player Hand: " + player1.getHand());
                System.out.println("-------------------------------------------------------------------");


                gameTwoPlayer.incrementClickDeckCounter();
            }
        });

        next.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                //gameTwoPlayer.playRound();
                playRound(btnList1, btnList2);
                labelInstructions.setText(gameTwoPlayer.getInstruction());
                player1.three(gameTwoPlayer.getDeck());
                player2.three(gameTwoPlayer.getDeck());
                setImg(player1, btnList1);
                setImg(player2, btnList2);
                //changeHandBtnDisEn(player1, btnList1);




                if (player1.getStatusPlayer()) {
                    labelInstructions.setText("Player 1, it's your turn!");
                    if (gameTwoPlayer.checkIfEndOfRound(player1)) {
                        labelInstructions.setText("Player 2! your turn is the last of this round.");
                    }
                    if (gameTwoPlayer.checkIfEndOfRound(player2)) {
                        labelInstructions.setText("Player 1! your turn is the last of this round.");
                    }


                }
                if (player2.getStatusPlayer()) {
                    labelInstructions.setText("Player 2, it's your turn!");
                    if (gameTwoPlayer.checkIfEndOfRound(player2)) {
                        labelInstructions.setText("Player 1! your turn is the last of this round.");
                    }
                    if (gameTwoPlayer.checkIfEndOfRound(player1)) {
                        labelInstructions.setText("Player 2! your turn is the last of this round.");
                    }

                }
                //labelInstructions.setText(gameTwoPlayer.getInstruction());

                endRound(btnList1, btnList2);

                labelScorePlayer1.setText(player1.scoreToString());
                labelScorePlayer2.setText(player2.scoreToString());



                player1.changeStatusPlayer();
                player2.changeStatusPlayer();

                gameTwoPlayer.resetClickDeckCounter();
                gameTwoPlayer.resetClickLeftHandCounter();
                gameTwoPlayer.resetClickRightHandCounter();
                gameTwoPlayer.incrementPlayerMoveCounter();
                gameTwoPlayer.incrementPlayersRoundCounter();

                System.out.println(gameTwoPlayer.getPlayerMoveCounter());
                System.out.println(gameTwoPlayer.getPlayersRoundCounter());

                System.out.println("clickLeft_Hand: " + gameTwoPlayer.getClickLeftHandCounter() + " | clickRight_Hand: " + gameTwoPlayer.getClickRightHandCounter() + " | clickDeck: " + gameTwoPlayer.getClickDeckCounter() + " | playerMove: " + gameTwoPlayer.getPlayerMoveCounter() + " | playerRound: " + gameTwoPlayer.getPlayersRoundCounter());
                System.out.println("statusPlayer1: " + player1.getStatusPlayer() + " | statusPlayer2: " + player2.getStatusPlayer());

            }
        });







            p1c0.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(0, p1c0, player1, labelScorePlayer1);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p1c0, player1, 0, labelScorePlayer1);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p1c1.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(1, p1c1, player1, labelScorePlayer1);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p1c1, player1, 1, labelScorePlayer1);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

        p1c2.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(2, p1c2, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c2, player1, 2, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c3.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(3, p1c3, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c3, player1, 3, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c4.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(4, p1c4, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c4, player1, 4, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c5.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(5, p1c5, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c5, player1, 5, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c6.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(6, p1c6, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c6, player1, 6, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c7.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(7, p1c7, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c7, player1, 7, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c8.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(8, p1c8, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c8, player1, 8, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c9.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(9, p1c9, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c9, player1, 9, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c10.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(10, p1c10, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c10, player1, 10, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });

        p1c11.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                flipCard(11, p1c11, player1, labelScorePlayer1);
                gameTwoPlayer.incrementClickLeftHandCounter();
                nextPlayer(btnList1, btnList2);
            } else if (event1.getButton() == MouseButton.SECONDARY) {
                swapHandDiscard(deck, p1c11, player1, 11, labelScorePlayer1);
                gameTwoPlayer.incrementClickRightHandCounter();
                nextPlayer(btnList1, btnList2);
            }
        });




        //}







            p2c0.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(0, p2c0, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c0, player2, 0, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c1.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(1, p2c1, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c1, player2, 1, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c2.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(2, p2c2, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c2, player2, 2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c3.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(3, p2c3, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c3, player2, 3, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c4.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(4, p2c4, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c4, player2, 4, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c5.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(5, p2c5, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c5, player2, 5, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c6.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(6, p2c6, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c6, player2, 6, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c7.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(7, p2c7, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c7, player2, 7, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c8.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(8, p2c8, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c8, player2, 8, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c9.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(9, p2c9, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c9, player2, 9, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c10.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(10, p2c10, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c10, player2, 10, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });

            p2c11.setOnMouseClicked(event1 -> {
                if (event1.getButton() == MouseButton.PRIMARY) {
                    flipCard(11, p2c11, player2, labelScorePlayer2);
                    gameTwoPlayer.incrementClickLeftHandCounter();
                    nextPlayer(btnList1, btnList2);
                } else if (event1.getButton() == MouseButton.SECONDARY) {
                    swapHandDiscard(deck, p2c11, player2, 11, labelScorePlayer2);
                    gameTwoPlayer.incrementClickRightHandCounter();
                    nextPlayer(btnList1, btnList2);
                }
            });





    }
}
