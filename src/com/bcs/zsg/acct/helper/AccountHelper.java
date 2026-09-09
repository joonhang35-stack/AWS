package com.bcs.zsg.acct.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.vo.AmountCalcViewVO;
import com.bcs.zsg.gst.service.GSTService;

@Component
public class AccountHelper {
	
	public static final String initValue = "0.00";

	private static AccountService acctService;
	
	@Autowired
	private AccountService acctServiceAW;
	
	@Autowired
	private GSTService gstService;
	
	public static List<String> nonClaimableTaxCode = new ArrayList<String>();
	
    @PostConstruct
    public void init() throws BusinessException {
    	AccountHelper.acctService = acctServiceAW;
    	nonClaimableTaxCode = gstService.getNonClaimableTaxList();
    }
    
	private static AmountCalcViewVO initAmountViewVO(AmountCalcViewVO amountCalcViewVO) {
		if (null == amountCalcViewVO) amountCalcViewVO = new AmountCalcViewVO();
		
		amountCalcViewVO.setTotalAmountIncludeTax(new BigDecimal(initValue));
		amountCalcViewVO.setTotalAmountPaid(new BigDecimal(initValue));
		amountCalcViewVO.setTotalTax(new BigDecimal(initValue));
		amountCalcViewVO.setTotalNonClaimableTax(new BigDecimal(initValue));
		
		amountCalcViewVO.setTotalAmountAfterRounding(new BigDecimal(initValue));
		amountCalcViewVO.setTotalRounding(new BigDecimal(initValue));

		return amountCalcViewVO;
	}
	
	private static AmountCalcViewVO amountRounding(AmountCalcViewVO amountViewVO) {

		amountViewVO.setTotalAmountAfterRounding(BigDecimal.valueOf(roundToNearest(amountViewVO.getTotalAmountIncludeTax().doubleValue())));
		amountViewVO.setTotalRounding(amountViewVO.getTotalAmountAfterRounding()
										.subtract(amountViewVO.getTotalAmountIncludeTax()));
		
		return amountViewVO;
	}
	
	// For calculation on SST
	public static AmountCalcViewVO computeAmountTotalBasedItem(AmountCalcViewVO amountCalcViewVO, boolean updateItemAmount, List<? extends TaxableAmount> itemList,  
				List<? extends TaxableAmount> itemPaymentList, boolean includeRounding, Double SSTRate) 
			throws BusinessException {

		amountCalcViewVO = initAmountViewVO(amountCalcViewVO);
		calcTotalAmountIncTaxBasedItem(amountCalcViewVO, updateItemAmount, itemList);
		
		if(itemPaymentList != null)
			calcTotalPaidBasedItem(amountCalcViewVO, itemPaymentList);
		
		// before SST calculation, total amount is subtotal
		amountCalcViewVO.setSubtotal(amountCalcViewVO.getTotalAmountIncludeTax());
		
		// SST calculation
		BigDecimal taxRate = new BigDecimal(String.valueOf(SSTRate));
		taxRate = taxRate.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);	// eg: 6.00% to 0.06
		BigDecimal taxAmount = new BigDecimal(initValue);
		taxAmount = amountCalcViewVO.getTotalAmountIncludeTax().multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		amountCalcViewVO.setTotalAmountIncludeTax(amountCalcViewVO.getTotalAmountIncludeTax().add(taxAmount));
		
		amountCalcViewVO.setSstAmt(taxAmount);

		if(includeRounding)
			amountCalcViewVO = amountRounding(amountCalcViewVO);
		else 
			amountCalcViewVO.setTotalAmountAfterRounding(amountCalcViewVO.getTotalAmountIncludeTax());
		
		amountCalcViewVO.setTotalBalance(amountCalcViewVO.getTotalAmountAfterRounding()
										.subtract(amountCalcViewVO.getTotalAmountPaid()));
		
