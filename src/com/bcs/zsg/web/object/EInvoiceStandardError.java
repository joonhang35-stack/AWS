package com.bcs.zsg.web.object;

import java.io.Serializable;
import java.util.List;

public class EInvoiceStandardError implements Serializable {
	
	// https://sdk.myinvois.hasil.gov.my/standard-error-response/

	private static final long serialVersionUID = 1L;
	
	public String propertyName;
	public String propertyPath;
	public String errorCode;
	public String error; // Human readable error message, in *English*
	public String errorMS; // Human readable error message, in *Bahasa Malaysia*
	public String target;
	public List<EInvoiceStandardError> innerError;
	
	public List<EInvoiceStandardError> getInnerError() {
		return innerError;
	}
	public void setInnerError(List<EInvoiceStandardError> innerError) {
		this.innerError = innerError;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyPath() {
		return propertyPath;
	}
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorMS() {
		return errorMS;
	}
	public void setErrorMS(String errorMS) {
		this.errorMS = errorMS;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	} 
}
