package aeee.api.gasprice.dto.comparator;


import aeee.api.gasprice.dto.TransactionCountDTO;

import java.util.Comparator;

public class TransactionCountDTOComparator {
    public static Comparator<TransactionCountDTO> ComparatorAsc = (o1, o2) -> o1.getGasPrice().compareTo(o2.getGasPrice());
}
