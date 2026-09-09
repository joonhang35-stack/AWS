package com.bcs.zsg.bank.vo;

import java.util.Date;

public class CashBookSumVO extends CashBookVO {

	private static final long serialVersionUID = 1L;

	private Double debitSum=0.0;

	public CashBookSumVO() {
		super();
	}
	public CashBookSumVO(long id, long idBank,long idSupplier, long idCustomer, Date dtTrans,
							String sysCode, String sysPrefix, String sysNo, String refNo,
							String transTypeCd, String payee, Double debit, Double debitSum, Double credit,
							boolean isClear, boolean isMark, String typeCd, String groupNo, String remarks, Date dtClear, Date dtMark) {

		super();
		setId(id);
		setIdBank(idBank);
		setIdSupplier(idSupplier);
		setIdCustomer(idCustomer);
		setDtTrans(dtTrans);
		setSysCode(sysCode);
		setSysPrefix(sysPrefix);
		setSysNo(sysNo);
		setRefNo(refNo);
		setTransTypeCd(transTypeCd);
		setPayee(payee);
		setDebit(debit);
		setDebitSum(debitSum);
		setCredit(credit);
		setIsClear(isClear);
		setIsMark(isMark);
		setTypeCd(typeCd);
		setGroupNo(groupNo);
		setRemarks(remarks);
		setDtClear(dtClear);
		setDtMark(dtMark);
		this.debitSum = debitSum;
	}
	
	public Double getDebitSum() {
		return debitSum;
	}
	public void setDebitSum(Double debitSum) {
		this.debitSum = debitSum;
	}
}
