package com.bcs.zsg.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.bank.dao.CashBookDAO;
import com.bcs.zsg.bank.vo.BankAcctViewVO;
import com.bcs.zsg.bank.vo.CashBookBalVO;
import com.bcs.zsg.bank.vo.CashBookSumVO;
import com.bcs.zsg.bank.vo.CashBookVO;
import com.bcs.zsg.common.dao.BaseCommonDAO;
import com.bcs.zsg.common.dao.BaseCommonDAOLM;
import com.bcs.zsg.common.helper.CommonErrConstant;
import com.bcs.zsg.common.service.BaseCommonServiceLMImpl;
import com.bcs.zsg.common.vo.SearchParamVO;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.core.helper.BaseConstant;

public class CashBookServiceImpl extends BaseCommonServiceLMImpl implements CashBookService {

	@Autowired
	private CashBookDAO cashBookDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#getCashBookList(java.lang.Long)
	 */
	@Override
	public List<BankAcctViewVO> getBankAcctList(Long idCompany) throws BusinessException {
		return cashBookDAO.getBankAcctList(idCompany);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#getCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public <T> List<T> getCashBookList(SearchParamVO searchParamVO, Object... method) throws BusinessException {
		return cashBookDAO.getCashBookList(searchParamVO, method);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#getPrevCashBookList(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public <T> List<T> getPrevCashBookList(SearchParamVO searchParamVO, Object... method) throws BusinessException {
		return cashBookDAO.getPrevCashBookList(searchParamVO, method);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#getCashBookBal(com.bcs.zsg.common.vo.SearchParamVO)
	 */
	@Override
	public CashBookBalVO getCashBookBal(SearchParamVO searchParamVO) throws BusinessException {
		return cashBookDAO.getCashBookBal(searchParamVO);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#updCashBook(com.bcs.zsg.bank.vo.CashBookVO)
	 */
	@Override
	public void updCashBook(CashBookVO cashBookVO) throws BusinessException {
		cashBookDAO.update(cashBookVO);
	}

	@Override
	protected BaseCommonDAOLM getLMDAO() {
		return cashBookDAO;
	}

	@Override
	protected BaseCommonDAO getDAO() {
		return getLMDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#updCashBookClear(java.lang.Object[], java.util.List)
	 */
	@Override
	public void updCashBookClear(List<CashBookSumVO> selectedCBList, List<CashBookSumVO> unselectedCBList) throws BusinessException {
		cashBookDAO.updCashBookClear(selectedCBList, unselectedCBList);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#getCashBookById(java.lang.Long)
	 */
	@Override
	public CashBookVO getCashBookById(Long id) throws BusinessException {
		return cashBookDAO.getCashBookById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.bank.service.CashBookService#getCashBookBySysNo(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public CashBookVO getCashBookBySysNo(Long idCompany, String sysCode, String sysNo, String transTypeCd) throws BusinessException {
		CashBookVO bankPmntVO = cashBookDAO.getCashBookBySysNo(idCompany, sysCode, sysNo, transTypeCd);
		if (bankPmntVO == null) throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_FOUND);
		
		if ((bankPmntVO.getTransTypeCd().equals("cash_with") && !bankPmntVO.getStatusCode().equals(BaseConstant.STATUS_ACTIVE)) ||
				(bankPmntVO.getTransTypeCd().equals("bill_pymt") && !bankPmntVO.getStatusCode().equals(BaseConstant.STATUS_TERMINATED)))
			bankPmntVO = null;
		
		if (bankPmntVO == null) throw new BusinessException(CommonErrConstant.ERR_BANK_PAYMENT_NOT_FOUND);
		return bankPmntVO;
	}

}
