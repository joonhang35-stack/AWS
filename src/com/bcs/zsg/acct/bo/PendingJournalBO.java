package com.bcs.zsg.acct.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.PendingReverseJournalVO;
import com.bcs.zsg.core.exception.BusinessException;

public interface PendingJournalBO {
	public List<PendingReverseJournalVO> getPendingReverseJournalList(Map<String, Object> params) throws BusinessException;

	public int getPendingReverseJournalListSize(Map<String, Object> params) throws BusinessException;
}
