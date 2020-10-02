package bo.com.emprendeya.model;

public class Base<T> {
    private boolean success;

    private int errorCode;

    private Exception exception;

    private T data;

    public Base(T data) {
        this.success = true;
        this.data = data;

        this.errorCode = 0;
        this.exception = null;
    }

    public Base(int errorCode, Exception exception) {
        this.success = false;
        this.data = null;

        this.errorCode = errorCode;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Exception getException() {
        return exception;
    }

    public T getData() {
        return data;
    }
}
