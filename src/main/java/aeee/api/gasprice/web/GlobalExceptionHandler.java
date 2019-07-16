package aeee.api.gasprice.web;

import aeee.api.gasprice.exception.DeserializationException;
import aeee.api.gasprice.web.vo.dto.ResponseDTO;
import aeee.api.gasprice.web.vo.dto.ResponseType;
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

}
