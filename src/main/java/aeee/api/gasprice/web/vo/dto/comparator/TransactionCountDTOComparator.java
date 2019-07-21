package aeee.api.gasprice.web.vo.dto.comparator;

import aeee.api.gasprice.web.vo.dto.TransactionCountDTO;

import java.util.Comparator;

public class TransactionCountDTOComparator {
    public static Comparator<TransactionCountDTO> ComparatorAsc = (o1, o2) -> o1.getGasPrice().compareTo(o2.getGasPrice());
    public static Comparator<TransactionCountDTO> ComparatorDesc = (o1, o2) -> o2.getGasPrice().compareTo(o1.getGasPrice());
}
