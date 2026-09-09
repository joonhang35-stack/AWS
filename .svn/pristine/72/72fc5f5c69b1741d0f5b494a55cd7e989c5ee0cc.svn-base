package com.bcs.zsg.apiresponse.vo;

import java.io.Serializable;
import java.util.List;

public class CRMRedeemVoucherRequestVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private List<RedemptionCodeVO> redemptionCodes;
	private String refSalesId;
	private String remarks;
	private String updatedBy;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<RedemptionCodeVO> getRedemptionCodes() {
		return redemptionCodes;
	}

	public void setRedemptionCodes(List<RedemptionCodeVO> redemptionCodes) {
		this.redemptionCodes = redemptionCodes;
	}

	public String getRefSalesId() {
		return refSalesId;
	}

	public void setRefSalesId(String refSalesId) {
		this.refSalesId = refSalesId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static class RedemptionCodeVO {

		private String code;
		private Integer quantity;
		
		// only for Rebate
		private String voucherCategory;
		private String detailCode;
		
		public RedemptionCodeVO(String code, Integer quantity) {
			super();
			this.code = code;
			this.quantity = quantity;
		}

		/**
		 * for Rebate voucher used only
		 * @param code
		 * @param quantity
		 * @param voucherCategory
		 * @param detailCode
		 */
		public RedemptionCodeVO(String code, Integer quantity, String voucherCategory, String detailCode) {
			super();
			this.code = code;
			this.quantity = quantity;
			this.voucherCategory = voucherCategory;
			this.detailCode = detailCode;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public String getVoucherCategory() {
			return voucherCategory;
		}

		public void setVoucherCategory(String voucherCategory) {
			this.voucherCategory = voucherCategory;
		}

		public String getDetailCode() {
			return detailCode;
		}

		public void setDetailCode(String detailCode) {
			this.detailCode = detailCode;
		}
	}
}
