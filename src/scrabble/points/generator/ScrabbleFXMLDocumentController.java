/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble.points.generator;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author patel
 */
public class ScrabbleFXMLDocumentController implements Initializable {

    @FXML
    private Label points;
    @FXML
    private TextField wordCheck;
    @FXML
    private Button subButton;
    @FXML
    private Label errorMsg;
    @FXML
    private Label lblA, lblB, lblC, lblD, lblE, lblF, lblG, lblH, lblI, lblJ, lblK, lblL, lblM, lblN, lblO, lblP, lblQ, lblR, lblS,
            lblT, lblU, lblV, lblW, lblX, lblY, lblZ; 
    @FXML
    private HBox previousWord;

    private static final Map<Character, Integer> mapOfWords;
    private static int totalValue = 0;
    private static Map<Character, Integer> remainingBucket;
    private static List<String> savedWordList;

    static {
        mapOfWords = new HashMap<Character, Integer>();
        mapOfWords.put('A', 1);
        mapOfWords.put('B', 3);
        mapOfWords.put('C', 3);
        mapOfWords.put('D', 2);
        mapOfWords.put('E', 1);
        mapOfWords.put('F', 4);
        mapOfWords.put('G', 2);
        mapOfWords.put('H', 4);
        mapOfWords.put('I', 1);
        mapOfWords.put('J', 8);
        mapOfWords.put('K', 5);
        mapOfWords.put('L', 1);
        mapOfWords.put('M', 3);
        mapOfWords.put('N', 1);
        mapOfWords.put('O', 1);
        mapOfWords.put('P', 3);
        mapOfWords.put('Q', 10);
        mapOfWords.put('R', 1);
        mapOfWords.put('S', 1);
        mapOfWords.put('T', 1);
        mapOfWords.put('U', 1);
        mapOfWords.put('V', 4);
        mapOfWords.put('W', 4);
        mapOfWords.put('X', 8);
        mapOfWords.put('Y', 4);
        mapOfWords.put('Z', 10);

        remainingBucket = new HashMap<Character, Integer>();
        remainingBucket.put('E', 12);
        remainingBucket.put('A', 9);
        remainingBucket.put('R', 6);
        remainingBucket.put('O', 8);
        remainingBucket.put('I', 8);
        remainingBucket.put('T', 6);
        remainingBucket.put('S', 4);
        remainingBucket.put('N', 6);
        remainingBucket.put('L', 4);
        remainingBucket.put('D', 4);
        remainingBucket.put('U', 4);
        remainingBucket.put('G', 3);
        remainingBucket.put('P', 2);
        remainingBucket.put('M', 2);
        remainingBucket.put('B', 2);
        remainingBucket.put('H', 2);
        remainingBucket.put('C', 2);
        remainingBucket.put('W', 2);
        remainingBucket.put('Y', 2);
        remainingBucket.put('F', 2);
        remainingBucket.put('V', 2);
        remainingBucket.put('K', 1);
        remainingBucket.put('X', 1);
        remainingBucket.put('Z', 1);
        remainingBucket.put('J', 1);
        remainingBucket.put('Q', 1);

        savedWordList = new ArrayList<String>();

    }

