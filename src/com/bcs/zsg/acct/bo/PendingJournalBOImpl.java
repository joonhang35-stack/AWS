package com.bcs.zsg.acct.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.PendingJournalService;
import com.bcs.zsg.acct.vo.PendingReverseJournalVO;
import com.bcs.zsg.core.exception.BusinessException;

public class PendingJournalBOImpl implements PendingJournalBO {
	
	@Autowired
	private PendingJournalService pendingJournalService;
	
	@Override
	public int getPendingReverseJournalListSize(Map<String, Object> params) throws BusinessException {
		return pendingJournalService.getPendingReverseJournalListSize(params);
	}

	@Override
	public List<PendingReverseJournalVO> getPendingReverseJournalList(Map<String, Object> params) throws BusinessException {
		return pendingJournalService.getPendingReverseJournalList(params);
	}
}
