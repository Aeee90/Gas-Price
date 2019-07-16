package aeee.api.gasprice.web.vo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ResponseDTO<T> implements Serializable {

    public static ResponseDTO get(ResponseType type){ return new ResponseDTO(type.status); }
    public static ResponseDTO get(ResponseType type, String messege){ return new ResponseDTO(type.status, messege); }
    public static <R> ResponseDTO get(ResponseType type, String messege, R data){ return new ResponseDTO<>(type.status, messege, data); }

    @Getter
    private int status;
    @Getter
    private String messege;
    @Getter
    private T data;

    private ResponseDTO(int status){
        this.status = status;
    }
    private ResponseDTO(int status, String messege){
        this(status);
        this.messege =messege;
    }

    public ResponseDTO(int status, String messege, T data){
        this(status, messege);
        this.data = data;
    }
}