    @FXML
    public void submitWord() {
        String word = wordCheck.getText();
        String[] words = word.split(" ");
        boolean flag = true;
        if (word.trim().isEmpty()) {
            errorMsg.setText("Word is blank");
            flag = false;
        } else if (word.trim().length() < 2) {
            errorMsg.setText("Word is too short (only 1 character)");
            flag = false;
        } else if (word.trim().length() > 8) {
            errorMsg.setText("Word is too long (more than 8 characters)");
            flag = false;
        } else if (!word.toUpperCase().contains("A") && !word.toUpperCase().contains("E")
                && !word.toUpperCase().contains("I") && !word.toUpperCase().contains("O")
                && !word.toUpperCase().contains("U") && !word.toUpperCase().contains("Y")) {
            errorMsg.setText("Word does not include vowel");
            flag = false;
        }
        if (flag && words.length < 2 && wordBucket(word) && !savedWordList.contains(word)) {
            errorMsg.setText("");
            for (int j = 0; j < word.length(); j++) {
                totalValue += mapOfWords.get(word.toUpperCase().charAt(j));
            }
            updateBucket(word);
            savedWordList.add(word);
            System.out.println(totalValue);
            points.setText(String.valueOf(totalValue));

            previousWord.getChildren().clear();
            for (int i = 0; i < savedWordList.size(); i++) {
                Label preWord = new Label(savedWordList.get(i));
                preWord.setPadding(new Insets(5, 5, 5, 5));
                previousWord.getChildren().add(preWord);
            }
            lblA.setText(String.valueOf(remainingBucket.get('A')));
            lblB.setText(String.valueOf(remainingBucket.get('B')));
            lblC.setText(String.valueOf(remainingBucket.get('C')));
            lblD.setText(String.valueOf(remainingBucket.get('D')));
            lblE.setText(String.valueOf(remainingBucket.get('E')));
            lblF.setText(String.valueOf(remainingBucket.get('F')));
            lblG.setText(String.valueOf(remainingBucket.get('G')));
            lblH.setText(String.valueOf(remainingBucket.get('H')));
            lblI.setText(String.valueOf(remainingBucket.get('I')));
            lblJ.setText(String.valueOf(remainingBucket.get('J')));
            lblK.setText(String.valueOf(remainingBucket.get('K')));
            lblL.setText(String.valueOf(remainingBucket.get('L')));
            lblM.setText(String.valueOf(remainingBucket.get('M')));
            lblN.setText(String.valueOf(remainingBucket.get('N')));
            lblO.setText(String.valueOf(remainingBucket.get('O')));
            lblP.setText(String.valueOf(remainingBucket.get('P')));
            lblQ.setText(String.valueOf(remainingBucket.get('Q')));
            lblR.setText(String.valueOf(remainingBucket.get('R')));
            lblS.setText(String.valueOf(remainingBucket.get('S')));
            lblT.setText(String.valueOf(remainingBucket.get('T')));
            lblU.setText(String.valueOf(remainingBucket.get('U')));
            lblV.setText(String.valueOf(remainingBucket.get('V')));
            lblW.setText(String.valueOf(remainingBucket.get('W')));
            lblX.setText(String.valueOf(remainingBucket.get('X')));
            lblY.setText(String.valueOf(remainingBucket.get('Y')));
            lblZ.setText(String.valueOf(remainingBucket.get('Z')));

            checkGameOver();
        }

    }

    private void checkGameOver() {
        List<Integer> totalWord = new ArrayList<Integer>(remainingBucket.values());
        int totalWordValue = 0;
        for (int i = 0; i < totalWord.size(); i++) {
            totalWordValue += totalWord.get(i);
        }
        if (totalWordValue == 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, Bucket of Total");
            alert.setContentText("only one letter remaining in bag");
            alert.showAndWait();
            subButton.setDisable(true);
        }

        Map<Character, Integer> tempMap = new HashMap<Character, Integer>(remainingBucket);
        if (tempMap.get('A') == 0 && tempMap.get('E') == 0 && tempMap.get('I') == 0 && tempMap.get('O') == 0
                && tempMap.get('U') == 0 && tempMap.get('Y') == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, Bucket Of Total");
            alert.setContentText("Word contains letter that is no longer available “in bag”!");
            alert.showAndWait();
            subButton.setDisable(true);
        }
    }

    private void updateBucket(String word) {
        for (int j = 0; j < word.length(); j++) {
            remainingBucket.put(word.toUpperCase().charAt(j), remainingBucket.get(word.toUpperCase().charAt(j)) - 1);

        }
        System.out.println(remainingBucket);
    }

    public boolean wordBucket(String word) {
        Map<Character, Integer> tempMap = new HashMap<Character, Integer>(remainingBucket);
        for (int j = 0; j < word.length(); j++) {
            if (tempMap.get(word.toUpperCase().charAt(j)) <= 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, Bucket Total Of " + word.toUpperCase().charAt(j));
                alert.setContentText("Word contains letter that is no longer available “in bag”!");
                alert.showAndWait();
                return false;
            }
            tempMap.put(word.toUpperCase().charAt(j), tempMap.get(word.toUpperCase().charAt(j)) - 1);
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
