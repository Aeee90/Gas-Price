package aeee.api.gasprice.web.vo.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultVO implements InitializerWithJsonNode<ResultVO>, Serializable {

    @Setter @Getter
    private String number;
    @Setter @Getter
    private ArrayList<TranscationVO> transactions = new ArrayList<>();

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
    public ResultVO initializWithJwonNode(JsonNode jsonNode) {
        this.number = jsonNode.get("number").asText();

        jsonNode.get("number").asText();

        JsonNode tsArray = jsonNode.get("transactions");
        if(tsArray.isArray())
            for (JsonNode node : tsArray) transactions.add(new TranscationVO().initializWithJwonNode(node));


        return this;
    }
}
