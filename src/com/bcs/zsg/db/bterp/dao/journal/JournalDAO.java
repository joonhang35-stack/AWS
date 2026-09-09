package com.bcs.zsg.db.bterp.dao.journal;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.core.dao.BaseDAO;
import com.bcs.zsg.core.exception.BusinessException;

public interface JournalDAO extends BaseDAO {

	public int getListSizeJournal(Map<String, Object> params) throws BusinessException;
	public List<JournalVO> getListJournal(Map<String, Object> params) throws BusinessException;

	public JournalVO getJournal(JournalVO journalVO) throws BusinessException ;
	public JournalVO getJournalById(Long idJournal, Long idCompany) throws BusinessException;
	public List<JournalVO> getJournalListByBillId(Long idBill, Long idCompany) throws BusinessException;
	public JournalVO getJournalByPaxStmtId(Long idPaxStmt, Long idCompany) throws BusinessException;
}
