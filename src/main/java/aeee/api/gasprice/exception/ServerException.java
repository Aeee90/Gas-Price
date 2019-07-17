package aeee.api.gasprice.exception;

public class ServerException extends RuntimeException {
    private static String message = "서버에 이상이 있습니다.";

    public ServerException() { super(message); }
    public ServerException(String message) { super(message); }
    public ServerException(Exception e) { super(message, e.getCause()); }
    public ServerException(Throwable t) { super(message, t); }
}