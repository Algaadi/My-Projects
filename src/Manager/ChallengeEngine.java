package Manager;

import java.util.Random;

public class ChallengeEngine {

    private String[] challenges = {
            "اكتب الكلمة: برمجة",
            "اختر الشكل: دائرة",
            "اختر الحرف: A",
            "اكتب الكلمة: Java",
            "اختر الشكل: مربع"
    };

    private Random random;

    public ChallengeEngine() {
        random = new Random();
    }

    public String getNextChallenge() {
        int index = random.nextInt(challenges.length);
        return challenges[index];
 }
}
