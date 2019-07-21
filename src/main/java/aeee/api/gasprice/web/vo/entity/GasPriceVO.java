package aeee.api.gasprice.web.vo.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class GasPriceVO implements Serializable, InitializeWithJsonNode<GasPriceVO> {

    @Setter @Getter
    private String jsonrpc;
    @Setter @Getter
    private String id;
    @Setter @Getter
    private ResultVO result;

    @Override
    public String toString() {
        return this.getClass().getName() + "(jsonrpc:" + jsonrpc + ", id:" + id + ", result:" + result.toString() + ")";
    }

    @Override
    public GasPriceVO initializeWithJsonNode(JsonNode jsonNode) {

        setJsonrpc(jsonNode.get("jsonrpc1").asText());
        setId(jsonNode.get("id").asText());
        setResult(new ResultVO().initializeWithJsonNode(jsonNode.get("result")));

        return this;
    }
}
