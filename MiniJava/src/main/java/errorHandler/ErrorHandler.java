package errorHandler;

/**
 * Created by Alireza on 6/28/2015.
 */

public class ErrorHandler {
    private static boolean hasError = false;

    private ErrorHandler() {
    }

    public static void setHasError(boolean hasError) {
        ErrorHandler.hasError = hasError;
    }

    public static boolean getHasError() {
        return hasError;
    }

    public static void printError(String msg) {
        setHasError(true);
        System.out.println(msg);
    }
}
