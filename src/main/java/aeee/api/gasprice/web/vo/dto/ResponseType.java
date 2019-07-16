package aeee.api.gasprice.web.vo.dto;


public enum  ResponseType {

    Success(200), FailDeserialization(500);

    public final int status;

    private ResponseType(int status){
        this.status = status;
    }
}
