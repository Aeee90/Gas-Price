package aeee.api.gasprice.web.api;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
@Component
public class InfuraAPI extends HttpSender implements ResponseErrorHandler {

    public InfuraAPI(){
        super("infura.api.url");
    }

    @Override
    protected HttpHeaders setHeader(HttpHeaders headers){
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public <T> T getLatestTransaction(Class<T> clazz){
        JSONObject json= new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "eth_getBlockByNumber");
        JSONArray params = new JSONArray();
        params.put("latest");
        params.put(true);
        json.put("params", params);
        json.put("id", 1);

        HttpEntity httpEntity = getHttpEntity(json.toString());

        return post(httpEntity, clazz);
    }


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}
