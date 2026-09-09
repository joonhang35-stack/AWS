package com.bcs.zsg.acct.service;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.PendingReverseJournalVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.vo.InvoiceVO;

public interface PendingJournalService {
	public List<PendingReverseJournalVO> getPendingReverseJournalList(Map<String, Object> params) throws BusinessException;
	public int getPendingReverseJournalListSize(Map<String, Object> params) throws BusinessException;
	public PendingReverseJournalVO insertPendingReverseJournal(InvoiceVO paxStmtVO, InvoiceVO invoiceVO);
	public PendingReverseJournalVO insertPendingReverseJournal(Long idPS, Long idInv);
}
