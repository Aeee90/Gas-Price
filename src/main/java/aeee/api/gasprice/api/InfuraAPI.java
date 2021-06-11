package aeee.api.gasprice.api;

import aeee.api.gasprice.vo.GasPrice;

public interface InfuraAPI {

    GasPrice getEth_getBlockByNumber();

}
