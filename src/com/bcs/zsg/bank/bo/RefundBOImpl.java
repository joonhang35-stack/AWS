package com.bcs.zsg.bank.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.service.RefundService;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;

public class RefundBOImpl implements RefundBO {

	@Autowired
	private RefundService refundService;


	@Override
	public void addRefund(CashBookVO cashBookVO, Long idCompany,SystemNumberGenerationVO sysNumGenVO,List<SystemNumberGenerationVO> sysNumGenList,BankAcctVO bankAcctSearchVO,LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT, AcctVO acctVO) throws BusinessException {
		cashBookVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_N);
		refundService.addRefund(cashBookVO,idCompany,sysNumGenVO,sysNumGenList,bankAcctSearchVO,lookupItemBT,lookupItemCBT,acctVO);
	}

	@Override
	public void updTransList(List<AcctTransViewVO> addTransList,
			List<AcctTransViewVO> updTransist, List<AcctTransViewVO> delTransList,CashBookVO cashBookVO,Long idCompany,BankAcctVO bankAcctSearchVO, AcctTransViewVO acctTransCashBookVO, LookupItemVO lookupItemCBT, SystemNumberGenerationVO sysGenCodeVO, List<AcctTransViewVO> acctTransListView, Long CompId)
					throws BusinessException {
		cashBookVO.setTaxCodeGroup(CommonConstant.TAX_CODE_GROUP_N);
		refundService.updTransList(addTransList,updTransist,delTransList,cashBookVO,idCompany,bankAcctSearchVO,acctTransCashBookVO,lookupItemCBT,sysGenCodeVO,acctTransListView,CompId);
	}
	
	@Override
	public void delInvPmnt(String cashBookId, Long idCompany) throws BusinessException{
		refundService.delInvPmnt(cashBookId, idCompany);
	}
	
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException{
		return refundService.getInvoicePaymentListNoCashbook(CompId);
	}
}
