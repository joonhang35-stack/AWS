package com.bcs.zsg.acct.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.service.AccountService;
import com.bcs.zsg.acct.service.ChartOfAcctService;
import com.bcs.zsg.acct.vo.AcctBalVO;
import com.bcs.zsg.acct.vo.AcctBalViewVO;
import com.bcs.zsg.acct.vo.AcctCatVO;
import com.bcs.zsg.acct.vo.AcctSubCatVO;
import com.bcs.zsg.acct.vo.AcctTransVO;
import com.bcs.zsg.acct.vo.AcctVO;
import com.bcs.zsg.acct.vo.AcctViewVO;
import com.bcs.zsg.acct.vo.JournalVO;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;
import com.bcs.zsg.core.vo.BaseVO;

public class ChartOfAcctBOImpl implements ChartOfAcctBO {

	@Autowired
	private transient ChartOfAcctService chartOfAcctService;
	@Autowired
	private AccountService accountService;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#insertVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void insertVO(BaseVO vo) throws BusinessException {
		chartOfAcctService.insertVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#updateVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void updateVO(BaseVO vo) throws BusinessException {
		chartOfAcctService.updateVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#deleteVO(com.bcs.zsg.core.vo.BaseVO)
	 */
	@Override
	public void deleteVO(BaseVO vo) throws BusinessException {
		chartOfAcctService.deleteVO(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctCatList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctCatVO> getAcctCatList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getAcctCatList(idCompany, searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctSubCatList()
	 */
	@Override
	public List<AcctSubCatVO> getAcctSubCatList(Long idAcctCat, Long idCompany) throws BusinessException {
		return chartOfAcctService.getAcctSubCatList(idAcctCat, idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAcctList(Long idCompany, Long idAcctCat) throws BusinessException {
		return chartOfAcctService.getAcctList(idCompany, idAcctCat);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctListByAcctCat(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AcctVO> getAcctListByAcctCat(Long idCompany, Long idAcctCat) throws BusinessException {
		return chartOfAcctService.getAcctListByAcctCat(idCompany, idAcctCat);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#addAcctCat(com.bcs.zsg.acct.vo.AccountCatVO)
	 */
	@Override
	public void addAcctCat(AcctCatVO acctCatVO) throws BusinessException {
		if (!chartOfAcctService.isAcctCatValid(acctCatVO)) throw new BusinessException(CommonErrConstant.ERR_ACCT_CAT_CD_DUP);
		insertVO(acctCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#addAcctSubCat(com.bcs.zsg.acct.vo.AccountSubCatVO)
	 */
	@Override
	public void addAcctSubCat(AcctSubCatVO acctSubCatVO) throws BusinessException {
		if (!chartOfAcctService.isAcctSubCatValid(acctSubCatVO)) throw new BusinessException(CommonErrConstant.ERR_ACCT_SUB_CAT_DUP);
		insertVO(acctSubCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#delAcctSubCat(com.bcs.zsg.acct.vo.AcctSubCatVO)
	 */
	@Override
	public void delAcctSubCat(AcctSubCatVO acctSubCatVO) throws BusinessException {
		if (chartOfAcctService.isAcctSubCatInUsed(acctSubCatVO)) throw new BusinessException(CommonErrConstant.ERR_ACCT_SUB_CAT_USED);
		acctSubCatVO.setStatusCode(BaseConstant.STATUS_DELETED);
		updateVO(acctSubCatVO);
		//deleteVO(acctSubCatVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#addAcct(com.bcs.zsg.acct.vo.AccountVO, com.bcs.zsg.acct.vo.AccountBalanceVO)
	 */
	@Override
	public void addAcct(AcctVO acctVO, AcctBalVO acctBalVO) throws BusinessException {
		if (!chartOfAcctService.isAcctValid(acctVO)) throw new BusinessException(CommonErrConstant.ERR_ACCT_DUP);
		Date finPeriod1stDt = chartOfAcctService.getFinPeriod1stDt(acctVO.getIdCompany());
		//if (finPeriod1stDt == null) throw new BusinessException(CommonErrConstant.ERR_ACCT_1ST_FIN_PERIOD_NOT_EXISTED);
		
		Calendar cal = Calendar.getInstance();
		//cal.setTime(new Date());
		cal.setTime(finPeriod1stDt);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		//(getSessionInfoBean().getCompanyVO().getId());
		
		/*if (StringUtils.isNotEmpty(acctVO.getSubCode()) && chartOfAcctService.isMainAcctValid(acctVO)) {
			AcctVO vo = new AcctVO();
			vo.setIdCompany(acctVO.getIdCompany());
			vo.setIdAcctCat(acctVO.getIdAcctCat());
			vo.setIdAcctSubCat(acctVO.getIdAcctSubCat());
			vo.setCode(acctVO.getCode());
			vo.setSubCode("");
			vo.setDesc(acctVO.getDesc());
			vo.setStatusCode(acctVO.getStatusCode()	);
			insertVO(vo);
			
			AcctBalVO balVO = new AcctBalVO();
			balVO.setIdAcct(vo.getId());
			balVO.setDtBeginBal(cal.getTime());
			balVO.setDtCloseBal(cal.getTime());
			balVO.setDtCurrentBal(cal.getTime());
			balVO.setDebitBeginBal(0.0);
			balVO.setCreditBeginBal(0.0);
			balVO.setDebitCloseBal(0.0);
			balVO.setCreditCloseBal(0.0);
			balVO.setDebitCurrentBal(0.0);
			balVO.setCreditCurrentBal(0.0);
			insertVO(balVO);
		}*/
		acctVO.setTypeCode("N");
		insertVO(acctVO);
		
		acctBalVO.setIdAcct(acctVO.getId());
		acctBalVO.setDtBeginBal(cal.getTime());
		acctBalVO.setDtCloseBal(cal.getTime());
		acctBalVO.setDtCurrentBal(cal.getTime());
		acctBalVO.setDebitCloseBal(acctBalVO.getDebitBeginBal());
		acctBalVO.setCreditCloseBal(acctBalVO.getCreditBeginBal());
		acctBalVO.setDebitCurrentBal(acctBalVO.getDebitBeginBal());
		acctBalVO.setCreditCurrentBal(acctBalVO.getCreditBeginBal());
		acctBalVO.setTypeCode("I");
		insertVO(acctBalVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#delAcct(com.bcs.zsg.acct.vo.AccountVO, com.bcs.zsg.acct.vo.AccountBalanceVO)
	 */
	@Override
	public void delAcct(AcctVO acctVO, AcctBalVO acctBalVO) throws BusinessException {
		/*if (StringUtils.isEmpty(acctVO.getSubCode())) {
			// check sub account existed or not
			if (chartOfAcctService.isSubAcctExisted(acctVO)) throw new BusinessException(CommonErrConstant.ERR_ACCT_SUB_ACCT_EXISTED);
		}*/
		if (chartOfAcctService.isAcctUsed(acctVO, acctBalVO)) throw new BusinessException(CommonErrConstant.ERR_ACCT_ID_USED);
		
		//deleteVO(acctBalVO);
		//chartOfAcctService.deleteAcctBal(acctVO);
		//deleteVO(acctVO);
		acctVO.setStatusCode(BaseConstant.STATUS_DELETED);
		updateVO(acctVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#updAcct(com.bcs.zsg.acct.vo.AccountVO, com.bcs.zsg.acct.vo.AccountBalanceVO)
	 */
	@Override
	public void updAcct(AcctVO acctVO, AcctVO prevAcctVO, AcctBalVO acctBalVO) throws BusinessException {
		if (StringUtils.isEmpty(acctVO.getSubCode())) {
			if (!acctVO.getCode().equalsIgnoreCase(prevAcctVO.getCode()) && !chartOfAcctService.isAcctValid(acctVO))
				throw new BusinessException(CommonErrConstant.ERR_ACCT_DUP);
		} else {
			if (acctVO.getCode().equalsIgnoreCase(prevAcctVO.getCode())) {
				if (!acctVO.getSubCode().equalsIgnoreCase(prevAcctVO.getSubCode()) && !chartOfAcctService.isAcctValid(acctVO))
					throw new BusinessException(CommonErrConstant.ERR_ACCT_DUP);
				
			} else {
				if (!chartOfAcctService.isAcctValid(acctVO))
					throw new BusinessException(CommonErrConstant.ERR_ACCT_DUP);
			}
		}
		
		//acctBalVO.setDebitCloseBal(acctBalVO.getDebitBeginBal());
		//acctBalVO.setCreditCloseBal(acctBalVO.getCreditBeginBal());
		//acctBalVO.setDebitCurrentBal(acctBalVO.getDebitBeginBal());
		//acctBalVO.setCreditCurrentBal(acctBalVO.getCreditBeginBal());
		
		//chartOfAcctService.updAcct(acctVO, acctBalVO);
		updateVO(acctVO);
		//updateVO(acctBalVO);
		
		// If the account bal record more then 1 mean already "closing" can not edit the balance
		if (!chartOfAcctService.isClosedAcct(acctVO.getIdCompany(), acctVO.getId())) {
			updBeginBal(acctBalVO);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#updBeginBal(com.bcs.zsg.acct.vo.AcctBalVO)
	 */
	@Override
	public void updBeginBal(AcctBalVO acctBalVO) throws BusinessException {
		AcctBalVO vo = (AcctBalVO) chartOfAcctService.getAcctBeginBalVO(acctBalVO.getIdAcct()).clone();
		
		if (acctBalVO.getDebitBeginBal().doubleValue() != vo.getDebitBeginBal().doubleValue() ||
				acctBalVO.getCreditBeginBal().doubleValue() != vo.getCreditBeginBal().doubleValue()) {
			
			// update other balance
			List<AcctBalVO> voList = chartOfAcctService.getAcctBalList(acctBalVO.getIdAcct());
			for (AcctBalVO balVO : voList) {
				if (balVO.getId().longValue() == acctBalVO.getId().longValue()) {
					balVO.setDebitBeginBal(acctBalVO.getDebitBeginBal());
					balVO.setCreditBeginBal(acctBalVO.getCreditBeginBal());
					balVO.setDebitCloseBal(balVO.getDebitCloseBal() - vo.getDebitBeginBal() + acctBalVO.getDebitBeginBal());
					balVO.setCreditCloseBal(balVO.getCreditCloseBal() - vo.getCreditBeginBal() + acctBalVO.getCreditBeginBal());
					balVO.setDebitCurrentBal(balVO.getDebitCurrentBal() - vo.getDebitBeginBal() + acctBalVO.getDebitBeginBal());
					balVO.setCreditCurrentBal(balVO.getCreditCurrentBal() - vo.getCreditBeginBal() + acctBalVO.getCreditBeginBal());
					
				} else {
					balVO.setDebitBeginBal(balVO.getDebitBeginBal() - vo.getDebitBeginBal() + acctBalVO.getDebitBeginBal());
					balVO.setCreditBeginBal(balVO.getCreditBeginBal() - vo.getCreditBeginBal() + acctBalVO.getCreditBeginBal());
					balVO.setDebitCurrentBal(balVO.getDebitCurrentBal() - vo.getDebitBeginBal() + acctBalVO.getDebitBeginBal());
					balVO.setCreditCurrentBal(balVO.getCreditCurrentBal() - vo.getCreditBeginBal() + acctBalVO.getCreditBeginBal());
					balVO.setDebitCloseBal(balVO.getDebitCloseBal() - vo.getDebitBeginBal() + acctBalVO.getDebitBeginBal());
					balVO.setCreditCloseBal(balVO.getCreditCloseBal() - vo.getCreditBeginBal() + acctBalVO.getCreditBeginBal());
				}
				updateVO(balVO);
			}
		}
		vo = null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctViewList(java.lang.Long)
	 */
	@Override
	public List<AcctViewVO> getAcctViewList(Long idCompany, String flag) throws BusinessException {
		return chartOfAcctService.getAcctViewList(idCompany, flag);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctViewList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getAcctViewList(Long idCompany, String flag, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getAcctViewList(idCompany, flag, searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctTotalBeginBal(java.lang.Long)
	 */
	@Override
	public AcctBalViewVO getAcctTotalBeginBal(Long idCompany) throws BusinessException {
		return chartOfAcctService.getAcctTotalBeginBal(idCompany);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctVO(java.lang.Long)
	 */
	@Override
	public AcctVO getAcctVO(Long idAcct) throws BusinessException {
		return chartOfAcctService.getAcctVO(idAcct);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#audAcctTrans(java.lang.String, com.bcs.zsg.acct.vo.AcctTransVO)
	 */
	@Override
	public void audAcctTrans(String func, AcctTransVO acctTransVO) throws BusinessException {
		accountService.auditAcctTrans(func, acctTransVO);
	}
	
	@Override
	public void audAcctTransEmailPayment(String func, AcctTransVO acctTransVO) throws BusinessException {
		accountService.auditAcctTransEmailPayment(func, acctTransVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctBalVO(java.lang.Long)
	 */
	@Override
	public AcctBalVO getAcctBeginBalVO(Long idAcct) throws BusinessException {
		return chartOfAcctService.getAcctBeginBalVO(idAcct);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getAcctBalList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctBalVO> getAcctBeginBalList(Long idAcct, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getAcctBeginBalList(idAcct, searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getBankBeginBal(java.lang.Long, java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public double getBankBeginBal(Long idBank, Long idAcct, Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getBankBeginBal(idBank, idAcct, idCompany, searchParamVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#updAcctTransDt(java.lang.String, java.lang.String, java.util.Date)
	 */
	@Override
	public void updAcctTransDt(String sysCd, String sysNo, Date transDt, Long companyId) throws BusinessException {
		chartOfAcctService.updAcctTransDt(sysCd, sysNo, transDt, companyId);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getTrdgPftLssList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrdgPftLssList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getTrdgPftLssList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getTrdgPftLssAdtList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrdgPftLssAdtList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getTrdgPftLssAdtList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getTrialBalanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrialBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getTrialBalanceList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getTrialBalanceListYear(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getTrialBalanceListYear(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getTrialBalanceListYear(idCompany, searchParamVO);
	}

	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#isClosedAcct(java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean isClosedAcct(Long idCompany, Long idAcct) throws BusinessException {
		return chartOfAcctService.isClosedAcct(idCompany, idAcct);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#isAcctBeginBalExisted(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public boolean isAcctBeginBalExisted(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.isAcctBeginBalExisted(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getFinPeriodYear(java.lang.Long)
	 */
	@Override
	public String getFinPeriodYear(Long idCompany) throws BusinessException {
		return chartOfAcctService.getFinPeriodYear(idCompany);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getFinPeriodYearList(java.lang.Long)
	 */
	@Override
	public List<String> getFinPeriodYearList(Long idCompany) throws BusinessException {
		return chartOfAcctService.getFinPeriodYearList(idCompany);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getBalanceSheetList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getBalanceSheetList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getBalanceSheetList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getBalanceSheetDetailsList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getBalanceSheetDetailsList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getBalanceSheetDetailsList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getBeginningBalanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getBeginningBalanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getBeginningBalanceList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getDebtorLedgerList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getDebtorLedgerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getDebtorLedgerList(idCompany, searchParamVO);
	}
	
	@Override
	public List<AcctViewVO> getDebtorLedgerList2(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getDebtorLedgerList2(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getDebtorSummaryList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getDebtorSummaryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getDebtorSummaryList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getCreditorLedgerList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getCreditorLedgerList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getCreditorLedgerList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getCreditorSummaryList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getCreditorSummaryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getCreditorSummaryList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getJournalList(java.lang.Long)
	 */
	@Override
	public List<JournalVO> getJournalList(Long idCompany) throws BusinessException {
		return chartOfAcctService.getJournalList(idCompany);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getJournalEntryList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<JournalVO> getJournalEntryList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getJournalEntryList(idCompany, searchParamVO);
	}
	
	/* (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getSalesInAdvanceList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getSalesInAdvanceList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getSalesInAdvanceList(idCompany, searchParamVO);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.acct.bo.ChartOfAcctBO#getChartOfAccountList(java.lang.Long, com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public List<AcctViewVO> getChartOfAccountList(Long idCompany, SearchParamVO searchParamVO) throws BusinessException {
		return chartOfAcctService.getChartOfAccountList(idCompany, searchParamVO);
	}
}
