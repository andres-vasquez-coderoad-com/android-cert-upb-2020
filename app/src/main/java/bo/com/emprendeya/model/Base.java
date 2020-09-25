package bo.com.emprendeya.model;

public class Base<T> {
    private boolean success;

    private String message;

    private Exception exception;

    private T data;

    public Base(T data) {
        this.success = true;
        this.data = data;

        this.message = null;
        this.exception = null;
    }

    public Base(String message, Exception exception) {
        this.success = false;
        this.data = null;

        this.message = message;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }

    public T getData() {
        return data;
    }
}
