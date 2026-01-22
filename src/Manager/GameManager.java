package Manager;

/**
 * GameManager - منطق احتساب النقاط (مبسط)
 */
public class GameManager {

    /**
     * computeScore: حسبة مبسطة للرِبح.
     * reactionMs: زمن الاستجابة بالملي ثانية
     * challengeType: "Letter" | "Word" | "Symbol" أو غيرها
     */
    public int computeScore(long reactionMs, String challengeType) {
        int base = 1000;
        int factor = 1;
        if (challengeType == null) challengeType = "";
        switch (challengeType.toLowerCase()) {
            case "letter": factor = 2; break;
            case "word":   factor = 3; break;
            case "symbol": factor = 4; break;
            default: factor = 1; break;
        }
        return Math.max(0, (int) (base / (reactionMs + 1)) * factor);
    }
}
