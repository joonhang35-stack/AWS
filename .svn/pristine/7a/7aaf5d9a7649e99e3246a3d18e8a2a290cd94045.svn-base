package com.bcs.zsg.bank.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.service.PaymentService;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.purchase.vo.SupplierVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public class PaymentBOImpl implements PaymentBO {

	@Autowired
	private PaymentService paymentService;

	@Override
	public List<AcctTransVO> getAcctTransList(Long CompId) throws BusinessException {
		return paymentService.getAcctTransList(CompId);
	}

	@Override
	public List<CashBookVO> getCashBookList() throws BusinessException {
		return paymentService.getCashBookList();
	}

	

	@Override	public List<BankAcctVO> getBankAcctList() throws BusinessException {
		return paymentService.getBankAcctList();
	}

	@Override
	public List<AcctVO> getAcctList(Long idCompany,String strAutoComplete) throws BusinessException {
		return paymentService.getAcctList(idCompany,strAutoComplete);
	}

	@Override
	public AcctVO getAcctDescList(String acctCd,String acctSubCd,Long CompId) throws BusinessException {
		return paymentService.getAcctDescList(acctCd,acctSubCd,CompId);
	}

	@Override
	public void addPayment(CashBookVO cashBookVO, Long idCompany, BankAcctVO bankAcctSearchVO, 
			LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, AcctVO acctVO) throws BusinessException, QueueException {
		checkTaxCodeGroup(cashBookVO);
		paymentService.addPayment(cashBookVO, idCompany, bankAcctSearchVO, lookupItemBT, lookupItemCBT, acctVO);
	}

	
	@Override
	public void deletePaymentList(CashBookVO cashBookVO, Long idCompany) throws BusinessException {
		paymentService.deletePaymentList(cashBookVO, idCompany);
	}

	@Override
	public void updatePayment(CashBookVO cashBookVO, List<AcctTransVO> acctTransList) throws BusinessException {
		paymentService.updatePayment(cashBookVO,acctTransList);
	}

	@Override
	public BankAcctVO getBankAcctSearchList(Long idBank) throws BusinessException {
		return paymentService.getBankAcctSearchList(idBank);
	}


	@Override
	public void updTransList(List<AcctTransViewVO> addTransList,
			List<AcctTransViewVO> updTransist, List<AcctTransViewVO> delTransList,CashBookVO cashBookVO,Long idCompany,BankAcctVO bankAcctSearchVO, AcctTransViewVO acctTransCashBookVO, LookupItemVO lookupItemCBT,SystemNumberGenerationVO sysGenCodeVO,List<AcctTransViewVO> acctTransListView)
			throws BusinessException {
		paymentService.updTransList(addTransList,updTransist,delTransList,cashBookVO,idCompany,bankAcctSearchVO,acctTransCashBookVO,lookupItemCBT,sysGenCodeVO,acctTransListView);
	}

	@Override
	public List<AcctVO> getAcctCodeList(Long idAccount) throws BusinessException {
		return paymentService.getAcctCodeList(idAccount);
	}

	@Override
	public List<AcctTransViewVO> getAcctTransListView() throws BusinessException {
		return paymentService.getAcctTransListView();
	}

	@Override
	public List<AcctTransViewVO> getAcctTransViewTableList(String sysNo, String transTypeCd, String sysCode,String statusActive,Long CompId) throws BusinessException {
		return paymentService.getAcctTransViewTableList(sysNo,transTypeCd,sysCode,statusActive,CompId);
	}

	@Override
	public List<AcctTransViewVO> getAcctTransSysNoList(String sysNo,String sysCode, Long CompId) throws BusinessException {
		return paymentService.getAcctTransSysNoList(sysNo,sysCode,CompId);
	}
	
	@Override
	public LookupItemVO getlookupItemBT(String cd)throws BusinessException {
		return paymentService.getlookupItemBT(cd);
	}

	@Override
	public LookupItemVO getlookupItemCBT(String gd) throws BusinessException {
		return paymentService.getlookupItemCBT(gd);
	}


	@Override
	public SupplierVO getSupplier(Long supplierId, Long idCompany) throws BusinessException {
		return paymentService.getSupplier(supplierId,idCompany);
	}

	@Override
	public AcctVO getAcctSearchList(Long idAcct) throws BusinessException {
		return paymentService.getAcctSearchList(idAcct);
	}

	@Override
	public List<CashBookVO> getBankCashBookList(String sysNumCdBankPmnt, Long idCompany, SearchParamVO searchParamVO, Map<String, Object> params) throws BusinessException {
		return paymentService.getBankCashBookList(sysNumCdBankPmnt, idCompany, searchParamVO, params);
	}
	
	@Override
	public int getBankCashBookListSize(String sysNumCdBankPmnt, Long idCompany, SearchParamVO searchParamVO, Map<String, Object> params) throws BusinessException {
		return paymentService.getBankCashBookListSize(sysNumCdBankPmnt, idCompany, searchParamVO, params);
	}
	
	@Override
	public int getCashBookListSize(Map<String, Object> params) throws BusinessException {
		return paymentService.getCashBookListSize(params);
	}
	
	@Override
	public List<CashBookVO> getCashBookList(Map<String, Object> params) throws BusinessException {
		return paymentService.getCashBookList(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.PaymentBO#updatePayment(com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.acct.vo.AcctTransViewVO, com.bcs.zsg.bank.vo.BankAcctVO)
	 */
	@Override
	public void updatePayment(CashBookVO cashBookVO, AcctTransViewVO acctTransCashBookVO, BankAcctVO bankAcctSearchVO) throws BusinessException {
		checkTaxCodeGroup(cashBookVO);
		paymentService.updatePayment(cashBookVO, acctTransCashBookVO, bankAcctSearchVO);
	}
	
	/**
	 * Check whether tax code missing or not
	 * @param exOrderBillVO
	 * @param acctTransVO
	 * @throws BusinessException
	 */
	private void checkTaxCodeGroup(CashBookVO cashBookVO) throws BusinessException {
		// check account trans missing tax code
		int flag = 0;
		for (AcctTransVO vo : cashBookVO.getAcctTransList()) {
			if (StringUtils.isEmpty(vo.getTaxCode())) {
				flag = 1;
				break;
			}
		}
		if (flag == 0) cashBookVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_Y);
		else cashBookVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_N);
	}
	
}
