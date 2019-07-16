package aeee.api.gasprice.web.vo.dto;

import lombok.Getter;

import java.io.Serializable;

public class ResponseDTO<T> implements Serializable {

    public static <R> ResponseDTO<R> get(ResponseType type){ return new ResponseDTO<>(type.status); }
    public static <R> ResponseDTO<R> get(ResponseType type, String message){ return new ResponseDTO<>(type.status, message); }
    public static <R> ResponseDTO<R> get(ResponseType type, String message, R data){ return new ResponseDTO<>(type.status, message, data); }

    @Getter
    private int status;
    @Getter
    private String message;
    @Getter
    private T data;

    private ResponseDTO(int status){
        this.status = status;
    }
    private ResponseDTO(int status, String message){
        this(status);
        this.message =message;
    }

    private ResponseDTO(int status, String message, T data){
        this(status, message);
        this.data = data;
    }
}
