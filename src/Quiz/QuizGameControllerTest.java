package Quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuizGameControllerTest {
    private QuizGameController qgc;
    @BeforeEach
    void setUp() {
        qgc = new QuizGameController();
    }

    @Test
    void getRandomNum() {
        //generate a random number int between 2 values
        //result should not be more than 15 or less than 0
        int result = qgc.getRandomNum(0,15);
        assertNotEquals(16,result);
        assertNotEquals(-1,result);
    }
}