package aeee.api.gasprice.controller;

import aeee.api.gasprice.dto.BlockInfoDTO;
import aeee.api.gasprice.service.GasPriceService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gasprice")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GasPriceController {

    private final GasPriceService gasPriceService;

    public GasPriceController(GasPriceService gasPriceService){
        this.gasPriceService = gasPriceService;
    }

    @GetMapping
    public BlockInfoDTO getBlockInfo(){
        return gasPriceService.manufactureGasPrice();
    }
}
