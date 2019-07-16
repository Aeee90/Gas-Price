package aeee.api.gasprice.web.service;

import aeee.api.gasprice.web.api.InfuraAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GasPriceService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private InfuraAPI infuraAPI;

    public JsonNode getLatestTransactionJson(){
        String str = infuraAPI.getLatestTransactionString();
        try {
            return objectMapper.readTree(str);
        }catch (IOException e){
            log.error("Can't Convert String To JsonNoe: {}", str);
            return null;
        }
    }
}
