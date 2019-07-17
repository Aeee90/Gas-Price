package aeee.api.gasprice.web.api;

import aeee.api.gasprice.define.InfuraMethod;
import aeee.api.gasprice.exception.ServerException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class InfuraAPI extends HttpSender {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public InfuraAPI(){
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
            log.error("Status is Null");
            throw new ServerException();
        }

        if(status != null) {
            switch (status){
                case NOT_FOUND: log.error("{} Is Not Found.", URL); throw new ServerException();
                case REQUEST_TIMEOUT:
                    String message = "네트워크 상태가 좋지 않습니다. 잠시후에 시도하세요.";
                    log.error(message);
                    throw new ServerException(message);
                default: log.error("{}'s Status Is Not Found.", URL); throw new ServerException();
            }
        }else {
            log.error("Status is Null");
        }
    }

    private JSONObject getBaseParameter(InfuraMethod method){
        JSONObject json= new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", method.value);
        json.put("id", 1);
        return json;
    }

    private <T> T request(Class<T> clazz, InfuraMethod method, JSONArray params){
        JSONObject json= getBaseParameter(method);
        json.put("params", params);

        return request(clazz, json.toString());
    }

    private <T> T request(Class<T> clazz, String param){

        HttpEntity httpEntity = getHttpEntity(param);

        return post(httpEntity, clazz);
    }

    public JsonNode getEth_getBlockByNumber(){
        JSONArray params = new JSONArray();
        params.put("latest");
        params.put(true);

        return checkError(request(String.class, InfuraMethod.eth_getBlockByNumber, params));
    }

    private JsonNode checkError(String str){
        JsonNode data;
        try {
            data =  objectMapper.readTree(str);
        }catch (IOException e){
            log.error("Can't Convert String To JsonNoe: {}", str);
            throw new ServerException();
        }

        if(data == null){
            log.error("JsonNode is NUll, Can't Convert String To JsonNoe: {}", str);
            throw new ServerException();
        }else{
            JsonNode error = data.get("error");
            if(error != null) {
                log.error("API is Error(Code: {}, Message: {})", error.get("code"), error.get("message"));
                throw new ServerException();
            }
        }

        return data;
    }
}
