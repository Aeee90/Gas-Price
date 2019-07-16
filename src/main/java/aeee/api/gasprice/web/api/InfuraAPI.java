package aeee.api.gasprice.web.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

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

    public String getLatestTransactionString() {

        JSONObject json= new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "eth_getBlockByNumber");
        JSONArray params = new JSONArray();
        params.put("latest");
        params.put(true);
        json.put("params", params);
        json.put("id", 1);

        HttpEntity<String> request = getHttpEntity(json.toString());

        return postForObject(request, String.class);
    }


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}
