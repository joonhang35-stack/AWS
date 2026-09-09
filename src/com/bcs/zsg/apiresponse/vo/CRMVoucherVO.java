package com.bcs.zsg.apiresponse.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bcs.zsg.common.helper.CRMUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.crm.vo.CustomerPointTierVO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.sales.vo.InvoiceVoucherVO;

/**
 * 
 */
public class CRMVoucherVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String customerId;
    private String referenceId;
    private String referenceType;
    private String redemptionCode;
    private String status;

    private Boolean isReissued;

    private String refOriginalId;
    private String refInvoiceNumber;

    private String expiryDate;

    private String remarks;

    private Integer pointsRequired;

    private String redeemedBy;
    private String redeemedDateTime;

    private String redeemChannel;

    private String memberPointTransactionId;

    private String customFields;

    private List<RedemptionDescriptionVO> descriptions;

    private List<String> productCategories;

    private List<String> channels;
    private List<String> invoiceCategories;
    private List<String> itemCode;
    
    // Catalog fields
    private String name;
    private String redemptionId;
    private String description;
    private BigDecimal minSales;
    private BigDecimal maxSales;
    private BigDecimal minPoints;
    private BigDecimal maxPoints;
    private BigDecimal conversionPoints;
    private BigDecimal conversionRebateAmt;
    private BigDecimal discountAmt;
    private BigDecimal percentage;
    private BigDecimal maxDiscountCapped;
    private Integer expiryDays;
    private Boolean isExpiryEndOfMonth;
    private Boolean reclaimable;
    private Boolean stackable;
    private Boolean isAllowMoreThanOne;
    private Integer quantity;
    private Date effectiveFrom;
    private Date effectiveUntil;
    private Boolean isActive;
    private String currency;
    private String categoryName;
    private String categoryType;
    private String categoryDesc;
    
    // Claimed Voucher
    private CRMVoucherVO redemptionType;
    
    // self-created fields
    private boolean inCart;
    
    public boolean isVoucherClaimable(CustomerPointTierVO pointTierVO) throws BusinessException {
    	if (pointTierVO == null || minPoints == null)	return true;
    	
    	if (minPoints.intValue() > pointTierVO.getTotalPointsBalance()) {
			return false;
		} else {
			return true;
		}
	}
    
    /**
     * from claimed voucher
     * @param invoiceVO
     * @return
     */
    public InvoiceVoucherVO convertClaimedVoucherToInvoiceVoucherVO(InvoiceVO invoiceVO) {
    	
    	InvoiceVoucherVO invVoucherVO = new InvoiceVoucherVO();
    	
		invVoucherVO.setIdInv(invoiceVO.getId());
		invVoucherVO.setIdCust(invoiceVO.getCustDetailsVO().getCustId());
		invVoucherVO.setVoucherId(id);
		invVoucherVO.setVoucherCode(redemptionCode);
		invVoucherVO.setVoucherDesc(redemptionType.getDescription());
		invVoucherVO.setRefVoucherId(referenceId);
		invVoucherVO.setCategory(referenceType);
		invVoucherVO.setMasterVoucherCode(redemptionType.getRedemptionCode());
		invVoucherVO.setDtRedeem(new Date());
		invVoucherVO.setPointsUsed(pointsRequired);
		
		invVoucherVO.setQty(1);
		invVoucherVO.setUnitPrice(0.0); 
		if (discountAmt != null)	invVoucherVO.setUnitPrice(discountAmt.doubleValue() * -1);
		invVoucherVO.setAmount(BigDecimal.valueOf(invVoucherVO.getQty()).multiply(BigDecimal.valueOf(invVoucherVO.getUnitPrice())).doubleValue());
		invVoucherVO.setStatusCode(CRMUtils.VOUCHER_PENDING);
		invVoucherVO.setSeq(invoiceVO.getVoucherList().size() + 1);
		
		return invVoucherVO;
	}
    
    
    /**
     * from voucher catalog
     * @return
     */
    public InvoiceVoucherVO convertVoucherCatalogToInvoiceVoucherVO() {
    	
    	InvoiceVoucherVO invVoucherVO = new InvoiceVoucherVO();
		
//		invVoucherVO.setVoucherId(id);
		invVoucherVO.setVoucherCode(redemptionCode);
		invVoucherVO.setVoucherDesc(description);
		invVoucherVO.setRefVoucherId(redemptionId);
		invVoucherVO.setCategory(categoryName);
		invVoucherVO.setDtRedeem(new Date());
		invVoucherVO.setPointsUsed(minPoints != null ? minPoints.intValue() : maxPoints.intValue());
				
		invVoucherVO.setQty(1);
		invVoucherVO.setUnitPrice(discountAmt != null ? discountAmt.doubleValue() * -1 : 0.0);
		invVoucherVO.setAmount(BigDecimal.valueOf(invVoucherVO.getQty()).multiply(BigDecimal.valueOf(invVoucherVO.getUnitPrice())).doubleValue());
		invVoucherVO.setStatusCode(CRMUtils.VOUCHER_PENDING_CLAIM);
		
		return invVoucherVO;
    }

	public boolean isInCart() {
		return inCart;
	}

	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}

	public CRMVoucherVO getRedemptionType() {
		return redemptionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRedemptionType(CRMVoucherVO redemptionType) {
		this.redemptionType = redemptionType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMinSales() {
		return minSales;
	}

	public void setMinSales(BigDecimal minSales) {
		this.minSales = minSales;
	}

	public BigDecimal getMaxSales() {
		return maxSales;
	}

	public void setMaxSales(BigDecimal maxSales) {
		this.maxSales = maxSales;
	}

	public BigDecimal getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(BigDecimal minPoints) {
		this.minPoints = minPoints;
	}

	public BigDecimal getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(BigDecimal maxPoints) {
		this.maxPoints = maxPoints;
	}

	public BigDecimal getConversionPoints() {
		return conversionPoints;
	}

	public void setConversionPoints(BigDecimal conversionPoints) {
		this.conversionPoints = conversionPoints;
	}

	public BigDecimal getConversionRebateAmt() {
		return conversionRebateAmt;
	}

	public void setConversionRebateAmt(BigDecimal conversionRebateAmt) {
		this.conversionRebateAmt = conversionRebateAmt;
	}

	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getMaxDiscountCapped() {
		return maxDiscountCapped;
	}

	public void setMaxDiscountCapped(BigDecimal maxDiscountCapped) {
		this.maxDiscountCapped = maxDiscountCapped;
	}

	public Integer getExpiryDays() {
		return expiryDays;
	}

	public void setExpiryDays(Integer expiryDays) {
		this.expiryDays = expiryDays;
	}

	public Boolean getIsExpiryEndOfMonth() {
		return isExpiryEndOfMonth;
	}

	public void setIsExpiryEndOfMonth(Boolean isExpiryEndOfMonth) {
		this.isExpiryEndOfMonth = isExpiryEndOfMonth;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveUntil() {
		return effectiveUntil;
	}

	public void setEffectiveUntil(Date effectiveUntil) {
		this.effectiveUntil = effectiveUntil;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getRedemptionCode() {
        return redemptionCode;
    }

    public void setRedemptionCode(String redemptionCode) {
        this.redemptionCode = redemptionCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsReissued() {
        return isReissued;
    }

    public void setIsReissued(Boolean isReissued) {
        this.isReissued = isReissued;
    }

    public String getRefOriginalId() {
        return refOriginalId;
    }

    public void setRefOriginalId(String refOriginalId) {
        this.refOriginalId = refOriginalId;
    }

    public String getRefInvoiceNumber() {
        return refInvoiceNumber;
    }

    public void setRefInvoiceNumber(String refInvoiceNumber) {
        this.refInvoiceNumber = refInvoiceNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(Integer pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public String getRedeemedBy() {
        return redeemedBy;
    }

    public void setRedeemedBy(String redeemedBy) {
        this.redeemedBy = redeemedBy;
    }

    public String getRedeemedDateTime() {
        return redeemedDateTime;
    }

    public void setRedeemedDateTime(String redeemedDateTime) {
        this.redeemedDateTime = redeemedDateTime;
    }

    public String getRedeemChannel() {
        return redeemChannel;
    }

    public void setRedeemChannel(String redeemChannel) {
        this.redeemChannel = redeemChannel;
    }

    public String getMemberPointTransactionId() {
        return memberPointTransactionId;
    }

    public void setMemberPointTransactionId(String memberPointTransactionId) {
        this.memberPointTransactionId = memberPointTransactionId;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

//    public RedemptionDescriptionVO getDescriptions() {
//        return descriptions;
//    }
//
//    public void setDescriptions(RedemptionDescriptionVO descriptions) {
//        this.descriptions = descriptions;
//    }

    public List<String> getProductCategories() {
        return productCategories;
    }

    public List<RedemptionDescriptionVO> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<RedemptionDescriptionVO> descriptions) {
		this.descriptions = descriptions;
	}

	public void setProductCategories(List<String> productCategories) {
        this.productCategories = productCategories;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
    
    public List<String> getInvoiceCategories() {
		return invoiceCategories;
	}

	public void setInvoiceCategories(List<String> invoiceCategories) {
		this.invoiceCategories = invoiceCategories;
	}

	public List<String> getItemCode() {
		return itemCode;
	}

	public void setItemCode(List<String> itemCode) {
		this.itemCode = itemCode;
	}

	public Boolean getReclaimable() {
		return reclaimable;
	}

	public void setReclaimable(Boolean reclaimable) {
		this.reclaimable = reclaimable;
	}

	public Boolean getStackable() {
		return stackable;
	}

	public void setStackable(Boolean stackable) {
		this.stackable = stackable;
	}

	public Boolean getIsAllowMoreThanOne() {
		return isAllowMoreThanOne;
	}

	public void setIsAllowMoreThanOne(Boolean isAllowMoreThanOne) {
		this.isAllowMoreThanOne = isAllowMoreThanOne;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public class RedemptionDescriptionVO implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String entityType;
        private String referenceId;
        private String contentType;
        private String title;
        private String descriptionText;
        private Integer displayOrder;
        private String languageCode;
        private Boolean isActive;
        private String effectiveFrom;
        private String effectiveTo;

        public String getEntityType() {
            return entityType;
        }

        public void setEntityType(String entityType) {
            this.entityType = entityType;
        }

        public String getReferenceId() {
            return referenceId;
        }

        public void setReferenceId(String referenceId) {
            this.referenceId = referenceId;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescriptionText() {
            return descriptionText;
        }

        public void setDescriptionText(String descriptionText) {
            this.descriptionText = descriptionText;
        }

        public Integer getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getEffectiveFrom() {
            return effectiveFrom;
        }

        public void setEffectiveFrom(String effectiveFrom) {
            this.effectiveFrom = effectiveFrom;
        }

        public String getEffectiveTo() {
            return effectiveTo;
        }

        public void setEffectiveTo(String effectiveTo) {
            this.effectiveTo = effectiveTo;
        }
    }
}
