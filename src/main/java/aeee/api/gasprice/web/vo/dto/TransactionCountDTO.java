package aeee.api.gasprice.web.vo.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionCountDTO {

    public TransactionCountDTO() { }

    public TransactionCountDTO(BigDecimal gasPrice, Long count){
        this.gasPrice = gasPrice;
        this.count = count;
    }

    private BigDecimal gasPrice;
    private Long count;
}
