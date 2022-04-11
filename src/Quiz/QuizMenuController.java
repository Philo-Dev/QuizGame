package Quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizMenuController implements Initializable {

    public Button startButton;
    public Button multiplayerButton;
    public Button addQuestionsButton;

    /**
     * handles outputs for the start game button.
     * @param event
     * @throws IOException
     */

    @FXML
    private void handleMenuButtonAction (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == startButton){
            stage = (Stage) startButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("QuizGame.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Who Wants To Be A Zillionaire - Player 1");
        stage.show();
    }

    /**
     * Opens two instances of Quiz Game for multiplayer.
     */

    @FXML
    private void handleMultiplayerButton (ActionEvent event) throws IOException {
        Stage stage = null;
        Parent myNewScene = null;

        if (event.getSource() == multiplayerButton) {
            stage = (Stage) multiplayerButton.getScene().getWindow();
            myNewScene = FXMLLoader.load(getClass().getResource("QuizGame.fxml"));
        }

        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Who Wants To Be A Zillionaire - Player 1");
        stage.show();

        Stage primaryStage = new Stage();

        if (event.getSource() == multiplayerButton) {
            Parent root = FXMLLoader.load(getClass().getResource("QuizGame.fxml"));
            primaryStage.setTitle("Who Wants To Be A Zillionaire - Player 2");
            primaryStage.setScene(new Scene(root, 1000, 740));
            primaryStage.show();
        }
    }

    /**
     * handles the add question button. Didn't have time to finish
     * add question feature so its just here.
     * @param event
     */

    @FXML
    private void handleAddQuestionsButton (ActionEvent event) {
        Alert addQuestions = new Alert(Alert.AlertType.CONFIRMATION);
        addQuestions.setTitle("Add Questions");
        addQuestions.setHeaderText("Coming Soon!");
        addQuestions.setContentText("Choose your option.");
        addQuestions.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

}
