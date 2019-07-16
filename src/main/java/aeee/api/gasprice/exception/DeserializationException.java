package aeee.api.gasprice.exception;

public class DeserializationException extends RuntimeException {
    public DeserializationException(String message) { super(message); }
    public DeserializationException(Exception e) { super(e.getMessage(), e.getCause()); }
    public DeserializationException(String message, Throwable t) { super(message, t); }
}
