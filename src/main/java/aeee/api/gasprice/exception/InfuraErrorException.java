package aeee.api.gasprice.exception;

import aeee.api.gasprice.web.vo.entity.ErrorEntity;

public class InfuraErrorException extends RuntimeException {

    private static String message = "서버에 이상이 있습니다.";

    private ErrorEntity errorEntity;

    public InfuraErrorException(ErrorEntity errorEntity) {
        super(message);
        this.errorEntity = errorEntity;
    }

    public InfuraErrorException(String message, ErrorEntity errorEntity) {
        super(message);
        this.errorEntity = errorEntity;
    }

    public Long getInfurErrorCode(){
        return errorEntity.getCode();
    };

    public String getInfuraErrorMessage(){
        return errorEntity.getMessage();
    }

}
