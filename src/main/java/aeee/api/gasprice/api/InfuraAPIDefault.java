package aeee.api.gasprice.api;

import aeee.api.gasprice.api.configuration.HttpSender;
import aeee.api.gasprice.define.InfuraMethod;
import aeee.api.gasprice.exception.InfuraErrorException;
import aeee.api.gasprice.vo.GasPrice;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class InfuraAPIDefault implements InfuraAPI{

    private final HttpSender sender;

    public InfuraAPIDefault(HttpSender sender){
        this.sender = sender;
    }

    private JSONObject getBaseParameter(InfuraMethod method){
        JSONObject json= new JSONObject();
        json.put("jsonrpc1", "2.0");
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

    @Override
    public GasPrice getEth_getBlockByNumber(){
        JSONArray params = new JSONArray();
        params.put("latest");
        params.put(true);

        return request(GasPrice.class, InfuraMethod.ETH_GET_BLOCK_BY_NUMBER, params);
    }
}
