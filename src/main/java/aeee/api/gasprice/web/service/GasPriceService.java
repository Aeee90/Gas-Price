package aeee.api.gasprice.web.service;

import aeee.api.gasprice.web.api.InfuraAPI;
import aeee.api.gasprice.web.vo.entity.GasPriceVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GasPriceService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private InfuraAPI infuraAPI;

    public GasPriceService() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(GasPriceVO.class, new GasPriceVO());
        mapper.registerModule(simpleModule);
    }

    public JsonNode getLatestTransactionJson(){
        String str = infuraAPI.getLatestTransaction(String.class);
        try {
            return mapper.readTree(str);
        }catch (IOException e){
            log.error("Can't Convert String To JsonNoe: {}", str);
            return null;
        }
    }

    public GasPriceVO getLatestTransactionVO(){

        String str = infuraAPI.getLatestTransaction(String.class);
        try {
            return mapper.readValue(str, GasPriceVO.class);
        }catch (IOException e){
            log.error("Can't Convert String To JsonNoe: {}", str);
            return null;
        }
    }
}
