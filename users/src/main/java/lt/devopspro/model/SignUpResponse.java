package lt.devopspro.model;

import java.io.Serializable;

public class SignUpResponse implements Serializable {

    public enum ResponseStatus {OK, ERROR};
    private ResponseStatus status;
    private String message;

    public SignUpResponse() {
    }

    public SignUpResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
