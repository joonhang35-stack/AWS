package com.bcs.zsg.acct.bo;

import java.util.List;
import java.util.Map;

import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.common.vo.AddUpdDelVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.purchase.vo.ExOrderBillVO;
import com.bcs.zsg.zextra.backend.helper.QueueException;

public interface JournalBO {

	/**
	 * 
	 * @return 
	 * @throws BusinessException
	 */
	public void insertJournal(JournalVO journalVO, List<AcctTransVO> journalCartList) throws BusinessException, QueueException;
	
	/**
	 * 
	 * @return 
	 * @throws BusinessException
	 */
	public void updateJournal(JournalVO journalVO, List<AcctTransVO> journalCartList, AddUpdDelVO journalItemsVO) throws BusinessException, QueueException;
	
	/**
	 * 
	 * @return 
	 * @throws BusinessException
	 */
	public void delJournal(JournalVO journalVO) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<AcctTransVO> getJournalItems(JournalVO journalVO) throws BusinessException;

	public List<AcctVO> getAcctAutoCompleteList(Long idCompany, String strAutoCompleteValue) throws BusinessException;
	
	public boolean isAccountValid(AcctVO acctVO) throws BusinessException;
	

	public int getJournalListSize(Map<String, Object> params) throws BusinessException;
	public List<?> getJournalList(Map<String, Object> params) throws BusinessException;

	public JournalVO getJournalById(Long idJournal, Long idCompany) throws BusinessException;

	public List<JournalVO> getJournalListByBillId(Long idBill, Long idCompany) throws BusinessException;

	public JournalVO getJournalDetails(JournalVO journalVO) throws BusinessException;

	

	
	/**
	 * Validates that the total amount of all journals associated with the specified bill 
	 * does not exceed (or optionally, equal) the bill amount.
	 * <p>
	 * This method calculates the sum of all {@link JournalVO#getTotalAmt()} values from 
	 * {@code exOrderBillVO.getJournalList()} and compares it with 
	 * {@code exOrderBillVO.getBillAmt()}. If the total journal amount exceeds the bill 
	 * amount—or equals it when {@code allowEqual} is {@code false}—a {@link BusinessException} 
	 * is thrown.
	 * @param exOrderBillVO
	 * @param initialAmount an optional initial value to include in the total calculation (may be {@code null})
	 * @param allowEqual whether the total amount is allowed to be equal to the bill amount
	 * @throws BusinessException if the total journal amount exceeds or (when not allowed) equals the bill amount
	 */
	public void validateJournalTotalAgainstBill(ExOrderBillVO exOrderBillVO, Double initialAmount, boolean allowEqual)
			throws BusinessException;

	/**
	 * See {@link #validateJournalTotalAgainstBill(ExOrderBillVO, Double, boolean)} for detailed documentation.
	 * @param exOrderBillVO
	 * @throws BusinessException
	 */
	public void validateJournalTotalAgainstBill(ExOrderBillVO exOrderBillVO) throws BusinessException;

	/**
	 * See {@link #validateJournalTotalAgainstBill(ExOrderBillVO, Double, boolean)} for detailed documentation.
	 * @param exOrderBillVO
	 * @param allowEqual
	 * @throws BusinessException
	 */
	public void validateJournalTotalAgainstBill(ExOrderBillVO exOrderBillVO, boolean allowEqual) throws BusinessException;

}
