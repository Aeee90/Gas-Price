package aeee.api.gasprice.web.controller;

import aeee.api.gasprice.web.service.GasPriceService;
import aeee.api.gasprice.web.vo.dto.ResponseDTO;
import aeee.api.gasprice.web.vo.dto.ResponseType;
import aeee.api.gasprice.web.vo.dto.TestDto;
import aeee.api.gasprice.web.vo.entity.GasPriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gasprice")
public class GasPriceController extends FilterController {

    @Autowired
    private GasPriceService gasPriceService;

    @GetMapping("/hello")
    public ResponseDTO<TestDto> hello(){
        return ResponseDTO.get(ResponseType.Success, "", new TestDto());
    }

    @GetMapping("")
    public ResponseDTO<GasPriceVO> getLatestTransaction(){
        return ResponseDTO.get(ResponseType.Success, "", gasPriceService.getLatestTransactionVO());
    }

    @GetMapping("gasprice")
    public ResponseDTO<GasPriceVO> gasprice(){
        return ResponseDTO.get(ResponseType.Success, "", new GasPriceVO());
    }
}
