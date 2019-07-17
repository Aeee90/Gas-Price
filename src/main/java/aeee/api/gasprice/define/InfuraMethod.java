package aeee.api.gasprice.define;


public enum  InfuraMethod {

    eth_getBlockByNumber("eth_getBlockByNumber");

    public final String value;

    private InfuraMethod(String value){
        this.value = value;
    }

}
