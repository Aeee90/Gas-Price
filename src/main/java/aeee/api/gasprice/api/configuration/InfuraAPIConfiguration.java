package aeee.api.gasprice.api.configuration;

import aeee.api.gasprice.exception.InfuraErrorException;
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
        throw new InfuraErrorException(response);
    }
}
