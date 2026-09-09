package com.bcs.zsg.web.object;

import java.io.Serializable;

public class EInvoiceValidationStepResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String status;
	private EInvoiceStandardError error;
	
	public EInvoiceStandardError getError() {
		return error;
	}
	public void setError(EInvoiceStandardError error) {
		this.error = error;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
