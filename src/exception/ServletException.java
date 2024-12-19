package exception;

public class ServletException extends RuntimeException {
    public ServletException(String message, Throwable cause) {
        super(message, cause);
    }
}
