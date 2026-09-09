package com.bcs.zsg.common.web.bean;

public class RedirectionBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;
	
	private final String URL_AUDIT_TRAIL = "/app/history";
	
	/**
	 * Redirect to audit trail
	 */
	public void gotoAuditTrial() {
		try {
			sendRedirect(URL_AUDIT_TRAIL);
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

	/**
	 * Redirect to Customer Merge
	 */
	public void gotoCustomerMerge() {
		try {
			sendRedirect("/app/entity/customer/merge");
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	public void gotoCustomerList() {
		try {
			sendRedirect("/app/sales/custtest");
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/*******************
	 * GETTER & SETTER *
	 *******************/
	
	@Override
	public void resetForm() {
	}

}
