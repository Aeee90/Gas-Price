package aeee.api.gasprice.define;


public enum  InfuraMethod {

    ETH_GET_BLOCK_BY_NUMBER("eth_getBlockByNumber");

    public final String value;

    private InfuraMethod(String value){
        this.value = value;
    }

}
