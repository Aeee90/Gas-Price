package aeee.api.gasprice.web.vo.entity;

import aeee.api.gasprice.exception.DeserializationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


public class GasPriceVODeserializer extends StdDeserializer<GasPriceVO> {

    public GasPriceVODeserializer() { this(null); }

    protected GasPriceVODeserializer(Class<?> vc) { super(vc); }

    @Override
    public GasPriceVO deserialize(JsonParser jp, DeserializationContext ctxt) {
        GasPriceVO gasPriceVO = new GasPriceVO();
        try {
            JsonNode node = jp.getCodec().readTree(jp);

            gasPriceVO.initializeWithJsonNode(node);
        }catch (Exception e){
            throw new DeserializationException(e);
        }

        return gasPriceVO;
    }
}
