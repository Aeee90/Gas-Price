package aeee.api.gasprice.infura;

import aeee.api.gasprice.SpeedTime;
import aeee.api.gasprice.util.UnitConvertor;
import aeee.api.gasprice.service.GasPriceService;
import aeee.api.gasprice.dto.BlockInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InfuraService {

    @Autowired
    private GasPriceService gasPriceService;

    @Test
    public void convert16to10(){
        log.info(UnitConvertor.convertHexStrToDecimalBigDecimal("0x104c533c00").toString());
    }

    @Test
    public void getBlockInfo(){
        BlockInfoDTO blockInfoDTO = gasPriceService.manufactureGasPrice();
        assert(blockInfoDTO != null);
        log.info(blockInfoDTO.toString());
    }

    @Test
    public void measureSpeed(){
//        long run = 100;
//        long sum = 0;
//        for(int i =0; i<run; i++){
//            sum  += SpeedTime.measure("Api", o->{
//                gasPriceService.manufactureGasPrice();
//                return null;
//            });
//        }
//        log.info("Avg: {}", sum/run);
    }
}
