package bo.com.emprendeya.model;

public class Base {
    private boolean success;

    private String message;

    private Exception exception;

    private Object data;

    public Base(Object data) {
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

    public Object getData() {
        return data;
    }
}
