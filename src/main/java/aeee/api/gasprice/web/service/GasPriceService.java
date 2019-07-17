package aeee.api.gasprice.web.service;

import aeee.api.gasprice.define.Unit;
import aeee.api.gasprice.util.UnitConvertor;
import aeee.api.gasprice.web.api.InfuraAPI;
import aeee.api.gasprice.web.vo.dto.BlockInfoDTO;
import aeee.api.gasprice.web.vo.dto.TransactionCountDTO;
import aeee.api.gasprice.web.vo.entity.GasPriceVO;
import aeee.api.gasprice.web.vo.entity.GasPriceVODeserializer;
import aeee.api.gasprice.web.vo.entity.ResultVO;
import aeee.api.gasprice.web.vo.entity.TransactionVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GasPriceService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private InfuraAPI infuraAPI;

    public GasPriceService() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(GasPriceVO.class, new GasPriceVODeserializer());
        mapper.registerModule(simpleModule);
    }

    public JsonNode getLatestTransactionJson(){
        String str = infuraAPI.getLatestTransaction(String.class);
        try {
            return mapper.readTree(str);
        }catch (IOException e){
            log.error("Can't Convert String To JsonNoe: {}", str);
            return null;
        }
    }

    public GasPriceVO getLatestTransactionVO(){
        String str = infuraAPI.getLatestTransaction(String.class);
        try {
            return mapper.readValue(str, GasPriceVO.class);
        }catch (IOException e){
            log.error("Can't Convert String To JsonNoe: {}", str);
            return null;
        }
    }

    public BlockInfoDTO purifyGasPrice(){
        GasPriceVO gasPriceVO = getLatestTransactionVO();
        ResultVO resultVO = gasPriceVO.getResult();
        List<TransactionVO> transactionVOS = gasPriceVO.getResult().getTransactions();

        BlockInfoDTO blockInfoDTO = new BlockInfoDTO();

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal min = BigDecimal.ZERO;

        int size = transactionVOS.size();
        if(!transactionVOS.isEmpty()){
            Map<BigDecimal, Long> counter = new HashMap<>();

            BigDecimal first = transactionVOS.get(0).getGasPrice();
            sum = min = max = first;
            counter.put(UnitConvertor.convertUnitWithRoundHalf(first, Unit.WEI, Unit.GIGA, 1), 1L);

            for(int i=1; i < size; i++){
                TransactionVO tr = transactionVOS.get(i);
                BigDecimal gp = tr.getGasPrice();
                sum = sum.add(gp);
                if(gp.compareTo(max) > 1) max = gp;
                if(gp.compareTo(min) < -1) min = gp;

                BigDecimal cgp = UnitConvertor.convertUnitWithRoundHalf(gp, Unit.WEI, Unit.GIGA, 1);
                Long count = counter.get(cgp);
                counter.put(cgp, count == null? 1L : count + 1);
            }

            blockInfoDTO.setTransactionCountor(counter.entrySet().stream()
                .map( entry->  new TransactionCountDTO(entry.getKey(), entry.getValue()) )
                .sorted((TransactionCountDTO a, TransactionCountDTO b)-> a.getGasPrice().compareTo(b.getGasPrice()) )
                .collect(Collectors.toList())
            );
        }

        blockInfoDTO.setNumber(resultVO.getNumber());
        blockInfoDTO.setSize(Long.valueOf(size));
        BigDecimal ave = UnitConvertor.convertUnitWithRoundHalf(sum.divide(new BigDecimal(size), 0, RoundingMode.HALF_UP), Unit.WEI, Unit.GIGA, 1);
        blockInfoDTO.setAverage(ave);
        blockInfoDTO.setMin(UnitConvertor.convertUnitWithRoundHalf(min, Unit.WEI, Unit.GIGA, 1));
        blockInfoDTO.setMax(UnitConvertor.convertUnitWithRoundHalf(max, Unit.WEI, Unit.GIGA, 1));

        return blockInfoDTO;
    }
}
