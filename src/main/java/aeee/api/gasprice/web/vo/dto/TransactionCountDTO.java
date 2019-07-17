package aeee.api.gasprice.web.vo.dto;


import aeee.api.gasprice.web.vo.entity.TransactionVO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class TransactionCountDTO {

    public TransactionCountDTO() { }

    public TransactionCountDTO(BigDecimal gasPrice, Long count){
        this.gasPrice = gasPrice;
        this.count = count;
    }

    @Setter @Getter
    private BigDecimal gasPrice;
    @Setter @Getter
    private Long count;
}
