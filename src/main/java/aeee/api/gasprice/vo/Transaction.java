package aeee.api.gasprice.vo;

import aeee.api.gasprice.util.UnitConvertor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private BigDecimal gasPrice;

    public void setGasPrice(String gasPrice){
        this.gasPrice = UnitConvertor.convertHexStrToDecimalBigDecimal(gasPrice);
    }

    public void setGasPrice(BigDecimal gasPrice){
        this.gasPrice = gasPrice;
    }
}
