package aeee.api.gasprice.infura;

import aeee.api.gasprice.web.api.InfuraAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfuraApi {

    @Value("${infura.api.url}")
    private String URL;

    @Before
    public void checkProperties(){
        assert(URL != null && !URL.isEmpty());
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getLatestTransaction() throws JSONException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject json= new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "eth_getBlockByNumber");
        JSONArray params = new JSONArray();
        params.put("latest");
        params.put(true);
        json.put("params", params);
        json.put("id", 1);

        log.info(json.toString());

        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        String responseEntity = restTemplate.postForObject(URL, request, String.class);

        JsonNode root = objectMapper.readTree(responseEntity);

        log.info(root.toString());
    }

    @Autowired
    private InfuraAPI infuraAPI;

    @Test
    public void getGasPriceServiceString(){
        String gasPriceLatest = infuraAPI.getLatestTransaction(String.class);
        log.info(gasPriceLatest);
        assert(gasPriceLatest != null && !gasPriceLatest.isEmpty());
    }
}
