package bo.com.emprendeya.preparation.models;

public class Base<T> {
    private boolean success;

    private String message;

    private Exception exception;

    private T data;

    public Base(T data) {
        this.success = true;
        this.message = "";
        this.exception = null;
        this.data = data;
    }

    public Base(String message, Exception exception) {
        this.success = false;
        this.message = message;
        this.exception = exception;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
