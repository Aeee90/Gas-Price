package aeee.api.gasprice.web.vo.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class TranscationVO implements InitializerWithJsonNode<TranscationVO>, Serializable {

    @Setter @Getter
    private String gasPrice;

    @Override
    public String toString() {
        return this.getClass().getName() + "(gasPrice:" + gasPrice + ")";
    }

    @Override
    public TranscationVO initializWithJwonNode(JsonNode jsonNode) {
        this.gasPrice = jsonNode.get("gasPrice").asText();
        return this;
    }
}
