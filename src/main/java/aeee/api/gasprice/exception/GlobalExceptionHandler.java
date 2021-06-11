package aeee.api.gasprice.exception;

import aeee.api.gasprice.dto.ResponseDTO;
import aeee.api.gasprice.dto.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {


    @ExceptionHandler(DeserializationException.class)
    public ResponseDTO deserializationException(DeserializationException e){

        log.error(e.getMessage());

        return ResponseDTO.get(ResponseType.FailDeserialization, "서버 상에 문제가 있습니다.");
    }

    @ExceptionHandler(InfuraErrorException.class)
    public ResponseDTO infuraErrorException(InfuraErrorException e){

        log.error("[" + e.getInfurErrorCode() + "]" + e.getInfuraErrorMessage());

        return ResponseDTO.get(ResponseType.InfuraErrorException, "서버 상에 문제가 있습니다.");
    }

}
