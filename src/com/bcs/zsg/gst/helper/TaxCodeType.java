package com.bcs.zsg.gst.helper;

public enum TaxCodeType {
	GST("GST"),
	TAX("TAX");

	private String value;

    private TaxCodeType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

