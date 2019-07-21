package aeee.api.gasprice.web.vo.entity;

import aeee.api.gasprice.util.UnitConvertor;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ResultVO implements InitializeWithJsonNode<ResultVO>, Serializable {

    @Setter @Getter
    private BigDecimal number;
    @Setter @Getter
    private ArrayList<TransactionVO> transactions = new ArrayList<>();

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName())
            .append("(number:")
            .append(number)
            .append(", transactions[");
        transactions.forEach(ts -> sb.append(ts.toString()).append(","));
        sb.append("]")
            .append(")");


        return sb.toString();
    }

    @Override
    public ResultVO initializeWithJsonNode(JsonNode jsonNode) {
         setNumber(UnitConvertor.convertHexStrToDecimalBigDecimal(jsonNode.get("number").asText()));

        JsonNode tsArray = jsonNode.get("transactions");
        if(tsArray.isArray())
            for (JsonNode node : tsArray) transactions.add(new TransactionVO().initializeWithJsonNode(node));

        return this;
    }
}
