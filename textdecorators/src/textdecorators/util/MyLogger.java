package textdecorators.util;

public class MyLogger {
    public static enum DebugLevel { KEYWORDDECORATOR, MOSTFREQUENTDECORATOR ,SENTENCEDECORATOR, SPELLCHECKDECORATOR };

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
        switch (levelIn) {
            case 4: debugLevel = DebugLevel.SENTENCEDECORATOR; break;
            case 3: debugLevel = DebugLevel.SPELLCHECKDECORATOR; break;
            case 2: debugLevel = DebugLevel.KEYWORDDECORATOR; break;
            case 1: debugLevel = DebugLevel.MOSTFREQUENTDECORATOR; break;
            // default: debugLevel = DebugLevel.NONE; break;
        }
    }

    /***
     * Setting the input vslue for switch case.
     * @param levelIn
     */
    public static void setDebugValue (DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    /***
     * writeMessage helps to print the output message.
     * @param message
     * @param levelIn
     */
    public static void writeMessage (String message, DebugLevel levelIn ) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    /***
     * Message helps to print the output message.
     * @param message
     */
    public static void Message (String message ) {
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}