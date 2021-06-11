package aeee.api.gasprice.exception;


import lombok.Data;

@Data
public class InfuraErrorException extends RuntimeException {

    private static String message = "서버에 이상이 있습니다.";

    private Object data;

    public InfuraErrorException() {
        this(message, null);
    }

    public InfuraErrorException(Object data) {
        this(message, data);
    }

    public InfuraErrorException(String message, Object data) {
        super(message);
        this.data = data;
    }

}
