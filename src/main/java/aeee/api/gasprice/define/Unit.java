package aeee.api.gasprice.define;

import lombok.Getter;

import java.math.BigDecimal;

public enum Unit {

    WEI(new BigDecimal(1))
    , KILO(new BigDecimal(1_000))
    , MEGA(new BigDecimal(1_000_000))
    , GIGA(new BigDecimal(1_000_000_000))
    , TERA(new BigDecimal(1_000_000_000_000L))
    , PETA(new BigDecimal(1_000_000_000_000_000L))
    , EXA(new BigDecimal(1_000_000_000_000_000_000L));

    @Getter
    private BigDecimal unit;

    private Unit(BigDecimal unit){
        this.unit = unit;
    }
}
