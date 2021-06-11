package aeee.api.gasprice.vo;

import lombok.Data;

@Data
public class GasPrice {

    private String jsonrpc;
    private String id;

    private Result result;
}