		return amountCalcViewVO;
	}

	//For calculation on single item list
	public static AmountCalcViewVO computeAmountTotalBasedItem(AmountCalcViewVO amountCalcViewVO, List<? extends TaxableAmount> itemList) 
			throws BusinessException {
		
		return computeAmountTotalBasedItem(amountCalcViewVO, itemList, null);
	}

	public static AmountCalcViewVO computeAmountTotalBasedItem(AmountCalcViewVO amountCalcViewVO,List<? extends TaxableAmount> itemList, 
				List<? extends TaxableAmount> itemPaymentList) 
			throws BusinessException {
		return computeAmountTotalBasedItem(amountCalcViewVO, true, itemList, itemPaymentList, true);
	}
	public static AmountCalcViewVO computeAmountTotalBasedItem(AmountCalcViewVO amountCalcViewVO, boolean updateItemAmount, List<? extends TaxableAmount> itemList, 
			List<? extends TaxableAmount> itemPaymentList) 
			throws BusinessException {
		return computeAmountTotalBasedItem(amountCalcViewVO, updateItemAmount, itemList, itemPaymentList, true);
	}
	
	//For calculation on item list with payment such as invoice
	public static AmountCalcViewVO computeAmountTotalBasedItem(AmountCalcViewVO amountCalcViewVO, boolean updateItemAmount, List<? extends TaxableAmount> itemList,  
				List<? extends TaxableAmount> itemPaymentList, boolean includeRounding) 
			throws BusinessException {

		amountCalcViewVO = initAmountViewVO(amountCalcViewVO);
		calcTotalAmountIncTaxBasedItem(amountCalcViewVO, updateItemAmount, itemList);
		
		if(itemPaymentList != null)
			calcTotalPaidBasedItem(amountCalcViewVO, itemPaymentList);

		if(includeRounding)
			amountCalcViewVO = amountRounding(amountCalcViewVO);
		else 
			amountCalcViewVO.setTotalAmountAfterRounding(amountCalcViewVO.getTotalAmountIncludeTax());
		
		amountCalcViewVO.setTotalBalance(amountCalcViewVO.getTotalAmountAfterRounding()
										.subtract(amountCalcViewVO.getTotalAmountPaid()));
		
		return amountCalcViewVO;
	}

	protected static AmountCalcViewVO calcTotalAmountIncTaxBasedItem(AmountCalcViewVO amountViewVO, boolean updateItemAmount, List<? extends TaxableAmount> itemList) 
			throws BusinessException {
		
		if (CollectionUtils.isNotEmpty(itemList)) {

			for (TaxableAmount vo : itemList) {
				if (StringUtils.isNotEmpty(vo.getTaxCode())) {
					
					if(updateItemAmount)
						calcTaxableAmount(vo, false, 0, 0.00);
					
				} else {
					vo.setDTaxAmount(0.00);
					vo.setDAmountIncludeTax(vo.getDAmount());
				}
				
				amountViewVO.setTotalAmountIncludeTax(amountViewVO.getTotalAmountIncludeTax()
														.add(new BigDecimal(String.valueOf(vo.getDAmountIncludeTax()))) );
				
				if (StringUtils.isNotEmpty(vo.getTaxCode()) && nonClaimableTaxCode.contains(vo.getTaxCode())) {
					amountViewVO.setTotalNonClaimableTax(amountViewVO.getTotalNonClaimableTax()
							.add(new BigDecimal(String.valueOf(vo.getDTaxAmount()))) );
				} else {
					amountViewVO.setTotalTax(amountViewVO.getTotalTax()
							.add(new BigDecimal(String.valueOf(vo.getDTaxAmount()))) );
				}
				
			}
		}
		return amountViewVO;
	}
	
	public static TaxableAmount calcTaxableAmount(TaxableAmount vo, boolean haveQuantity, int quantity, double unitPrice) {
		BigDecimal amountIncludeTax = new BigDecimal(initValue);
		BigDecimal amount = new BigDecimal(initValue);
		BigDecimal taxAmount = new BigDecimal(initValue);
		BigDecimal taxRate = new BigDecimal(initValue);
		
		if (vo.getDTaxRate() != 0.00) 
			taxRate = new BigDecimal(String.valueOf(vo.getDTaxRate()));
		
		taxRate = taxRate.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);	// eg: 6.00% to 0.06
		
		if(haveQuantity) {

			BigDecimal dQuantity = new BigDecimal(String.valueOf(quantity));
			BigDecimal dUnitPrice = new BigDecimal(String.valueOf(unitPrice));
			
			amount = dQuantity.multiply(dUnitPrice);
		} else {
			amount = new BigDecimal(String.valueOf(vo.getDAmount()));
		}

		taxAmount = amount.multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
		amountIncludeTax = amount.add(taxAmount);
		
		vo.setDTaxAmount(taxAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setDAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		vo.setDAmountIncludeTax(amountIncludeTax.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		return vo;
	}

	protected static AmountCalcViewVO calcTotalPaidBasedItem(AmountCalcViewVO amountViewVO, List<? extends TaxableAmount> itemPaymentList) 
			throws BusinessException {
		
		if (CollectionUtils.isNotEmpty(itemPaymentList)) {
			for (TaxableAmount vo : itemPaymentList) {
				amountViewVO.setTotalAmountPaid(amountViewVO.getTotalAmountPaid()
												.add(new BigDecimal(String.valueOf(vo.getDAmount()))) );
			}
		}
		return amountViewVO;
	}
	
	public static double roundToNearest(double price) {
		BigDecimal value = BigDecimal.valueOf(price);
		value = value.multiply(new BigDecimal("2")).setScale(1, BigDecimal.ROUND_HALF_UP);
		value = value.divide(new BigDecimal("2")).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return value.doubleValue();
	}
	
	public static TaxableAmount calcAmountBaseOnTotalTaxableAmount(TaxableAmount vo) throws BusinessException {
		
		BigDecimal amountIncludeTax = new BigDecimal(initValue);
		BigDecimal amount = new BigDecimal(initValue);
		BigDecimal taxAmount = new BigDecimal(initValue);
		BigDecimal taxRate = new BigDecimal(initValue);
	
		if(vo.getDAmountIncludeTax() != 0.00) {
			amountIncludeTax = new BigDecimal(String.valueOf(vo.getDAmountIncludeTax()));
		} else {
			amountIncludeTax = new BigDecimal(String.valueOf(vo.getDAmount()));
		}
		
		if(vo.getDTaxRate() != 0.00) {
	
			taxRate = new BigDecimal(String.valueOf(vo.getDTaxRate()));
			taxRate = taxRate.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//FORMULA: 106 [AMOUNT] * 6 [TAXRATE] / 106
			amount = amountIncludeTax.setScale(2, BigDecimal.ROUND_HALF_UP).multiply(taxRate).setScale(2, BigDecimal.ROUND_HALF_UP);
			taxRate = taxRate.add(new BigDecimal("100"));
			taxAmount = amount.divide(taxRate, 2, RoundingMode.HALF_UP);
			amount = amountIncludeTax.subtract(taxAmount);
		} else {
			amount = amountIncludeTax;
		}
		
		vo.setDAmount(amount.doubleValue());
		//vo.setDTaxAmount(taxAmount.doubleValue() < 0 ? -taxAmount.doubleValue() : taxAmount.doubleValue());
		vo.setDTaxAmount(taxAmount.doubleValue());
		vo.setDAmountIncludeTax(amountIncludeTax.doubleValue());
		
		return vo;
	}

	public static void roundingAndTaxUpdate(AmountCalcViewVO amountCalcViewVO, AcctTransVO acctRef) throws BusinessException {
		roundingAndTaxUpdate (amountCalcViewVO, acctRef, false);
	}
	
	public static void roundingAndTaxUpdate(AmountCalcViewVO amountCalcViewVO, AcctTransVO acctRef, boolean isDebitCreditTax) throws BusinessException {
		//acctRef MUST be the main DEBTOR or CREDITOR reference.
		// GST Account Trans
		// Suspense GST
		if (amountCalcViewVO.getIdTaxAcct() != null) {
			
			if(isDebitCreditTax) {
				//THIS METHOD will require ACCOUNT REF object to contain both TAX ONLY amount in DEBIT and CREDIT field.
				acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdTaxAcct(), acctRef.getDebit(), acctRef.getCredit(),
						CommonConstant.SYS_NUM_CD_TAX, acctRef);
			} else
				acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdTaxAcct(), amountCalcViewVO.getTotalTax().doubleValue(), 
											CommonConstant.SYS_NUM_CD_TAX, acctRef);
		}
		
		// Non Claimable GST
		if (amountCalcViewVO.getIdTaxNonClaimableAcct() != null) {
			if(isDebitCreditTax) {
				//THIS METHOD will require ACCOUNT REF object to contain both TAX ONLY amount in DEBIT and CREDIT field.
				acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdTaxNonClaimableAcct(), acctRef.getDebit(), acctRef.getCredit(),
						CommonConstant.SYS_NUM_CD_NCT, acctRef);
			} else
				acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdTaxNonClaimableAcct(), amountCalcViewVO.getTotalNonClaimableTax().doubleValue(), 
											CommonConstant.SYS_NUM_CD_NCT, acctRef);
		}
		
		//Rounding Account Trans
		if (amountCalcViewVO.getIdRoundingAcct() != null) {
			acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdRoundingAcct(), 
										amountCalcViewVO.getTotalRounding().doubleValue(), CommonConstant.SYS_NUM_CD_ROUNDING, acctRef);
		}
	}
	
	private static void acctUpdateRoundingAndTaxTrans(Long idAcct, double taxAmount, String type, AcctTransVO acctRef) throws BusinessException {
		//Calling this method, the acctRef object Is the main account where the DEBIT/CREDIT determine the tax amount
		// Example, 
		//   tax = 6.00, acctRef.credit = 106
		// Will insert suspense RECORD with debit = 6.00 to balance the amount
		
		double debitTaxAmount = 0;
		double creditTaxAmount = 0;
		
		if (acctRef.getCredit() != 0.00) {
			if (taxAmount < 0.00) {
				debitTaxAmount = 0.00;
				creditTaxAmount = -taxAmount;
				
			} else {
				debitTaxAmount = taxAmount;
				creditTaxAmount = 0.00;
			}
		} else if (acctRef.getDebit() != 0.00) {
			if (taxAmount < 0.00) {
				debitTaxAmount = -taxAmount;
				creditTaxAmount = 0.00;
				
			} else {
				debitTaxAmount = 0.00;
				creditTaxAmount = taxAmount;
			}
		}
		
		acctUpdateRoundingAndTaxTrans(idAcct, debitTaxAmount, creditTaxAmount, type, acctRef);
		
		/*acctUpdateRoundingAndTaxTrans(idAcct, 
				acctRef.getCredit() != 0.00 ? taxAmount : 0.00, 
				acctRef.getDebit() != 0.00 ? taxAmount : 0.00, type, acctRef);*/
	}
	
	public static void roundingAndTaxUpdateBill(AmountCalcViewVO amountCalcViewVO, AcctTransVO acctRef, boolean isDebitCreditTax) throws BusinessException {
		//acctRef MUST be the main DEBTOR or CREDITOR reference.
		// GST Account Trans
		// Suspense GST
		if (amountCalcViewVO.getIdTaxAcct() != null) {
			
			if(isDebitCreditTax) {
				//THIS METHOD will require ACCOUNT REF object to contain both TAX ONLY amount in DEBIT and CREDIT field.
				acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdTaxAcct(), acctRef.getDebit(), acctRef.getCredit(),
						CommonConstant.SYS_NUM_CD_TAX, acctRef);
			} else
				acctUpdateRoundingAndTaxTransBill(amountCalcViewVO.getIdTaxAcct(), amountCalcViewVO.getTotalTax().doubleValue(), 
											CommonConstant.SYS_NUM_CD_TAX, acctRef);
		}
		
		// Non Claimable GST
		if (amountCalcViewVO.getIdTaxNonClaimableAcct() != null) {
			if(isDebitCreditTax) {
				//THIS METHOD will require ACCOUNT REF object to contain both TAX ONLY amount in DEBIT and CREDIT field.
				acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdTaxNonClaimableAcct(), acctRef.getDebit(), acctRef.getCredit(),
						CommonConstant.SYS_NUM_CD_NCT, acctRef);
			} else
				acctUpdateRoundingAndTaxTransBill(amountCalcViewVO.getIdTaxNonClaimableAcct(), amountCalcViewVO.getTotalNonClaimableTax().doubleValue(), 
												CommonConstant.SYS_NUM_CD_NCT, acctRef);
		}
		
		//Rounding Account Trans
		if (amountCalcViewVO.getIdRoundingAcct() != null) {
			acctUpdateRoundingAndTaxTrans(amountCalcViewVO.getIdRoundingAcct(), 
										amountCalcViewVO.getTotalRounding().doubleValue(), CommonConstant.SYS_NUM_CD_ROUNDING, acctRef);
		}
	}
	
	private static void acctUpdateRoundingAndTaxTransBill(Long idAcct, double taxAmount, String type, AcctTransVO acctRef) throws BusinessException {
		//Calling this method, the acctRef object Is the main account where the DEBIT/CREDIT determine the tax amount
		// Example, 
		//   tax = 6.00, acctRef.credit = 106
		// Will insert suspense RECORD with debit = 6.00 to balance the amount
		
		double debitTaxAmount = 0;
		double creditTaxAmount = 0;
		
		if (acctRef.getCredit() != 0.00) {
			if (taxAmount < 0.00) {
				debitTaxAmount = 0.00;
				creditTaxAmount = -taxAmount;
				
			} else {
				debitTaxAmount = taxAmount;
				creditTaxAmount = 0.00;
			}
		} else if (acctRef.getDebit() != 0.00) {
			if (taxAmount < 0.00) {
				debitTaxAmount = 0.00;
				creditTaxAmount = -taxAmount;
				
			} else {
				debitTaxAmount = 0.00;
				creditTaxAmount = taxAmount;
			}
		}
		
		acctUpdateRoundingAndTaxTrans(idAcct, debitTaxAmount, creditTaxAmount, type, acctRef);
		
		/*acctUpdateRoundingAndTaxTrans(idAcct, 
				acctRef.getCredit() != 0.00 ? taxAmount : 0.00, 
				acctRef.getDebit() != 0.00 ? taxAmount : 0.00, type, acctRef);*/
	}

	private static void acctUpdateRoundingAndTaxTrans(Long idAcct, double debitTaxAmount, double creditTaxAmount, String type, AcctTransVO acctRef) throws BusinessException {
		AcctTransVO acctVO = new AcctTransVO();
		acctVO.setAcctId(idAcct);
		acctVO.setType(type);
		
		acctVO.setDebit(debitTaxAmount);
		acctVO.setCredit(creditTaxAmount);
		
		acctService.roundingAndTaxUpdate(acctVO, acctRef);
	}

	public static AcctTransVO updateAcctTransTaxRelated(AcctTransVO acctUpdate, AcctTransVO acctRef) throws BusinessException {
		acctUpdate.setTaxCode(acctRef.getTaxCode());
		acctUpdate.setTaxAmount(acctRef.getTaxAmount());
		acctUpdate.setTaxRate(acctRef.getTaxRate());
		
		return acctUpdate;
	}
}
