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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    // label ende
    private String viewStart = "/at/eca/skyjo/fxml/sceneStart.fxml";
    private String viewRules = "/at/eca/skyjo/fxml/sceneRules.fxml";

    Deck deck = new Deck();
    Player player1 = new Player("Player1", deck);
    Player player2 = new Player("Player2", deck);


    @FXML
    private Button deckImgFaceDown;

    @FXML
    private Button deckImgFaceUp;


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




    public void testBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewRules));
        Parent viewRules = loader.load();
        Scene sceneRules = new Scene(viewRules);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneRules);
        window.show();
    }

    public void deckBtn(ActionEvent event) throws IOException {
        deckImgFaceUp.setGraphic((Node) deck.getDeckCard().getCardViewImage());
        deck.addToDiscardPile();

        //test
        labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
        labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
    }

    // +++++++++++++++ PLAYER ONE Actions ++++++++++++++++++++++++





    /*
    @FXML
    public void playerOneCard00(ActionEvent event) {

        pOneCard00GameTwo.setGraphic((Node) player1.getCard(0).getCardViewImage());
    }


     */



    public void playerOneCard10(ActionEvent event) throws IOException {
        pOneCard10GameTwo.setGraphic((Node) player1.getCard(1).getCardViewImage());
    }

    public void playerOneCard20(ActionEvent event) throws IOException {
        pOneCard20GameTwo.setGraphic((Node) player1.getCard(2).getCardViewImage());
    }

    public void playerOneCard30(ActionEvent event) throws IOException {
        pOneCard30GameTwo.setGraphic((Node) player1.getCard(3).getCardViewImage());
    }

    public void playerOneCard01(ActionEvent event) throws IOException {
        pOneCard01GameTwo.setGraphic((Node) player1.getCard(4).getCardViewImage());
    }

    public void playerOneCard11(ActionEvent event) throws IOException {
        pOneCard11GameTwo.setGraphic((Node) player1.getCard(5).getCardViewImage());
    }

    public void playerOneCard21(ActionEvent event) throws IOException {
        pOneCard21GameTwo.setGraphic((Node) player1.getCard(6).getCardViewImage());
    }

    public void playerOneCard31(ActionEvent event) throws IOException {
        pOneCard31GameTwo.setGraphic((Node) player1.getCard(7).getCardViewImage());
    }

    public void playerOneCard02(ActionEvent event) throws IOException {
        pOneCard02GameTwo.setGraphic((Node) player1.getCard(8).getCardViewImage());
    }

    public void playerOneCard12(ActionEvent event) throws IOException {
        pOneCard12GameTwo.setGraphic((Node) player1.getCard(9).getCardViewImage());
    }

    public void playerOneCard22(ActionEvent event) throws IOException {
        pOneCard22GameTwo.setGraphic((Node) player1.getCard(10).getCardViewImage());
    }

    public void playerOneCard32(ActionEvent event) throws IOException {
        pOneCard32GameTwo.setGraphic((Node) player1.getCard(11).getCardViewImage());




    }





    // +++++++++++++++ END PLAYER ONE Actions ++++++++++++++++++++++++



    @Override
    public void initialize(URL location, ResourceBundle resources) {




        pOneCard00GameTwo.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                pOneCard00GameTwo.setGraphic((Node) player1.getCard(0).getCardViewImage());
            } else if (e.getButton() == MouseButton.SECONDARY) {

                deck.swapHandDiscard(0, deck, player1);

                pOneCard00GameTwo.setGraphic((Node) player1.getCard(0).getCardViewImage());
                deckImgFaceUp.setGraphic((Node) deck.getDiscardCard().getCardViewImage());
            }
        });





        deckImgFaceDown.setGraphic((Node) deck.getCardBack().get(25));
        deckImgFaceUp.setGraphic((Node) deck.addToDiscardPile().getCardViewImage());


        labelCardsLeftDeck.setText(String.valueOf("Cards left in the deck: " + deck.getSizeCards()));
        labelCardsLeftDiscard.setText(String.valueOf("Cards in the discard pile: " + deck.getSizeDiscardPile()));
        labelInstructions.setText("Each player reveals two of their cards. The player with the highest total begins the first round.");

        /*
        Deck deck = new Deck();
        Card card = new Card(0);
        Card card2 = new Card(2);

        pOneCard00GameTwo.setGraphic(card.getCardViewBackground());
        pOneCard10GameTwo.setGraphic(card2.getCardViewBackground());
        pOneCard20GameTwo.setGraphic(card.getCardViewBackground());
        pOneCard30GameTwo.setGraphic(card.getCardViewBackground());

         */

        //Deck deck = new Deck();

        //pOneCard00GameTwo.setGraphic(player1.getCardViewBackground());
        //pOneCard10GameTwo.setGraphic(player1.getCardViewBackground());
        //deckImgFaceDown.setGraphic(player1.getCardViewBackground());

        //deckImgFaceDown.setGraphic((Node) player1.getCardBack().get(0));
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





    }
}
