package aeee.api.gasprice.web.vo.dto.comparator;

import aeee.api.gasprice.web.vo.dto.TransactionCountDTO;

import java.util.Comparator;

public class TransactionCountDTOComparator {

    public static Comparator<TransactionCountDTO> ComparatorAsc = new Comparator<TransactionCountDTO>() {
        @Override
        public int compare(TransactionCountDTO o1, TransactionCountDTO o2) {
            return o1.getGasPrice().compareTo(o2.getGasPrice());
        }
    };

    public static Comparator<TransactionCountDTO> ComparatorDesc = new Comparator<TransactionCountDTO>() {
        @Override
        public int compare(TransactionCountDTO o1, TransactionCountDTO o2) {
            return o2.getGasPrice().compareTo(o1.getGasPrice());
        }
    };

}
