package com.bcs.zsg.acct.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public interface JournalService {

	public void insertJournal(JournalVO journalVO, List<AcctTransVO> journalCartList) throws BusinessException, QueueException;
	
	public void updateJournal(JournalVO journalVO, List<AcctTransVO> journalCartList, AddUpdDelVO journalItemsVO) throws BusinessException, QueueException;
	
	public void delJournal(JournalVO journalVO) throws BusinessException;
	
	public int getJournalListSize(Map<String, Object> params) throws BusinessException;
	public List<?> getJournalList(Map<String, Object> params) throws BusinessException;
	

	public List<AcctTransVO> getJournalItems(JournalVO journalVO) throws BusinessException;

	public JournalVO getJournalById(Long idJournal, Long idCompany) throws BusinessException;

	/**
	 * @param paxStmtVO
	 * @param invoiceVO - clonedVO
	 * @return 
	 * @throws BusinessException
	 * @throws QueueException
	 */
	public JournalVO insertReverseJournal(InvoiceVO paxStmtVO, InvoiceVO invoiceVO) throws BusinessException, QueueException;

	public List<JournalVO> getJournalListByBillId(Long idBill, Long idCompany) throws BusinessException;

	public JournalVO getJournalByPaxStmtId(Long idPaxStmt, Long idCompany) throws BusinessException;

	public JournalVO getJournalDetails(JournalVO journalVO) throws BusinessException;
}
