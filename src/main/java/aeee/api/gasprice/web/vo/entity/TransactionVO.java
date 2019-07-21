package aeee.api.gasprice.web.vo.entity;

import aeee.api.gasprice.util.UnitConvertor;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionVO implements InitializeWithJsonNode<TransactionVO>, Serializable {

    @Setter @Getter
    private BigDecimal gasPrice;

    @Override
    public String toString() {
        return this.getClass().getName() + "(gasPrice:" + gasPrice + ")";
    }

    @Override
    public TransactionVO initializeWithJsonNode(JsonNode jsonNode) {
        setGasPrice(UnitConvertor.convertHexStrToDecimalBigDecimal(jsonNode.get("gasPrice").asText()));
        return this;
    }
}
