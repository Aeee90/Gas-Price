package aeee.api.gasprice.web.vo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class BlockInfoDTO implements Serializable {

    private BigDecimal number;
    private Long size;
    private BigDecimal average;
    private BigDecimal max;
    private BigDecimal min;

    private List<TransactionCountDTO> transactionCounter;
}
