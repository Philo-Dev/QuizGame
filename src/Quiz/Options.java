package Quiz;

public class Options {
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public Options(String option1, String option2, String option3, String option4) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public Options() {

    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    @Override
    public String toString() {
        return option1 + '\'' +
                ", " + option2 +
                ", " + option3 +
                ", " + option4;
    }
}
