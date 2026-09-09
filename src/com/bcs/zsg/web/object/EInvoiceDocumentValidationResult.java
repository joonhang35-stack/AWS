package com.bcs.zsg.web.object;

import java.io.Serializable;
import java.util.List;

public class EInvoiceDocumentValidationResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private List<EInvoiceValidationStepResult> validationSteps;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<EInvoiceValidationStepResult> getValidationSteps() {
		return validationSteps;
	}
	public void setValidationSteps(List<EInvoiceValidationStepResult> validationSteps) {
		this.validationSteps = validationSteps;
	}
	
	
}
