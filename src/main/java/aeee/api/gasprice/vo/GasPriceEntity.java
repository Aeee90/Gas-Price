package aeee.api.gasprice.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GasPriceEntity implements Serializable, InfuraValidErrorEntity {

    private String jsonrpc;
    private String id;

    private ResultEntity result;

    ErrorEntity error = null;

    @Override
    public boolean isError() {
        return error != null;
    }
}
