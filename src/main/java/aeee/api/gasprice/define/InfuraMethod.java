package aeee.api.gasprice.define;


public enum  InfuraMethod {

    EthGetBlockByNumber("eth_getBlockByNumber");

    public final String value;

    private InfuraMethod(String value){
        this.value = value;
    }

}
