package aeee.api.gasprice.web.vo.dto;


import aeee.api.gasprice.web.vo.entity.TransactionVO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Comparator;

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


    public static Comparator<TransactionCountDTO> ComparatorAsc = new Comparator<TransactionCountDTO>() {
        @Override
        public int compare(TransactionCountDTO o1, TransactionCountDTO o2) {
            return o1.getGasPrice().compareTo(o2.getGasPrice());
        }
    };

    public static Comparator<TransactionCountDTO> ComparatorDESC = new Comparator<TransactionCountDTO>() {
        @Override
        public int compare(TransactionCountDTO o1, TransactionCountDTO o2) {
            return o2.getGasPrice().compareTo(o1.getGasPrice());
        }
    };


}
