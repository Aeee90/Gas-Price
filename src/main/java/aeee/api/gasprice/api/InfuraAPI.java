package aeee.api.gasprice.api;

import aeee.api.gasprice.vo.GasPriceEntity;

public interface InfuraAPI {

    GasPriceEntity getEth_getBlockByNumber();

}
