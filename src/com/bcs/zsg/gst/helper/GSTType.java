package com.bcs.zsg.gst.helper;

public enum GSTType {
	INPUT("IN"),
	OUTPUT("OUT"),
	
	// OLD
	/*IN_TX("TX"),
	IN_IM("IM"),
	IN_IS("IS"),
	IN_BL("BL"),
	IN_NR("NR"),
	IN_ZP("ZP"),
	IN_EP("EP"),
	IN_OP("OP"),
	IN_TX_E43("TX-E43"),
	IN_TX_N43("TX-N43"),
	IN_TX_RE("TX-RE"),
	IN_GP("GP"),
	IN_AJP("AJP"),

	OUT_SR("SR"),
	OUT_ZRL("ZRL"),
	OUT_ZRE("ZRE"),
	OUT_ES43("ES43"),
	OUT_DS("DS"),
	OUT_OS("OS"),
	OUT_ES("ES"),
	OUT_RS("RS"),
	OUT_GS("GS"),
	OUT_AJS("AJS"),*/
	
	IN_TX("TX"),
	IN_IM("IM"),
	IN_IS("IS"),
	IN_BL("BL"),
	IN_NR("NR"),
	IN_ZP("ZP"),
	IN_EP("EP"),
	IN_OP("OP"),
	IN_RP("RP"),
	IN_TX_IES("TX-IES"),
	IN_TX_ES("TX-ES"),
	IN_TX_RE("TX-RE"),
	IN_GP("GP"),
	IN_AJP("AJP"),
	IN_TX_FRS("TX-FRS"),
	IN_TX_NC("TX-NC"),
	IN_TX_ER("TX-ER"),
	IN_TX_CG("TX-CG"),
	IN_IM_CG("IM-CG"),
	IN_IM_RE("IM-RE"),
	IN_NP("IM-NP"),

	OUT_SR("SR"),
	OUT_ZRL("ZRL"),
	OUT_ZRE("ZRE"),
	OUT_IES("IES"),
	OUT_DS("DS"),
	OUT_OS("OS"),
	OUT_ES("ES"),
	OUT_RS("RS"),
	OUT_GS("GS"),
	OUT_AJS("AJS"),
	OUT_ZDA("ZDA"),
	OUT_SR_MS("SR-MS"),
	OUT_SR_JS("SR-JS"),
	OUT_SR_JWS("SR-JWS"),
	OUT_OS_ER("OS-ER"),
	OUT_OS_OV("OS-OV"),
	OUT_NS("NS"),
	OUT_OS_TXM("OS-TXM"),
	OUT_NTX("NTX"),
	
	//Inactive Tax Code
	IN_TX_E43("TX-E43"),
	IN_TX_N43("TX-N43"),
	OUT_ES43("ES43");
	
	private String value;
	
    private GSTType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

