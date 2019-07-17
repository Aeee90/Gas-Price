package aeee.api.gasprice.web.controller;

import aeee.api.gasprice.web.service.GasPriceService;
import aeee.api.gasprice.web.vo.dto.BlockInfoDTO;
import aeee.api.gasprice.web.vo.dto.ResponseDTO;
import aeee.api.gasprice.web.vo.dto.ResponseType;
import aeee.api.gasprice.web.vo.entity.GasPriceVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gasprice")
public class GasPriceController extends FilterController {

    private final GasPriceService gasPriceService;

    public GasPriceController(GasPriceService gasPriceService){
        this.gasPriceService = gasPriceService;
    }

    @GetMapping("")
    public ResponseDTO<BlockInfoDTO> getBlockInfo(){
        return ResponseDTO.get(ResponseType.Success, "", gasPriceService.purifyGasPrice());
    }

    @GetMapping("/latest")
    public ResponseDTO<GasPriceVO> getLatestTransaction(){
        return ResponseDTO.get(ResponseType.Success, "", gasPriceService.getLatestTransactionVO());
    }
}
