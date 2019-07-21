package aeee.api.gasprice.web.api;

import aeee.api.gasprice.define.InfuraMethod;
import aeee.api.gasprice.exception.ServerException;
import aeee.api.gasprice.web.api.configuration.HttpSender;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class InfuraAPI {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpSender sender;

    public InfuraAPI(HttpSender sender){
        this.sender = sender;
    }

    private JSONObject getBaseParameter(InfuraMethod method){
        JSONObject json= new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", method.value);
        json.put("id", 1);
        return json;
    }

    private <T> T request(Class<T> clazz, InfuraMethod method, JSONArray params){
        JSONObject json = getBaseParameter(method);
        json.put("params", params);

        return request(clazz, json.toString());
    }

    private <T> T request(Class<T> clazz, String param){

        HttpEntity httpEntity = sender.getHttpEntity(param);

        return sender.post(httpEntity, clazz);
    }

    public JsonNode getEth_getBlockByNumber(){
        JSONArray params = new JSONArray();
        params.put("latest");
        params.put(true);

        return checkError(request(String.class, InfuraMethod.EthGetBlockByNumber, params));
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
