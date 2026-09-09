package com.bcs.zsg.bank.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctTransViewVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.bank.dao.DepositDAO;
import com.bcs.zsg.bank.dao.RefundDAO;
import com.bcs.zsg.bank.vo.BankAcctVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.bank.vo.InvPmntCBLinkVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.maintenance.vo.LookupItemVO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;
import com.bcs.zsg.sales.vo.InvoicePaymentVO;

public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundDAO refundDAO;
	@Autowired
	private AccountService accountService;
	@Autowired
	private DepositDAO depositDAO;
	
	@Override
	public void addRefund(CashBookVO cashBookVO,Long idCompany,SystemNumberGenerationVO sysNumGenVO,List<SystemNumberGenerationVO> sysNumGenList,BankAcctVO bankAcctSearchVO, LookupItemVO lookupItemBT, LookupItemVO lookupItemCBT,AcctVO acctVO) throws BusinessException {
		//cashBookVO.setDtTrans(transDate);
		cashBookVO.setSysCode(sysNumGenVO.getCode());
		cashBookVO.setSysPrefix(sysNumGenVO.getPrefixid());
		cashBookVO.setSysNo(String.valueOf(sysNumGenVO.getNextnumber()));
		cashBookVO.setTransTypeCd(lookupItemBT.getCode());
		cashBookVO.setDebit(0.00);
		cashBookVO.setIsClear(false);
		cashBookVO.setIsMark(false);
		cashBookVO.setTypeCd(lookupItemCBT.getCode());
		cashBookVO.setGroupNo(null);
		cashBookVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
		refundDAO.insert(cashBookVO);

		AcctTransVO acctTransVO1=new AcctTransVO();
		acctTransVO1.setCompanyId(idCompany);
		acctTransVO1.setAcctId(bankAcctSearchVO.getIdAcct());
		acctTransVO1.setRefNo(cashBookVO.getRefNo());
		acctTransVO1.setCredit(cashBookVO.getCredit());
		if (StringUtils.isEmpty(acctVO.getSubCode()))
		{
			acctTransVO1.setCode(acctVO.getCode());
		}
		else
		{	
			acctTransVO1.setCode(acctVO.getCode() + "-" + acctVO.getSubCode());
		}
		if (StringUtils.isEmpty(acctVO.getSubDesc())) 
		{

			acctTransVO1.setDesc(acctVO.getDesc());
		}
		else
		{
			acctTransVO1.setDesc(acctVO.getDesc()+","+acctVO.getSubDesc());
		}
		acctTransVO1.setDebit(0.00);
		acctTransVO1.setType(lookupItemBT.getCode());
		acctTransVO1.setTransDt(cashBookVO.getDtTrans());
		acctTransVO1.setSysPrefix(sysNumGenVO.getPrefixid());
		acctTransVO1.setSysCode(sysNumGenVO.getCode());
		acctTransVO1.setSysNo(String.valueOf(sysNumGenVO.getNextnumber()));
		acctTransVO1.setSource("BR-"+acctTransVO1.getSysNo()+"-"+bankAcctSearchVO.getName());
		acctTransVO1.setDestination(cashBookVO.getRemarks()+" To-"+cashBookVO.getPayee());
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD,acctTransVO1);

		if(!(cashBookVO.getAcctTransList() == null)){
			for(int i=0; i <cashBookVO.getAcctTransList().size();i++)
			{
				AcctTransVO acctTransVO=new AcctTransVO();
				acctTransVO.setCompanyId(idCompany);
				acctTransVO.setAcctId(cashBookVO.getAcctTransList().get(i).getAcctViewVO().getId());
				acctTransVO.setRefNo(cashBookVO.getRefNo());
				acctTransVO.setCredit(0.00);
				if(StringUtils.isEmpty(cashBookVO.getAcctTransList().get(i).getAcctViewVO().getSubCode())) 
				{
					acctTransVO.setCode(cashBookVO.getAcctTransList().get(i).getAcctViewVO().getCode().toString());
				}
				else
				{
					acctTransVO.setCode(cashBookVO.getAcctTransList().get(i).getAcctViewVO().getCode()+ "-" +cashBookVO.getAcctTransList().get(i).getAcctViewVO().getSubCode());
				}
				acctTransVO.setDesc(cashBookVO.getAcctTransList().get(i).getDesc());
				acctTransVO.setDebit(cashBookVO.getAcctTransList().get(i).getCredit());
				acctTransVO.setType(lookupItemCBT.getCode());
				acctTransVO.setTransDt(cashBookVO.getDtTrans());
				acctTransVO.setSysPrefix(sysNumGenVO.getPrefixid());
				acctTransVO.setSysCode(sysNumGenVO.getCode());
				acctTransVO.setSysNo(String.valueOf(sysNumGenVO.getNextnumber()));
				acctTransVO.setSource("BR-"+acctTransVO.getSysNo()+"-"+bankAcctSearchVO.getName());
				acctTransVO.setDestination(cashBookVO.getRemarks()+" To-"+cashBookVO.getPayee());
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD,acctTransVO);
			}
		}
		sysNumGenVO.setNextnumber(sysNumGenVO.getNextnumber()+1);
		refundDAO.update(sysNumGenVO);
		
		// update invoice payment
		//refundDAO.updInvPmnt(cashBookVO);
		if (cashBookVO.getInvPymtList().size() > 0) {
			for (InvoicePaymentVO vo : cashBookVO.getInvPymtList()) {
				InvPmntCBLinkVO invPmntCBLinkVO = new InvPmntCBLinkVO();
				invPmntCBLinkVO.setIdCompany(idCompany);
				invPmntCBLinkVO.setIdInvoice(vo.getInvoiceId());
				invPmntCBLinkVO.setInvoiceNo(vo.getInvoiceNo());
				invPmntCBLinkVO.setIdInvPmnt(vo.getId());
				invPmntCBLinkVO.setIdCashBook(cashBookVO.getId());
				invPmntCBLinkVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				depositDAO.insert(invPmntCBLinkVO);
			}
		}
	}

	@Override
	public void updTransList(List<AcctTransViewVO> addTransList,
			List<AcctTransViewVO> updTransist, 
			List<AcctTransViewVO> delTransList,
			CashBookVO cashBookVO,Long idCompany,BankAcctVO bankAcctSearchVO, AcctTransViewVO acctTransCashBookVO, 
			LookupItemVO lookupItemCBT, SystemNumberGenerationVO sysGenCodeVO, List<AcctTransViewVO> acctTransListView, Long CompId)
					throws BusinessException {
		
		refundDAO.update(cashBookVO);
		
		if (CollectionUtils.isNotEmpty(addTransList)) 
		{
			for (AcctTransViewVO vo : addTransList) 
			{
				vo.setCompanyId(idCompany);
				vo.setAcctId(vo.getAcctViewVO().getId());
				vo.setTransDt(cashBookVO.getDtTrans());
				vo.setSysCode(sysGenCodeVO.getCode());
				vo.setSysPrefix(cashBookVO.getSysPrefix());
				vo.setSysNo(cashBookVO.getSysNo());
				vo.setRefNo(cashBookVO.getRefNo());
				vo.setCredit(0.00);
				vo.setSource("BR-" + vo.getSysNo() + "-" + bankAcctSearchVO.getName());
				vo.setDestination(cashBookVO.getRemarks() + " To-" + cashBookVO.getPayee());
				
				vo.setType(lookupItemCBT.getCode());
				
				
				
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_ADD,vo);
			}
		}
		// update contacts
		if (CollectionUtils.isNotEmpty(updTransist)) {
			for (AcctTransViewVO vo : updTransist) {
				vo.setAcctId(vo.getAcctViewVO().getId());
				vo.setTransDt(cashBookVO.getDtTrans());
				vo.setRefNo(cashBookVO.getRefNo());
				vo.setSource("BR-"+vo.getSysNo()+"-"+bankAcctSearchVO.getName());
				vo.setDestination(cashBookVO.getRemarks() + " To-" + cashBookVO.getPayee());
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD,vo);
			}
		}
		// delete contacts
		if (CollectionUtils.isNotEmpty(delTransList)) {
			for (AcctTransViewVO vo : delTransList) {
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_DEL,vo);

			}
		}
		acctTransCashBookVO.setTransDt(cashBookVO.getDtTrans());
		acctTransCashBookVO.setRefNo(cashBookVO.getRefNo());
		acctTransCashBookVO.setCompanyId(idCompany);
		acctTransCashBookVO.setAcctId(bankAcctSearchVO.getIdAcct());
		acctTransCashBookVO.setCredit(cashBookVO.getCredit());
		acctTransCashBookVO.setSource("BR-" + acctTransCashBookVO.getSysNo() + "-" + bankAcctSearchVO.getName());
		acctTransCashBookVO.setDestination(cashBookVO.getRemarks() + " To-" + cashBookVO.getPayee());
		accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD,acctTransCashBookVO);
		
		if (CollectionUtils.isNotEmpty(acctTransListView)) {
			for (AcctTransViewVO vo : acctTransListView) {
				vo.setTransDt(cashBookVO.getDtTrans());
				vo.setRefNo(cashBookVO.getRefNo());
				vo.setAcctId(vo.getAcctViewVO().getId());
				vo.setDestination(cashBookVO.getRemarks() + " To-" + cashBookVO.getPayee());
				accountService.auditAcctTrans(CommonConstant.ACTION_CD_UPD,vo);
			}
		}
		
		// update invoice payment
		// refundDAO.updInvPmnt(cashBookVO);
		updateInvoicePmnt(cashBookVO, CompId);
	}
	
	@Override
	public void updateInvoicePmnt(CashBookVO cashBookVO, Long CompId) throws BusinessException {
		List<InvPmntCBLinkVO> invPmntCBLinkList = depositDAO.getInvPmntCBLinkList(cashBookVO.getId());
		depositDAO.updateInvPmntCBLink(cashBookVO.getId(), CompId);

		boolean update = false;
		for (InvoicePaymentVO pmnt : cashBookVO.getInvPymtList()) {
			update = false;
			for (InvPmntCBLinkVO link : invPmntCBLinkList) {
				if (pmnt.getId().equals(link.getIdInvPmnt())) {
					link.setStatusCode(BaseConstant.STATUS_ACTIVE);
					refundDAO.update(link);
					update = true;
				}
			}
			if (!update) {
				InvPmntCBLinkVO invPmntCBLinkVO = new InvPmntCBLinkVO();
				invPmntCBLinkVO.setIdCompany(CompId);
				invPmntCBLinkVO.setIdInvoice(pmnt.getInvoiceId());
				invPmntCBLinkVO.setInvoiceNo(pmnt.getInvoiceNo());
				invPmntCBLinkVO.setIdInvPmnt(pmnt.getId());
				invPmntCBLinkVO.setIdCashBook(cashBookVO.getId());
				invPmntCBLinkVO.setStatusCode(BaseConstant.STATUS_ACTIVE);
				refundDAO.insert(invPmntCBLinkVO);
			}
		}
	}
	
	
	@Override
	public void delInvPmnt(String cashBookId, Long idCompany) throws BusinessException{
		//refundDAO.delInvPmnt(cashBookId);
		depositDAO.updateInvPmntCBLink(Long.valueOf(cashBookId), idCompany);
	}
	
	@Override
	public List<InvoicePaymentVO> getInvoicePaymentListNoCashbook(Long CompId) throws BusinessException{
		return refundDAO.getInvoicePaymentListNoCashbook(CompId);
	}
}
