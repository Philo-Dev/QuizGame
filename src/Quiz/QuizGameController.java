package Quiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class QuizGameController {
    //JavaFX variables
    public TextField aOption;
    public TextField bOption;
    public TextField cOption;
    public TextField dOption;
    public Button aButton;
    public Button bButton;
    public Button cButton;
    public Button dButton;
    public Label questionText;
    public Label categoryText;
    public Button nextButton;
    public TextField questionNumText;
    public ProgressBar timerBar;
    public Button hintButton;
    public TextField hintText;
    public ListView moneyList;
    public Button askAudience;
    public Button halfAndHalf;
    public BarChart audienceChart;

    //creates arrays lists for quiz game
    private ArrayList<Question> questionsList = new ArrayList<>();
    private ArrayList<Options> optionsList = new ArrayList<>();
    private ArrayList<Integer> qNumArray = new ArrayList<>();

    //creates a new global instance of Money class
    Money mny = new Money();

    //Variables
    int totalQuestions;
    int qNum;
    int index;
    char answer;

    @FXML
    private void initialize()
    {
        //Computer Science Questions
        addQuestion(new Question("Who is credited with inventing the World Wide Web","Computer Science","He has the shortest first name",'A'));
        addQuestion(new Question("What was the Google Search Engine originally called?","Computer Science","Yes, Really!",'B'));
        addQuestion(new Question("What influential software was released on 20th November 1985","Computer Science","DOOM was released in 1993",'C'));
        addQuestion(new Question("What was the first commercially available programming language?","Computer Science","This quiz is NOT written using it",'C'));
        addQuestion(new Question("How many Megabytes are in 1 Gigabyte?","Computer Science","It's MORE than 8",'D'));
        //Math Questions
        addQuestion(new Question("What is 10 + 1?","Maths","You are already adding 1 and 10",'A'));
        addQuestion(new Question("What is 384 x 92?","Maths","Look at the numbers carefully",'A'));
        addQuestion(new Question("What is 50 - 5?","Maths","Watch for the decimal point",'C'));
        addQuestion(new Question("What is 80 ÷ 4?","Maths","It's not 1",'D'));
        addQuestion(new Question("What is 9433 ÷ 1?","Maths","Look at the numbers carefully",'A'));
        //General Knowledge Questions
        addQuestion(new Question("What House does Jon Snow belong to in 'Game Of Thrones'?","General Knowledge","House of Fraser is a department store.",'A'));
        addQuestion(new Question("The names of the ghosts in Pac-Man are: Inky, Blinky, Pinky and _____?","General Knowledge","The one you least expect",'D'));
        addQuestion(new Question("Which year was the FIRST episode of 'The Simpsons' originally aired?","General Knowledge","It's older than you think...",'C'));
        addQuestion(new Question("Which car is featured prominently in 'Back to the Future'?","General Knowledge","You don't need a hint for this one",'A'));
        addQuestion(new Question("What is the smallest country in the world?","General Knowledge","It's also a City",'A'));
        //More General Knowledge Questions
        addQuestion(new Question("Which actor played 'Jules' in Pulp Fiction?","General Knowledge","John Travolta played Vincent Vega",'A'));
        addQuestion(new Question("What iconic hat does Walter White wear is 'Breaking Bad'?","General Knowledge","A Top Hat would be very distracting",'D'));
        addQuestion(new Question("What is the smallest planet in our solar system?","General Knowledge","One of these is technically not a planet",'C'));
        addQuestion(new Question("What is the most sold flavour of Walker’s crisps?","General Knowledge","It's not Ready Salted",'B'));
        addQuestion(new Question("What is the capital city of Australia?","General Knowledge","Not the one you think it is",'B'));
        //Computer Science Options
        addOptions(new Options("Tim Berners-Lee", "Bill Gates", "Steve Jobs", "Steve Wozniak"));
        addOptions(new Options("Finder", "BackRub", "WebSearch", "Googol"));
        addOptions(new Options("Minecraft", "DOOM", "Microsoft Windows", "WinRar"));
        addOptions(new Options("Java", "C", "FORTRAN", "Python"));
        addOptions(new Options("1000","1020","8","1024"));
        //Maths Options
        addOptions(new Options("11","9","1","10"));
        addOptions(new Options("35328","33582","32853","83523"));
        addOptions(new Options("4.5","55","45","54"));
        addOptions(new Options("10","1","15","20"));
        addOptions(new Options("9433","1","9344","9343"));
        //General Knowledge Options
        addOptions(new Options("House Stark","House Lannister","House Targaryen","House Of Fraser"));
        addOptions(new Options("Slinky","Thinky","Stinky","Clyde"));
        addOptions(new Options("1997","1996","1989","1993"));
        addOptions(new Options("DeLorean DMC 12","Chevrolet Corvette","Toyota Supra","Ford Escort"));
        addOptions(new Options("Vatican City","Monaco","Luxembourg","Malta"));
        //More General Knowledge Options
        addOptions(new Options("Samuel L Jackson","John Travolta","Quentin Tarantino","Bruce Willis"));
        addOptions(new Options("Top Hat","Beanie","No Hat","Pork Pie Hat"));
        addOptions(new Options("Pluto","Venus","Mercury","Mars"));
        addOptions(new Options("Ready Salted","Cheese and Onion","Salt and Vinegar","Roast Chicken"));
        addOptions(new Options("Sydney","Canberra","Adelaide","Melbourne"));

        //sets up parameters for quiz game
        setNewMoney();
        nextButton.setDisable(true);
        totalQuestions = questionsList.size();
        getRandomNumSet();
        System.out.println(qNumArray.toString());
        categorySelectPopup();
        nextQuestion();
    }

    /**
     * Sets up the next question, unless the index reaches 15
     * at which point results() is activated.
     */

    public void nextQuestion() {
        if (index >= 15) {
            results();
            questionNumText.setText("");
        } else {
            Options options = optionsList.get(qNum);
            Question question = questionsList.get(qNum);
            questionNumText.setText("Q" + (index+1));

            audienceChart.setVisible(false);
            nextButton.setDisable(true);
            hintText.setVisible(false);
            enableButtons();

            questionText.setText(question.getName());
            categoryText.setText(question.getCategory());
            aOption.setText(options.getOption1());
            bOption.setText(options.getOption2());
            cOption.setText(options.getOption3());
            dOption.setText(options.getOption4());
        }
    }

    //activates the nextButton and runs nextQuestion()
    /**
     * Updates MoneyList, activates nextQuestion and resets the border colour
     * when the nextButton is activated.
     * @param actionEvent
     */

    public void useNextButton(ActionEvent actionEvent) {
        if (actionEvent.getSource() == nextButton){
            mny.moneySelect();
            setNewMoney();
            nextQuestion();
            aOption.setBorder(Border.EMPTY);
            bOption.setBorder(Border.EMPTY);
            cOption.setBorder(Border.EMPTY);
            dOption.setBorder(Border.EMPTY);
        }
    }

    /**
     * Activates "Get a Hint" feature by making the hintText textField visible
     * and gets hint text from questionList. Also deactivates the button.
     * @param actionEvent
     */

    public void useHintButton(ActionEvent actionEvent) {
        Question question = questionsList.get(qNum);
        if (actionEvent.getSource() == hintButton){
            hintText.setVisible(true);
            hintText.setBorder(new Border(new BorderStroke(Color.valueOf("#FF69B4"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            hintText.setText(question.getHint());
            hintButton.setDisable(true);
        }
    }

    /**
     * Used for "Ask the Public" feature. Generates random numbers
     * applies them to BarChart and prints them as percentages
     * @param actionEvent
     */

    public void setAskAudience(ActionEvent actionEvent) {
        //generates the random numbers for each audience poll
        int aAudience = getRandomNum(0,100);
        int bAudience = getRandomNum(0,100);
        int cAudience = getRandomNum(0,100);
        int dAudience = getRandomNum(0,100);

        //gets the total amount of votes
        int maxAudience = aAudience + bAudience + cAudience + dAudience;

        //gets votes as a (int) percentage
        int aPercent = getPercentageAsInt(aAudience,maxAudience);
        int bPercent = getPercentageAsInt(bAudience,maxAudience);
        int cPercent = getPercentageAsInt(cAudience,maxAudience);
        int dPercent = getPercentageAsInt(dAudience,maxAudience);

        //[barChart]adds percentages to the BarChart
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("A", aPercent));
        series1.getData().add(new XYChart.Data("B", bPercent));
        series1.getData().add(new XYChart.Data("C", cPercent));
        series1.getData().add(new XYChart.Data("D", dPercent));
        audienceChart.getData().addAll(series1);

        //Disables buttons and sets hintText to the percentages as a number value
        askAudience.setDisable(true);
        audienceChart.setVisible(true);
        hintText.setVisible(true);
        hintText.setBorder(new Border(new BorderStroke(Color.valueOf("#00FFFF"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hintText.setText("A: " + aPercent + "% | " + "B: " + bPercent + "% | " + "C: " + cPercent + "% | " + "D: " + dPercent + "% ");

    }

    /**
     * returns percentage as int from given
     * amount and max values.
     * @param amount, max
     */

    public int getPercentageAsInt(int amount, int max) {
        return (amount*100)/max;
    }

    /**
     * Disables half of the options and turns border for them red
     * depending on which option is correct.
     * @param actionEvent
     * @throws
     */

    public void setHalfAndHalf(ActionEvent actionEvent) {
        Question question = questionsList.get(qNum);
        halfAndHalf.setDisable(true);

        //Not chosen at random, if A is the correct answer the b and c are disabled leaving A and D as the only possible choices etc...
        if (question.getAnswer() == 'A') {
            bButton.setDisable(true);
            cButton.setDisable(true);
            bOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            cOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        if (question.getAnswer() == 'B') {
            aButton.setDisable(true);
            dButton.setDisable(true);
            aOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            dOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        if (question.getAnswer() == 'C') {
            bButton.setDisable(true);
            dButton.setDisable(true);
            bOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            dOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        if (question.getAnswer() == 'D') {
            aButton.setDisable(true);
            cButton.setDisable(true);
            aOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            cOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }

    /**
     * Adds values from Money[] in Money Class
     * to the Observable MoneyList.
     */

    public void setNewMoney() {
        ObservableList<String> moneyCounter = FXCollections.observableArrayList(mny.getMoney());
        moneyList.setItems(moneyCounter);
    }

    /**
     * Handle's input of all answers,
     * if incorrect then results().
     * @param actionEvent
     */

    @FXML
    private void handleAnswerSelect(ActionEvent actionEvent) {
        Question question = questionsList.get(qNum);

        //to stop players from trying to make another choice then allows you press next question.
        disableButtons();
        nextButton.setDisable(false);

        //If answer is incorrect then results()
        if (actionEvent.getSource() == aButton) {
            answer = 'A';
            if (answer != question.getAnswer()) {
                results();
            }
        }
        if (actionEvent.getSource() == bButton) {
            answer = 'B';
            if (answer != question.getAnswer()) {
                results();
            }
        }
        if (actionEvent.getSource() == cButton) {
            answer = 'C';
            if (answer != question.getAnswer()) {
                results();
            }
        }
        if (actionEvent.getSource() == dButton) {
            answer = 'D';
            if (answer != question.getAnswer()) {
                results();
            }
        }
        //gets next question
        displayCorrect();
        index++;
        qNum = qNumArray.get(index);
    }

    /**
     * Displays Correct answers with green border,
     * and Incorrect answers with red border.
     */

    public void displayCorrect() {
        Question question = questionsList.get(qNum);

        if (question.getAnswer() != 'A')
            aOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (question.getAnswer() != 'B')
            bOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (question.getAnswer() != 'C')
            cOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (question.getAnswer() != 'D')
            dOption.setBorder(new Border(new BorderStroke(Color.valueOf("#FF0000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        if (question.getAnswer() == 'A')
            aOption.setBorder(new Border(new BorderStroke(Color.valueOf("#00FF00"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (question.getAnswer() == 'B')
            bOption.setBorder(new Border(new BorderStroke(Color.valueOf("#00FF00"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (question.getAnswer() == 'C')
            cOption.setBorder(new Border(new BorderStroke(Color.valueOf("#00FF00"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        if (question.getAnswer() == 'D')
            dOption.setBorder(new Border(new BorderStroke(Color.valueOf("#00FF00"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    /**
     * Clears the screen and disables buttons
     * for results popup window
     */

    private void results() {
        questionText.setText("Results");
        categoryText.setVisible(false);

        hintButton.setDisable(true);
        disableButtons();
        askAudience.setDisable(true);
        halfAndHalf.setDisable(true);
        nextButton.setDisable(true);

        aOption.setVisible(false);
        bOption.setVisible(false);
        cOption.setVisible(false);
        dOption.setVisible(false);

        resultPopup();
    }

    /**
     * Opens results popup window
     * and shows the money you have won
     */

    public void resultPopup() {
        Alert resultPop = new Alert(Alert.AlertType.CONFIRMATION);
        resultPop.setTitle("Results!");

        if (index != 0) {
            resultPop.setHeaderText("You WON: " + mny.money[(15-index)]);
        } else {
            resultPop.setHeaderText("You LOSE!");
        }

        resultPop.showAndWait();
    }

    /**
     * Displays the category select at the start of the game
     * Also removes generated number from qNumArray so question doesn't repeat.
     */

    public void categorySelectPopup() {
        Alert categoryPop = new Alert(Alert.AlertType.CONFIRMATION);
        categoryPop.setTitle("Category Select");
        categoryPop.setHeaderText("Select Your Starting Category");
        categoryPop.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Computer Science");
        ButtonType buttonTypeTwo = new ButtonType("Mathematics");
        ButtonType buttonTypeThree = new ButtonType("General Knowledge");

        categoryPop.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);

        Optional<ButtonType> result = categoryPop.showAndWait();

        //gets random number within each range for first question and then removes from qNumArray
        if (result.get() == buttonTypeOne) {
            qNum = getRandomNum(0,4);
            qNumArray.remove(Integer.valueOf(qNum));
        }
        if (result.get() == buttonTypeTwo) {
            qNum = getRandomNum(5,9);
            qNumArray.remove(Integer.valueOf(qNum));
        }
        if (result.get() == buttonTypeThree) {
            qNum = getRandomNum(10,14);
            qNumArray.remove(Integer.valueOf(qNum));
        }
    }

    /**
     * Disables the option buttons. For convenience.
     */

    public void disableButtons() {
        aButton.setDisable(true);
        bButton.setDisable(true);
        cButton.setDisable(true);
        dButton.setDisable(true);
    }

    /**
     * Enables the option buttons. For convenience.
     */

    public void enableButtons() {
        aButton.setDisable(false);
        bButton.setDisable(false);
        cButton.setDisable(false);
        dButton.setDisable(false);
    }

    /**
     * Generates a random number in given range of 2 values.
     * ADAPTED FROM: https://www.baeldung.com/java-generating-random-numbers-in-range
     * @param min, max
     */

    public int getRandomNum(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Generates a sequence of non-repeating random numbers in given range of 2 values.
     * ADAPTED FROM: https://stackoverflow.com/questions/4040001/creating-random-numbers-with-no-duplicates
     */

    public void getRandomNumSet() {
        for (int i = 0; i < totalQuestions; i++) {
            qNumArray.add(i);
        }
        Collections.shuffle(qNumArray);
    }

    public void addOptions(Options opts) {
        optionsList.add(opts);
    }

    public void addQuestion(Question qs) {
        questionsList.add(qs);
    }

}
