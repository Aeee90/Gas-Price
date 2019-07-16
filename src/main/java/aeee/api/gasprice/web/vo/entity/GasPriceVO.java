package aeee.api.gasprice.web.vo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class GasPriceVO implements Serializable {

    public static GasPriceVO EMPTY = new GasPriceVO();

    @Setter @Getter
    private String jsonrpc;
    @Setter @Getter
    private String id;
    @Setter @Getter
    private ResultVO result;


    @Override
    public String toString() {
        return this.getClass().getName() + "(jsonrpc:" + jsonrpc + ", id:" + id + ", result:" + result.toString() + ")";
    }
}
