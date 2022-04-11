package Quiz;

public class Question {
    private String name;
    private String category;
    private String hint;
    private char answer;

    public Question(String name, String category, String hint, char answer) {
        this.name = name;
        this.category = category;
        this.hint = hint;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getHint() {
        return hint;
    }

    public char getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return name + '\'' +
                ", " + category +
                ", " + answer;
    }
}