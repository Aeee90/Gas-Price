package aeee.api.gasprice.vo;

import aeee.api.gasprice.util.UnitConvertor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class Result {

    private BigDecimal number;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public void setNumber(String number){
        this.number = UnitConvertor.convertHexStrToDecimalBigDecimal(number);
    }

    public void setNumber(BigDecimal number){
        this.number = number;
    }

}
