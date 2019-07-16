package aeee.api.gasprice.web.controller;

import aeee.api.gasprice.web.api.InfuraAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gasprice")
public class GasPriceController extends FilterController {

    @Autowired
    private InfuraAPI infuraAPI;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/")
    public String getLatestTransaction(){
        try{
            return infuraAPI.getLatestTransactionString();
        } catch (Exception e){
            return "";
        }
    }
}
