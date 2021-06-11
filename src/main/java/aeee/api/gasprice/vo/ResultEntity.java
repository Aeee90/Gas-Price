package aeee.api.gasprice.vo;

import aeee.api.gasprice.util.UnitConvertor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class ResultEntity implements Serializable {

    private BigDecimal number;
    private ArrayList<TransactionEntity> transactions = new ArrayList<>();

    public void setNumber(String number){
        this.number = UnitConvertor.convertHexStrToDecimalBigDecimal(number);
    }

    public void setNumber(BigDecimal number){
        this.number = number;
    }

}
