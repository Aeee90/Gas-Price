package aeee.api.gasprice.infura;

import aeee.api.gasprice.web.service.GasPriceService;
import aeee.api.gasprice.web.vo.entity.GasPriceVO;
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
    public void getGasPriceServiceGasPriceVO(){
        GasPriceVO gasPriceVO = gasPriceService.getLatestTransactionVO();
        assert(gasPriceVO != null);
        log.info(gasPriceVO.toString());
    }
}
