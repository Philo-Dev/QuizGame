package Quiz;

public class Money {
    int moneyNum;

    public String[] getMoney() {
        return money;
    }

    public void setMoney(String[] money) {
        this.money = money;
    }

    /**
     * Holds all money values for list view
     */

    String[] money = {
            "15 · £1 Million",
            "14 · £500,000",
            "13 · £250,000",
            "12 · £125,000",
            "11 · £64,000",
            "10 · £32,000",
            "9 · £16,000",
            "8 · £8,000",
            "7 · £4,000",
            "6 · £2,000",
            "5 · £1,000",
            "4 · £500",
            "3 · £300",
            "2 · £200",
            "1 · £100",
    };

    /**
     * Changes money values on list view as correct guesses increase.
     */

    public void moneySelect() {
        moneyNum++;
        int moneyFinal = money.length - moneyNum;
        money[moneyFinal] = "★ " + money[moneyFinal] + " ★";
    }
}
