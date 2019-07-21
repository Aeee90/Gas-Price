package aeee.api.gasprice.web.api.configuration;

import aeee.api.gasprice.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class InfuraAPIConfiguration extends HttpSender {

    public InfuraAPIConfiguration(){
        super("infura.api.url");
    }

    @Override
    protected HttpHeaders setHeader(HttpHeaders headers){
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) {
        try{
            HttpStatus.Series status = response.getStatusCode().series();
            return status == HttpStatus.Series.CLIENT_ERROR || status == HttpStatus.Series.SERVER_ERROR;
        }catch (IOException e){
            return true;
        }
    }

    @Override
    public void handleError(ClientHttpResponse response) {
        HttpStatus status = null;
        try{
            status = response.getStatusCode();
        }catch (IOException e){
            log.error("getStatusCode of ClientHttpResponse is Error: {}", e.getMessage());
            throw new ServerException();
        }

        switch (status){
            case NOT_FOUND: log.error("{} Is Not Found.", url); throw new ServerException();
            case REQUEST_TIMEOUT:
                String message = "네트워크 상태가 좋지 않습니다. 잠시후에 시도하세요.";
                log.error(message);
                throw new ServerException(message);
            default: log.error("{}'s Status Is Not Found.", url); throw new ServerException();
        }

    }
}
