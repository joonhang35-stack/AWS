package com.bcs.zsg.acct.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.acct.dao.PendingJournalDAO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.acct.vo.PendingReverseJournalVO;
import com.bcs.zsg.common.helper.CommonConstant;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.db.bterp.dao.journal.JournalDAO;
import com.bcs.zsg.maintenance.dao.CorporateProfileDAO;
import com.bcs.zsg.maintenance.vo.CompanyVO;
import com.bcs.zsg.sales.dao.InvoiceDAO;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public class PendingJournalServiceImpl implements PendingJournalService, Job {
	
	@Autowired
	private JournalService journalService;
	@Autowired
	private JournalDAO journalDAO;
	@Autowired
	private PendingJournalDAO pendingJournalDAO;
	@Autowired
	private InvoiceDAO invoiceDAO;
	@Autowired
	private CorporateProfileDAO corporateProfileDAO;
	
	@Override
	public int getPendingReverseJournalListSize(Map<String, Object> params) throws BusinessException {
		return pendingJournalDAO.getPendingReverseJournalListSize(params);
	}
	
	@Override
	public List<PendingReverseJournalVO> getPendingReverseJournalList(Map<String, Object> params) throws BusinessException {
		return pendingJournalDAO.getPendingReverseJournalList(params);
	}
	
	private void processPendingReverseJournal(Long idCompany) throws BusinessException, QueueException {
		
		TrackingLogUtils.printLogs("JournalServiceImpl.processPendingReverseJournal()");
		
		if (pendingJournalDAO == null)
			SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idCompany", idCompany);
		map.put("statusCd", CommonConstant.EMAIL_PMNT_PENDING);
		
		List<PendingReverseJournalVO> pendingList = pendingJournalDAO.getPendingReverseJournalList(map);
		
		for (PendingReverseJournalVO pendingVO : pendingList) {
			try {
				processSinglePendingReverseJournal(pendingVO);
			} catch (Throwable t) {
				
				t.printStackTrace();
				
				pendingVO.setStatusCode(CommonConstant.EMAIL_PMNT_FAILED);
				pendingJournalDAO.updatePendingReverseJournalStatus(pendingVO.getId(), CommonConstant.EMAIL_PMNT_FAILED, null);
				
				TrackingLogUtils.printLogs("[ReverseSalesJournal] Failed (PendingReverseJournal ID = " + pendingVO.getId() + ")");

				t.printStackTrace();
			}
		}
		
	}
	
	@Transactional
	private void processSinglePendingReverseJournal(PendingReverseJournalVO pendingVO)
			throws BusinessException, QueueException {
		
		InvoiceVO paxStmtVO = invoiceDAO.getInvoiceById(pendingVO.getIdPs());
		paxStmtVO.setInvoiceItemList(invoiceDAO.getInvoiceItemList(paxStmtVO.getId()));
		
		InvoiceVO invVO = invoiceDAO.getInvoiceById(pendingVO.getIdInv());
		invVO.setInvoiceItemList(invoiceDAO.getInvoiceItemList(invVO.getId()));
//		invVO = invoiceDAO.getInvoiceDetails(invVO);
		
		// set tax account to generate acct trans of tax trans
		CompanyVO companyVO = corporateProfileDAO.getCompanyDetails(paxStmtVO.getCompanyId());
		paxStmtVO.setIdAcctGST(companyVO.getIdAcctGST());
		invVO.setIdAcctGST(companyVO.getIdAcctGST());
		
		TrackingLogUtils.printLogs("[ReverseSalesJournal] Creating (PS" + paxStmtVO.getPsNo() + ")");

		JournalVO reverseJournalVO = journalService.insertReverseJournal(paxStmtVO, invVO);

		pendingVO.setStatusCode(CommonConstant.EMAIL_PMNT_SUCCESS);
		pendingJournalDAO.updatePendingReverseJournalStatus(pendingVO.getId(), CommonConstant.EMAIL_PMNT_SUCCESS, reverseJournalVO.getId());
		
		TrackingLogUtils.printLogs("[ReverseSalesJournal] Success (PS" + paxStmtVO.getPsNo() + "), Journal ID=" + reverseJournalVO.getId());
	}
	
	@Override
	public PendingReverseJournalVO insertPendingReverseJournal(InvoiceVO paxStmtVO, InvoiceVO invoiceVO) {
		return insertPendingReverseJournal(paxStmtVO.getId(), invoiceVO.getId());
	}
	
	@Override
	public PendingReverseJournalVO insertPendingReverseJournal(Long idPS, Long idInv) {
		PendingReverseJournalVO pendingReverseJournalVO = new PendingReverseJournalVO();
		pendingReverseJournalVO.setIdPs(idPS);
		pendingReverseJournalVO.setIdInv(idInv);
		pendingReverseJournalVO.setStatusCode(CommonConstant.EMAIL_PMNT_PENDING);
		invoiceDAO.insert(pendingReverseJournalVO);
		
		return pendingReverseJournalVO;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Scheduler Pending Journal Daily at : " + new Date());
		try {
			processPendingReverseJournal(null);
		} catch (BusinessException | QueueException e) {
			TrackingLogUtils.printLogs("Scheduler Pending Journal Error");
		}
	}
	
	
}
