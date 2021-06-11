package aeee.api.gasprice.service;

import aeee.api.gasprice.api.InfuraAPI;
import aeee.api.gasprice.define.Unit;
import aeee.api.gasprice.dto.BlockInfoDTO;
import aeee.api.gasprice.dto.TransactionCountDTO;
import aeee.api.gasprice.dto.comparator.TransactionCountDTOComparator;
import aeee.api.gasprice.util.UnitConvertor;
import aeee.api.gasprice.vo.GasPrice;
import aeee.api.gasprice.vo.Result;
import aeee.api.gasprice.vo.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class GasPriceServiceDefault implements GasPriceService{

    private final InfuraAPI infuraAPI;

    public BlockInfoDTO manufactureGasPrice(){
        GasPrice gasPriceVO = infuraAPI.getEth_getBlockByNumber();
        Result result = gasPriceVO.getResult();
        List<Transaction> transactionEntities = gasPriceVO.getResult().getTransactions();

        BlockInfoDTO blockInfoDTO = new BlockInfoDTO();
        blockInfoDTO.setAverage(BigDecimal.ZERO);

        BigDecimal sum;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal min = BigDecimal.ZERO;

        int size = transactionEntities.size();
        if(!transactionEntities.isEmpty()){
            Map<BigDecimal, Long> counter = new HashMap<>();

            BigDecimal first = transactionEntities.get(0).getGasPrice();
            sum = min = max = first;
            counter.put(UnitConvertor.convertUnitWithRoundHalf(first, Unit.WEI, Unit.GIGA, 1), 1L);

            for(int i=1; i < size; i++){
                Transaction tr = transactionEntities.get(i);
                BigDecimal gp = tr.getGasPrice();
                sum = sum.add(gp);
                if(gp.compareTo(max) > 0) max = gp;
                if(gp.compareTo(min) < 0) min = gp;

                BigDecimal cgp = UnitConvertor.convertUnitWithRoundHalf(gp, Unit.WEI, Unit.GIGA, 1);
                Long count = counter.get(cgp);
                counter.put(cgp, count == null? 1L : count + 1);
            }

            blockInfoDTO.setTransactionCounter(counter.entrySet().stream()
                    .map( entry->  new TransactionCountDTO(entry.getKey(), entry.getValue()) )
                    .sorted(TransactionCountDTOComparator.ComparatorAsc)
                    .collect(Collectors.toList())
            );

            BigDecimal ave = UnitConvertor.convertUnitWithRoundHalf(sum.divide(new BigDecimal(size), 0, RoundingMode.HALF_UP), Unit.WEI, Unit.GIGA, 1);
            blockInfoDTO.setAverage(ave);
        }

        blockInfoDTO.setNumber(result.getNumber());
        blockInfoDTO.setSize(Long.valueOf(size));
        blockInfoDTO.setMin(UnitConvertor.convertUnitWithRoundHalf(min, Unit.WEI, Unit.GIGA, 1));
        blockInfoDTO.setMax(UnitConvertor.convertUnitWithRoundHalf(max, Unit.WEI, Unit.GIGA, 1));

        return blockInfoDTO;
    }
}
