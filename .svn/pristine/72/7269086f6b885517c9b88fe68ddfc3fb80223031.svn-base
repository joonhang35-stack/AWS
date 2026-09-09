package com.bcs.zsg.bank.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.service.DepositService;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.helper.LookupItemUtils;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.helper.SalesConstant;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public class DepositBOImpl implements DepositBO {

	@Autowired
	private DepositService depositService;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#addDeposit(com.bcs.zsg.bank.vo.CashBookVO, java.util.Date, java.lang.Long, com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO, java.util.List, com.bcs.zsg.bank.vo.BankAcctVO, com.bcs.zsg.maintenance.vo.LookupItemVO, com.bcs.zsg.maintenance.vo.LookupItemVO, com.bcs.zsg.acct.vo.AcctVO, com.bcs.zsg.common.vo.AddUpdDelVO)
	 */
	@Override
	public void addDeposit(CashBookVO cashBookVO, Long idCompany,SystemNumberGenerationVO sysNumGenVO,List<SystemNumberGenerationVO> sysNumGenList,
			BankAcctVO bankAcctSearchVO,LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, AcctVO acctVO,AddUpdDelVO invPmntAUDList) throws BusinessException, QueueException {
		checkTaxCodeGroup(cashBookVO);
		setInvoicePayment(cashBookVO, idCompany);
		depositService.addDeposit(cashBookVO,idCompany,sysNumGenVO,sysNumGenList,bankAcctSearchVO,lookupItemBT,lookupItemCBT,acctVO,invPmntAUDList);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#getAcctTransCashBook(java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public AcctTransViewVO getAcctTransCashBook(String sysNo,  String transTypeCd, String sysCode, Long CompId) throws BusinessException {
		return depositService.getAcctTransCashBook(sysNo, transTypeCd,sysCode,CompId);
	}



	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#getInvoicePaymentListByCashbook(java.lang.Long)
	 */
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListByCashbook(Long idCashBook) throws BusinessException{
		return depositService.getInvoicePaymentListByCashbook(idCashBook);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#getInvoicePaymentListNoCashbook(java.lang.Long)
	 */
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException{
		return depositService.getInvoicePaymentListNoCashbook(CompId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#getInvoicePaymentListNoCashbook(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long compId, SearchParamVO searchParamVO) throws BusinessException {
		return depositService.getInvoicePaymentListNoCashbook(compId, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#updateDeposit(com.bcs.zsg.common.vo.AddUpdDelVO, java.lang.String)
	 */
	@Override
	public void updateDeposit( AddUpdDelVO invPmntAUDList,String cashBookId) throws BusinessException{
		depositService.updateDeposit(invPmntAUDList,  cashBookId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#deleteDeposit(java.lang.String)
	 */
	@Override
	public void deleteDeposit(String cashBookId, Long idCompany) throws BusinessException{
		depositService.deleteDeposit(cashBookId, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#getDepositListSize(java.util.Map)
	 */
	@Override
	public int getDepositListSize(Map<String, Object> params) throws BusinessException {
		return depositService.getDepositListSize(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#getDepositList(java.util.Map)
	 */
	@Override
	public List<CashBookVO> getDepositList(Map<String, Object> params) throws BusinessException {
		return depositService.getDepositList(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.bo.DepositBO#updateDeposit(com.bcs.zsg.bank.vo.CashBookVO, com.bcs.zsg.acct.vo.AcctTransViewVO, com.bcs.zsg.bank.vo.BankAcctVO)
	 */
	@Override
	public void updateDeposit(CashBookVO cashBookVO, AcctTransViewVO acctTransCashBookVO, BankAcctVO bankAcctSearchVO, Long CompId) throws BusinessException {
		checkTaxCodeGroup(cashBookVO);
		setInvoicePayment(cashBookVO, CompId);
		depositService.updateDeposit(cashBookVO, acctTransCashBookVO, bankAcctSearchVO, CompId);
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
	
	/**
	 * Set invoice payment
	 * @param cashBookVO
	 * @throws BusinessException
	 */
	private void setInvoicePayment(CashBookVO cashBookVO, Long idCompany) throws BusinessException {
		// set invoice payment
		if (CollectionUtils.isNotEmpty(cashBookVO.getInvPymtList())) {
			StringBuilder sb = new StringBuilder();
			for (InvoicePaymentVO vo : cashBookVO.getInvPymtList()) {
//				sb.append(vo.getInvId()).append(",");
				if (vo.getInvoiceDocTypeCd().equals(SalesConstant.DOC_TYPE_CD_PAX_STMT)) {
					sb.append(
							LookupItemUtils.getSysNumGenVO(idCompany, CommonConstant.SYS_NUM_CD_PAX_STMT).getPrefixid() 
							+ vo.getPsNo()
					);
				} else {
					sb.append(
							LookupItemUtils.getSysNumGenVO(idCompany, CommonConstant.SYS_NUM_CD_INVC).getPrefixid() 
							+ vo.getInvoiceNo()
					);
				}
				sb.append(",");
			}
			cashBookVO.setInvoices(sb.substring(0, sb.length() - 1));
		} else cashBookVO.setInvoices(null);
	}

	@Override
	public void updateInvoicePaymentHideStatus(String paymentIDs, int hideStatus) throws BusinessException {
		depositService.updateInvoicePaymentHideStatus(paymentIDs, hideStatus);
	}

	@Override
	public void postToDeposit(List<InvoicePaymentVO> paymentList, List<BankAcctViewVO> bankAcctViewList, CompanyVO companyVO, boolean isAcctMgr) throws BusinessException, QueueException {
		List<InvoicePaymentVO> list = new ArrayList<InvoicePaymentVO>();
		Date prevTransDate = null;
		long prevCustId = 0;
		String prevRefNo = null;
		
		for (InvoicePaymentVO vo : paymentList) {
			if (LookupItemUtils.getFinAndGSTPeriodClosedStatus(companyVO.getId(), vo.getPmntDt(), isAcctMgr))
				throw new BusinessException(CommonErrConstant.ERR_GST_FINANCIAL_CLOSED);
				
			if (prevTransDate == null) prevTransDate = vo.getPmntDt();
			if (prevCustId == 0) prevCustId = vo.getCustId();
			if (prevRefNo == null) prevRefNo = vo.getRefNo();
			
			if (vo.getPmntDt().equals(prevTransDate) && prevCustId == vo.getCustId().longValue() && prevRefNo.equals(vo.getRefNo())) {
				list.add(vo);
				
			} else {
				depositService.postToDeposit(list, bankAcctViewList, companyVO);
				list = new ArrayList<InvoicePaymentVO>();
				list.add(vo);
			}
		}
		if (!list.isEmpty()) depositService.postToDeposit(list, bankAcctViewList, companyVO);
	}
}
