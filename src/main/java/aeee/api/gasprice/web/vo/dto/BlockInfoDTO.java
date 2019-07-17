package aeee.api.gasprice.web.vo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BlockInfoDTO implements Serializable {

    @Setter @Getter
    private BigDecimal number;
    @Setter @Getter
    private Long size;
    @Setter @Getter
    private BigDecimal average;
    @Setter @Getter
    private BigDecimal max;
    @Setter @Getter
    private BigDecimal min;

    @Setter @Getter
    private List<TransactionCountDTO> transactionCountor;

}
