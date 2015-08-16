package net.epv.epvserver.core;

public class JsonExceptionMessageContainer {
    private String message;

    public JsonExceptionMessageContainer(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
