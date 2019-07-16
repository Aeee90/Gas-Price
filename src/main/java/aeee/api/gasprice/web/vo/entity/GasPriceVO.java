package aeee.api.gasprice.web.vo.entity;

import aeee.api.gasprice.exception.DeserializationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;

public class GasPriceVO extends StdDeserializer<GasPriceVO> implements Serializable {

    @Setter @Getter
    private String jsonrpc;
    @Setter @Getter
    private String id;
    @Setter @Getter
    private ResultVO result;

    public GasPriceVO() {
        this(null);
    }

    protected GasPriceVO(Class<?> vc) {
        super(vc);
    }

    @Override
    public GasPriceVO deserialize(JsonParser jp, DeserializationContext ctxt) {
        try {
            JsonNode node = jp.getCodec().readTree(jp);

            this.jsonrpc = node.get("jsonrpc").asText();
            this.id = node.get("id").asText();
            this.result = new ResultVO().initializWithJwonNode(node.get("result"));
        }catch (Exception e){
            throw new DeserializationException(e);
        }

        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "(jsonrpc:" + jsonrpc + ", id:" + id + ", result:" + result.toString() + ")";
    }

}
